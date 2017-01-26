package com.redhat.drools.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelUtil {
	private Workbook workbook;
	private HSSFSheet worksheet = null;
	@SuppressWarnings("unused")
	private HSSFSheet declareWorkSheet = null;

	private FileOutputStream out;
	private HSSFRow row = null;

	public void readExcelFile(String filename) throws IOException {
		try {
			out = new FileOutputStream(filename);
			workbook = new HSSFWorkbook();
			// Access the worksheet, so that we can update / modify it.
			worksheet = (HSSFSheet) workbook.createSheet("rules");
			declareWorkSheet = (HSSFSheet) workbook.createSheet("facts");

			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void registerFile(String filename) {
		try {
			workbook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addCellValue(int rowindex, int col, String value) {
		// Update the value of cell
		if (worksheet.getRow(rowindex) == null)
			row = worksheet.createRow(rowindex);
		else
			row = worksheet.getRow(rowindex);
		HSSFCell cell = row.createCell(col);
		cell.setCellValue(value);
	}

	public void mergeCell(int rowindexfrom, int rowindexto, int cellindexfrom, int cellindexto) {
		worksheet.addMergedRegion(new CellRangeAddress(rowindexfrom, rowindexto, cellindexfrom, cellindexto));
	}

	public void mergeAndCenterCell(int rowindexfrom, int rowindexto, int cellindexfrom, int cellindexto, String value) {
		// Update the value of cell
		if (worksheet.getRow(rowindexfrom) == null)
			row = worksheet.createRow(rowindexfrom);
		else
			row = worksheet.getRow(rowindexfrom);
		HSSFCell cell = row.createCell(cellindexfrom);
		cell.setCellValue(value);
		worksheet.addMergedRegion(new CellRangeAddress(rowindexfrom, rowindexto, cellindexfrom, cellindexto));
		HSSFCellStyle mergedStyle = (HSSFCellStyle) workbook.createCellStyle();
		mergedStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// set the style to the merged cell
		cell.setCellStyle(mergedStyle);
	}

}
