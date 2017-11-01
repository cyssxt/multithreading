# multithread
多线程处理
# how to use it
AbstractTask abstractTask = new AbstractTask(){
    //just to do
    public void run() {
        System.out.println("asdasd");
    }
    // how to run，if return ture,it will be run forever
    public boolean filter(){
        return true;
    }
};
ThreadManager threadManager = ThreadManager.getIntance(3,abstractTask);
//start thread list
threadManager.start();
