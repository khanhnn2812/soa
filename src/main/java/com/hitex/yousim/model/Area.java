package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "area")
public class Area {
    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Parent")
    private String parent;

    @Column(name = "Type")
    private String type;

}
