package de.tubs.mt.codegen.methods;



/**
 * The Interface MethodGenerator.
 */
public interface MethodGenerator {

	/**
	 * Gets the method.
	 *
	 * @param methodCall the method call
	 * @param number the number
	 * @param depth the depth
	 * @param width the width
	 * @param specialMethod the special method
	 * @return the method
	 */
	public Method getMethod(int methodCall, int number, int depth, int width, Method specialMethod);
}
