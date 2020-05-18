package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：tt
 * @date ：Created in 2020/5/14 2:29 下午
 * @description：后端传送数据到前台的封装模型
 * @modified By：
 * @version:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResponseModel<T>  implements Serializable {
    private static final long serialVersionUID = 3650723307741625800L;
    private int code;
    private String msg;
    private T data;

}
