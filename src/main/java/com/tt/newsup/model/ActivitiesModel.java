package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 流程流转MODEL
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivitiesModel {
    private String content;
    private String timestamp;
    private String size;
    private String type;
    private String icon;
    private String color;

}
