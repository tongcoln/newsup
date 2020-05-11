package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：tt
 * @date ：Created in 2020/5/6 5:21 下午
 * @description：网络线条最后的答案保存模型
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceManaageNetworkAnswerModel {
    private Integer manageAnsweId;//manage_answer_id
    private String manageAnswerUserId;//manage_answer_user_id
    private String manageAnswerRadio;//manage_answer_radio
    private String manageAnswerTextarea;//manage_answer_textarea
    private String manageAnswerImg;//manage_answer_img
    private Integer manageAnswerTopicId;//manage_answer_topic_id
    private Integer manageAnswerProceedId;//manage_answer_proceed_id
}
