package com.rossi.springunittest.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class DataResponse extends BaseResponse implements Serializable {
    private Object responseData;
}
