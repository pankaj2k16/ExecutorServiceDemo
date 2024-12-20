package com.epankaj.demos;

import java.util.concurrent.*;

public class ExecutorsServiceDemo {
    public static void main(String[] args) throws Exception{

        // Creating executor service object
        ExecutorService service = Executors.newFixedThreadPool(10);
        RunnableTask task1 = new RunnableTask();
        RunnableTask task2 = new RunnableTask();
        RunnableTask task3 = new RunnableTask();

        Future<?> f1 = service.submit(task1);
        Future<?> f2 = service.submit(task2);
        Future<?> f3 = service.submit(task3);

        System.out.println("task1 is done: "+ f1.isDone());
        System.out.println("task2 is done: "+ f2.isDone());
        f3.get();
        System.out.println("task3 is done: "+ f3.isDone());


        boolean flag = service.awaitTermination(1, TimeUnit.MINUTES);

        if(!flag) {
            System.out.println("Shutdown task forcefully ... ");
            service.shutdownNow();
        }

        System.out.println("++++++++++++++++++++CALLABLE++++++++++++++++++++++++++++++++++++");

        CallableTask callableTask = new CallableTask();
        Future<String> future = service.submit(callableTask);
        future.get();
        future.notifyAll();

        service.shutdown();

    }
}

class RunnableTask implements Runnable {

    @Override
    public void run() {
        Thread t1 = Thread.currentThread();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String name = t1.getName();
        System.out.println(name);
    }
}

class CallableTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread t1 = Thread.currentThread();
        String name = t1.getName();
        return name+" I am working bro ...";
    }
}