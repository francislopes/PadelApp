package com.francis.padelapp.util;

import com.francis.padelapp.model.Game;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BuildXLSX {

    public static void buildXLSX(HttpServletResponse response, List<Game> report) throws IOException {
        var workbook = new XSSFWorkbook();
        var sheet = workbook.createSheet("Report");
        sheet.setColumnWidth(0, 2000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 4000);
        sheet.setColumnWidth(3, 4000);
        sheet.setColumnWidth(4, 4000);
        sheet.setColumnWidth(5, 4000);

        // header
        var header = sheet.createRow(0);

        var headerStyle = workbook.createCellStyle();
        var font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        headerStyle.setFont(font);

        var headerCell = header.createCell(0);
        headerCell.setCellValue("ID");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("DATE");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("TIME");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(3);
        headerCell.setCellValue("ADDRESS");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(4);
        headerCell.setCellValue("STATUS");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(5);
        headerCell.setCellValue("COMMENTS");
        headerCell.setCellStyle(headerStyle);

        // rows
        var rowNum = 1;
        for (Game r : report) {
            var row = sheet.createRow(rowNum++);

            Cell cell = row.createCell(0);
            cell.setCellValue(String.valueOf(r.getId()));

            cell = row.createCell(1);
            cell.setCellValue(String.valueOf(r.getDate()));

            cell = row.createCell(2);
            cell.setCellValue(String.valueOf(r.getTime()));

            cell = row.createCell(3);
            cell.setCellValue(String.valueOf(r.getAddress()));

            cell = row.createCell(4);
            cell.setCellValue(String.valueOf(r.getStatus()));

            cell = row.createCell(5);
            cell.setCellValue(String.valueOf(r.getComments()));
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
