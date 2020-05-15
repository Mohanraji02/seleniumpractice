package session;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class facebookexcel {
	
	
	 XSSFWorkbook wb;
	 XSSFSheet sheet;
public facebookexcel(String excelpath)

{
	
try
{
	File src=new File(excelpath);
	FileInputStream fis=new FileInputStream(src);
	 wb=new XSSFWorkbook(fis);
}
catch(Exception e)
{
	System.out.println(e.getMessage());
}}
	public  String getdata(int sheetindex,int row,int column)
	{
		String data1="";
	 sheet=wb.getSheetAt(sheetindex);
	 XSSFCell data=sheet.getRow(row).getCell(column);
	 switch(data.getCellType())
	 {
	 
	 case Cell.CELL_TYPE_STRING:
		  data1=data.getStringCellValue();
	 break;
	 
	 case Cell.CELL_TYPE_NUMERIC:
		 
		Integer res=(int)data.getNumericCellValue();
		 data1=res.toString();
		break;
	 }
	 return data1;
	}
	 

	public int getrownum(int sheetindex)
	{
	sheet=	wb.getSheetAt(sheetindex);
	int rows=sheet.getLastRowNum();
	rows=rows+1;
	return rows;
	}
	

}
