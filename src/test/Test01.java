/**
 * Test01.java	  V1.0   2018年5月9日 上午10:36:22
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Test01 {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(6);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(1);
		list.add(7);
		Collections.sort(list);//正序
		Collections.reverse(list);//反转
		System.out.println(list.toString());
	}
}
