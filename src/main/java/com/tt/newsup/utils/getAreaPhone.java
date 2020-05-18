package com.tt.newsup.utils;

import com.tt.newsup.enumutils.areaPhone;
import com.tt.newsup.enumutils.buildPhone;

public class getAreaPhone {

    public static String getPhone(String s){
        String phoneMessage = null;
        String name = null;
        String phone = null;

        if(s.contains("金牛")){
            name = areaPhone.JINNIU.getName();
            phone = areaPhone.JINNIU.getPhone();

        }else if(s.contains("青羊")){
            name = areaPhone.QINGYANG.getName();
            phone = areaPhone.QINGYANG.getPhone();
        }else if(s.contains("武侯")){
            name = areaPhone.WUHOU.getName();
            phone = areaPhone.WUHOU.getPhone();
        }else if(s.contains("高新分")){
            name = areaPhone.GAOXINNAN.getName();
            phone = areaPhone.GAOXINNAN.getPhone();
        }else if(s.contains("高新西")){
            name = areaPhone.GAOXINXI.getName();
            phone = areaPhone.GAOXINXI.getPhone();

        }else if(s.contains("锦江")){
            name = areaPhone.JINJIANG.getName();
            phone = areaPhone.JINJIANG.getPhone();
        }else if(s.contains("彭州")){
            name = areaPhone.PENGZHOU.getName();
            phone = areaPhone.PENGZHOU.getPhone();
        }else if(s.contains("新都")){
            name = areaPhone.XINDU.getName();
            phone = areaPhone.XINDU.getPhone();
        }else if(s.contains("金堂")){
            name = areaPhone.JINTANG.getName();
            phone = areaPhone.JINTANG.getPhone();
        }else if (s.contains("简阳")){
            name = areaPhone.JIANYANG.getName();
            phone = areaPhone.JIANYANG.getPhone();
        }else if (s.contains("青白江")){
            name = areaPhone.QINGBAIJIANG.getName();
            phone = areaPhone.QINGBAIJIANG.getPhone();
        }else if(s.contains("成华")){
            name = areaPhone.CHENGHUA.getName();
            phone = areaPhone.CHENGHUA.getPhone();
        }else if(s.contains("浦江")){
            name = areaPhone.PUJIANG.getName();
            phone = areaPhone.PUJIANG.getPhone();
        }else if(s.contains("大邑")){
            name = areaPhone.DAYI.getName();
            phone = areaPhone.DAYI.getPhone();
        }else if(s.contains("邛崃")){
            name = areaPhone.QIONGLAI.getName();
            phone = areaPhone.QIONGLAI.getPhone();
        }else if (s.contains("都江堰")){
            name = areaPhone.DUJIANGYAN.getName();
            phone = areaPhone.DUJIANGYAN.getPhone();
        }else if(s.contains("温江")){
            name = areaPhone.WENJIANG.getName();
            phone = areaPhone.WENJIANG.getPhone();
        }else if (s.contains("崇州")){
            name = areaPhone.CHONGZHOU.getName();
            phone = areaPhone.CHONGZHOU.getPhone();
        }else if(s.contains("郫")){
            name = areaPhone.PIDU.getName();
            phone = areaPhone.PIDU.getPhone();
        }else {
            name = null;
            phone = null;
        }

        phoneMessage = "环节联系人:"+ name+",联系电话:"+phone;
        return phoneMessage;
    }


