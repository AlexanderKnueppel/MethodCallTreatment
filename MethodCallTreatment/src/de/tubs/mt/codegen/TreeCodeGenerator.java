package de.tubs.mt.codegen;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import de.tubs.mt.codegen.methods.Method;
import de.tubs.mt.codegen.methods.MethodGenerator;

public abstract class TreeCodeGenerator {


	public void generateCode(OutputStream stream, int depth, int width, String name) throws IOException {
	
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(stream))) {
			bw.write("public class "+name+" {\n");
			List<Method> generatedMethods = new ArrayList<>();
			List<List<Method>> openMethods = new ArrayList<>();
			int number = 1;
			int currentDepth = 1;
			List<Method> methods = new ArrayList<>();
	
			openMethods.add(new ArrayList<Method>());
			openMethods.add(new ArrayList<Method>());
			
			Method blattMethod = getLeafMethodGenerator().getMethod(1, 0, depth, 0, null);
			MethodGenerator firstMethodGenerator = getNextMethodGenerator();
			Method firstMethod = firstMethodGenerator.getMethod(currentDepth, number, depth, width, blattMethod);
	
			
			if(getExecutionMethodGenerator() != null){
				generatedMethods.add(getExecutionMethodGenerator().getMethod(0, 0, depth, width, firstMethod));
			}
			
			generatedMethods.add(blattMethod);
			
			openMethods.get(0).add(firstMethod);
			generatedMethods.add(firstMethod);
			
			while (currentDepth < depth) {
				while (!openMethods.get(0).isEmpty()) {
					while (openMethods.get(0).get(0).getNumberOfOpenMethods() != 0) {
						MethodGenerator mg = getNextMethodGenerator();
						Method m = mg.getMethod(currentDepth + 1, number, depth, width, blattMethod);
						generatedMethods.add(m);
						methods.add(m);
						openMethods.get(1).add(m);
						openMethods.get(0).get(0).setMethodCall(m.getMethodCallCode());
						number++;
					}
					openMethods.get(0).remove(0);
				}
				openMethods.remove(0);
				openMethods.add(new ArrayList<Method>());
				currentDepth++;
				number = 1;
			}
			while (!openMethods.get(0).isEmpty()) {
				while (openMethods.get(0).get(0).getNumberOfOpenMethods() != 0) {
					openMethods.get(0).get(0).setMethodCall(blattMethod.getMethodCallCode());
				}
				openMethods.get(0).remove(0);
			}
	
			for (Method method : generatedMethods) {
				bw.write(method.getCode());
			}
			bw.write("}");
			bw.flush();
		}
	}

	
	protected abstract MethodGenerator getNextMethodGenerator();
	protected abstract MethodGenerator getLeafMethodGenerator();
	protected abstract MethodGenerator getExecutionMethodGenerator();

}