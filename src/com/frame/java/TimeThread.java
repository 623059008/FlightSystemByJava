package com.frame.java;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.flight.java.*;

/**
 * �����ǳ�������ڣ�����Login��¼���� �ǳ���Ҫ�������ǳ���ʼ����ʱ�Զ������ػ��߳������º����FlighStatus
 * 
 * @author RT
 * 
 */
public class TimeThread implements Runnable {
	/*
	 * �����̵߳�flag PS:��ʹ��Thread.stop������ֹ�߳�����Ϊstop��ǿ����ֹ�߳� �ᵼ��û������Ĳ�����ֹ
	 */
	public volatile boolean ThreadFlag = true;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		while (ThreadFlag) {
			// System.out.println("�����\��ing"+df.format(new Date()));
			DateTime NowDate = new DateTime(df.format(new Date()));
			Flight.AutoUpdateStatus(NowDate);
			// 1s*60*5=5min
			int time = 1000 * 60 * 1;
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/*
	 * public static void main(String[] args) {
	 * 
	 * 
	 * long ing=0; while(true) { ing++; //System.out.println("�������߳�"); try {
	 * Thread.sleep(100); } catch (InterruptedException e) { // TODO
	 * Auto-generated catch block e.printStackTrace();
	 * 
	 * } if((ing % 1000)==0) { //PS:��Ϊ��Sleep�����Ի����м�����
	 * updateBegin.ThreadFlag=false; System.out.println("�߳��Ѿ���ֹ"); break; } } }
	 */

}
