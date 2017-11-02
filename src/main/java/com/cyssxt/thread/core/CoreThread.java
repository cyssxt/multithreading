/**
 * author:cyssxt
 * date:2017-10-30
 */
package com.cyssxt.thread.core;

import com.cyssxt.task.AbstractTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.List;

public class CoreThread extends Thread{

    private final Logger logger = LoggerFactory.getLogger(CoreThread.class);
    private ThreadContext threadContext;
    private int index;

    public CoreThread(ThreadContext threadContext,int index){
        logger.info("CoreThread=>init:index:{}",index);
        this.threadContext = threadContext;
        this.index = index;
    }

    @Override
    public void run() {
        logger.info("CoreThread=>run:index:{},date={}",index,new Date());
        List<AbstractTask> abstractTaskList  = threadContext.getThreadConfig().getAbstractTaskList();
        AbstractTask abstractTask = abstractTaskList.get(index);
        threadContext.onStart(abstractTask);
        abstractTask.onStart();
        while(abstractTask.filter()){
            abstractTask.run(threadContext);
        }
        logger.info("CoreThread=>run:end:{},date={}",index,new Date());
        abstractTask.onEnd();
        threadContext.onEnd(abstractTask);
    }
}
