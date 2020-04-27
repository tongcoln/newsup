package com.tt.newsup.enumutils;

public enum areaPhone {

    QINGYANG("费腾","18280063423"),
    WUHOU("廖维尧","13881867624"),
    JINNIU("袁超","13981727450"),
    GAOXINNAN("李梦翔","15928019934"),
    GAOXINXI("张虎","13518184864"),
    JINJIANG("施志强","15102887864"),
    PENGZHOU("黄思睿","18224404445"),
    XINDU("李瑞瀚","13648084723"),
    JINTANG("向挺","15202875740"),
    JIANYANG("施侃","13795706760"),
    QINGBAIJIANG("邓鹏","18848460103"),
    CHENGHUA("王若谷","13980030234"),
    PUJIANG("孙永久","13980463847"),
    DAYI("王丹","13981898740"),
    QIONGLAI("舒雪","13551110433"),
    DUJIANGYAN("宋冉柯","18349367464"),
    WENJIANG("吴骏锋","13882165401"),
    CHONGZHOU("刘智豪","15718056400"),
    PIDU("洪艳","13541276040");

    private String name;
    private String phone;


    private areaPhone( String name , String phone ){
        this.name = name ;
        this.phone = phone ;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setIndex(String phone) {
        this.phone = phone;
    }
}
