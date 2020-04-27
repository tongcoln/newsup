package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ：tt
 * @date ：Created in 2020/4/25 1:01 上午
 * @description：返回前台的包装信息
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetrunMessModel {
    private String code;
    private  List<List<DataItemsModel>> objectlist;
}
