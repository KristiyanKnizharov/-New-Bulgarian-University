package company.Models;

class ThreadPool implements Runnable {
    public void run() {
        var t1 = new Thread("First thread.");
        System.out.println("Running ");
        try {
            System.out.println("Thread: " + t1 + ", ");
            // Let the thread sleep for a while.
            Thread.sleep(50);
        }
        catch (Exception e) {
            System.out.println("Thread " +  t1 + " interrupted.");
        }
        System.out.println("Thread " +  t1 + " exiting.");
    }

    public void start() {
        var t2 = new Thread("Current thread.");
        System.out.println("Starting " + t2);
        if (t2 == null) {
            t2 = new Thread(this, String.valueOf(t2));
            t2.start();
        }
    }
}

//package company.Models;
//
//public class NewThread implements Runnable {
//
//    public NewThread(Runnable threadOb, String threadName){
//
//    }
//
//    Thread t;
//    NewThread(){
//        t = new Thread(this, "Second thread");
//        System.out.println("Child thread: " + t);
//        t.start(); // start the thread
//    }
//
//    public void start(){
//        run();
//    }
//
//    public void run() {
//
//    }
//}