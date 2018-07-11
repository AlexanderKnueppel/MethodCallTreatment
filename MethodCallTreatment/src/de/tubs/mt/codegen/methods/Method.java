package de.tubs.mt.codegen.methods;

import java.util.ArrayList;
import java.util.List;

import de.tubs.mt.codegen.CallHelper;
import de.tubs.mt.codegen.JMLAnnotation;
import de.tubs.mt.codegen.structure.Code;
import de.tubs.mt.codegen.structure.MethodCallCode;
import de.tubs.mt.codegen.structure.TextCode;
import de.tubs.mt.codegen.structure.Variable;


/**
 * The Class Method.
 */
public class Method {
	
	/** The method code. */
	private List<Code> methodCode;
	
	/** The open method calls. */
	private List<MethodCallCode> openMethodCalls = new ArrayList<>();
	
	/** The method calls. */
	private List<MethodCallCode> methodCalls = new ArrayList<>();
	
	/** The jml. */
	private JMLAnnotation jml = new JMLAnnotation();
	
	/** The next open method call. */
	private int nextOpenMethodCall = 0;
	
	/** The name. */
	private String name;
	
	/** The variables. */
	private List<Variable> variables;

	/**
	 * Instantiates a new method.
	 *
	 * @param methodCall the method call
	 * @param number the number
	 * @param returnType the return type
	 * @param variables the variables
	 * @param width the width
	 */
	public Method(int methodCall, int number, String returnType, List<Variable> variables, int width){
		this(MethodNameHelper.getName(methodCall)+number, returnType, variables, width);
	}
	
	/**
	 * Instantiates a new method.
	 *
	 * @param methodName the method name
	 * @param returnType the return type
	 * @param variables the variables
	 * @param width the width
	 */
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
	
	/**
	 * Adds the code.
	 *
	 * @param code the code
	 */
	public void addCode(Code code){
		methodCode.add(code);
	}
	
	/**
	 * Gets the jml.
	 *
	 * @return the jml
	 */
	public JMLAnnotation getJML(){
		return jml;
	}
	
	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode(){
		StringBuilder sb = new StringBuilder();
		sb.append(jml.generateCode());
		for (Code c : methodCode) {
			sb.append(c.getCode());
		}
		sb.append("\n	}\n\n");
		return sb.toString();
	}

	/**
	 * Gets the number of open methods.
	 *
	 * @return the number of open methods
	 */
	public int getNumberOfOpenMethods(){
		return openMethodCalls.size();
	}
	
	/**
	 * Gets the next method call.
	 *
	 * @return the next method call
	 */
	public MethodCallCode getNextMethodCall(){
		return nextOpenMethodCall < methodCalls.size() ? methodCalls.get(nextOpenMethodCall++) : null;
	}
	
	/**
	 * Sets the method call.
	 *
	 * @param mcc the new method call
	 */
	public void setMethodCall(MethodCallCode mcc){
		MethodCallCode openMethodCall = openMethodCalls.get(0);
		openMethodCall.copyValues(mcc);
		openMethodCalls.remove(0);
	}
	
	/**
	 * Gets the method call code.
	 *
	 * @return the method call code
	 */
	public MethodCallCode getMethodCallCode(){
		MethodCallCode mcc = new MethodCallCode();
		mcc.setName(name);
		for (Variable v : variables) {
			mcc.addVariable(v);
		}
		return mcc;
	}
}
