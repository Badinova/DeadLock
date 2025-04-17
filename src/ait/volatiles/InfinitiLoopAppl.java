package ait.volatiles;

import ait.volatiles.task.InfiniteLoop;

public class InfinitiLoopAppl {
    public static void main(String[] args) throws InterruptedException {
        InfiniteLoop loop = new InfiniteLoop();
        Thread thread = new Thread(loop);
        thread.start();
        Thread.sleep(3000);
        loop.setFlag(false);
        System.out.println("flag = " + loop.isFlag());
        System.out.println(Thread.currentThread().getName() + " is finished");
    }
}
