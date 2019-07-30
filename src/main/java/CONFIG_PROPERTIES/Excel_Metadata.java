package CONFIG_PROPERTIES;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Metadata 
{
	@SuppressWarnings("deprecation")
	public static Map<String,  Map<String, String>> setMapData() throws IOException {

		String path = ".//resources//SF_API_Data_Sheet.xlsx";

		FileInputStream FIS = new FileInputStream(path);

		Workbook WB = new XSSFWorkbook(FIS);

		Sheet sheet = WB.getSheetAt(0);
		DataFormatter formatter = new DataFormatter();
		int lastRow = sheet.getLastRowNum();

		Map<String, Map<String, String>> excelFileMap = new HashMap<String, Map<String,String>>();

		Map<String, String> dataMap = new HashMap<String, String>();

		//Looping over entire row
		for(int i=0; i<=lastRow; i++){

			Row row = sheet.getRow(i);

			//1st Cell as Value
			Cell valueCell = row.getCell(1);
			valueCell.setCellType(CellType.STRING);
			

			//0th Cell as Key
			Cell keyCell = row.getCell(0);
			keyCell.setCellType(CellType.STRING);

			String value = valueCell.getStringCellValue().trim();
			String key = keyCell.getStringCellValue().trim();
			
			

			//Putting key & value in dataMap
			dataMap.put(key, value);

			//Putting dataMap to excelFileMap
			excelFileMap.put("DataSheet", dataMap);
		}

		//Returning excelFileMap
		return excelFileMap;

	}

	public static String getMapData(String key) throws IOException
	{
		Map<String, String> m = setMapData().get("DataSheet");
		String value = m.get(key);
		return value;
	}

}
