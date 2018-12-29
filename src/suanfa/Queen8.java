/**
 * Queen8.java	  V1.0   2018年11月2日 上午9:58:09
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package suanfa;

public class Queen8 {
	//皇后数量;
	static final int MAX_NUM = 8;
	//二维数组作为棋盘
	//第一个维度代表横坐标，第二个维度代表纵坐标
	int chessBord[][] = new int[MAX_NUM][MAX_NUM];
	
	//检查落点是否符合规则
	boolean check(int x,int y){
		for(int i=0;i<y;i++){
			//检查纵向
			if(chessBord[x][i]==1){
				return false;
			}
			//检查左侧斜向
			if(x-1-i>=0 && chessBord[x-1-i][y-1-i] ==1){
				return false;
			}
			//检查右侧斜向
			if(x+1+i<MAX_NUM && chessBord[x+1+i][y-1-i] ==1){
				return false;
			}
		}
		return true;
	}
	
	//递归回溯
	boolean settleQueen(int y){
		//行数超过8，说明已经找出答案
		if(y == MAX_NUM){
			return true;
		}
		//遍历当前行，逐一格子验证
		for (int i = 0; i < MAX_NUM; i++) {
			//为当前行清零，以免在回溯的时候出现脏数据
			for(int x=0;x<MAX_NUM;x++){
				chessBord[x][y] = 0;
			}
			//检查是否符合规则，如果符合，更改元素值并进一步递归
			if(check(i,y)){
				chessBord[i][y] = 1;
				//递归如果返回true,说明下层已找到解法，无需继续循环
				if(settleQueen(y+1)){
					return true;
				}
			}
		}
		return false;
	}
	
	//打印棋盘当前值
	void printChessBord(){
		for(int j=0;j<MAX_NUM;j++){
			for(int i=0;i<MAX_NUM;i++){
				System.out.print(chessBord[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Queen8 queen8 = new Queen8();
		queen8.settleQueen(0);
		queen8.printChessBord();
	}
}
