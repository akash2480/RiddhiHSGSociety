package com.riddhiHousingSociety.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class XLUtils2 {
	
	String filePath=null;	
	
	public XLUtils2(String filePath)
	{
		this.filePath=filePath;
	}
	
	
	@DataProvider
	public String[][] getExcelData(String filePath) throws IOException
	{
		
		
		//File excelFile = new File("./src/test/resources/TestExcelData.xlsx");
		File excelFile = new File(filePath);
		//System.out.println(excelFile.exists());
		FileInputStream fis = new FileInputStream(excelFile);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getLastCellNum();
		
		String [][] data = new String[rowCount-1][colCount];
		
		for(int i=0; i<rowCount-1; i++)
		{
			for(int j=0; j<colCount; j++)
			{
				DataFormatter df = new DataFormatter();
				data[i][j] = df.formatCellValue(sheet.getRow(i+1).getCell(j));
								
			}
		}
		
		workbook.close();
		fis.close();
		
		/*
		 * for(String[] eachData: data) { System.out.println(Arrays.toString(eachData));
		 * }
		 */
		
		return data;
		
		
		}
	
	
	/*
	 * public void getRowCount() {
	 * 
	 * }
	 * 
	 * public void getCellCount() {
	 * 
	 * }
	 * 
	 * public void getCellData() {
	 * 
	 * }
	 * 
	 * public void setCellData() {
	 * 
	 * }
	 */

}
