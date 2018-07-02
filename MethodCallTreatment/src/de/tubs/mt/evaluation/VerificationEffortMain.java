package de.tubs.mt.evaluation;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.key_project.util.collection.ImmutableSet;

import de.tubs.mt.files.FileControl;
import de.uka.ilkd.key.control.KeYEnvironment;
import de.uka.ilkd.key.java.abstraction.KeYJavaType;
import de.uka.ilkd.key.logic.op.IObserverFunction;
import de.uka.ilkd.key.proof.Proof;
import de.uka.ilkd.key.proof.init.ProofInputException;
import de.uka.ilkd.key.proof.io.ProblemLoaderException;
import de.uka.ilkd.key.settings.ChoiceSettings;
import de.uka.ilkd.key.settings.ProofSettings;
import de.uka.ilkd.key.speclang.Contract;
import de.uka.ilkd.key.strategy.StrategyProperties;
import de.uka.ilkd.key.util.KeYTypeUtil;
import de.uka.ilkd.key.util.MiscTools;

public class VerificationEffortMain {

	

	private List<Integer> result = new ArrayList<Integer>();
	private int width;
	private int depth;
	private boolean contracting;
	private int runs;


	public VerificationEffortMain(int width, boolean contracting) {
		this.width = width;
		this.contracting = contracting;
	}
	

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the depth
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * @param depth
	 *            the depth to set
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**
	 * @return the runs
	 */
	public int getRuns() {
		return runs;
	}

	/**
	 * @param runs
	 *            the runs to set
	 */
	public void setRuns(int runs) {
		this.runs = runs;
	}
	
	
	public List<Integer> verifyProgram(int seed, String whitelist, String folder) {
		result.clear();
		for (VerificationResult v : verify(FileControl.getExecPath().getPath() + "/" + seed + "/", Arrays.asList(whitelist))) {
			result.add(v.getStatistics().nodes);
			System.out.println("\nClosed? " + v.isClosed());
		}
		return result;
	}
	
	

	
	private List<VerificationResult> verify(String folder, List<String> whitelist) {
		List<File> classPaths = null; // Optionally: Additional specifications
										// for API classes
		File bootClassPath = null; // Optionally: Different default
									// specifications for Java API
		List<File> includes = null; // Optionally: Additional includes to
									// consider
		

		File location = new File(folder);

		List<VerificationResult> resultList = new ArrayList<VerificationResult>();

		try {
			// Ensure that Taclets are parsed
			if (!ProofSettings.isChoiceSettingInitialised()) {
				KeYEnvironment<?> env = KeYEnvironment.load(location, classPaths, bootClassPath, includes);
				env.dispose();
			}
			// Set Taclet options
			ChoiceSettings choiceSettings = ProofSettings.DEFAULT_SETTINGS.getChoiceSettings();
			HashMap<String, String> oldSettings = choiceSettings.getDefaultChoices();
			HashMap<String, String> newSettings = new HashMap<String, String>(oldSettings);
			newSettings.putAll(MiscTools.getDefaultTacletOptions());
			choiceSettings.setDefaultChoices(newSettings);

			// Load source code
			KeYEnvironment<?> env = KeYEnvironment.load(location, classPaths, bootClassPath, includes);
			// env.getLoadedProof() returns the performed proof if a *.proof
			// file is loaded
			try {
				// List all specifications of all types in the source location
				// (not classPaths and bootClassPath)
				final List<Contract> proofContracts = new LinkedList<Contract>();
				Set<KeYJavaType> kjts = env.getJavaInfo().getAllKeYJavaTypes();
				for (KeYJavaType type : kjts) {
					if (!KeYTypeUtil.isLibraryClass(type)) {
						ImmutableSet<IObserverFunction> targets = env.getSpecificationRepository()
								.getContractTargets(type);
						for (IObserverFunction target : targets) {
							ImmutableSet<Contract> contracts = env.getSpecificationRepository().getContracts(type,
									target);
							for (Contract contract : contracts) {
								proofContracts.add(contract);
							}
						}
					}
				}
				
				System.out.print("Skipped: ");
				for (Contract contract : proofContracts) {
					if (whitelist != null) {
						if (!whitelist.contains(contract.getTarget().toString().split("::")[1])) {
							System.out.print((contract.getTarget().toString().split("::")[1]) + " ");
							continue;
						}
					}

					Proof proof = null;
					try {
						// Create proof
						proof = env.createProof(contract.createProofObl(env.getInitConfig(), contract));
						// Set proof strategy options
						StrategyProperties sp = proof.getSettings().getStrategySettings().getActiveStrategyProperties();
						sp.setProperty(StrategyProperties.METHOD_OPTIONS_KEY,
								(contracting) ? StrategyProperties.METHOD_CONTRACT : StrategyProperties.METHOD_EXPAND);
						proof.getSettings().getStrategySettings().setActiveStrategyProperties(sp);

						// Make sure that the new options are used
						int maxSteps = 500000;
						ProofSettings.DEFAULT_SETTINGS.getStrategySettings().setMaxSteps(maxSteps);
						ProofSettings.DEFAULT_SETTINGS.getStrategySettings().setActiveStrategyProperties(sp);
						proof.getSettings().getStrategySettings().setMaxSteps(maxSteps);
						proof.setActiveStrategy(
								proof.getServices().getProfile().getDefaultStrategyFactory().create(proof, sp));

						// Start auto mode
						env.getUi().getProofControl().startAndWaitForAutoMode(proof);

						resultList.add(new VerificationResult(contract.getTarget().toString(),
								contract.getDisplayName(), proof.getStatistics(), proof.openGoals().isEmpty()));
					} catch (ProofInputException e) {
						System.out.println(
								"Exception at '" + contract.getDisplayName() + "' of " + contract.getTarget() + ":");
						e.printStackTrace();
					} finally {
						if (proof != null) {
							proof.dispose(); // Ensure always that all instances
												// of Proof are disposed
						}
					}

				}
			} finally {
				env.dispose(); // Ensure always that all instances of
								// KeYEnvironment are disposed
			}
		} catch (ProblemLoaderException e) {
			System.out.println("Exception at '" + location + "':");
			e.printStackTrace();
		}
		return resultList;
	}
}
