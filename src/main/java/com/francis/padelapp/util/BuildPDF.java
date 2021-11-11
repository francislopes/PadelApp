package com.francis.padelapp.util;

import com.francis.padelapp.model.Game;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BuildPDF {

    public static void buildPDF(HttpServletResponse response, List<Game> report) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4.rotate(), 10f, 10f, 10f, 0f);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        PdfPTable table = new PdfPTable(10);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10f);
        writeHeader(table);
        writeData(table, report);
        document.add(table);

        document.close();
    }

    public static void writeHeader(PdfPTable table) {
        PdfPCell header = new PdfPCell();
        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
        header.setPadding(3f);

        Font font = new Font();
        font.setSize(7f);
        font.isBold();

        header.setPhrase(new Phrase("ID", font));
        table.addCell(header);
        header.setPhrase(new Phrase("DATE", font));
        table.addCell(header);
        header.setPhrase(new Phrase("TIME", font));
        table.addCell(header);
        header.setPhrase(new Phrase("ADDRESS", font));
        table.addCell(header);
        header.setPhrase(new Phrase("STATUS", font));
        table.addCell(header);
        header.setPhrase(new Phrase("COMMENTS", font));
        table.addCell(header);
        header.setPhrase(new Phrase("PLAYER_ONE", font));
        table.addCell(header);
        header.setPhrase(new Phrase("PLAYER_TWO", font));
        table.addCell(header);
        header.setPhrase(new Phrase("PLAYER_THREE", font));
        table.addCell(header);
        header.setPhrase(new Phrase("PLAYER_FOUR", font));
        table.addCell(header);
    }

    public static void writeData(PdfPTable table, List<Game> report) {
        Font font = new Font();
        font.setSize(8f);

        for (Game r : report) {
            PdfPCell data = new PdfPCell();
            data.setPadding(3f);

            data.setPhrase(new Phrase(String.valueOf(r.getId()), font));
            table.addCell(data);
            data.setPhrase(new Phrase(String.valueOf(r.getDate()), font));
            table.addCell(data);
            data.setPhrase(new Phrase(String.valueOf(r.getTime()), font));
            table.addCell(data);
            data.setPhrase(new Phrase(String.valueOf(r.getAddress()), font));
            table.addCell(data);
            data.setPhrase(new Phrase(String.valueOf(r.getStatus()), font));
            table.addCell(data);
            data.setPhrase(new Phrase(String.valueOf(r.getComments()), font));
            table.addCell(data);
            data.setPhrase(new Phrase(String.valueOf(r.getPlayerOne()), font));
            table.addCell(data);
            data.setPhrase(new Phrase(String.valueOf(r.getPlayerTwo()), font));
            table.addCell(data);
            data.setPhrase(new Phrase(String.valueOf(r.getPlayerThree()), font));
            table.addCell(data);
            data.setPhrase(new Phrase(String.valueOf(r.getPlayerFour()), font));
            table.addCell(data);
        }
    }
}