    public static String getBuildPhone(String s){
        String phoneMessage = null;
        String name = null;
        String phone = null;
        String department = null;

        if(s.contains("金牛")){
            name = buildPhone.JINNIU.getName();
            phone = buildPhone.JINNIU.getPhone();
            department = buildPhone.JINNIU.getDepartment();
        }else if(s.contains("青羊")){
            name = buildPhone.QINGYANG.getName();
            phone = buildPhone.QINGYANG.getPhone();
            department = buildPhone.QINGYANG.getDepartment();
        }else if(s.contains("武侯")){
            name = buildPhone.WUHOU.getName();
            phone = buildPhone.WUHOU.getPhone();
            department = buildPhone.WUHOU.getDepartment();
        }else if(s.contains("高新分")){
            name = buildPhone.GAOXINNAN.getName();
            phone = buildPhone.GAOXINNAN.getPhone();
            department = buildPhone.GAOXINNAN.getDepartment();
        }else if(s.contains("高新西")){
            name = buildPhone.GAOXINXI.getName();
            phone = buildPhone.GAOXINXI.getPhone();
            department = buildPhone.GAOXINXI.getDepartment();

        }else if(s.contains("锦江")){
            name = buildPhone.JINJIANG.getName();
            phone = buildPhone.JINJIANG.getPhone();
            department = buildPhone.JINJIANG.getDepartment();
        }else if(s.contains("彭州")){
            name = buildPhone.PENGZHOU.getName();
            phone = buildPhone.PENGZHOU.getPhone();
            department = buildPhone.PENGZHOU.getDepartment();

        }else if(s.contains("新都")){
            name = buildPhone.XINDU.getName();
            phone = buildPhone.XINDU.getPhone();
            department = buildPhone.XINDU.getDepartment();

        }else if(s.contains("金堂")){
            name = buildPhone.JINTANG.getName();
            phone = buildPhone.JINTANG.getPhone();
            department = buildPhone.XINDU.getDepartment();


        }else if (s.contains("简阳")){
            name = buildPhone.JIANYANG.getName();
            phone = buildPhone.JIANYANG.getPhone();
            department = buildPhone.JIANYANG.getDepartment();


        }else if (s.contains("青白江")){
            name = buildPhone.QINGBAIJIANG.getName();
            phone = buildPhone.QINGBAIJIANG.getPhone();
            department = buildPhone.QINGBAIJIANG.getDepartment();
        }else if(s.contains("成华")){
            name = buildPhone.CHENGHUA.getName();
            phone = buildPhone.CHENGHUA.getPhone();
            department = buildPhone.CHENGHUA.getDepartment();

        }else if(s.contains("浦江")){
            name = buildPhone.PUJIANG.getName();
            phone = buildPhone.PUJIANG.getPhone();
            department = buildPhone.PUJIANG.getDepartment();
        }else if(s.contains("大邑")){
            name = buildPhone.DAYI.getName();
            phone = buildPhone.DAYI.getPhone();
            department = buildPhone.DAYI.getDepartment();

        }else if(s.contains("邛崃")){
            name = buildPhone.QIONGLAI.getName();
            phone = buildPhone.QIONGLAI.getPhone();
            department = buildPhone.QIONGLAI.getDepartment();

        }else if (s.contains("都江堰")){
            name = buildPhone.DUJIANGYAN.getName();
            phone = buildPhone.DUJIANGYAN.getPhone();
            department = buildPhone.DUJIANGYAN.getDepartment();

        }else if(s.contains("温江")){
            name = buildPhone.WENJIANG.getName();
            phone = buildPhone.WENJIANG.getPhone();
            department = buildPhone.WENJIANG.getDepartment();

        }else if (s.contains("崇州")){
            name = buildPhone.CHONGZHOU.getName();
            phone = buildPhone.CHONGZHOU.getPhone();
            department = buildPhone.CHONGZHOU.getDepartment();
        }else if(s.contains("郫")){
            name = buildPhone.PIDU.getName();
            phone = buildPhone.PIDU.getPhone();
            department = buildPhone.PIDU.getDepartment();

        }else if(s.contains("新津")){
            name = buildPhone.XINJING.getName();
            phone = buildPhone.XINJING.getPhone();
            department = buildPhone.XINJING.getDepartment();
        }else{
            name = null;
            phone = null;
        }

        phoneMessage = "环节联系人:"+ name+",联系电话:"+phone+",施工单位:"+department;
        return phoneMessage;
    }
}
