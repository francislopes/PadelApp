package com.francis.padelapp.util;

import com.francis.padelapp.model.Game;
import com.opencsv.CSVWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuildCSV {

    public static void buildCSV(HttpServletResponse response, List<Game> report) throws IOException {
        CSVWriter writer = new CSVWriter(response.getWriter());
        writer.writeAll(convert(report));
        writer.close();
    }

    private static List<String[]> convert(List<Game> report) {
        String[] header = {"ID", "DATE", "TIME", "ADDRESS", "STATUS", "COMMENTS"};

        List<String[]> list = new ArrayList<>();
        list.add(header);

        for (Game r : report) {
            String[] temp = new String[header.length];
            temp[0] = String.valueOf(r.getId());
            temp[1] = String.valueOf(r.getTime());
            temp[2] = String.valueOf(r.getTime());
            temp[3] = String.valueOf(r.getAddress());
            temp[4] = String.valueOf(r.getStatus());
            temp[5] = String.valueOf(r.getComments());
            list.add(temp);
        }
        return list;
    }
}
