/**
 * MD5.java	  V1.0   2018年11月12日 下午3:50:58
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package suanfa;

public class MD5 {
	/**
	 * MD5:	是信息摘要的一种实现，他可以从任意长度的明文字符串生成128位的哈希值
	 * 	
	 * MD5算法底层原理:
	 * 		简单概括起来，MD5算法的过程分为四步:处理原文，设置初始值、循环加工，
	 * 		拼接结果。
	 * 
	 * 		第一步:处理原文:
	 * 			首先，我们计算出原文长度（bit）对512求余的结果，如果不等于448，就
	 * 			需要填充原文使得原文对512求余的结果等于448。填充的方法是第一位填充1，
	 * 			其余位填充0。填充完后，信息的长度就是512*N+448。之后，用剩余的位置
	 * 			（512-448=64位）记录原文的真正长度，把长度的二进制补在最后。这样处
	 * 			理后的信息长度就是512*（N+1）
	 * 
	 * 		第二步：设置初始值
	 * 			MD5的哈希结果长度为128位，按照每32位分成一组功4组。这四组结果是有4个
	 * 			初始值A、B、C、D经过不断的演变得到。MD5的官方实现中,A,B,C,D的初始值如下：
	 * 			A=0x0123456
	 * 			B=0x89ABCDEF
	 * 			C=0xFEDCBA98
	 * 			D=0x76543210
	 * 
	 * 		第三部：循环加工
	 * 
	 * 			这一步是最复杂的一步，
	 * 
	 * 			
	 */
}
