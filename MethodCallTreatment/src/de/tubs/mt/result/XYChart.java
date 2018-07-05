package de.tubs.mt.result;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import java.awt.BasicStroke;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;


public class XYChart extends JFrame {

	private String chartTitle;
	private List<List<ChartResults>> rfxy;
	private List<String> propertiesList;

	public static enum Chart {
		LINE, BAR, BOXPLOT
	}

	public XYChart(String applicationTitle, String chartTitle, List<List<ChartResults>> resultLists,
			List<String> propertiesList, Chart chart, boolean merge) {
		super(applicationTitle);
		this.chartTitle = chartTitle;
		this.rfxy = resultLists;
		this.propertiesList = propertiesList;
		
		JFreeChart chartPLot = null;
		
		if (chart.equals(Chart.LINE)) {
			chartPLot = getLineChart(merge);
		} else if (chart.equals(Chart.BAR)) {
			chartPLot = getBarChart();
		} else if (chart.equals(Chart.BOXPLOT)) {
			chartPLot = getBoxPlot();
		}
		
		ChartPanel chartPanel = new ChartPanel(chartPLot);
        setContentPane(chartPanel);
		RefineryUtilities.centerFrameOnScreen(this);
		pack();
		setVisible(true);

	}

	private JFreeChart getBoxPlot() {
		BoxAndWhiskerCategoryDataset dataset = createBoxPlotDataset();
		CategoryAxis xAxis = new CategoryAxis("Specification [%]");
        NumberAxis yAxis = new NumberAxis("Verification effort [proof-steps]");
        yAxis.setAutoRangeIncludesZero(false);
        
        final BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        renderer.setFillBox(true);
        renderer.setToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
        CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);

        return new JFreeChart("Box-and-Whisker Demo", new Font("SansSerif", Font.BOLD, 12), plot, true);
        

	}

	private BoxAndWhiskerCategoryDataset createBoxPlotDataset() {
		
		List<ChartResults> results = rfxy.get(rfxy.size()-1);
		List<Integer> specList = DataPrep.getSpecList(results);
		List<Integer> depthList = DataPrep.getDepthList(results);
		
		final DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();
		
		for (int depth : depthList) {
			for (int spec : specList) {
				List<Integer> list = DataPrep.getResultForSpecDepthList(results, spec, depth);
				dataset.add(list, "Depth " + depth, "" + spec);
			}

		}

		return dataset;
	}
	
	
	private JFreeChart getLineChart(boolean merge) {
		JFreeChart xylineChart = ChartFactory.createXYLineChart(chartTitle, "Call Depth", "Proof Steps",
				createLineDataset(merge), PlotOrientation.VERTICAL, true, true, false);
		
		// Create an NumberAxis
		NumberAxis xAxis = new NumberAxis();
		xAxis.setTickUnit(new NumberTickUnit(1));
		xAxis.setAutoRangeIncludesZero(false);
		xAxis.setLabel("Depth");

		// Assign it to the chart
		XYPlot plot = (XYPlot) xylineChart.getPlot();
		plot.setDomainAxis(xAxis);
		return xylineChart;

	}

	private XYSeriesCollection createLineDataset(boolean merge) {

		final XYSeriesCollection dataset = new XYSeriesCollection();

		if (!merge) {
			List<ChartResults> results = rfxy.get(rfxy.size()-1);
			XYSeries contract = new XYSeries(propertiesList.get(propertiesList.size()-1));
			for (ChartResults result : results) {
				contract.add((Number) result.getDepth(), result.getResults());
			}
			dataset.addSeries(contract);
		} else {
			int tmp = 0;
			for (List<ChartResults> results : rfxy) {
				XYSeries contract = new XYSeries(propertiesList.get(tmp));
				for (ChartResults result : results) {
					contract.add((Number) result.getDepth(), result.getResults());
				}
				dataset.addSeries(contract);
				tmp++;
			}
		}
		return dataset;
	}

	private JFreeChart getBarChart() {
		return ChartFactory.createBarChart3D("Contract Driven Statistics", "Specification (%)", "Proof-Steps",
				createBarDataset(), PlotOrientation.VERTICAL, true, true, false);
	}

	
	private DefaultCategoryDataset createBarDataset() {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<ChartResults> results = rfxy.get(rfxy.size() - 1);
		for (ChartResults result : results) {
			dataset.addValue(result.getResults(), "Depth = " + String.valueOf(result.getDepth()),
					String.valueOf(result.getSpec()));
		}
		return dataset;

	}
}