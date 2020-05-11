package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：tt
 * @date ：Created in 2020/5/7 4:05 下午
 * @description：网络线条的体验者执行表最终答案
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TasteAnswerNetworkModel {
    private Integer tasteAnswerId;//taste_answer_id
    private String tasteAnswerUserId;//taste_answer_user_id
    private String tasteAnswerRadio;//taste_answer_radio
    private String tasteAnswerTextarea;//taste_answer_textarea
    private String tasteAnswerImg;//taste_answer_img
    private Integer tasteAnswerTopicId;//taste_answer_topic_id
    private Integer tasteAnswerProceedId;//taste_answer_proceed_id

}
