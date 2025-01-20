package org.project.parsers.impl;

import org.project.parsers.abstracts.AbstractParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StringParser extends AbstractParser<String> {
    private final List<String> result = new ArrayList<>();

    @Override
    public void parse(List<String> files) {
        for (String filePath : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!isNumber(line)) {
                        result.add(line);
                    }
                }
            } catch (FileNotFoundException f) {
                System.out.println("В переданных файлах отсутствуют строки");
            } catch (IOException e) {
                System.out.println("Произошла ошибка при попытке работы с файлом");
            }
        }
    }

    public boolean isNumber(String line) {
        try {
            Byte.parseByte(line);
            return true;
        } catch (NumberFormatException ignored) {}

        try {
            Short.parseShort(line);
            return true;
        } catch (NumberFormatException ignored) {}

        try {
            Integer.parseInt(line);
            return true;
        } catch (NumberFormatException ignored) {}

        try {
            Long.parseLong(line);
            return true;
        } catch (NumberFormatException ignored) {}

        try {
            Float.parseFloat(line);
            return true;
        } catch (NumberFormatException ignored) {}

        try {
            Double.parseDouble(line);
            return true;
        } catch (NumberFormatException ignored) {}

        return false;
    }

    @Override
    public List<String> getResult() {
        return result;
    }
}
