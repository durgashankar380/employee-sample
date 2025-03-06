package com.employee.demo.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.employee.demo.model.Employee;

public class ExcelHelper {

	// to check the format of file is excel or not
	public static boolean checkExcelFormat(MultipartFile file) {
		String contentType = file.getContentType();
		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}
	}

	// convert excel file data into list of Object
	public static List<Employee> convertExcelToListOfEmployee(InputStream is) {
		List<Employee> list = new ArrayList<>();
		try {
			@SuppressWarnings("resource")
			XSSFWorkbook workBook = new XSSFWorkbook(is);
			XSSFSheet sheet = workBook.getSheet("Sheet1");
			int rowNumber = 0;
			Iterator<Row> iterator = sheet.iterator();
			while (iterator.hasNext()) {
				Row row = iterator.next();
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cells = row.iterator();
				int cId = 0;
				Employee employee = new Employee();
				while (cells.hasNext()) {
					Cell cell = cells.next();
					if (cId == 0) {
						cId++;
						continue;
					}
					switch (cId) {
					case 0:
						employee.setId(cell.getColumnIndex());
						break;
					case 1:
						employee.setName(cell.getStringCellValue());
						break;
					case 2:
						employee.setDepartment(cell.getStringCellValue());
						break;
					case 3:
						employee.setSalary(cell.getNumericCellValue());
						break;
					default:
						break;
					}
					cId++;
				}
				list.add(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
