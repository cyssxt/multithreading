/**
 * author:cyssxt
 */
package com.cyssxt.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * author:cyssxt
 * date:2017/11/1
 * multi-thread share msg
 **/
public class TaskShareMsg {
    private final static Logger logger = LoggerFactory.getLogger(TaskShareMsg.class);
    public  static Integer FIRST_ID = 1;
    public  Map<String,Object> map = new HashMap<String, Object>();
    public  LinkStack linkStack = new LinkStack();
    public int id = 0;

    public TaskShareMsg() {
        synchronized (FIRST_ID){
            this.id = ++FIRST_ID;
        }
        logger.info("taskShareMsg:init[{}]",this.id);
    }

    /**
     * 栈压入
     * @param object
     */
    public void push(Object object){
        logger.info("taskShareMsg:push[{}]:{}",this.id,object);
        synchronized (linkStack){
            linkStack.push(object);
        }
    }

    /**
     * 栈压出
     * @return
     */
    public Object pop(){
        logger.info("taskShareMsg:pop[{}]",this.id);
        synchronized (linkStack){
            return linkStack.pop();
        }
    }
    public static class LinkStack{
        public class Stack{
            private Stack pre;
            private Stack next;
            private Object object;
            public Stack(Object object,Stack pre,Stack next){
                this.object = object;
                this.pre = pre;
                this.next = next;
            }

            public Stack getPre() {
                return pre;
            }

            public void setPre(Stack pre) {
                this.pre = pre;
            }

            public Stack getNext() {
                return next;
            }

            public void setNext(Stack next) {
                this.next = next;
            }

            public Object getObject() {
                return object;
            }

            public void setObject(Object object) {
                this.object = object;
            }
        }
        private Stack top = null;
        private Stack bottom = null;

        public void push(Object object){
            Stack tempStack = bottom;
            bottom = new Stack(object,bottom,null);
            if(top==null){
                top = bottom;
            }
            if(tempStack!=null){
                tempStack.next = bottom;
            }
        }

        public Object pop(){
            Stack temp = top;
            top = temp.next;
            return temp!=null?temp.object:null;
        }

        public boolean isEmpty(){
            return top==null;
        }
    }

    /**
     *
     * @param key param name
     * @param value param value
     * save info by key-value format
     */
    public void put(String key,String value){
        logger.info("taskShareMsg:put[{}],key:{},value:{}",this.id,key,value);
        synchronized(map){
            map.put(key,value);
        }
    }

    /**
     *
     * @param key
     * @return get value by key
     */
    public Object get(String key){
        logger.info("taskShareMsg:get[{}],key:{}",this.id,key);
        synchronized(map) {
            return map.get(key);
        }
    }

    /**
     * clear info in map
     */
    public void clear(){
        logger.info("taskShareMsg:clear[{}]",this.id);
        map.clear();
    }
}
