package de.tubs.mt.files;

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

public abstract class ExcelFile {

	private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		return style;
	}

	public static void createTable(List<Integer> list, String verifyEffort) throws IOException {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Employees sheet");

		int rownum = 0;
		Cell cell;
		Row row;
		//
		HSSFCellStyle style = createStyleForTitle(workbook);

		row = sheet.createRow(rownum);

		// Depth
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("call depth ");
		cell.setCellStyle(style);

		// Proof-Steps Contracting
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("proof steps " + verifyEffort);
		cell.setCellStyle(style);

		
		int depth = 1;

		
		// Data
		for (int proofsteps : list) {
			rownum++;
			row = sheet.createRow(rownum);

			// Proof-Steps Inlining
			cell = row.createCell(0, CellType.NUMERIC);
			cell.setCellValue(depth);

			// Proof-Steps Contracting
			cell = row.createCell(1, CellType.NUMERIC);
			cell.setCellValue(proofsteps);

			depth++;
		}

		File file = new File(FileControl.getExcelPath(verifyEffort));
		file.getParentFile().mkdirs();

		FileOutputStream outFile = new FileOutputStream(file);
		workbook.write(outFile);
		System.out.println("Created file: " + file.getAbsolutePath());

	}

}
