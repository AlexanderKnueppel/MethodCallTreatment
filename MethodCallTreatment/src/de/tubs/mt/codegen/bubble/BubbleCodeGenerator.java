package de.tubs.mt.codegen.bubble;

import de.tubs.mt.codegen.TreeCodeGenerator;
import de.tubs.mt.codegen.methods.MethodGenerator;

public class BubbleCodeGenerator extends TreeCodeGenerator{

	private static final long serialVersionUID = 3916047168088510651L;

	@Override
	protected MethodGenerator getNextMethodGenerator() {
		return new LeaflessBubbleLayerGenerator();
		//return new BubbleLayerGenerator();
	}

	@Override
	protected MethodGenerator getLeafMethodGenerator() {
		return new BubbleLeafGenerator();
	}
	
	@Override
	protected MethodGenerator getExecutionMethodGenerator() {
		return new BubbleExecutionLayerGenerator();
	}

}
