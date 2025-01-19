package org.project.io.impl;

import org.project.io.abstracts.AbstractWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StringWriter extends AbstractWriter<String>{
    private static final String fileName = "strings.txt";
    private int cnt = 0;
    private int max = Integer.MIN_VALUE;
    private int min = Integer.MAX_VALUE;
    @Override
    public void writeContents(List<String> contents, String path, String prefix, boolean isNeedToAddInExistingFiles) {
        String fullPath = fileName;

        if (path != null && prefix != null) {
            fullPath = path + File.separator + fullPath;
        } else if (path != null) {
            fullPath = path + File.separator + fullPath;
        } else if (prefix != null) {
            fullPath = prefix + File.separator + fullPath;
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
            for(String s: contents) {
                fileWriter.write(s + "\n");
                cnt++;
                max = Math.max(max, s.length());
                min = Math.min(min, s.length());
            }
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void printFullStats() {
        if (cnt == 0) {
            System.out.println("Отсутствуют записанные строки");
        } else {
            System.out.println("Количество записанных строк: " + cnt);
            System.out.println("Минимальная длина записанной строки: " + min);
            System.out.println("Максимальная длина записанной строки: " + max);
        }
    }

    @Override
    public void printShortStats() {
        if (cnt == 0) {
            System.out.println("Отсутствуют записанные строки");
        } else {
            System.out.println("Количество записанных строк: " + cnt);
        }
    }
}
