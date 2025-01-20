package org.project.io.impl;

import org.project.io.abstracts.BaseWriter;

import java.util.List;

public class FloatWriter extends BaseWriter<Float>{
    private static final String fileName = "floats.txt";

    @Override
    public void writeContents(List<Float> floats, String path, String prefix, boolean isNeedToAddInExistingFiles) {
        super.setFileName(fileName);
        super.setType(Float.class);
        super.writeContents(floats, path, prefix, isNeedToAddInExistingFiles);
    }

    @Override
    public void printFullStats() {
        super.printFullStats();
    }

    @Override
    public void printShortStats() {
        super.printShortStats();
    }
}
