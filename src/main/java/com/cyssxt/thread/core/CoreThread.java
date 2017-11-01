package com.cyssxt.thread.core;

import com.cyssxt.manager.ThreadManager;
import com.cyssxt.task.AbstractTask;

import java.util.List;

public class CoreThread extends Thread{

    private ThreadManager threadManager;
    private int index;

    public CoreThread(ThreadManager threadManager,int index){
        this.threadManager = threadManager;
        this.index = index;
    }

    @Override
    public void run() {
        List<AbstractTask> abstractTaskList  = threadManager.getThreadConfig().getAbstractTaskList();
        AbstractTask abstractTask = abstractTaskList.get(index);
        this.threadManager.start(abstractTask);
        abstractTask._start();
        while(abstractTask.filter()){
            abstractTask.run();
        }
        abstractTask.end();
        this.threadManager.end(abstractTask);
    }
}
