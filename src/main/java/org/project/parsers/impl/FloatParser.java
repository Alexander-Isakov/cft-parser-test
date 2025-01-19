package org.project.parsers.impl;

import org.project.parsers.abstracts.AbstractParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FloatParser extends AbstractParser<Float>{
    private final List<Float> result = new ArrayList<>();
    @Override
    public void parse(List<String> files) {
        for (String filePath : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        if (Float.parseFloat(line) != (int) Float.parseFloat(line)) {
                            result.add(Float.parseFloat(line));
                        }
                    } catch (NumberFormatException ignored) {
                    }
                }
            } catch (FileNotFoundException f) {
                System.out.println("В переданных файлах отсутствуют вещественные числа");
            } catch (IOException e) {
                System.out.println("Произошла ошибка при попытке работы с файлом");
            }
        }
    }

    @Override
    public List<Float> getResult() {
        return result;
    }
}
