package com.cyssxt;

import com.cyssxt.manager.ThreadManager;
import com.cyssxt.task.AbstractTask;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * author:cyssxt
 * date:2017/11/1
 * $comment$
 **/
public class test {



    @Test
    public void test(){

        AbstractTask abstractTask = new AbstractTask(){
            private int run_time = 10;
            public void run() {
                System.out.println("asdasd");
            }

            public boolean filter(){
                return run_time-->0;
            }
        };
        AbstractTask abstractTask1 = new AbstractTask(){
            public void run() {
                System.out.println("asdasd1");
            }

//            public boolean filter(){
//                return true;
//            }
        };
        List<AbstractTask> list = new ArrayList<AbstractTask>();
        list.add(abstractTask);
        list.add(abstractTask1);
        ThreadManager threadManager = ThreadManager.getInstance(1,list);
        threadManager.start();
    }

    public static void main(String[] args) {
        test test1 = new test();
        test1.test();
    }

}
