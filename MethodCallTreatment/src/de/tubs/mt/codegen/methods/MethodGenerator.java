package de.tubs.mt.codegen.methods;

import java.util.List;

public interface MethodGenerator {

	public Method getMethod(int methodCall, int number, int depth, int width, Method specialMethod);
}
