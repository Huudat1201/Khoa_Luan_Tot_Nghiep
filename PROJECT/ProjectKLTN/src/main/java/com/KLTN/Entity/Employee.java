package com.KLTN.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Employee implements Serializable {

	// Thông tin id nhân viên - tự động tăng, kiểu int
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    // Thông tin người dùng - kiểu ManyToOne
    // (Một người dùng có thể có nhiều vai trò như nhân viên, quản trị viên, người dùng).
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    // Thông tin phòng ban - giới hạn 255 ký tự
    @Column(name = "department", length = 255)
    String department;

    // Thông tin vị trí - giới hạn 255 ký tự
    @Column(name = "position", length = 255)
    String position;

    // Thông tin số điện thoại - giới hạn 15 ký tự
    @Column(name = "phone", length = 15)
    String phone;

    // Thông tin ảnh đại diện - giới hạn 255 ký tự
    @Column(name = "image", length = 255)
    String image;

    // Thông tin ngày bắt đầu làm việc - giới hạn 50 ký tự
    @Column(name = "startday", length = 50)
    String Startday;

    // Thông tin lương - kiểu int
    @Column(name = "salary")
    int salary;

    // Thông tin ngày tạo - giới hạn 50 ký tự
    @Column(name = "createday", length = 50)
    String Createday;

    // Thông tin mã người tạo - kiểu int
    @Column(name = "personcreate")
    int Personcreate;

    // Thông tin ngày cập nhật - giới hạn 50 ký tự
    @Column(name = "updateday", length = 50)
    String Updateday;

    // Thông tin mã người cập nhật - kiểu int
    @Column(name = "personupdate")
    int Personupdate;

    // Thông tin ngày xóa - giới hạn 50 ký tự
    @Column(name = "deleteday", length = 50)
    String Deleteday;

    // Thông tin mã người xóa - kiểu int
    @Column(name = "persondelete")
    int Persondelete;

}
