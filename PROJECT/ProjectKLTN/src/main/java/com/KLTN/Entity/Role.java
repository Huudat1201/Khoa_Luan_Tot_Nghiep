package com.KLTN.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role implements Serializable {
	
	// Thông tin Role Id - tự động tăng, kiểu int
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    // Lấy danh sách User Role - kiểu OneToMany
    @JsonIgnore
    @OneToMany(mappedBy = "role")
    List<UserRole> listUserRole;

    // Thông tin Role Name - giới hạn 100 ký tự
    @Column(name = "name", length = 100)
    String name;

}
