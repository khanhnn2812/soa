package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "gianuoc")
public class GiaNuoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "bac")
    private int bac;

    @Column(name = "gia_nuoc")
    private float giaNuoc;

    @Column(name = "ho_ngheo")
    private boolean hoNgheo;

    @Column(name = "doanh_nghiep")
    private boolean doanhNghiep;

    @Column(name = "description")
    private String description;

}
