package utilities;

import configuration.Configuration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public enum LogEngine {
    instance;

    public BufferedWriter bufferedWriter;

    public void init() {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(new File(Configuration.instance.logFile)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void write(String line) {
        try {
            bufferedWriter.write(line + Configuration.instance.lineSeparator);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public void close() {
        try {
            bufferedWriter.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}