package com.tt.newsup.enumutils;

public enum noPonPhone {

    zongdiao("易小川","15828054496"),
    RESOURCEJIEDUAN("曹宋杰","18215555464"),
    DIANLU("周坤","13908000844 ") ;

    private String name;
    private String phone;




    private noPonPhone( String name , String phone ){
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
