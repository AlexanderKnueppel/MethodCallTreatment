package de.tubs.mt.chart;

import java.util.ArrayList;
import java.util.List;

public abstract class DataPrep {
	
	public static List<Integer> getSpecList(List<ResultsForXY> results) {
		List<Integer> specList = new ArrayList<>();
		int tmp = results.get(0).getSpec();
		specList.add(tmp);
		for(ResultsForXY result : results) {
			if(result.getSpec() != tmp && !specList.contains(result.getSpec())) {
				tmp = result.getSpec();
				specList.add(tmp);
			}
		}
		return specList; 	
	}
	
	public static List<Integer> getDepthList(List<ResultsForXY> results){
		List<Integer> depthList = new ArrayList<>();
		int tmp = results.get(0).getDepth();
		depthList.add(tmp);
		for(ResultsForXY result : results) {
			if(result.getDepth() != tmp && !depthList.contains(result.getDepth())) {
				tmp = result.getDepth();
				depthList.add(tmp);
			}

		}
		return depthList; 		
	}
		

	
	public static List<Integer> getResultForSpecDepthList(List<ResultsForXY> results, int spec, int depth) {
		List<Integer> resList = new ArrayList<>();
		for(ResultsForXY result : results) {
			if(result.getSpec() == spec && result.getDepth() == depth) {
				resList.add(result.getResults());
			}
		}
		return resList;
	}

}