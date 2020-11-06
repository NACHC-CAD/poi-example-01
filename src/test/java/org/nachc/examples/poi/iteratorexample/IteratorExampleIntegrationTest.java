package org.nachc.examples.poi.iteratorexample;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IteratorExampleIntegrationTest {

	public static final int MAX = 220;
	
	@Test
	public void shouldWriteCells() throws Exception {
		SXSSFWorkbook book = new SXSSFWorkbook();
		Sheet sheet = book.createSheet("sheet-001");
		// create the book
		log.info("* * * CREATING * * *");
		for(int r=0;r<MAX;r++) {
			Row row = sheet.createRow(r);
			for(int c=0;c<5;c++) {
				Cell cell = row.createCell(c);
				String str = "ROW " + r + " COL " + c;
				log.info("CREATING: " + str);
				cell.setCellValue(str);
			}
		}
		log.info("* * * ECHO * * *");
		Iterator<Row> rowIterator = sheet.rowIterator();
		while(rowIterator.hasNext()) {
			Row row = rowIterator.next();
			log.info("Got Row: " + row.getRowNum());
		}
		book.close();
		log.info("Done.");
	}

}
