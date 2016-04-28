package webparser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelWriter {
	
	public static void writeExcel(List<RestaurantsModel> listBook, String excelFilePath) throws IOException {
	    Workbook workbook = new HSSFWorkbook();
	    Sheet sheet = workbook.createSheet();	 
	    int rowCount = 0;	 
	    for (RestaurantsModel aRestInfo : listBook) {
	        Row row = sheet.createRow(++rowCount);
	       //System.out.println(aRestInfo.getName());
	        writeBook(aRestInfo, row);
	      
	    }	 
	    
	    try {
	    	FileOutputStream outputStream = new FileOutputStream(excelFilePath);
	        workbook.write(outputStream);
	    }catch (Exception e) {
			// TODO: handle exception
		}
	}
	private static void writeBook(RestaurantsModel restaurantsModel, Row row) {
	    Cell cell = row.createCell(1);
	    cell.setCellValue(restaurantsModel.getName());
	 
	    cell = row.createCell(2);
	    cell.setCellValue(restaurantsModel.getPhone());
	 
	    cell = row.createCell(3);
	    cell.setCellValue(restaurantsModel.getAddress());
	    
	    cell = row.createCell(4);
	    cell.setCellValue(restaurantsModel.getRating());
	    
	    cell = row.createCell(5);
	    cell.setCellValue(restaurantsModel.getYear());
	  
	}

}
