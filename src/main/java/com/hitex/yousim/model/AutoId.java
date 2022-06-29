package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "auto_id")
public class AutoId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "f_code")
    private String code;

    @Column(name = "f_index")
    private int index;

    @Column(name = "f_value")
    private String value;

}
