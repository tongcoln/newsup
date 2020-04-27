package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NegativeOrZero;
import java.io.Serializable;

/**
 * @author ：tt
 * @date ：Created in 2020/4/25 12:35 上午
 * @description：黑名单
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlackListModel  implements Serializable {
    private static final long serialVersionUID = 2704417665453593134L;
    private Integer blackListId;
    private String blackListUserId;
}
