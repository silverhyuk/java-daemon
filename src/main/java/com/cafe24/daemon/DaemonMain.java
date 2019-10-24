package com.cafe24.daemon;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;

public class DaemonMain implements Daemon,Runnable{

    private String status = "";
    private int increasingNumber = 0;
    private Thread thread = null;


    public void init(DaemonContext context) throws DaemonInitException, Exception {
        System.out.println("init.........");
        String[] args = context.getArguments();
        if(args != null){
            for(String arg: args){
                System.out.println(arg);
            }
        }
        status = "INITED";
        this.thread = new Thread(this);
        System.out.println("init OK");
        System.out.println();
    }

    public void start() throws Exception {
        System.out.println("status: " + status);
        System.out.println("start...");
        status = "STARTED";
        this.thread.start();
        System.out.println("start OK.");
        System.out.println();
    }

    public void stop() throws Exception {
        System.out.println("status: " + status);
        System.out.println("stop...");
        status = "STOPED";
        this.thread.join(10);
        System.out.println("stop OK.");
        System.out.println();
    }


    public void destroy() {
        System.out.println("status: " + status);
        System.out.println("destroy...");
        status = "DESTROIED";
        System.out.println("destroy OK.");
        System.out.println();
    }


    public void run() {
        while (true){
            System.out.println(increasingNumber);
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            if(increasingNumber > 1000){
                break;
            }
            increasingNumber++;
        }
    }
}
