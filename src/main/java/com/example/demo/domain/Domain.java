package com.example.demo.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public abstract class Domain implements Serializable {

    @Column(name = "f_crea")
    private LocalDateTime fcrea = null;

    @Column(name = "f_act")
    private LocalDateTime fact = null;

    public Domain() {
    }

    @PrePersist
    protected void onPrePersist() {
        this.fcrea = LocalDateTime.now();
    }

    @PreUpdate
    protected void onPreUpdate() {
        this.fact = LocalDateTime.now();
    }

    public LocalDateTime getFcrea() {
        return this.fcrea;
    }

    public void setFcrea(LocalDateTime fcrea) {
        this.fcrea = fcrea;
    }

    public LocalDateTime getFact() {
        return this.fact;
    }

    public void setFact(LocalDateTime fact) {
        this.fact = fact;
    }
}

