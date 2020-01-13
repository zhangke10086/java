package com.example.common.auditorinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract  class AbstractPublicFields {
    @CreatedBy
    @Column(updatable = false, length = 128)
    private String createuser;
    @CreatedDate
    @Column(updatable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createtime;
    @LastModifiedBy
    @Column(length = 128)
    @CollectionTable
    private String lastmodifieduser;
    @LastModifiedDate
    @Column(nullable = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp lastmodifiedtime;

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getLastmodifieduser() {
        return lastmodifieduser;
    }

    public void setLastmodifieduser(String lastmodifieduser) {
        this.lastmodifieduser = lastmodifieduser;
    }

    public Timestamp getLastmodifiedtime() {
        return lastmodifiedtime;
    }

    public void setLastmodifiedtime(Timestamp lastmodifiedtime) {
        this.lastmodifiedtime = lastmodifiedtime;
    }
}
