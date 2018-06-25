package de.tubs.mt.chart;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import java.awt.BasicStroke;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class XYChart extends JFrame {

	private JFreeChart barChart;
	private JFreeChart lineChart;
	private JFreeChart bubbleChart;

	private String chartTitle;
	private List<List<ResultsForXY>> rfxy;
	private List<String> propertiesList;

	public static enum Chart {
		LINE, BAR, BOXPLOT, BUBBLE
	}

	public XYChart(String applicationTitle, String chartTitle, List<List<ResultsForXY>> resultLists,
			List<String> propertiesList, Chart chart, boolean merge) {
		super(applicationTitle);
		this.chartTitle = chartTitle;
		this.rfxy = resultLists;
		this.propertiesList = propertiesList;

		if (chart.equals(Chart.LINE)) {
			showLineChart(merge);
		} else if (chart.equals(Chart.BAR)) {
			showBarChart();
		} else if (chart.equals(Chart.BOXPLOT)) {
			showBoxPlot();
		}

	}

	private void showBoxPlot() {
		BoxAndWhiskerCategoryDataset dataset = createBoxPlotDataset();
		CategoryAxis xAxis = new CategoryAxis("Specification [%]");
        NumberAxis yAxis = new NumberAxis("Verification effort [proof-steps]");
        yAxis.setAutoRangeIncludesZero(false);
        
        final BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        renderer.setFillBox(true);
        renderer.setToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
        CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);
        
        final JFreeChart chart = new JFreeChart(
                "Box-and-Whisker Demo",
                new Font("SansSerif", Font.BOLD, 12),
                plot,
                true
            );
        
        ChartPanel chartPanel = new ChartPanel(chart);
        setContentPane(chartPanel);
		RefineryUtilities.centerFrameOnScreen(this);
		pack();
		setVisible(true);

	}

	private BoxAndWhiskerCategoryDataset createBoxPlotDataset() {
		
		List<ResultsForXY> results = rfxy.get(rfxy.size()-1);
		List<Integer> specList = getSpecList(results);
		List<Integer> depthList = getDepthList(results);
		
		final DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
		
		for (int depth : depthList) {
			for (int spec : specList) {
				List<Integer> list = getResultForSpecDepthList(results, spec, depth);
				dataset.add(list, "Depth " + depth, "" + spec);
			}

		}

		return dataset;
	}
	
	private List<Integer> getSpecList(List<ResultsForXY> results) {
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
	
	private List<Integer> getDepthList(List<ResultsForXY> results){
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
		

	
	private List<Integer> getResultForSpecDepthList(List<ResultsForXY> results, int spec, int depth) {
		List<Integer> resList = new ArrayList<>();
		for(ResultsForXY result : results) {
			if(result.getSpec() == spec && result.getDepth() == depth) {
				resList.add(result.getResults());
			}
		}
		return resList;
	}

	
	private void showLineChart(boolean merge) {
		JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle, "Call Depth", "Proof Steps",
				createLineDataset(merge), PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(xylineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		final XYPlot plot = xylineChart.getXYPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

		int tmp = rfxy.size() - 1;
		for (int i = merge ? 0 : tmp; i <= tmp; i++) {
			renderer.setSeriesStroke(i, new BasicStroke(1.5f));
			renderer.setSeriesShapesVisible(i, true);
		}

		plot.setRenderer(renderer);
		setContentPane(chartPanel);
		RefineryUtilities.centerFrameOnScreen(this);
		pack();
		setVisible(true);

	}

	private XYDataset createLineDataset(boolean merge) {

		final XYSeriesCollection dataset = new XYSeriesCollection();

		if (!merge) {
			List<ResultsForXY> results = rfxy.get(rfxy.size()-1);
			XYSeries contract = new XYSeries(propertiesList.get(propertiesList.size()-1));
			for (ResultsForXY result : results) {
				contract.add(result.getDepth(), result.getResults());
			}
			dataset.addSeries(contract);
		} else {
			int tmp = 0;
			for (List<ResultsForXY> results : rfxy) {
				XYSeries contract = new XYSeries(propertiesList.get(tmp));
				for (ResultsForXY result : results) {
					contract.add(result.getDepth(), result.getResults());
				}
				dataset.addSeries(contract);
				tmp++;
			}
		}
		return dataset;
	}

	private void showBarChart() {
		barChart = ChartFactory.createBarChart3D("Contract Driven Statistics", "Specification (%)", "Proof-Steps",
				createBarDataset(), PlotOrientation.VERTICAL, true, true, false);
		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
		setContentPane(chartPanel);
		RefineryUtilities.centerFrameOnScreen(this);
		pack();
		setVisible(true);

	}

	private DefaultCategoryDataset createBarDataset() {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<ResultsForXY> results = rfxy.get(rfxy.size() - 1);
		for (ResultsForXY result : results) {
			dataset.addValue(result.getResults(), "Depth = " + String.valueOf(result.getDepth()),
					String.valueOf(result.getSpec()));
		}
		return dataset;

	}
}