package com.example.learningdemo.pojo;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelExportZip {


    public static void main(String[] args) {
        List<Workbook> workbooks = new ArrayList<>();

        // 生成多个Workbook示例
        for (int i = 0; i < 3; i++) {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet1");
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("This is workbook " + (i + 1));
            workbooks.add(workbook);
        }

        String zipFilePath = "C:\\Users\\zhuangy\\Desktop\\workbooks.zip";
        try (ZipArchiveOutputStream zipOut = new ZipArchiveOutputStream(new FileOutputStream(new File(zipFilePath)))) {
            for (int i = 0; i < workbooks.size(); i++) {
                Workbook workbook = workbooks.get(i);
                String workbookFileName = "workbook" + (i + 1) + ".xlsx";
                ZipArchiveEntry zipEntry = new ZipArchiveEntry(workbookFileName);
                zipOut.putArchiveEntry(zipEntry);
                workbook.write(zipOut);
                workbook.close();
                zipOut.closeArchiveEntry();
            }
            System.out.println("Workbooks compressed to " + zipFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
