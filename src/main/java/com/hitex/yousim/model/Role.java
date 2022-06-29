package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table (name = "role")
public class Role extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;
    @Column(name = "role_code")
    private String roleCode;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "status_role")
    private int statusRole;

}
