package org.project.parsers.interfaces;

import java.io.IOException;
import java.util.List;

public interface Parser<T>{
    List<T> getResult();
    void parse(List<String> files) throws IOException;
}
