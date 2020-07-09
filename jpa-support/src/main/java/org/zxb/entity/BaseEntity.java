package org.zxb.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @Column(name="create_by")
    public String createBy;

    @Column(name="create_time")
    public Date createTime;

    @Column(name="update_by")
    public String updateBy;

    @Column(name="update_time")
    public Date updateTime;

}
