package de.tubs.mt.codegen.methods;

import java.util.ArrayList;
import java.util.List;

import de.tubs.mt.codegen.CallHelper;
import de.tubs.mt.codegen.JMLAnnotation;
import de.tubs.mt.codegen.structure.Code;
import de.tubs.mt.codegen.structure.MethodCallCode;
import de.tubs.mt.codegen.structure.TextCode;
import de.tubs.mt.codegen.structure.Variable;

public class Method {
	
	private List<Code> methodCode;
	private List<MethodCallCode> openMethodCalls = new ArrayList<>();
	private List<MethodCallCode> methodCalls = new ArrayList<>();
	private JMLAnnotation jml = new JMLAnnotation();
	int nextOpenMethodCall = 0;
	private String name;
	private List<Variable> variables;

	public Method(int methodCall, int number, String returnType, List<Variable> variables, int width){
		this(MethodNameHelper.getName(methodCall)+number, returnType, variables, width);
	}
	public Method(String methodName, String returnType, List<Variable> variables, int width){
		this.name = methodName;
		this.variables = variables;
		this.methodCode = new ArrayList<Code>();
		
		TextCode tc = new TextCode();
		StringBuilder code = new StringBuilder();
		code.append("	public ").append(returnType).append(" ").append(name).append(CallHelper.getVariableDefinition(variables)).append("{\n");
		
		tc.setCode(code.toString());
		for (int i = 0; i < width; i++) {
			MethodCallCode mcc = new MethodCallCode();
			openMethodCalls.add(mcc);
			methodCalls.add(mcc);
		}
		methodCode.add(tc);
	}
	
	public void addCode(Code code){
		methodCode.add(code);
	}
	
	public JMLAnnotation getJML(){
		return jml;
	}
	
	public String getCode(){
		StringBuilder sb = new StringBuilder();
		sb.append(jml.generateCode());
		for (Code c : methodCode) {
			sb.append(c.getCode());
		}
		sb.append("\n	}\n\n");
		return sb.toString();
	}

	public int getNumberOfOpenMethods(){
		return openMethodCalls.size();
	}
	
	public MethodCallCode getNextMethodCall(){
		return nextOpenMethodCall < methodCalls.size() ? methodCalls.get(nextOpenMethodCall++) : null;
	}
	
	public void setMethodCall(MethodCallCode mcc){
		MethodCallCode openMethodCall = openMethodCalls.get(0);
		openMethodCall.copyValues(mcc);
		openMethodCalls.remove(0);
	}
	
	public MethodCallCode getMethodCallCode(){
		MethodCallCode mcc = new MethodCallCode();
		mcc.setName(name);
		for (Variable v : variables) {
			mcc.addVariable(v);
		}
		return mcc;
	}
}
