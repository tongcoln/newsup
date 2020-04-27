package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 界面呈现MODEL
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataItemsModel {
    private  String label;
    private  String value;
}
