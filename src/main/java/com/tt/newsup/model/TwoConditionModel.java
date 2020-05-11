package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TwoConditionModel implements Serializable {
    private static final long serialVersionUID = 52035771535997819L;
    List<String> dataItem;
    String value;

}
