package com.github.alonwang.netty;

import io.netty.util.Recycler;

/**
 * @author alonwang
 * @date 2020/12/28 17:11
 */
public class RecyclerDemo {
    private final Recycler<User> recycler;

    public RecyclerDemo(Recycler<User> recycler) {
        this.recycler = recycler;
    }

    static class User extends Recycler<User>{
        private final Handle<User> handle;
        public String name;

        public User(Handle<User> handle) {
            this.handle = handle;
        }
        public void recycle(){
            handle.recycle(this);
        }
        @Override
        protected User newObject(Handle<User> handle) {
            return new User(handle);
        }

    }

    public static void main(String[] args) {
        RecyclerDemo demo=new RecyclerDemo(new Recycler<User>() {
            @Override
            protected User newObject(Handle<User> handle) {
                return new User(handle);
            }

        });
        for (int i=0;i<100000;i++){
            User u=demo.recycler.get();
            u.name=i+"";
            u.recycle();
        }
       User u2= demo.recycler.get();
        System.out.println(u2.name);
    }
}
