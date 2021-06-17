package com.senior.challenge.hotel.persistence;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@Data
@MappedSuperclass
public class Updatable extends Creatable {
    @Column(name = "updated_at")
    private Date updatedAt;

    @PrePersist
    @PreUpdate
    public void setUpdateDate() {
        this.updatedAt = new Date();
    }
}
