package com.github.alonwang.algorithm.sort.externel;

import java.io.File;
import java.util.List;

public class Launcher {
    public static void main(String[] args) {
        File bigFile=new File("");
        ExternalSort sorter=null;
        List<File> smallFiles=sorter.splitToSmallFiles(bigFile,Runtime.getRuntime().freeMemory());
        List<File> sortedSmallFiles=sorter.sortSmallFiles(smallFiles);
        File targetFile=sorter.mergeSortedSmallFiles(sortedSmallFiles);
    }
}
