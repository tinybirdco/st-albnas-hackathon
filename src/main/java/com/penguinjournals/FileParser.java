package com.penguinjournals;

import java.io.*;
import java.util.ArrayList;

public class FileParser {
    public ArrayList<String> Parse(String file) {
        ArrayList<String> parsedContent = new ArrayList<>();
        BufferedReader reader;

        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classloader.getResourceAsStream(file);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();

            while (line != null) {
                parsedContent.add(cleanLine(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parsedContent;
    }

    private String cleanLine(String line) {
        return line.replace("[", "")
                .replace("]","")
                .replace(",", "")
                .replace("'", "")
                .trim();
    }
}
