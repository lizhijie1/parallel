/**
 * ReadWriterLockDemo.java	  V1.0   2018��12��15�� ����8:26:58
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day02;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 
 * ������������д��
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */
public class ReadWriterLockDemo {
	//������
	private static Lock lock = new ReentrantLock();
	
	private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	//����
	private static Lock readLock = readWriteLock.readLock();
	
	//д��
	private static Lock writeLock = readWriteLock.writeLock();
	
	private int value;
	
	public Object handleRead(Lock lock) throws InterruptedException{
		try {
			lock.lock();	//ģ�������
			Thread.sleep(1000); //�������ĺ���Խ�࣬��д�������ƾ�Խ����
			return value;
		} finally {
			// TODO: handle finally clause
			lock.unlock();
		}
	} 
	
	public void handleWrite(Lock lock,int index) throws InterruptedException{
		try {
			lock.lock();
			Thread.sleep(1000);//ģ��д����
			value = index;
		} finally {
			// TODO: handle finally clause
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final ReadWriterLockDemo demo = new ReadWriterLockDemo();
		Runnable readRunnable = new Runnable(){
			@Override
			public void run() {
				try {
					//demo.handleRead(readLock);
					demo.handleRead(lock);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
		};
		
		Runnable writeRunnable = new Runnable(){
			@Override
			public void run() {
				try {
					//demo.handleWrite(writeLock, new Random().nextInt());
					demo.handleWrite(lock, new Random().nextInt());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		};
		
		for (int i = 0; i < 18; i++) {
			new Thread(readRunnable).start();
		}
		
		for (int i = 18; i < 20; i++) {
			new Thread(writeRunnable).start();
		}
	}
}
