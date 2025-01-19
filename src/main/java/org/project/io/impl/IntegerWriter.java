package org.project.io.impl;

import org.project.io.abstracts.AbstractWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class IntegerWriter extends AbstractWriter<Integer>{
    private static final String fileName = "integers.txt";
    private int cnt = 0;
    private int sum = 0;
    private int maxInt = Integer.MIN_VALUE;
    private int minInt = Integer.MAX_VALUE;
    @Override
    public void writeContents(List<Integer> integers, String path, String prefix, boolean isNeedToAddInExistingFiles) {
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
            for(Integer i: integers) {
                fileWriter.write(i + "\n");
                cnt++;
                sum+=i;
                maxInt = Math.max(maxInt, i);
                minInt = Math.min(minInt, i);
            }
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void printFullStats() {
        if (cnt == 0) {
            System.out.println("Отсутствуют записанные целые числа");
        } else {
            System.out.println("Минимальное записанное целое число: " + minInt);
            System.out.println("Максимальное записанное целое число: " + maxInt);
            System.out.println("Среднее арифметическое записанных целых чисел: " + (sum / cnt));
        }
    }

    @Override
    public void printShortStats() {
        if (cnt == 0) {
            System.out.println("Отсутствуют записанные целые числа");
        } else {
            System.out.println("Количество записанных целых чисел: " + cnt);
        }
    }
}
