package de.evaluation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.key_project.util.collection.ImmutableSet;

import de.tubs.mt.Incrementer;
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
import java.util.concurrent.TimeUnit ;
public class VerificationEffortMain {
	
	public enum Program {
	    ADD, BUBBLESORT 
	}
	
	private static String tmp_folder = "tmp";
	
	private Program program;
	private String path;
	private int width;
	private int depth;
	private boolean contracting = true;
	/**
	 * @return the program
	 */
	public Program getProgram() {
		return program;
	}

	/**
	 * @param program the program to set
	 */
	public void setProgram(Program program) {
		this.program = program;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
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
	 * @param depth the depth to set
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
	 * @param runs the runs to set
	 */
	public void setRuns(int runs) {
		this.runs = runs;
	}

	private int runs;
	
	private String resultsTXT = "Results.txt";
	
	VerificationEffortMain(Program p, String path, int width, int depth, int runs) {
		this.program = p;
		this.path = path;
		this.width = width;
		this.depth = depth;
		this.runs = runs;
	}
	
	public File getTmpPath() {
		return new File(path.replaceAll("/", "") + "/" + tmp_folder.replaceAll("/", "") + "/");
	}
	
	public File getTopPath() {
		return new File(path.replaceAll("/", "") + "/");	
	}
	
	public File getResultHandle() {
		return new File(path.replaceAll("/", "") + "/" + resultsTXT);	
	}
	
	public static void deleteRecursevly(File f){
        if(f.isDirectory()){
            for (File subFile : f.listFiles()) {
                deleteRecursevly(subFile);
            }
        }
        f.delete();
    }
	
	public void initStructure() throws IOException {
		File tmp = getTmpPath();
		
		if(tmp.exists()) {
			deleteRecursevly(tmp);
		}
		
		tmp.mkdirs();
			
		if(!getResultHandle().exists())
			getResultHandle().createNewFile();
	}
	
	public List<Integer> verifyProgram(int seed, int run) {
		return (program == Program.ADD ? verifyAddProgram(seed, run) : verifyBubbleSortProgram(seed, run));
	}
	
	public List<Integer> verifySingleProgram(Program p, boolean c) {
	 	  try {
	 		  if(p == Program.BUBBLESORT)
	 			Incrementer.generateFullySpecifiedProgramBubble(width, depth, 1000, getTmpPath().getPath());
	 		  else
	 			 Incrementer.generateFullySpecifiedProgramAdd(width, depth, 1000, getTmpPath().getPath());
	 	  } catch (FileNotFoundException e1) {
	 			// TODO Auto-generated catch block
	 			e1.printStackTrace();
	 	  }  
	 	  
	 	  contracting = c;
	 	  
	 	 List<Integer> result = new ArrayList<Integer>();
	 	 
	 	 for(VerificationResult v : verify(getTmpPath().getPath() + "/" + 1000 + "/", Arrays.asList("a1"))) {
	 		  result.add(v.getStatistics().nodes);
	 		  System.out.println("Closed? " + v.isClosed());
	 	  }
	 	  return result;
	}
	
	public List<Integer> verifyAddProgram(int seed, int run) {
	 	  try {
	 			Incrementer.generatRandomSpecifiedProgramsForAdd(width, depth, seed, getTmpPath().getPath(), (run > 0 ? false : true));
	 	  } catch (FileNotFoundException e1) {
	 			// TODO Auto-generated catch block
	 			e1.printStackTrace();
	 	  }  
	 	  
	 	  List<Integer> result = new ArrayList<Integer>();
	 	  
	 	  for(VerificationResult v : verify(getTmpPath().getPath() + "/" + seed + "/", Arrays.asList("a1"))) {
	 		  result.add(v.getStatistics().nodes);
	 	  }
	 	  
	 	  return result;
	}
	
	public List<Integer> verifyBubbleSortProgram(int seed, int run) {
	 	  try {
	 			Incrementer.generatRandomSpecifiedProgramsForBubbleSort(width, depth, seed, getTmpPath().getPath(), (run > 0? false : true));
	 	  } catch (FileNotFoundException e1) {
	 			// TODO Auto-generated catch block
	 			e1.printStackTrace();
	 	  }  
//	 	  
	 	  List<Integer> result = new ArrayList<Integer>();
//	 	  
	 	  for(VerificationResult v : verify(getTmpPath().getPath() + "/" + seed + "/", Arrays.asList("execute"))) {
	 		  result.add(v.getStatistics().nodes);
	 	  }
//	 	  
	 	  return result;
	}
	
	public List<VerificationResult> verify(String folder) {
		return verify(folder, null);
	}
	
	public List<VerificationResult> verify(String folder, List<String> whitelist) {
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

				for (Contract contract : proofContracts) {
					
					if(whitelist != null) {
						if (!whitelist.contains(contract.getTarget().toString().split("::")[1])) {
							System.out.println("Skipped: " + (contract.getTarget().toString().split("::")[1]));
							continue;
						}
					}

					Proof proof = null;
					try {
						// Create proof
						proof = env.createProof(contract.createProofObl(env.getInitConfig(), contract));
						// Set proof strategy options
						StrategyProperties sp = proof.getSettings().getStrategySettings().getActiveStrategyProperties();
						sp.setProperty(StrategyProperties.METHOD_OPTIONS_KEY, (contracting) ? StrategyProperties.METHOD_CONTRACT : StrategyProperties.METHOD_EXPAND);
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
						
						resultList.add(new VerificationResult(contract.getTarget().toString(), contract.getDisplayName(), proof.getStatistics(), proof.openGoals()
								.isEmpty()));
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
	
	/**
	 * call:
	 * @param args
	 */
	public static void main(String[] args) {
		
		/**
		 * Configuration 
		 * 
		 * 
		 */
		int runs = 10;
		Program p = Program.ADD;
		String path = "Results";
		int width = 5;
		int depth = 3;
		boolean completeSpec = true; //used for method inlining vs contracting
		boolean contracting = false; //only necessary when completeSpec is true
		
		if(completeSpec) {
			runs = 1;
		}
		
		boolean caching = true;
		String file = path + "/cache.txt";
		
		if(!new File(path).exists())
			new File(path).mkdir();
		
		if(caching && !new File(file).exists()) {
			try {
				new File(file).createNewFile();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		
		//for(int d = depth; d <= 30; ++d) {
		VerificationEffortMain executer = new VerificationEffortMain(p, path, width, depth, runs);
		
		try {
			executer.initStructure();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		List<String> lines = new LinkedList<String>();
		lines.add(executer.toString());
		if(completeSpec) lines.add("(Verification Effort for " + (contracting ? "Method Contracting" : "Method Inlining") + ")");
		lines.add("=============================================================");
		
		System.out.println(lines.get(0) + "\n" + lines.get(1) + "\n\n");
		if(caching) {
	 		try {
				Files.write(Paths.get(file), lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		long start = System.currentTimeMillis();
		
		int effortInlining = 0,
			effortContracting = 0;
				
		for(int i = 0; i < runs; ++i) {
			long diff = System.currentTimeMillis() - start;
			String currentTime = String.format("%d min, %d sec", 
				    TimeUnit.MILLISECONDS.toMinutes(diff),
				    TimeUnit.MILLISECONDS.toSeconds(diff) - 
				    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diff))
				);
			
			System.out.println("Run " + (i+1) + " [Time: " + currentTime + "]");
			int seed = Math.abs((int) System.currentTimeMillis());
			
			List<Integer> effort = null;
			
			if(!completeSpec) {
				effort = executer.verifyProgram(seed, i);
			} else {
				effort = executer.verifySingleProgram(p, contracting);
			}

			// Only first run needs to compute verification effort for 0% and 100%
			if(i == 0) {
				effortInlining = effort.get(0);
				effortContracting = effort.get(effort.size()-1);
			} else {
				effort.add(0,effortInlining);
				effort.add(effortContracting);
			}
			
			String result = "";
			for(Integer nodes : effort) {
				result += nodes + ",";
			}
			result = result.substring(0, result.length()-1);
			
			lines.add(result);
			
			System.out.println(result);
			
			if(caching) {
		 		try {
					Files.write(Paths.get(file), Arrays.asList(result), Charset.forName("UTF-8"), StandardOpenOption.APPEND);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		lines.add("\n");
		
 		try {
			Files.write(Paths.get(executer.getResultHandle().getPath()), lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Verifying... [program=" + program + ", width=" + width + ", depth=" + depth + ", runs=" + runs + "]";
	}
}
