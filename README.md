# multithread
a simple multi-thread utils
# how to use it
<pre><code>AbstractTask abstractTask = new AbstractTask(){
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
threadManager.start();</code></pre>
