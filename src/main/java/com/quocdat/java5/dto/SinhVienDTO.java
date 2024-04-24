package com.quocdat.java5.dto;


import jakarta.validation.constraints.*;

public class SinhVienDTO {
    @NotNull(message = "Không được để trống MSSV")
    private String mssv;

    @NotBlank(message = "Không được để trống Tên")
    private String tenSv;

    @Email(message = "Không được để trống mail Và mail phải hợp lệ ")
    @NotNull(message = "Không được để trống mail")
    private String email;

    @NotNull(message = "Không được để trống Giới Tính")
    private byte gioiTinh;

    @NotBlank(message = "Không được để trống Chuyên Ngành")
    private String chuyenNganh;

    @NotNull(message = "Không được để trống gpa")
    @DecimalMax(value = "10.0", message = "GPA phải thoả 1.0 <= GPA <= 10")
    @DecimalMin(value = "1.0", message = "GPA phải thoả 1.0 <= GPA <= 10")
    private Double gpa;

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getTenSv() {
        return tenSv;
    }

    public void setTenSv(String tenSv) {
        this.tenSv = tenSv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(byte gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(String chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }
}
