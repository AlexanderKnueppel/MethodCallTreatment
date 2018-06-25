package de.tubs.mt.codegen.methods;


public interface MethodGenerator {

	public Method getMethod(int methodCall, int number, int depth, int width, Method specialMethod);
}
