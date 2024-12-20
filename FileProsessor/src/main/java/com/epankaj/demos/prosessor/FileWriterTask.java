package com.epankaj.demos.prosessor;

import java.io.File;
import java.io.PrintWriter;
import java.util.concurrent.Callable;

public class FileWriterTask implements Callable<Long> {

    private final String filePath;

    public FileWriterTask(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Long call() throws Exception {

        String input = "I am the best Software Engineer on Planet Earth";
        try (PrintWriter writer = new PrintWriter(filePath)) {
            int i =0;
            while (i < 10000) {
                writer.println(input);
                i++;
            }


        }
        File file = new File(filePath);


        return file.length();
    }
}
