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
public class ContactModel {
	
	// Tên của người liên hệ (Name of the person contacting)
    String name;

    // Email của người liên hệ (Email of the person contacting)
    String email;

    // Nội dung liên hệ (Content of the message or contact)
    String content;
    
}
