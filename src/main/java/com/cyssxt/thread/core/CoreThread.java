package com.cyssxt.thread.core;

import com.cyssxt.manager.ThreadManager;
import com.cyssxt.task.AbstractTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.List;

public class CoreThread extends Thread{

    private final Logger logger = LoggerFactory.getLogger(CoreThread.class);
    private ThreadManager threadManager;
    private int index;

    public CoreThread(ThreadManager threadManager,int index){
        logger.info("CoreThread=>init:index:{}",index);
        this.threadManager = threadManager;
        this.index = index;
    }

    @Override
    public void run() {
        logger.info("CoreThread=>run:index:{},date={}",index,new Date());
        List<AbstractTask> abstractTaskList  = threadManager.getThreadConfig().getAbstractTaskList();
        AbstractTask abstractTask = abstractTaskList.get(index);
        this.threadManager.start(abstractTask);
        abstractTask._start();
        while(abstractTask.filter()){
            abstractTask.run();
        }
        logger.info("CoreThread=>run:end:{},date={}",index,new Date());
        abstractTask.end();
        this.threadManager.end(abstractTask);
    }
}
