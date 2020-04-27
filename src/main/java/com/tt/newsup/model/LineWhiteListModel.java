package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class LineWhiteListModel {
    private  Integer lineWhiteListId;
    private String lineWhiteListUserId;

}
