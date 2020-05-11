package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author ：tt
 * @date ：Created in 2020/4/27 10:29 上午
 * @description：我的课题模型
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MytopicListModel implements Serializable {
    private static final long serialVersionUID = 3784876986569029093L;
    private Integer type;
    private Integer mytopicId;
    private List<String> morenlist;
    private List<String> zidingyilist;
}

