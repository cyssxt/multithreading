/**
 * author xutao
 */
package com.cyssxt.manager;
import com.cyssxt.ThreadConfig;
import com.cyssxt.task.AbstractTask;
import com.cyssxt.thread.core.CoreThread;
import com.cyssxt.thread.core.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {
    private final Logger logger = LoggerFactory.getLogger(ThreadManager.class);
    private ThreadContext threadContext = null;

    /**
     * init ThreadManager
     * @param taskNum multi-thread run max num
     */
    private ThreadManager(int taskNum){
        ThreadConfig threadConfig = new ThreadConfig() ;
        threadConfig.setTaskNum(taskNum);
        List<AbstractTask> tmpAbstractTaskList = new ArrayList<AbstractTask>();
        threadConfig.setAbstractTaskList(tmpAbstractTaskList);
        threadContext = new ThreadContext(threadConfig);
    }
    public static ThreadManager getInstance(int taskNum, List<AbstractTask> abstractTaskList){
        ThreadManager threadManager = new ThreadManager(taskNum);
        threadManager.init(abstractTaskList);
        return threadManager;
    }

    /**
     * 获取实例
     * @param taskNum
     * @param abstractTask
     * @return
     */
    public static ThreadManager getInstance(int taskNum, AbstractTask abstractTask){
        List<AbstractTask> abstractTaskList = new ArrayList<AbstractTask>();
        abstractTaskList.add(abstractTask);
        return getInstance(taskNum,abstractTaskList);
    }

    /**
     * 初始化context
     * @param abstractTaskList
     */
    protected void init(List<AbstractTask> abstractTaskList){
        ThreadConfig threadConfig = threadContext.getThreadConfig();
        logger.info("init={}",threadConfig.getTaskNum());
        for(int i=0;i<abstractTaskList.size();i++){
            threadContext.appendTask(abstractTaskList.get(i));
        }
    }

    /**
     * 启动线程池
     */
    public void start(){
        threadContext.start();
    }



}
