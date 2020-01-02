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

    @Column(name="craete_by")
    public String craeteBy;

    @Column(name="craete_time")
    public Date createTime;

    @Column(name="update_by")
    public String updateBy;

    @Column(name="update_time")
    public Date updateTime;

}
