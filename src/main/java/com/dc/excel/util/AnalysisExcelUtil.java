package com.dc.excel.util;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * excel解析工具类 使用POI
 * @author Defferson.Cheng
 *
 */
public class AnalysisExcelUtil{
	/**
	 * 解析输入流的excel成为数组
	 * 注：
	 * 1.内容前后的包括回车空白字符会被去除
	 * 2.空行会被无视
	 * 3.每行列数不固定所以记住有可能下标超出
	 * 4.值不会出现null的现象空值会处理成为空字符串
	 * @param inputStream 输入流 会自动解析xls 或者 xlsx
	 * @param sheetName 要解析的sheet名称
	 * @return 返回表格 二维数组
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public static String[][] analysisExcel(InputStream inputStream,String sheetName) throws EncryptedDocumentException, InvalidFormatException, IOException{
		Workbook workBook = null;
		workBook=WorkbookFactory.create(inputStream);
		Sheet sheet = workBook.getSheet(sheetName);
		return analysisSheet(sheet);
	}
	/**
	 * 解析输入流的excel成为一堆二维数组
	 * 注：
	 * 1.内容前后的包括回车空白字符会被去除
	 * 2.空行会被无视
	 * 3.每行列数不固定所以记住有可能下标超出
	 * 4.值不会出现null的现象空值会处理成为空字符串
	 * @param inputStream excel 流输入， 可以解析 xls 和xlsx
	 * @return 返回以sheetName作为key的表格的有序集合
	 * @throws EncryptedDocumentException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public static TreeMap<String,String[][]> analysisExcel(InputStream inputStream) throws IOException, InvalidFormatException {
		TreeMap<String,String[][]> treeMap=new TreeMap<>();
		Workbook workBook;
		workBook=WorkbookFactory.create(inputStream);
		int sheetNum=workBook.getNumberOfSheets();
		for (int i=0;i<sheetNum;i++){
			Sheet sheet = workBook.getSheetAt(i);
			treeMap.put(sheet.getSheetName(),analysisSheet(sheet));
		}
		return treeMap;
	}
	private static String[][] analysisSheet(Sheet sheet){
		List<String[]> table=new LinkedList<String[]>();
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			String[] row=analysisRow(sheet.getRow(i));
			if (row!=null) {
				table.add(row);
			}
		}
		return table.toArray(new String[table.size()][]);
	}
	private static String[] analysisRow(Row row){
		if (row==null) {
			return null;
		}
		List<String> cellList=new LinkedList<String>();
		boolean isValidRow=false;
		for(int i=0;i<row.getLastCellNum();i++){
			Cell cell=row.getCell(i);
			if (cell==null) {
				cellList.add("");
			}
			else {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String value=cell.getStringCellValue();
				if (null!=value) {
					value=StrUtil.trimEx(value);
					cellList.add(value);
				}else{
					cellList.add("");
				}
				if (null!=value&&!"".equals(value)) {
				    isValidRow=true;
                }
			}
		}
		if (isValidRow) {
			return cellList.toArray(new String[cellList.size()]);
		}
		return null;
	}
	/**
	 *  根据 类上面的注解 解析excel， 其中sheetName为类 ExcelHead 注解配置，表头为 字段ExcelHead注解配置
	 *  默认第0行为表头
	 * @param inputStream 需要解析的excel
	 * @param targetClass 配置类
	 * @return targetClass类型的List
	 */
	public static <T> List<T> analysisExcel(InputStream inputStream,Class<T> targetClass){
		return analysisExcel(inputStream,targetClass,0);
	}
	
