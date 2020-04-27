package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformationUserModel implements Serializable {
    private static final long serialVersionUID = 1837517822621198399L;
    private Integer informationUserId;
    private Integer informationUserPhone;
}
