package com.cyssxt.task;

import com.cyssxt.thread.core.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * author:cyssxt
 * date:${date}
 * $comment$
 **/
public abstract class AbstractTask {

    private final static Logger logger = LoggerFactory.getLogger(AbstractTask.class);

    private int one_time = 1;
    public abstract void run(ThreadContext threadContext);

    /**
     * you can override run without params,also you can override run with threadContext to get info from threadContext
     * @param threadContext
     */
//    public void run(ThreadContext threadContext){
//        TaskShareMsg taskQueue = threadContext.getTaskShareMsg();
//        logger.info("run=>task queue:{}",taskQueue);
//        run();
//    }

    /**
     *
     * @return false 表示执行终止，true表示继续执行
     */
    public boolean filter(){
        boolean flag = one_time>0;
        one_time--;
        return flag;
    }

    public void onEnd(){};

    public void onStart(){}

}
