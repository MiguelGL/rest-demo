package com.mgl.restdemo.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.FluentSetter;
import lombok.NonNull;

@Entity
@Data @EqualsAndHashCode(of="id") @FluentSetter
@XmlRootElement
public class Customer implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private @NonNull @NotNull String name = "";

    private @NonNull @NotNull String description = "";

    @Column(unique=true)
    private @NonNull @NotNull String apiKey = "";

    @Temporal(TemporalType.TIMESTAMP)
    private @NonNull @NotNull Date creationTs = new Date();

    @OneToMany
    @XmlTransient
    private @NonNull Set<SysUser> users = Collections.emptySet();

    @OneToMany
    @XmlTransient
    private @NonNull Set<Vehicle> vehicles = Collections.emptySet();

}
