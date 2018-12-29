/**
 * Test2.java	  V1.0   2018年12月11日 下午4:41:21
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package annotation;

import java.util.HashMap;
import java.util.Map;

public class Test2 {
	static void setName(Map map){
		map.put("name","tomcat");
	}
	public static void main(String[] args) {
		Map map = new HashMap<String,String>();
		map.put("name", "weblogic");
		setName(map);
		System.out.println(map.get("name"));
	}
}
