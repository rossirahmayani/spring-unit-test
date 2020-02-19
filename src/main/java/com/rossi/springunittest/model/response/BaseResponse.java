package com.rossi.springunittest.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse implements Serializable {
    private String responseCode;
    private String responseMsg;
}
