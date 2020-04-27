package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：tt
 * @date ：Created in 2020/4/27 2:19 下午
 * @description：站厅清单
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceHallModel implements Serializable {
    private static final long serialVersionUID = -2267368552261141027L;
    private Integer serviceStoreId;
    private String serviceStoreCity;
    private String serviceStoreDitchName;
    private String serviceStoreDitchCode;
    private String serviceStoreType;
    private String serviceStoreSystemName;
    private String serviceStoreSystemType;
    private String serviceStoreEare;
    private String serviceStoreAdress;
    private double serviceStoreLong;
    private double serviceStoreLat;
    private String serviceStoreIsfive;
    private String serviceStoreIsconnected;
    private String serviceStoreAdmin;

}
