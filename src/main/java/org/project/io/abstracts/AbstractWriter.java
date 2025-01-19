package org.project.io.abstracts;

import org.project.io.interfaces.Writer;

import java.util.List;

public abstract class AbstractWriter<T> implements Writer<T>{
    @Override
    public abstract void writeContents(List<T> contents, String path, String prefix, boolean isNeedToAddInExistingFiles);
}
