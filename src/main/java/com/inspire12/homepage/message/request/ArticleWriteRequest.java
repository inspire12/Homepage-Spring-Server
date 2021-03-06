package com.inspire12.homepage.message.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

//@Value
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleWriteRequest {
    String boardType;
    String title;
    String content;
    List<String> tag;
    List<Object> files;
}
