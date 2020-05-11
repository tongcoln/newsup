package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenModel  implements Serializable {
    private static final long serialVersionUID = 4308643699383305592L;
    private  String accessToken;
}
