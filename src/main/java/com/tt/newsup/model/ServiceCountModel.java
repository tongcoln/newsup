package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：tt
 * @date ：Created in 2020/5/16 4:23 下午
 * @description：首页计数的模型
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceCountModel {
    private Integer grabCount;
    private Integer prolCount;
    private Integer judgeCount;
    private Integer endCount;
}
