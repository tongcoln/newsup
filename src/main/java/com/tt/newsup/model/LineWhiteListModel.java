package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：tt
 * @date ：Created in 2020/4/25 1:28 上午
 * @description：专线随手查白名单
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineWhiteListModel implements Serializable {
    private static final long serialVersionUID = 1770378778535578358L;
    private  Integer lineWhiteListId;
    private String lineWhiteListUserId;

}
