package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：tt
 * @date ：Created in 2020/4/27 4:19 下午
 * @description：进行中的站厅信息
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceHallProceedModel  implements Serializable {
    private static final long serialVersionUID = -5349572718341148885L;
    private Integer serviceStoreProceedId;
    private String serviceStoreUser;
    private String serviceStoreDitchCode; //service_store_ditch_code
    private String serviceStoreDate;
    private String serviceStoreTime;
    private Integer serviceStoreActive;
    private Integer serviceStoreMytopicId;
    private String serviceStoreClock;
}
