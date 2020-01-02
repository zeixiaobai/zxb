package org.zxb.entity;

import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseEntity {

    public Integer id;

    public String craeteBy;

    public Date createTime;

    public String updateBy;

    public Date updateTime;

}
