package com.github.alonwang.question;

public class DeepSearchTemplate<T>{
    public void deepSearch(T param){
        //终止条件
        if(true){

        }
        //递归主体
        /**
         *碰到关于排列时,常常涉及到状态的改变和恢复,如
         * v[i]=true;
         * deepSearch(param+1)
         * v[i]=false;
         */

    }

    public void bootstrapFunc(T initParam){
        deepSearch(initParam);
    }
}
