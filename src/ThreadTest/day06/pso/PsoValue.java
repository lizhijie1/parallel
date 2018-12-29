/**
 * PsoValue4.java	  V1.0   2018��12��24�� ����9:04:43
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.pso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PsoValue {
	//����ֵ
	final double value;
	
	final List<Double> x;
	
	
	public PsoValue(double v, List<Double> x) {
		this.value = v;
		List<Double> b = new ArrayList<Double>(5);
		b.addAll(x);
		this.x = Collections.unmodifiableList(b);
	}
	public double getValue() {
		return value;
	}
	public List<Double> getX() {
		return x;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("value:").append(value).append("\n").append(x.toString());
		return sb.toString();
	}
	
}
