package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：tt
 * @date ：Created in 2020/4/24 10:09 下午
 * @description：统计人员次数
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCountModel implements Serializable {
    private static final long serialVersionUID = 7405362089024011152L;
    private String user;
    private Integer usercount;
}
