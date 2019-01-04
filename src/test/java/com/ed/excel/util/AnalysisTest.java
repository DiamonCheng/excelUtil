package com.ed.excel.util;

import com.dc.excel.util.AnalysisExcelUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/19.
 */
public class AnalysisTest {
//    public static void main(String[] args) throws IOException {
//        ExportExcelUtils.exportExcel(User.class, Arrays.asList(new User().setName("沈吹风"),new User().setName("\"沈吹风2\"").setPassword("232312312")), Files.newOutputStream(FileSystems.getDefault().getPath("E:/1.xls")));
//        System.out.println(AnalysisExcelUtil.analysisExcel(Files.newInputStream(FileSystems.getDefault().getPath("E:/1.xls")),User.class));
//    }
    
    public static void main(String[] args) throws IOException, InvalidFormatException {
//        try(InputStream in=AnalysisTest.class.getResourceAsStream("/昆山农商行_接口定义_系统编号_系统名称.xlsx")){
//            TreeMap<String,String[][]> treemap=AnalysisExcelUtil.analysisExcel(in);
//            for (Map.Entry<String,String[][]> entry:treemap.entrySet()) {
//                System.out.println("------      "+entry.getKey()+"      -----");
//                String[][] table=entry.getValue();
//                for (int i = 0; i < table.length; i++) {
//                    System.out.printf("%-10d", i);
//                    for (int j = 0; j < table[i].length; j++) {
//                        System.out.printf("%d%-10s", j, table[i][j]);
//                    }
//                    System.out.println();
//                }
//            }
//        }
        try(InputStream in=AnalysisTest.class.getResourceAsStream("/昆山农商行_系统清单.xls")){
            List<SystemTransportDO> list=AnalysisExcelUtil.analysisExcel(in,SystemTransportDO.class,2);
            System.out.println(list);
        }
    }
}
