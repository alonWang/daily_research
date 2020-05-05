package com.github.alonwang.lang;

/**
 * clone只是浅拷贝
 */
public class CloneTest implements Cloneable {
    private int a;
    private Integer A;

    public CloneTest(int a, Integer a1) {
        this.a = a;
        A = a1;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        CloneTest a = new CloneTest(1, 1);

        CloneTest b = (CloneTest) a.clone();
        System.out.println("a.a == b.a ? " + (a.a == b.a));
        System.out.println("a.A equals b.A ? " + (a.A.equals(b.A)));
        System.out.println("a.A == b.A ? " + (a.A == b.A));
    }
}
