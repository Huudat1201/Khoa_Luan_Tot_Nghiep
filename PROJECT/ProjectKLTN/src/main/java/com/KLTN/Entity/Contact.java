package com.KLTN.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contact {
	
	// Thông tin id contact - tự động tăng, kiểu int
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    // Thông tin tên - giới hạn 255 ký tự
    @Column(name = "name", length = 255)
    String name;

    // Thông tin email - giới hạn 255 ký tự
    @Column(name = "email", length = 255)
    String email;

    // Thông tin nội dung - giới hạn 500 ký tự
    @Column(name = "content", length = 500)
    String content;

    // Thông tin ngày - giới hạn 50 ký tự
    @Column(name = "date", length = 50)
    String date;

    // Thông tin trạng thái - giới hạn 50 ký tự
    @Column(name = "status", length = 50)
    String status;
	
}
