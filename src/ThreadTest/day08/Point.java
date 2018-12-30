package day01;

import java.util.concurrent.locks.StampedLock;

/**
 * stampLock--读写锁的改进
 * StampedLock使用实例
 */
public class Point {
    private double x,y;
    private final StampedLock sl =new StampedLock();
    void move(double deltaX,double deltaY){
        //这是一个排他锁
        //stamp为时间戳
        long stamp = sl.writeLock();
        try {
            x+=deltaX;
            y+=deltaY;
        } finally {
            sl.unlockWrite(stamp);
        }
    }
    
    double distanceFromOrigin(){
        //tryOptimisticRead尝试一次乐观读，会返回邮戳整数stamp,这个stamp就可以作为这一次锁获取的凭证
        long stamp = sl.tryOptimisticRead();
        double currentX = x,currentY = y;
        if(!sl.validate(stamp)){
            stamp = sl.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally{
                sl.unlockRead(stamp);
            }
        }
        return  Math.sqrt(currentX*currentX+currentY*currentY);
    }
}
