package com.dc.excel.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @author Defferson.Cheng
 *
 */
public class ExportExcelUtils {
    public interface Filter{
        public Object filter(Object obj, String name);
    }
    
    /**
     * 根据类导出表格
     * 具体详见 @see ExcelHead
     * 字段排序一般按照 父类字段定义顺序+子类字段定义数据来
     * @param targetClass 解析的类（通过注解配置sheet名称，列名）
     * @param list 需要导出的数据
     * @param outputStream 导出的流， 不会关闭， 需要手动flush 以及close
     */
    public static void exportExcel(Class<?> targetClass, List<?> list,OutputStream outputStream){
        ExcelHead excelhead=targetClass.getAnnotation(ExcelHead.class);
        String title ="sheet1";
        if (excelhead!=null){
            title=excelhead.value();
        }
        List<String> headers=new ArrayList<>();
        List<String> properties=new ArrayList<>();
        Field[] fields=targetClass.getDeclaredFields();
        for (Field field:fields){
            ExcelHead excelHead=field.getAnnotation(ExcelHead.class);
            if (excelHead!=null){
                headers.add(excelHead.value());
                properties.add(field.getName());
            }
        }
        exportExcel(title,headers.toArray(new String[headers.size()]),list,properties.toArray(new String[properties.size()]),outputStream,null);
    }
    
    
	/**
	 * 导出excel，将会把excel二进制写入输出流，并且输出流不会close，需要手动flush以及close
	 * @param title：excel文件名称
	 * @param headers：表头
	 * @param dataset：要保存到表格的对象集合
	 * @param properties：对象要保存的属性
	 * @param out：输出目的地
	 * @param filter 字典之类的过滤器
	 */
	public static void exportExcel(String title, String[] headers, List<?> dataset, String[] properties, OutputStream out, Filter filter) {
		if (filter==null){
		    filter=new Filter() {
                @Override
                public Object filter(Object obj, String name) {
                    return obj;
                }
            };
		}
		// 声明一个工作薄
		Workbook workbook = new HSSFWorkbook();
		//创建样式
		CellStyle generalCellStyle=createDataStyle(workbook);
		CellStyle headCellStyle=createHeaderStyle(workbook);
		// 生成一个表格
		Sheet sheet = workbook.createSheet(title);
		
		// 设置表格默认列宽度为20
		sheet.setDefaultColumnWidth((short) 20);
		
		// 产生表头
		Row row = sheet.createRow(0);
		
		//插入表头数据
        setHeadText(headers, headCellStyle, row);
        
        int index = 1;
		for(Object bean : dataset){
			//得到一个bean，则生成表格的一行
			row = sheet.createRow(index++);
			for(int i=0;i<properties.length;i++){
				//得到bean的属性值
				Object attrValue = null;
				try {
					attrValue = ReflectionUtils.getValueByName(bean,properties[i]);
				} catch (Exception e) {
					throw new RuntimeException("无法获取bean的属性值：" + properties[i],e);
				} 
				try {
					attrValue=filter.filter(attrValue, properties[i]);
				} catch (NullPointerException e) {
					attrValue="null";
				}
				//转成字符串
				String cellValue = "";
				if(attrValue instanceof Date){
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    //把日期转换后赋给cellValue
					cellValue = df.format(attrValue);
				}else{
					if(attrValue!=null){
						cellValue = attrValue.toString();
					}
				}
				
				Cell cell = row.createCell(i);
				cell.setCellStyle(generalCellStyle);
				cell.setCellValue(cellValue);
			}
		}
		try {
			workbook.write(out);
		} catch (IOException e) {	
			throw new RuntimeException(e);
		}
	}
    
    /**
     * /**
     * 导出excel，将会把excel二进制写入输出流，并且输出流不会close，需要手动flush以及close
     * @param title sheet 名称
     * @param headers 表头
     * @param dataset 数据集合 Map类型数据
     * @param properties 导出那些属性 即字段名 顺序按表头排好
     * @param out 输出流
     * @param filter 过滤器 可以为空。new出来
     */
	public static void exportExcelByMap(String title, String[] headers, Collection<? extends Map<String,?>> dataset,String[] properties, OutputStream out,Filter filter) {
		if (filter==null){
			filter=new Filter() {
				@Override
				public Object filter(Object obj, String name) {
					return obj;
				}
			};
		}
		// 声明一个工作薄
		Workbook workbook = new HSSFWorkbook();
		//创建样式
		CellStyle generalCellStyle=createDataStyle(workbook);
		CellStyle headCellStyle=createHeaderStyle(workbook);
		// 生成一个表格
		Sheet sheet = workbook.createSheet(title);
		
		// 设置表格默认列宽度为20
		sheet.setDefaultColumnWidth((short) 20);
		
		// 产生表头
		Row row = sheet.createRow(0);
		
		//插入表头数据
        setHeadText(headers, headCellStyle, row);
        
        int index = 1;
		for(Map<String,?> map : dataset){
			//得到一个bean，则生成表格的一行
			row = sheet.createRow(index++);
			for(int i=0;i<properties.length;i++){
				
				Object attrValue = null;
				attrValue=map.get(properties[i]);
				try {
					attrValue=filter.filter(attrValue, properties[i]);
				} catch (NullPointerException e) {
					attrValue="null";
				}
				//转成字符串
				String cellValue = "";
				if(attrValue instanceof Date){
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					//把日期转换后赋给cellValue
					cellValue = df.format(attrValue);
				}else{
					if(attrValue!=null){
						cellValue = attrValue.toString();
					}else{
						cellValue="null";
					}
				}
				Cell cell = row.createCell(i);
				cell.setCellStyle(generalCellStyle);
				cell.setCellValue(cellValue);
			}
		}
		try {
			workbook.write(out);
		} catch (IOException e) {	
			throw new RuntimeException(e);
		}
	}
    
    private static void setHeadText(String[] headers, CellStyle headCellStyle, Row row) {
        for (short i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            //设置样式
            cell.setCellStyle(headCellStyle);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
    }
    
    private static CellStyle createHeaderStyle(Workbook workbook){
		// 生成一个样式
		CellStyle style = workbook.createCellStyle();

		// 设置表头的样式
		//设置表头背景颜色
		style.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 生成表头的字体
		Font font = workbook.createFont();
		font.setFontName("宋体");
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.WHITE.index);
		// 把字体应用到当前的样式
		style.setFont(font);
		return style;
	}
	private static CellStyle createDataStyle(Workbook workbook){
		CellStyle cellStyle=null;
		// 生成数据行的样式
		cellStyle = workbook.createCellStyle();
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成数据行的字体
		Font font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		cellStyle.setFont(font);
		return cellStyle;
	}

}
