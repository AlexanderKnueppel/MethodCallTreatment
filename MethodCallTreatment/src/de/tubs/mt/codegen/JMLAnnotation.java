package de.tubs.mt.codegen;

import java.util.ArrayList;
import java.util.List;

public class JMLAnnotation {
	
	public boolean include = true;

	private List<String> requires = new ArrayList<>();
	private List<String> ensures = new ArrayList<>();

	public void addRequires(String req){
		requires.add(req);
	}
	public void addEnsures(String ens){
		ensures.add(ens);
	}
	
	public String generateCode(){
		if(!include) return "";
		
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
