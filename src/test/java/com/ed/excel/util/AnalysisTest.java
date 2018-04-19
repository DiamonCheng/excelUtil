package com.ed.excel.util;

import com.dc.excel.util.AnalysisExcelUtil;
import com.dc.excel.util.ExportExcelUtils;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Arrays;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/19.
 */
public class AnalysisTest {
    public static void main(String[] args) throws IOException {
        ExportExcelUtils.exportExcel(User.class, Arrays.asList(new User().setName("沈吹风"),new User().setName("\"沈吹风2\"").setPassword("232312312")), Files.newOutputStream(FileSystems.getDefault().getPath("E:/1.xls")));
        System.out.println(AnalysisExcelUtil.analysisExcel(Files.newInputStream(FileSystems.getDefault().getPath("E:/1.xls")),User.class));
    }
}
