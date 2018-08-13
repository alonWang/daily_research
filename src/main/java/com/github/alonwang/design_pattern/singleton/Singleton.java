package com.github.alonwang.design_pattern.singleton;//饿汉式,jdk1.5之前有问题
// public class Singleton{
//     private static volatile Singleton instance;
//     private Singleton(){}

//     public static Singleton getInstance(){
//         if(null==instance){
//             synchronized(Singleton.class){
//                 if(null==instance)
//                     instance=new Singleton();
//             }
//         }
//         return instance;
//     }
// }
public class Singleton{
    private static class SingletonHolder{
        static{
            System.out.print("nested class");
        }
        private static final Singleton INSTANCE= new Singleton();
    }
    private Singleton(){
        System.out.print("contruct");
    }
    public static final Singleton getInstance(){
        
        return SingletonHolder.INSTANCE;
    }
    public static void f(){}
    public static void main(String[] args){
        System.out.print("main start");
        Singleton.f();
        //Singleton.getInstance();
        //Singleton.getInstance();
    }
}