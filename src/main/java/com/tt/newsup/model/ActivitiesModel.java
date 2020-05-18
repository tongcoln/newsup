package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 流程流转MODEL
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivitiesModel  implements Serializable {
    private static final long serialVersionUID = -5505745301007713641L;
    private String content;
    private String timestamp;
    private String size;
    private String type;
    private String icon;
    private String color;

}
