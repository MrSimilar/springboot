package com.xinyet.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Bean {
    private Integer id;
    private String name;
    private String address;
    private String message;//Base64编码
    private Date at;
}
