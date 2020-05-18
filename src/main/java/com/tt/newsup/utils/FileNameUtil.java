package com.tt.newsup.utils;

/**
 * @author ：tt
 * @date ：Created in 2020/4/28 3:37 下午
 * @description：文件名生成工具类
 * @modified By：
 * @version:
 */
public class FileNameUtil {
    /**
     * 获取文件后缀
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }
    /**
     * 生成新的文件名
     * @param fileOriginName 源文件名
     * @return
     */
    public static String getFileName(String fileOriginName){
        return UUIDUtils.getUUID() + FileNameUtil.getSuffix(fileOriginName);
    }
}
