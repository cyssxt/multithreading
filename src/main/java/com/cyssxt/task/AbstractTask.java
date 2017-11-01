package com.cyssxt.task;

/**
 * author:cyssxt
 * date:${date}
 * $comment$
 **/
public abstract class AbstractTask {

    private int one_time = 1;
    public abstract void run();

    public boolean filter(){
        boolean flag = one_time>0;
        one_time--;
        return flag;
    }

    public void end(){};

    public void _start(){}

}
