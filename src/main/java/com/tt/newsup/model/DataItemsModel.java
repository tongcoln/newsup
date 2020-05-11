package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 界面呈现MODEL
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataItemsModel  implements Serializable {
    private static final long serialVersionUID = -4091436855361471542L;
    private  String label;
    private  String value;
}
