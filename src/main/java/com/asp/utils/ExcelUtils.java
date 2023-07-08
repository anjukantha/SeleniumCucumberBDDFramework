package com.asp.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.asp.constants.FrameworkConstants;
import com.asp.exceptions.FrameworkException;
import com.asp.exceptions.InvalidPathForExcelException;

/**
 * Utility class to read or write to excel.
 * 
 * @author Anjan S P
 * @see com.asp.listeners.MethodInterceptor
 */
public final class ExcelUtils {

	/**
	 * Private constructor to avoid external instantiation
	 */
	private ExcelUtils() {
	}

	/**
	 * Used to get the test data of particular test
	 * 
	 * @param testName
	 * @return
	 */
	public static Map<String, String> getTestCaseData(String testName) {
		List<Map<String, String>> list = getAllDataOfSheet(getSheetNameofTestCase(testName));
		for (Map<String, String> map : list) {
			if (map.get("Test Name").equals(testName)) {
				return map;
			}
		}
		return Collections.emptyMap();
	}

	/**
	 * Used to get the actual sheet name where the test case defined. It will look
	 * into master sheet and get the sheet name.
	 * 
	 * @param testName
	 * @return
	 */
	private static String getSheetNameofTestCase(String testName) {
		List<Map<String, String>> list = getAllDataOfSheet("Master");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("Test Case Name").equals(testName)) {
				return list.get(i).get("Sheet Name");
			}
		}
		throw new FrameworkException("Test: " + testName + " is not present on the Master sheet");
	}

	/**
	 * Encapsulates all the value from the mentioned excel sheet and store it in
	 * List holding HashMaps. Key for the map in the column header in the excel
	 * sheet.
	 * 
	 * @author Anjan S P
	 * @param sheetname Excel sheet name to read the value from
	 * @return List where each index holds a map and Each map holds the details
	 *         about the test
	 */
	public static List<Map<String, String>> getAllDataOfSheet(String sheetname) {
		List<Map<String, String>> list = null;
		XSSFWorkbook workbook;
		try (FileInputStream fs = new FileInputStream(FrameworkConstants.getExcelpath())) {
			workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetname);

			int lastrownum = sheet.getLastRowNum();
			int lastcolnum = sheet.getRow(0).getLastCellNum();
			FormulaEvaluator eveluator = workbook.getCreationHelper().createFormulaEvaluator();

			Map<String, String> map = null;
			list = new ArrayList<>();

			for (int i = 1; i <= lastrownum; i++) {
				map = new HashMap<>();
				for (int j = 0; j < lastcolnum; j++) {
					String key = sheet.getRow(0).getCell(j).getStringCellValue();
					String value;
					switch (sheet.getRow(i).getCell(j).getCellType()) {
					case NUMERIC:
						value = String.valueOf(sheet.getRow(i).getCell(j).getNumericCellValue());
						break;
					case STRING:
						value = sheet.getRow(i).getCell(j).getStringCellValue().trim();
						break;
					case BOOLEAN:
						value = String.valueOf(sheet.getRow(i).getCell(j).getBooleanCellValue());
						break;
					case FORMULA:
						value = eveluator.evaluate(sheet.getRow(i).getCell(j)).toString();
						break;
					case BLANK:
						value = "BLANK";
						break;
					default:
						value = "DEFAULT";
					}
					map.put(key, value);
				}
				list.add(map);
			}
			workbook.close();
		} catch (FileNotFoundException e) {
			throw new InvalidPathForExcelException("Excel File you trying to read is not found");
		} catch (IOException e) {
			throw new FrameworkException("Some io exception happened  while reading excel file");
		}
		if (Objects.nonNull(workbook)) {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}
