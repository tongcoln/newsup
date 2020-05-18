package com.tt.newsup.enumutils;

public enum buildPhone {

    QINGYANG("张洪伟","15198223136","广东海格怡创科技有限公司"),
    WUHOU("王贵","18980555441","四川全盛通网络技术有限公司"),
    JINNIU("林颖","18628211997","四川中移通信技术工程有限公司"),
    GAOXINNAN("刘小兰","13980764414","浙江东冠通信技术股份有限公司"),
    GAOXINXI("唐小明","13547632299","四川汇源吉迅数码科技有限公司"),
    JINJIANG("邓鑫","17761265214","浙江科晓通信技术有限公司"),
    PENGZHOU("冷风彪","13688343420","四川中移通信技术工程有限公司"),
    XINDU("罗川雄","13550087289","四川中移通信技术工程有限公司"),
    JINTANG("邓培","18280181338","四川景云祥通信股份公司"),
    JIANYANG("吕勇","18681229621","中国通信建设第二工程局有限公司"),
    QINGBAIJIANG("邓培","18280181338","四川景云祥通信股份公司"),
    CHENGHUA("李汶亮","13628066300","四川景云祥通信股份公司"),
    PUJIANG("龚瑞","13880136257","浙江省邮电工程建设有限公司"),
    DAYI("周芸锋","15828329143","广东海格怡创科技有限公司"),
    QIONGLAI("严大猛","18227682945","湖南省通信建设有限公司"),
    DUJIANGYAN("邹波","15881113862","四川汇源吉迅数码科技有限公司"),
    WENJIANG("罗康新","17799119979","四川全盛通网络技术有限公司"),
    CHONGZHOU("袁杰","15108362669","四川全盛通网络技术有限公司"),
    PIDU("何思友","18715793021","四川汇源吉迅数码科技有限公司"),
    XINJING("刘小兰","13980764414","浙江东冠通信技术股份有限公司");



    private String name;
    private String phone;
    private String department;


    private buildPhone(String name,String phone,String department){
        this.name = name;
        this.phone = phone;
        this.department = department;
    }


    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getDepartment(){
        return department;
    }

    public void setDepartment(String department){
        this.department=department;
    }


}
