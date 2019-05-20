package Utilities;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil 

{
Workbook wb;
//it loads all excel sheet
	public ExcelFileUtil() throws Throwable
	{
		FileInputStream fis=new FileInputStream("./TestInputs/InputSheet.xlsx");
		wb=WorkbookFactory.create(fis);
	}
	
	public int rowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}
	public int colCount(String sheetname,int row)
	{
		return wb.getSheet(sheetname).getRow(row).getLastCellNum();
	}
	
	
public String getData(String sheetname,int row,int column)
	{
		String data="";
if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
	{
			
int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
data=String.valueOf(celldata);
}else
{
data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
}
return data;
}
	
public void setData(String sheetname,int row,int column,String status) throws Throwable
	{
		Sheet sh=wb.getSheet(sheetname);
		Row rownum=sh.getRow(row);
		Cell cell=rownum.createCell(column);
		cell.setCellValue(status);
		
		if(status.equalsIgnoreCase("PASS"))
		{
			CellStyle style=wb.createCellStyle();
			
			Font font=wb.createFont();
			
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}else
			if(status.equalsIgnoreCase("FAIL"))
			{
				CellStyle style=wb.createCellStyle();
				
				Font font=wb.createFont();
				
				font.setColor(IndexedColors.RED.getIndex());
				font.setBold(true);
				
				style.setFont(font);
				rownum.getCell(column).setCellStyle(style);	
			}else
				if(status.equalsIgnoreCase("Not Executed"))
				{
					CellStyle style=wb.createCellStyle();
					
					Font font=wb.createFont();
					
					font.setColor(IndexedColors.BLUE.getIndex());
					font.setBold(true);
					
					style.setFont(font);
					rownum.getCell(column).setCellStyle(style);
				}
		
		FileOutputStream fos=new FileOutputStream("./TestOutput/OutputSheet.xlsx");
		wb.write(fos);
		fos.close();
		
	}
	
	
	public static void main(String[] args) throws Throwable {
		
		ExcelFileUtil v=new ExcelFileUtil();
		int rc=v.rowCount("Sheet1");
		for(int i=1;i<=rc;i++)
		{
			String user=v.getData("Sheet1", i, 0);
			System.out.println(user);
		}
	}
	
	
	
	
	
	
	
}
