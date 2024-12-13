package com.KLTN.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {
	
	// Thông tin user id - tự động tăng, kiểu int
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    // Danh sách User Role - kiểu OneToMany
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<UserRole> listUserRole;

    // Danh sách Employee - kiểu OneToMany
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    List<Employee> listEmployee;

    // Danh sách địa chỉ - kiểu OneToMany
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    List<Address> listAddress;

    // Danh sách yêu thích - kiểu OneToMany
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    List<Favorite> listFavorite;

    // Thông tin email - giới hạn 100 ký tự
    @Column(name = "email", length = 100)
    String email;

    // Thông tin password - giới hạn 255 ký tự
    @Column(name = "password", length = 255)
    String password;

    // Thông tin fullname - giới hạn 255 ký tự
    @Column(name = "fullname", length = 255)
    String Fullname;

    // Thông tin giới tính - kiểu int
    @Column(name = "sex")
    int sex;

    // Thông tin ngày sinh nhật - giới hạn 50 ký tự (dd/MM/yyyy)
    @Column(name = "birthday", length = 50)
    String birthday;

    // Thông tin đăng ký nhận bản tin - kiểu int
    @Column(name = "subscribe")
    int subscribe;

    // Thông tin ngày tạo - giới hạn 10 ký tự (dd/MM/yyyy)
    @Column(name = "createday", length = 50)
    String Createday;

    // Thông tin ngày xóa - giới hạn 10 ký tự (dd/MM/yyyy)
    @Column(name = "deleteday", length = 50)
    String Deleteday;

    // Thông tin người xóa - kiểu int
    @Column(name = "persondelete")
    int Persondelete;

}
