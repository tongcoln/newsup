package com.tt.newsup.enumutils;

import lombok.Data;

public enum ponLine {


    ZONGDIAO("易小川","15828054496"),
    RESOURCEJIEDUAN("曹宋杰","18215555464");

    private String name;
    private String phone;


    private ponLine( String name , String phone ){
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
