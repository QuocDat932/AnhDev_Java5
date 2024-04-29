package com.quocdat.java5.entity;

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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private RoleE role;
}
