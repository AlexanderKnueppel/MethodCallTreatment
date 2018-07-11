package de.tubs.mt.codegen;

import java.util.ArrayList;
import java.util.List;


/**
 * The Class JMLAnnotation.
 */
public class JMLAnnotation {
	

	/** The requires. */
	private List<String> requires = new ArrayList<>();
	
	/** The ensures. */
	private List<String> ensures = new ArrayList<>();

	/**
	 * Adds the requires.
	 *
	 * @param req the req
	 */
	public void addRequires(String req){
		requires.add(req);
	}
	
	/**
	 * Adds the ensures.
	 *
	 * @param ens the ens
	 */
	public void addEnsures(String ens){
		ensures.add(ens);
	}
	
	/**
	 * Generate code.
	 *
	 * @return the string
	 */
	public String generateCode(){
		StringBuilder sb = new StringBuilder();
		sb.append("	/*@ public normal_behavior\n");
		for (String req : requires) {
			sb.append("	  @ requires "+req+";\n");
		}
		for (String ens : ensures) {
			sb.append("	  @ ensures "+ens+";\n");
		}
		sb.append("	  @*/\n");
		return sb.toString();
	}
}
