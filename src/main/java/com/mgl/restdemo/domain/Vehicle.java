package com.mgl.restdemo.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Vehicle implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @XmlTransient
    private @NonNull Customer customer = null;

    private @NonNull @NotNull String licensePlate = "";

    private int sortIndex = 0;

    @OneToMany
    @XmlTransient
    private @NonNull Set<Location> locations = Collections.emptySet();

}
