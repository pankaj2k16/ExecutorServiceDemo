package com.epankaj.demos.prosessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FileProsessor {

    public static final ExecutorService service = Executors.newFixedThreadPool(10);
    public static void  process() throws InterruptedException {
        List<Future<Long>> futureList = new ArrayList<>();
        for(int i=1; i<=1000; i++) {
            String filePath = String.format("files/%d.txt", i);
            FileWriterTask task = new FileWriterTask(filePath);
            Future<Long> future = service.submit(task);
            futureList.add(future);
        }

        long total = 0;
        try {
            for(Future<Long> f : futureList) {
                long size = f.get();
                total = total + size;
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            service.shutdown();
            boolean flag = service.awaitTermination(10, TimeUnit.SECONDS);
            if(!flag) {
                service.shutdownNow();
            }
        }

        System.out.println("Total written Bytes : "+total);

    }
}
