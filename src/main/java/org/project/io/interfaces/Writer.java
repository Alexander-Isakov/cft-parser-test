package org.project.io.interfaces;

import java.util.List;

public interface Writer<T>{
    void writeContents(List<T> contents, String path, String prefix, boolean isNeedToAddInExistingFiles);
    void printFullStats();
    void printShortStats();
}
