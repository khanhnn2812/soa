package com.hitex.yousim.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "chitiethoadon")
public class ChiTietHoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ma_hoa_don")
    private String maHoaDon;

    @Column(name = "bac_nuoc")
    private int bacNuoc;

    @Column(name = "so_tien")
    private double soTien;

    @Column(name = "so_nuoc")
    private double soNuoc;

    @Column(name = "thanh_tien")
    private double thanhTien;

}
