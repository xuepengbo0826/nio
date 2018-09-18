package com.learn.vertx.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Auther: xuepengbo
 * @Date: 2018/9/18 14:47
 * @Description:
 */
@Getter
@Setter
@ToString
@Builder
public class Book {

    private String name;

    private String author;
}
