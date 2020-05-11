package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：tt
 * @date ：Created in 2020/5/7 5:20 下午
 * @description：总结课题答案模型
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceSummaryAnswerModel {
    private Integer summaryAnswerId;//summary_answer_id
    private String summaryAnswerUserId;//summary_answer_user_id
    private String summaryAnswerTextarea;//summary_answer_textarea
    private Integer summaryAnswerTopicId;//summary_answer_topic_id
    private Integer summaryAnswerProceedId;//summary_answer_proceed_id

}
