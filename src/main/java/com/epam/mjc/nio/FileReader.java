package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class FileReader {

    public Profile getDataFromFile(File file) {

        String content = readFileIntoString(file);
        String[] pairs = parseContent(content);
        return mapDataToProfile(pairs);
    }

    private Profile mapDataToProfile(String[] pairs) {
        int step = pairs[0].length() - 2;
        Profile profile = new Profile();
        profile.setName(pairs[1].strip());
        profile.setAge(Integer.valueOf(pairs[1 + step].strip()));
        profile.setEmail(pairs[1 + step * 2].strip());
        profile.setPhone(Long.valueOf(pairs[1 + step * 3].strip()));
        return profile;
    }

    private String[] parseContent(String content) {
        return content.split("\\s");
    }

    private String readFileIntoString(File file) {
        try {
            return Files.readString(Path.of(file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
