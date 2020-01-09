package org.zxb.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class BaseEntity implements Serializable {

    public Integer id;

    public String createBy;

    public Date createTime;

    public String updateBy;

    public Date updateTime;

}
