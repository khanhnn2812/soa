package com.hitex.yousim.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "khachhang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idKhachHang;

    @Column(name = "ma_khach_hang")
    private String maKhachHang;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "tinh")
    private String tinh;

    @Column(name = "huyen")
    private String huyen;

    @Column(name = "xa")
    private String xa;

    @Column(name = "ho_ngheo")
    private boolean hoNgheo;

    @Column(name = "doanh_nghiep")
    private boolean doanhNghiep;

    @Column(name = "loai_doanh_nghiep")
    private String loaiDoanhNghiep;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "email")
    private String email;

    @Column(name = "trang_thai")
    private String trangThai;

    @Column(name = "cccdtruoc")
    private String cccdTruoc;

    @Column(name = "cccdsau")
    private String cccdSau;
}
