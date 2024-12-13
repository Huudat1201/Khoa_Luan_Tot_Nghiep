package com.KLTN.Model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AlertModel {

	// Nội dung của thông báo (The content of the alert message)
	String content;

	// Loại cảnh báo (Type of alert, e.g., success, error, warning)
	String alert;

	// Cờ để hiển thị thông báo (Flag to determine if the alert should be displayed)
	boolean display;
	
}
