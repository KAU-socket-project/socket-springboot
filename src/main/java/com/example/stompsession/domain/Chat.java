package com.example.stompsession.domain;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class Chat {

    private String senderName;
    private String text;
    private Timestamp createdTime;
}