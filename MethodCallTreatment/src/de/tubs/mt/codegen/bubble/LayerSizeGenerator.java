package de.tubs.mt.codegen.bubble;


/**
 * The Class LayerSizeGenerator.
 */
class LayerSizeGenerator {
	
	/**
	 * Calculate next offset.
	 *
	 * @param currentDepth the current depth
	 * @param depth the depth
	 * @param width the width
	 * @return the int
	 */
	static int calculateNextOffset(int currentDepth, int depth, int width){
		int layer = depth - currentDepth;
		int commingNodes = 0;
		for(int i = 0; i <= layer; i++){
			commingNodes *= width;
			commingNodes += 1;
		}
		return commingNodes > 0 ? commingNodes : 1;
	}
}
