package Api_Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	
	String path;
	
	public XLUtility(String path) {
		this.path=path;
	}
	
	public int getRowCount(String sheetName) throws Throwable {
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		
		return rowCount;
	}
	
	public int getCellCount(String sheetName, int rownum) throws Throwable {
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fi.close();
		
		return cellCount;
	}
	
	public String getCellData(String sheetName, int rownum, int colnum) throws Throwable {
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell); // To Return the Cell Value as String
		} catch (Exception e) {
			data = "";
		}
		workbook.close();
		fi.close();
		
		return data;
	}
	
	public void setCellData(String sheetName, int rownum, int colnum, String data) throws Throwable {
		
		File xlFile = new File(path);
		
	
		if(!xlFile.exists()) //If the File Does not exists then Create New File
		{
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workbook.write(fo);
		}
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		
		if (workbook.getSheetIndex(sheetName)==-1) //If the Sheet not exists the create New Sheet
		{
			workbook.createSheet(sheetName);
			sheet = workbook.getSheet(sheetName);
		}
		
		if (sheet.getRow(rownum)==null) {
			sheet.createRow(rownum);
			row = sheet.getRow(rownum);
			
			cell = row.createCell(colnum);
			cell.setCellValue(data);
			fo = new FileOutputStream(path);
			workbook.write(fo);
			workbook.close();
			fi.close();
			fo.close();
		}
 	}
}
