package org.project.io.impl;

import org.project.io.abstracts.BaseWriter;

import java.util.List;

public class StringWriter extends BaseWriter<String>{
    private static final String fileName = "strings.txt";

    @Override
    public void writeContents(List<String> strings, String path, String prefix, boolean isNeedToAddInExistingFiles) {
        super.setFileName(fileName);
        super.setType(String.class);
        super.writeContents(strings, path, prefix, isNeedToAddInExistingFiles);
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
