package de.tubs.mt.codegen.bubble;

import de.tubs.mt.codegen.TreeCodeGenerator;
import de.tubs.mt.codegen.methods.MethodGenerator;


/**
 * The Class BubbleCodeGenerator.
 */
public class BubbleCodeGenerator extends TreeCodeGenerator{


	/* (non-Javadoc)
	 * @see de.tubs.mt.codegen.TreeCodeGenerator#getNextMethodGenerator()
	 */
	@Override
	protected MethodGenerator getNextMethodGenerator() {
		return new LeaflessBubbleLayerGenerator();
		//return new BubbleLayerGenerator();
	}

	/* (non-Javadoc)
	 * @see de.tubs.mt.codegen.TreeCodeGenerator#getLeafMethodGenerator()
	 */
	@Override
	protected MethodGenerator getLeafMethodGenerator() {
		return new BubbleLeafGenerator();
	}
	
	/* (non-Javadoc)
	 * @see de.tubs.mt.codegen.TreeCodeGenerator#getExecutionMethodGenerator()
	 */
	@Override
	protected MethodGenerator getExecutionMethodGenerator() {
		return new BubbleExecutionLayerGenerator();
	}

}
