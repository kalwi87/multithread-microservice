package com.biel.microservice.model;

import org.omg.CORBA.Object;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String uuid = UUID.randomUUID().toString();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int hashCode(){
        return Objects.hash(uuid);
    }

    public boolean equals(Object that){
        return this == that || that instanceof BaseEntity
                && Objects.equals(uuid,((BaseEntity) that).uuid);
    }
}
