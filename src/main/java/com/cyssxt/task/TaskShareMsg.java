package com.cyssxt.task;

import java.util.HashMap;
import java.util.Map;

/**
 * author:cyssxt
 * date:2017/11/1
 * multi-thread share msg
 **/
public class TaskShareMsg {

    public final static Map<String,Object> map = new HashMap<String, Object>();

    public class LinkStack{
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
            tempStack.next = bottom;
        }

        public Object pop(){
            Stack temp = top;
            top = temp.next;
            return temp;
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
    public static void put(String key,String value){
        synchronized(map){
            map.put(key,value);
        }
    }

    /**
     *
     * @param key
     * @return get value by key
     */
    public static Object get(String key){
        synchronized(map) {
            return map.get(key);
        }
    }

    /**
     * clear info in map
     */
    public static void clear(){
        map.clear();
    }
}
