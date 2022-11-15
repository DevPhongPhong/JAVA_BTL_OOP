package com.group5.btl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {
	private String name;
	private String email;
	private String phoneNumber;
	private String studentCode;
	private String sectorId;
	private String password;
}
