package com.hitex.yousim.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "hoadon")
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "ma_hoa_don")
    private String maHoaDon;

    @Column(name = "ma_khach_hang")
    private String maKhachHang;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "thoi_gian_tao")
    private LocalDateTime thoiGianTao;

    @Column(name = "chi_so_cuoi")
    private Double chiSoCuoi;

    @Column(name = "so_nuoc_da_dung")
    private Double soNuocDaDung;

    @Column(name = "tong_tien")
    private Float tongTien;

    @Column(name = "da_thanh_toan")
    private boolean daThanhToan;

    @Column(name = "ngay_thanh_toan")
    private LocalDateTime ngayThanhToan;



}
