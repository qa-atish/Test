package com.sentieo.pages;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

public class WriteDataToExcel {

	public void writeData(Map<String, JSONObject> empinfo) throws IOException {
		// Create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet("Case Info");

		// Create row object
		XSSFRow row;
		// Iterate over data and write to sheet
		Set<Entry<String, JSONObject>> keyid = empinfo.entrySet();
		int rowid = 0;
		Cell cell = null;
		for (Entry<String, JSONObject> key : keyid) {

//         Object [] objectArr = empinfo.get(key);
			int cellid = 0;
			JSONObject obj = key.getValue();
			if (rowid == 0) {
				row = spreadsheet.createRow(rowid++);
				for (int i = 0; i < ExerciseTwo.key.size(); i++) {
					cell = row.createCell(cellid++);
					cell.setCellValue(ExerciseTwo.key.get(i));
				}
			}
			cellid = 0;
			row = spreadsheet.createRow(rowid++);
			for (int i = 0; i < ExerciseTwo.key.size(); i++) {

				cell = row.createCell(cellid++);
				cell.setCellValue(obj.getString(ExerciseTwo.key.get(i)));
			}

		}
		// Write the workbook in file system

		// enter excel sheet path
		FileOutputStream out = new FileOutputStream(new File("Writesheet.xlsx"));
		workbook.write(out);
		out.close();
		System.out.println("Writesheet.xlsx written successfully");
	}
}
