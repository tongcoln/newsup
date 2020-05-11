package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：tt
 * @date ：Created in 2020/5/6 7:35 下午
 * @description：体验者执行表的模型
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceHallTasteQuestionModel {
    private Integer tasteTableId;//taste_table_id
    private String tasteTableQuestion;//taste_table_question
}
