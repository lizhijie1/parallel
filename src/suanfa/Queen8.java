/**
 * Queen8.java	  V1.0   2018��11��2�� ����9:58:09
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package suanfa;

public class Queen8 {
	//�ʺ�����;
	static final int MAX_NUM = 8;
	//��ά������Ϊ����
	//��һ��ά�ȴ�������꣬�ڶ���ά�ȴ���������
	int chessBord[][] = new int[MAX_NUM][MAX_NUM];
	
	//�������Ƿ���Ϲ���
	boolean check(int x,int y){
		for(int i=0;i<y;i++){
			//�������
			if(chessBord[x][i]==1){
				return false;
			}
			//������б��
			if(x-1-i>=0 && chessBord[x-1-i][y-1-i] ==1){
				return false;
			}
			//����Ҳ�б��
			if(x+1+i<MAX_NUM && chessBord[x+1+i][y-1-i] ==1){
				return false;
			}
		}
		return true;
	}
	
	//�ݹ����
	boolean settleQueen(int y){
		//��������8��˵���Ѿ��ҳ���
		if(y == MAX_NUM){
			return true;
		}
		//������ǰ�У���һ������֤
		for (int i = 0; i < MAX_NUM; i++) {
			//Ϊ��ǰ�����㣬�����ڻ��ݵ�ʱ�����������
			for(int x=0;x<MAX_NUM;x++){
				chessBord[x][y] = 0;
			}
			//����Ƿ���Ϲ���������ϣ�����Ԫ��ֵ����һ���ݹ�
			if(check(i,y)){
				chessBord[i][y] = 1;
				//�ݹ��������true,˵���²����ҵ��ⷨ���������ѭ��
				if(settleQueen(y+1)){
					return true;
				}
			}
		}
		return false;
	}
	
	//��ӡ���̵�ǰֵ
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
