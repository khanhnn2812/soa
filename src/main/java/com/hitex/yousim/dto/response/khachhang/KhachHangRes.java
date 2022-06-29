package com.hitex.yousim.dto.response.khachhang;

import com.hitex.yousim.model.KhachHang;
import lombok.Data;

import java.util.List;

@Data
public class KhachHangRes  {
    List<KhachHang> listKhachHang;
}