package com.group5.btl.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPreview {
    private int userId;
    private String userName;
    private String userEmail;
    private String userPhoneNumber;
}
