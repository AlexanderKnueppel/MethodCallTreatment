package de.tubs.mt.result;

import java.util.ArrayList;
import java.util.List;


/**
 * The Class DataPrep.
 */
abstract class DataPrep {
	
	/**
	 * Gets the spec list.
	 *
	 * @param results the results
	 * @return the spec list
	 */
	public static List<Integer> getSpecList(List<ChartResults> results) {
		List<Integer> specList = new ArrayList<>();
		int tmp = results.get(0).getSpec();
		specList.add(tmp);
		for(ChartResults result : results) {
			if(result.getSpec() != tmp && !specList.contains(result.getSpec())) {
				tmp = result.getSpec();
				specList.add(tmp);
			}
		}
		return specList; 	
	}
	
	/**
	 * Gets the depth list.
	 *
	 * @param results the results
	 * @return the depth list
	 */
	public static List<Integer> getDepthList(List<ChartResults> results){
		List<Integer> depthList = new ArrayList<>();
		int tmp = results.get(0).getDepth();
		depthList.add(tmp);
		for(ChartResults result : results) {
			if(result.getDepth() != tmp && !depthList.contains(result.getDepth())) {
				tmp = result.getDepth();
				depthList.add(tmp);
			}

		}
		return depthList; 		
	}
		

	
	/**
	 * Gets the result for spec depth list.
	 *
	 * @param results the results
	 * @param spec the spec
	 * @param depth the depth
	 * @return the result for spec depth list
	 */
	public static List<Integer> getResultForSpecDepthList(List<ChartResults> results, int spec, int depth) {
		List<Integer> resList = new ArrayList<>();
		for(ChartResults result : results) {
			if(result.getSpec() == spec && result.getDepth() == depth) {
				resList.add(result.getResults());
			}
		}
		return resList;
	}

}
