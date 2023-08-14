package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class FileReader {

    public Profile getDataFromFile(File file) {

        String content = readFileIntoString(file);
        if (content == null) {
            return null;
        } else {
            String[] pairs = parseContent(content);
            return mapDataToProfile(pairs);
        }
    }

    private Profile mapDataToProfile(String[] pairs) {
        Profile p = new Profile();
        String[] pair;
        for (int i = 0; i < pairs.length; i++) {
            pair = pairs[i].split("\\s");
            switch (pair[0]) {
                case "Name:":
                    p.setName(pair[1]);
                    break;
                case "Age:":
                    p.setAge(Integer.valueOf(pair[1]));
                    break;
                case "Email:":
                    p.setEmail(pair[1]);
                    break;
                case "Phone:":
                    p.setPhone(Long.valueOf(pair[1]));
                    break;
                default:
                    try {
                        throw new IOException("Such field does not exist.");
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
            }
        }
        return p;
    }

    private String[] parseContent(String content) {
        return content.split("\n");
    }

    private String readFileIntoString(File file) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(file.getPath()))) {
            String buffer;
            while ((buffer = reader.readLine()) != null) {
                sb.append(buffer).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString().strip();
    }
}
