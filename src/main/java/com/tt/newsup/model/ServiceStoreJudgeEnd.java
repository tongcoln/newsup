package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：tt
 * @date ：Created in 2020/5/13 1:16 上午
 * @description：存放的是评价后的数据
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceStoreJudgeEnd {
    private Integer judgeTopicId;
    private String judgeValue;//        judge_value
}
