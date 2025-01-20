package org.project.io.impl;

import org.project.io.abstracts.BaseWriter;

import java.util.List;

public class IntegerWriter extends BaseWriter<Integer>{
    private static final String fileName = "integers.txt";

    @Override
    public void writeContents(List<Integer> integers, String path, String prefix, boolean isNeedToAddInExistingFiles) {
        super.setFileName(fileName);
        super.setType(Integer.class);
        super.writeContents(integers, path, prefix, isNeedToAddInExistingFiles);
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
