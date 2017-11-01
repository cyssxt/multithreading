package com.cyssxt.thread.core;

import com.cyssxt.ThreadConfig;
import com.cyssxt.task.AbstractTask;
import com.cyssxt.task.TaskShareMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * author:cyssxt
 * date:2017/11/1
 * $comment$
 **/
public class ThreadContext {

    private final static Logger logger = LoggerFactory.getLogger(ThreadContext.class);

    /**
     * every time will init a queue for share between in different queue.
     */
    private TaskShareMsg taskShareMsg = new TaskShareMsg();

    private List<CoreThread> coreThreadList = new ArrayList<CoreThread>();

    private ThreadConfig threadConfig = null;
    private int currentIndex = -1;

    public ThreadContext(ThreadConfig threadConfig) {
        this.threadConfig = threadConfig;
    }

    public TaskShareMsg getTaskShareMsg() {
        return taskShareMsg;
    }

    public ThreadConfig getThreadConfig() {
        return threadConfig;
    }

    public void appendTask(AbstractTask abstractTask){
        ThreadConfig threadConfig = this.threadConfig;
        List<AbstractTask> abstractTaskList = threadConfig.getAbstractTaskList();
        abstractTaskList.add(abstractTask);
        coreThreadList.add(new CoreThread(this,threadConfig.getAbstractTaskList().size()-1));
    }

    /**
     * get thread list
     * @return
     */
    public List<CoreThread> getCoreThreadList() {
        return coreThreadList;
    }

    public void onEnd(AbstractTask abstractTask) {
        logger.info("end:{}",abstractTask);
        if(this.currentIndex<this.coreThreadList.size()-1){
            this.coreThreadList.get(++this.currentIndex).start();
        }
    }

    public void start(){
        int taskNum = this.getThreadConfig().getTaskNum();
        logger.info("start:multi thread num:{},total task num:{}",taskNum,this.coreThreadList.size());
        List<CoreThread> coreThreadList = this.getCoreThreadList();
        for(int i=0;i<taskNum && i<coreThreadList.size();i++){
            coreThreadList.get(i).start();
            this.currentIndex = i;
        }
    }

    public void runCoreThread(int index){
//        coreThread.start();
//        this.currentIndex = index;
    }

    public void onStart(AbstractTask abstractTask) {
    }
}
