package com.epankaj.demos;

import com.epankaj.demos.prosessor.FileProsessor;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        FileProsessor.process();
        long end = System.currentTimeMillis();
        long diff = end -start;
        System.out.println("Total time : "+diff);
//        Total written Bytes : 490000000
//        Total time : 2285
    }
}