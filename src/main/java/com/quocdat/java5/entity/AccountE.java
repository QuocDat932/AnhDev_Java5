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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private RoleE role;

//   @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
//    fetch = FetchType.EAGER: cho phép lấy dữ liệu con khi dữ liệu cha vừa khởi tạo
//    cascade = CascadeType.<Type>: cho phép tương tác tới dữ liệu con khi dữ liệu cha bị tác động
//    Type:
//      + ALL: cho phép bất kì thao tác của CURD
//      + PERSIST: Tạo mới/ MERGE: Cập nhật/ REMOVE: Xoá/ REFRESH: Làm Mới/ DETACH: ngắt kết nối
//    Ví dụ: khi parent đang có dữ liệu Json kiểu: {..., role: {role_id = 1}}
//           nếu ta để CascadeType.MERGE thì ngay lập tức database sẽ update role_id = 1 và các cols khác sẽ = ''
//           vì thế trong trường hợp này chúng ta không nên thao tác j tới các thực thể con

}
