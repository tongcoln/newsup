package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineUserModel  implements Serializable {
    private static final long serialVersionUID = -8759270462715968054L;
    private  String  lineUser;
}
