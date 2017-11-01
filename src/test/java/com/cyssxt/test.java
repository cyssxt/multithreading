package com.cyssxt;

import com.cyssxt.manager.ThreadManager;
import com.cyssxt.task.AbstractTask;
import org.junit.Test;

/**
 * author:cyssxt
 * date:2017/11/1
 * $comment$
 **/
public class test {



//    @Test
    public static void test(){

        AbstractTask abstractTask = new AbstractTask(){



            public void run() {
                System.out.println("asdasd");
            }

//            public boolean filter(){
//                return true;
//            }

        };
        ThreadManager threadManager = ThreadManager.getIntance(3,abstractTask);
        threadManager.start();

    }

    public static void main(String[] args) {
        test();
    }

}
