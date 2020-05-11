package com.tt.newsup.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ：tt
 * @date ：Created in 2020/5/11 10:44 上午
 * @description：评价完的表
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceStoreProceedEndModel {
    private Integer serviceStoreProceedId;//service_store_proceed_id
    private String  serviceStoreUser;//service_store_user
    private String serviceStoreDitchCode;// service_store_ditch_code
    private String  serviceStoreDate;      //service_store_date
    private String serviceStoreTime;//service_store_time
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date   serviceStoreGrabTime;//service_store_grab_time
    private Integer serviceStoreActive;//service_store_active
    private Integer serviceStoreMytopicId;//service_store_mytopic_id
    private String serviceStoreState;//service_store_state
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date serviceStoreEndTime;//service_store_end_time
}
