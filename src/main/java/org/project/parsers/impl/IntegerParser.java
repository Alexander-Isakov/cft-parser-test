package org.project.parsers.impl;

import org.project.parsers.abstracts.AbstractParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IntegerParser extends AbstractParser<Integer>{
    private final List<Integer> result = new ArrayList<>();

    @Override
    public List<Integer> getResult() {
        return result;
    }

    @Override
    public void parse(List<String> files) {
        for (String filePath : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        if (Float.parseFloat(line) == (int) Float.parseFloat(line)) {
                            result.add(Integer.parseInt(line));
                        }
                    } catch (NumberFormatException ignored) {}
                }
            } catch (FileNotFoundException f) {
                System.out.println("В переданных файлах отсутствуют целые числа");
            } catch (IOException e) {
                System.out.println("Произошла ошибка при попытке работы с файлом");
            }
        }
    }
}
