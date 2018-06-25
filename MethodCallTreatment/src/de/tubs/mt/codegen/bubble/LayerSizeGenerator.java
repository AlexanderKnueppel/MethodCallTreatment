package de.tubs.mt.codegen.bubble;

public class LayerSizeGenerator {
	
	public static int calculateNextOffset(int currentDepth, int depth, int width){
		int layer = depth - currentDepth;
		int commingNodes = 0;
		for(int i = 0; i <= layer; i++){
			commingNodes *= width;
			commingNodes += 1;
		}
		return commingNodes > 0 ? commingNodes : 1;
	}
}
