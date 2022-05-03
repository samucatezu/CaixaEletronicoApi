package com.example.caixaeletronicoapi.error;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExceptionDetails {
    private String title;
    private int status;
    private String detail;
    private Long timeStamp;
    private String developorMessage;
}
