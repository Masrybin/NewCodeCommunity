package com.bin.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
   private Integer id;
   private Integer userId;
   private Integer entityType;
   private Integer entityId;
   private Integer targetId;
   private String content;
   private Integer status;
   private Date createTime;
}