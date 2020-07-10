package com.github.alonwang.lang.instrumentation;

import java.io.File;
import java.io.FileInputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;

/**
 * @author alonwang
 * @date 2020/5/18 17:16
 * @detail
 */
public class HotSwapAgent {
    public static void agentmain(String args, Instrumentation ins) {
        System.out.println("agentmain run");
        try {
            File file = new File("C:\\Users\\DELL\\Desktop\\ggg\\RedefineClassesLimit.class");
            ins.redefineClasses(new ClassDefinition(RedefineClassesLimit.class, new FileInputStream(file).readAllBytes()));
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    public static void premain(String args, Instrumentation ins) {

    }
}
