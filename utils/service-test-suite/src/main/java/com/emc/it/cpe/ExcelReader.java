/**
 * 
 */
package com.emc.it.cpe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author upadhs5
 *
 */
public class ExcelReader {
    private String filePath;
    private int fields;

    public ExcelReader(String filePath, int fields) {
        this.filePath = filePath;
        this.fields = fields;
    }

    public List<String[]> readExcel() {
        FileInputStream inputStream;
        List<String[]> records = new ArrayList<>();
        try {
            inputStream = new FileInputStream(new File(this.filePath));
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                if (row.getRowNum() == 0) {
                    // Header row Do nothing.
                }
                Iterator<Cell> cellIterator = row.cellIterator();
                if (row.getRowNum() > 0) {
                    int cellIndex = 0;
                    String[] record = new String[this.fields];
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        record[cellIndex] = cell.getStringCellValue();
                        cellIndex++;
                    }
                    records.add(record);
                }

            }
            workbook.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static void main(String[] args) {
        ExcelReader reader = new ExcelReader("src/test/resources/soap-data.xlsx",4);
        List<String[]> records = reader.readExcel();
        records.forEach(r ->{
            Arrays.toString(r);
        });

    }
}
