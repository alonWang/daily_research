package com.github.alonwang.other;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

/**
 * @author alonwang
 * @date 2020/12/29 19:23
 */
public class FileAlterationDemo {
    public static void main(String[] args) throws Exception {
        FileAlterationMonitor monitor=new FileAlterationMonitor(5000);
        File directory=new File("C:\\Users\\小可爱\\Desktop\\test");
        FileAlterationObserver fileAlterationObserver=new FileAlterationObserver(directory);
        monitor.addObserver(fileAlterationObserver);
        fileAlterationObserver.addListener(new FileAlterationListenerAdaptor(){
            @Override
            public void onFileChange(File file) {
                System.out.println("file change: "+file.getName());
            }
        });
        monitor.start();
        Thread.sleep(1000000);
    }
}
