package database.utils;

public class InitThread extends Thread{
    @Override
    public void run() {
        BUGUtils.controller.init();
    }
}
