/**
 * author xutao
 */
package com.cyssxt.manager;
import com.cyssxt.ThreadConfig;
import com.cyssxt.task.AbstractTask;
import com.cyssxt.thread.core.CoreThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {
    private final Logger logger = LoggerFactory.getLogger(ThreadManager.class);
    private int currentIndex = -1;
    private ThreadConfig threadConfig;
    private ArrayList<CoreThread> coreThreadList  = new ArrayList<CoreThread>();
    private ThreadManager(int taskNum, List<AbstractTask> abstractTaskList){
        ThreadConfig threadConfig = new ThreadConfig() ;
        threadConfig.setTaskNum(taskNum);
        List<AbstractTask> tmpAbstractTaskList = new ArrayList<AbstractTask>();
//        tmpAbstractTaskList.addAll(abstractTaskList);
        threadConfig.setAbstractTaskList(tmpAbstractTaskList);
        this.threadConfig = threadConfig;
    }
    public static ThreadManager getIntance(int taskNum, List<AbstractTask> abstractTaskList){
        ThreadManager threadManager = new ThreadManager(taskNum,abstractTaskList);
        threadManager.init(abstractTaskList);
        return threadManager;
    }
    public static ThreadManager getIntance(int taskNum, AbstractTask abstractTask){
        List<AbstractTask> abstractTaskList = new ArrayList<AbstractTask>();
        abstractTaskList.add(abstractTask);
        return getIntance(taskNum,abstractTaskList);
    }

    protected void init(List<AbstractTask> abstractTaskList){
        ThreadConfig threadConfig = this.getThreadConfig();
        logger.info("init={}",threadConfig.getTaskNum());
        for(int i=0;i<abstractTaskList.size();i++){
            this.appendTask(abstractTaskList.get(i));
        }
    }

    public void appendTask(AbstractTask abstractTask){
        ThreadConfig threadConfig = this.getThreadConfig();
        List<AbstractTask> abstractTaskList = threadConfig.getAbstractTaskList();
        abstractTaskList.add(abstractTask);
        coreThreadList.add(new CoreThread(this,abstractTaskList.size()-1));
    }

    public void start(){
        int taskNum = this.getThreadConfig().getTaskNum();
        logger.info("start:multi thread num:{},total task num:{}",taskNum,this.coreThreadList.size());
        for(int i=0;i<taskNum && i<this.coreThreadList.size();i++){
            this.coreThreadList.get(i).start();
            this.currentIndex = i;
        }
    }

    public ThreadConfig getThreadConfig() {
        return threadConfig;
    }

    public void end(AbstractTask abstractTask) {
        logger.info("end:{}",abstractTask);
        if(this.currentIndex<this.coreThreadList.size()-1){
            this.coreThreadList.get(++this.currentIndex).start();
        }
    }

    public void start(AbstractTask abstractTask) {
    }
}
