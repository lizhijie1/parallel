/**
 * ConCurrentSkinListMap.java	  V1.0   2018年12月17日 上午10:22:46
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day02.ThreadPool;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConCurrentSkinListMapDemo {
	public static void main(String[] args) {
		Map<Integer,Integer> map = new ConcurrentSkipListMap<Integer,Integer>();
	}
}
