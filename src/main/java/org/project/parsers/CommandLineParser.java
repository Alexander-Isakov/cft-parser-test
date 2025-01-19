package org.project.parsers;

import com.beust.jcommander.Parameter;

import java.util.List;

public class CommandLineParser {
    @Parameter(description = "Файлы", required = true)
    private List<String> files;

    @Parameter(names = "-o", description = "добавление пути для файлов")
    private String path;

    @Parameter(names = "-p", description = "указание префикса для имен файлов")
    private String prefix;

    @Parameter(names = "-s", description = "вывод короткой статистики")
    private boolean isNeedShortStatistics;

    @Parameter(names = "-f", description = "вывод полной статистики")
    private boolean isNeedFullStatistics;

    @Parameter(names = "-a", description = "режим добавления результатов в имеющиеся файлы")
    private boolean isNeedToAddInExistingFiles;

    public List<String> getFiles() {
        return files;
    }

    public String getPath() {
        return path;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean isNeedShortStatistics() {
        return isNeedShortStatistics;
    }

    public boolean isNeedFullStatistics() {
        return isNeedFullStatistics;
    }

    public boolean isNeedToAddInExistingFiles() {
        return isNeedToAddInExistingFiles;
    }
}
