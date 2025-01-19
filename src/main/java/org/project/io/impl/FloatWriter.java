package org.project.io.impl;

import org.project.io.abstracts.AbstractWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FloatWriter extends AbstractWriter<Float>{
    private static final String fileName = "floats.txt";
    private int cnt = 0;
    private float sum = 0.0f;
    private float min = Float.MAX_VALUE;
    private float max = Float.MIN_VALUE;
    @Override
    public void writeContents(List<Float> floats, String path, String prefix, boolean isNeedToAddInExistingFiles) {
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
            for(Float f: floats) {
                fileWriter.write(f + "\n");
                cnt++;
                sum+=f;
                min = Math.min(f, min);
                max = Math.max(f, max);
            }
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void printFullStats() {
        if (cnt == 0) {
            System.out.println("Отсутствуют записанные вещественные числа");
        } else {
            System.out.println("Минимальное записанное вещественное число: " + min);
            System.out.println("Максимальное записанное вещественное число: " + max);
            System.out.println("Среднее арифметическое записанных вещественных чисел: " + (sum / cnt));
        }
    }

    @Override
    public void printShortStats() {
        if (cnt == 0) {
            System.out.println("Отсутствуют записанные вещественные числа");
        } else {
            System.out.println("Количество записанных вещественных чисел: " + cnt);
        }
    }
}
