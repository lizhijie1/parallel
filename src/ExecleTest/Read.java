/**
 * Read.java	  V1.0   2018��12��27�� ����3:24:19
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ExecleTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * �������������������˵�
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */
public class Read {
	public static void main(String[] args) {
		//��Ҫ������Excel�ļ�
		///javaTest1/src/1.�����˵� - ����.xls
		String fileNameSyn = "src/1.�����˵�.xlsx";
		File file=new  File(fileNameSyn);
		Workbook wb = null;
		try {
			FileInputStream fs=FileUtils.openInputStream(file);
			if(fileNameSyn.endsWith(".xls")){
				wb = new HSSFWorkbook(fs);
			}else if(fileNameSyn.endsWith(".xlsx")){
				wb = new XSSFWorkbook(fs);
			}
			System.out.println("++++++++++++");
			Sheet sheet = wb.getSheetAt(0);
	    	Iterator<Row> rowList = sheet.iterator() ;
	    	System.out.println(rowList.toString());
	    	List<String> nameList = new ArrayList<String>();
	    	List<Map<String,Object>> obj = new ArrayList<Map<String,Object>>();
			for (int i = 0; rowList.hasNext(); i++){
				Row row = rowList.next() ;
				if(row != null){
					boolean flag = false;
					Map<String,Object> map = null;
					for (int j = 0; j < row.getLastCellNum(); j++) {
						Cell cell = row.getCell(j);
						if(cell == null){
							continue;
						}
						if(i==0){
							nameList.add(cell.toString());
						}
						if(!flag){
							 map = new HashMap<String,Object>();
							 flag = true;
						}else{
							map.put(nameList.get(j),cell.toString());
						}
					}
					obj.add(map);
				}else{
					continue;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
