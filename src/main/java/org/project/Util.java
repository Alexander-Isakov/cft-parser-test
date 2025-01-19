package org.project;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.project.io.abstracts.AbstractWriter;
import org.project.io.impl.FloatWriter;
import org.project.io.impl.IntegerWriter;
import org.project.io.impl.StringWriter;
import org.project.parsers.CommandLineParser;
import org.project.parsers.abstracts.AbstractParser;
import org.project.parsers.impl.FloatParser;
import org.project.parsers.impl.IntegerParser;
import org.project.parsers.impl.StringParser;

import java.util.List;

public class Util {
    public static void main(String[] args) {
        CommandLineParser commandLineParser = new CommandLineParser();

        try {
            JCommander.newBuilder().addObject(commandLineParser).build().parse(args);
        } catch (ParameterException e) {
            System.out.println("Некорректно переданы параметры");
            System.exit(0);
        }

        AbstractParser<String> stringParser = new StringParser();
        AbstractParser<Integer> integerParser = new IntegerParser();
        AbstractParser<Float> floatParser = new FloatParser();

        List<String> files = commandLineParser.getFiles();
        stringParser.parse(files);
        integerParser.parse(files);
        floatParser.parse(files);

        AbstractWriter<String> stringWriter = new StringWriter();
        AbstractWriter<Integer> integerWriter = new IntegerWriter();
        AbstractWriter<Float> floatWriter = new FloatWriter();

        stringWriter.writeContents(
                stringParser.getResult(),
                commandLineParser.getPath(),
                commandLineParser.getPrefix(),
                commandLineParser.isNeedToAddInExistingFiles()
        );

        integerWriter.writeContents(
                integerParser.getResult(),
                commandLineParser.getPath(),
                commandLineParser.getPrefix(),
                commandLineParser.isNeedToAddInExistingFiles()
        );

        floatWriter.writeContents(
                floatParser.getResult(),
                commandLineParser.getPath(),
                commandLineParser.getPrefix(),
                commandLineParser.isNeedToAddInExistingFiles()
        );

        if (commandLineParser.isNeedFullStatistics()) {
            stringWriter.printFullStats();
            System.out.println();
            integerWriter.printFullStats();
            System.out.println();
            floatWriter.printFullStats();
        } else if (commandLineParser.isNeedShortStatistics()) {
            stringWriter.printShortStats();
            integerWriter.printShortStats();
            floatWriter.printShortStats();
        }
    }
}