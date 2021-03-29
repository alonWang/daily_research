package com.github.alonwang.algorithm.sort.externel;

import java.io.File;
import java.util.List;

public class SimpleExternalSorter implements ExternalSort{
    @Override
    public List<File> splitToSmallFiles(File bigFile, long availMemory) {
        int num=(int)(bigFile.length()/availMemory +1);
        bigFile.




                
        return null;
    }

    @Override
    public List<File> sortSmallFiles(List<File> smallFiles) {
        return null;
    }

    @Override
    public File mergeSortedSmallFiles(List<File> sortedFiles) {
        return null;
    }
}
