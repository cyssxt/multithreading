package com.cyssxt;

import com.cyssxt.manager.ThreadManager;
import com.cyssxt.task.AbstractTask;
import com.cyssxt.task.TaskShareMsg;
import com.cyssxt.thread.core.ThreadContext;
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
    public void test_stack(){
        TaskShareMsg taskShareMsg = new TaskShareMsg();
        taskShareMsg.push("1");
        taskShareMsg.push("2");
        taskShareMsg.push("3");
        taskShareMsg.push("4");
        taskShareMsg.push("5");
        assert("1".equals((String)taskShareMsg.pop()));
        assert("2".equals((String)taskShareMsg.pop()));
        assert("3".equals((String)taskShareMsg.pop()));
        assert("4".equals((String)taskShareMsg.pop()));
        assert("5".equals((String)taskShareMsg.pop()));
    }
    @Test
    public void test(){

        AbstractTask abstractTask = new AbstractTask(){
            private int run_time = 10;
            public void run(ThreadContext threadContext) {
                TaskShareMsg taskShareMsg = threadContext.getTaskShareMsg();
                taskShareMsg.push("11111");
                System.out.println("asdasd");
            }

            public boolean filter(){
                return run_time-->0;
            }
        };
        AbstractTask abstractTask1 = new AbstractTask(){
            public void run(ThreadContext threadContext) {
                System.out.println("asdasd1");
                TaskShareMsg taskShareMsg = threadContext.getTaskShareMsg();
                String msg = (String)taskShareMsg.pop();
                System.out.println(msg);
            }
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
