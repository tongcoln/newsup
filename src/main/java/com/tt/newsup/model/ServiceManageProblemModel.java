package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：tt
 * @date ：Created in 2020/5/9 3:50 下午
 * @description：管理者执行表派发一线直通车的清单
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceManageProblemModel {
    private Integer serviceStoreProblemId;//service_store_problem_id
    private String serviceStoreProblemUserid;//service_store_problem_userid 用户的唯一标识
    private String serviceStoreManageTableName;//service_store_manage_table_name 管理者项目名称
    private Integer serviceStoreGrabId;//service_store_grab_id 抢单的ID号
    private Integer serviceStoreManageId;//service_store_manage_id 题目的ID
    private String serviceStoreProblemTitle; //service_store_problem_title
    private String serviceStoreProblemContext;//service_store_problem_context
    private String serviceStoreProblemImgPath;//service_store_problem_img_path
    private String serviceStoreProblemReturn;//service_store_problem_return
}
