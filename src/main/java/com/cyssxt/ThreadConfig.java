package com.cyssxt;

import com.cyssxt.task.AbstractTask;

import java.util.List;

public class ThreadConfig {
    private int taskNum;

    private List<AbstractTask> abstractTaskList;

    public List<AbstractTask> getAbstractTaskList() {
        return abstractTaskList;
    }

    public void setAbstractTaskList(List<AbstractTask> abstractTaskList) {
        this.abstractTaskList = abstractTaskList;
    }

    public int getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String toString() {
        return String.format("taskNum:%i",taskNum);
    }
}
