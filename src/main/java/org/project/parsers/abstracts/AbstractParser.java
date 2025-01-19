package org.project.parsers.abstracts;

import org.project.parsers.interfaces.Parser;

import java.util.List;

public abstract class AbstractParser<T> implements Parser<T>{
    public abstract void parse(List<String> files);
    public abstract List<T> getResult();
}

