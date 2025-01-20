package org.project.io.abstracts;

import org.project.io.interfaces.Writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BaseWriter<T> implements Writer<T>{
    private Class<T> type;
    private String fileName;
    private double sum = 0;
    private int cnt = 0;
    private double max = Double.MIN_VALUE;
    private double min = Double.MAX_VALUE;
    @Override
    public void writeContents(List<T> contents, String path, String prefix, boolean isNeedToAddInExistingFiles) {
        String fullPath = fileName;

        if (path != null && prefix != null) {
            fullPath = path + File.separator + prefix + fullPath;
        } else if (path != null) {
            fullPath = path + File.separator + fullPath;
        } else if (prefix != null) {
            fullPath = prefix + fullPath;
        }

        File file = new File(fullPath);
        File parentDirectory = file.getParentFile();

        if (parentDirectory != null && !parentDirectory.exists()) {
            if (!parentDirectory.mkdirs()) {
                System.err.println("Не удалось создать директорию: " + parentDirectory.getAbsolutePath());
            } else {
                System.out.println("Директория успешно создана!");
            }
        }

        try(FileWriter fileWriter = new FileWriter(file, isNeedToAddInExistingFiles)) {
            for(T t: contents) {
                fileWriter.write(t + "\n");
                cnt++;
                if (t instanceof String) {
                    max = Math.max(max, ((String) t).length());
                    min = Math.min(min, ((String) t).length());
                } else {
                    double value = Double.parseDouble(t.toString());
                    sum+= value;
                    max = Math.max(max, value);
                    min = Math.min(min, value);
                }
            }
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void printFullStats() {
        System.out.println("Полная статистика для типа данных " + this.type.getSimpleName());
        System.out.println();
        System.out.println("Кол-во записанных данных: " + cnt);

        if (this.type == String.class) {
            System.out.println("Минимальная длина записанной строки: " + min);
            System.out.println("Максимальная длина записанной строки: " + max);
        } else {
            System.out.println("Минимальное записанное значение: " + min);
            System.out.println("Максимальное записанное значение: " + max);
            System.out.println("Сумма записанных значений: " + sum);
            System.out.println("Среднее записанных значений: " + (sum / cnt));
        }

    }

    @Override
    public void printShortStats() {
        System.out.println("Кол-во записанных данных: " + cnt);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }
}
