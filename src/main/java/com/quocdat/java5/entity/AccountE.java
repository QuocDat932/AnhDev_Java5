package com.quocdat.java5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;


@Data
@Entity
@Table(schema = "project_final_java05", name = "sys_user")
public class AccountE implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "sys_id")
    private Long sysId;

    @Column(name= "user_name")
    private String userName;

    @Column(name= "tk")
    private String tk;

    @JsonIgnore
    @Column(name = "mk")
    private String mk;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer", "mk"})
    private RoleE role;
}