	/**
	 *  根据 类上面的注解 解析excel， 其中sheetName为类 ExcelHead 注解配置，表头为 字段ExcelHead注解配置
	 * @param inputStream 需要解析的excel
	 * @param targetClass 配置类
	 * @param headIndex 表头在第几行，  从0 开始数
	 * @return targetClass类型的List
	 */
	public static <T> List<T> analysisExcel(InputStream inputStream,Class<T> targetClass,int headIndex){
	    List<T> resultList=new ArrayList<>();
		ExcelHead excelhead=targetClass.getAnnotation(ExcelHead.class);
		String title ="sheet1";
		if (excelhead!=null){
			title=excelhead.value();
		}
		String[][] table;
		try {
			table=analysisExcel(inputStream,title);
		} catch (Exception e) {
			throw new IllegalStateException("Excel 解析失败",e);
		}
		if (table.length<headIndex+1){
			return resultList;
		}
		Map<String,Integer> headerToIndex=new HashMap<>(3);
		String[] header=table[headIndex];
		for (int i=0;i<header.length;i++){
			headerToIndex.put(header[i],i);
		}
		Map<Integer,Field> indexToProperties=new HashMap<>(3);
		Class cls=targetClass;
		while(!Object.class.equals(cls)){
			Field[] fields=cls.getDeclaredFields();
			for(Field field:fields){
				ExcelHead excelHead=field.getAnnotation(ExcelHead.class);
				if (excelHead==null){
					continue;
				}
				String head=excelHead.value();
				Integer index=headerToIndex.get(head);
				if (index==null){
					continue;
				}
				indexToProperties.put(index,field);
			}
			cls=cls.getSuperclass();
		}
		for (int rowIndex=headIndex+1;rowIndex<table.length;rowIndex++){
			String[] row=table[rowIndex];
			T target;
			try {
				target=targetClass.newInstance();
                resultList.add(target);
			} catch (Exception e) {
				throw new IllegalStateException(targetClass+" 没有一个默认无参构造函数",e);
			}
			for (int colIndex=0;colIndex<row.length;colIndex++){
				Field field=indexToProperties.get(colIndex);
				if (field==null){
					continue;
				}
				String value=row[colIndex];
				if (value==null){continue;}
				Object valueTranslated=null;
				try {
					if (String.class.equals(value.getClass())){
                        valueTranslated=value;
                    }else{
						if (value.length()==0){
                            valueTranslated=null;
						}else if(Integer.class.equals(field.getType())|| int.class.equals(field.getType())){
							valueTranslated=Integer.valueOf(value);
						}else if(Long.class.equals(field.getType())|| long.class.equals(field.getType())){
							valueTranslated=Long.valueOf(value);
						}else if(Short.class.equals(field.getType())|| short.class.equals(field.getType())){
							valueTranslated=Short.valueOf(value);
						}else if(Byte.class.equals(field.getType())|| byte.class.equals(field.getType())){
							valueTranslated=Byte.valueOf(value);
						}else if(Float.class.equals(field.getType())|| float.class.equals(field.getType())){
							valueTranslated=Float.valueOf(value);
						}else if(Double.class.equals(field.getType())|| double.class.equals(field.getType())){
							valueTranslated=Double.valueOf(value);
						}else if(Character.class.equals(field.getType())|| char.class.equals(field.getType())){
							valueTranslated=value.charAt(0);
						}else{
							throw new IllegalArgumentException("无法从字符串转换类型到"+targetClass+"."+field);
						}
					}
				} catch (Exception e) {
					throw new IllegalStateException(String.format("Excel 字段转换失败，格式出现问题，行：%d列：%d", rowIndex,colIndex),e);
				}
				if (valueTranslated!=null){
					ReflectionUtils.setValueByName(target,valueTranslated,field.getName());
				}
			}
			
		}
	    return resultList;
    }
	
    
    
    
    
    
    
    
    
    
    
    
    
    
	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {
		File file=new File("c:/Users/dcpho/Desktop/经典模式.xls");
		System.out.println(file.exists());
		System.out.println(file.getAbsolutePath());
		FileInputStream input=new FileInputStream(file);
		String[][] table=analysisExcel(input,"sheet1");
		for (int i = 0; i < table.length; i++) {
			System.out.printf("%-10d", i);
			for (int j = 0; j < table[i].length; j++) {
				System.out.printf("%d%-10s", j,table[i][j]);
			}
			System.out.println();
		}
	}
}