package com.mgl.restdemo.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Entity
@Data @EqualsAndHashCode(of="id")
@XmlRootElement
public class SysUser implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @XmlTransient
    private @NonNull Customer customer = null;

    private @NonNull @NotNull String username = "";
    private @NonNull @NotNull String email = "";

    private @NonNull @NotNull String md5Password = "";

}