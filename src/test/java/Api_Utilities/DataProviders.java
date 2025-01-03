package Api_Utilities;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="Data") // Return All the Data
	public String [] [] getAllData() throws Throwable {
		
		String path = System.getProperty("user.dir")+"//testdata//Framework.xlsx";
		XLUtility xl = new XLUtility(path);
		
		int rownum = xl.getRowCount("Test");
		int colcount = xl.getCellCount("Test", 1);
		
		String apidata [] [] = new String [rownum] [colcount];
		
		for (int i = 1; i < rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				apidata[i-1][j] = xl.getCellData("Test", i, j);
			}
		}
		return apidata;
	}
	
	@DataProvider(name="UserNames") // Return Only UserNames
	public String [] getUseNames() throws Throwable {
		
		String path = System.getProperty("user.dir")+"//testdata//Framework.xlsx";
		XLUtility xl = new XLUtility(path);
		
		int rownum = xl.getRowCount("Test");
		
		String apidata [] = new String [rownum];
		
		for (int i = 1; i < rownum; i++) {
			apidata[i-1] = xl.getCellData("Test", i, 1);
		}
		return apidata;
	}
	
}
