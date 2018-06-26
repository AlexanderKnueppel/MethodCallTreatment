package de.tubs.mt.chart;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import de.tubs.mt.files.FileControl;

public abstract class ExcelFile {

	private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		return style;
	}

	public static void createTable(List<List<ResultsForXY>> resultLists, String verifyEffort) throws IOException {
		
		List<ResultsForXY> results = resultLists.get(resultLists.size()-1);
		List<Integer> specList = DataPrep.getSpecList(results);
		List<Integer> depthList = DataPrep.getDepthList(results);

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Employees sheet");

		int rownum = 0;
		Cell cell;
		Row row;
		//
		HSSFCellStyle style = createStyleForTitle(workbook);

		row = sheet.createRow(rownum);

		// Specification
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("Specification [%] ");
		cell.setCellStyle(style);


		
		int depthTmp = 1;
		for (int i = depthList.get(0) ; i <= depthList.get(depthList.size()-1); i++) {
			cell = row.createCell(depthTmp, CellType.STRING);
			cell.setCellValue("Depth " + i);
			cell.setCellStyle(style);
			depthTmp++;
		}

		
		
		for (int spec : specList) {
			rownum++;
			row = sheet.createRow(rownum);
			cell = row.createCell(0, CellType.NUMERIC);
			cell.setCellValue(spec);
			depthTmp = 1;
			
			for (int depth : depthList) {
				cell = row.createCell(depthTmp, CellType.NUMERIC);
				cell.setCellValue(DataPrep.getResultForSpecDepthList(results, spec, depth).get(0));
				depthTmp++;
			}

		}
		

		File file = new File(FileControl.getExcelPath(verifyEffort));
		file.getParentFile().mkdirs();

		FileOutputStream outFile = new FileOutputStream(file);
		workbook.write(outFile);
		System.out.println("Created file: " + file.getAbsolutePath());

	}

}
