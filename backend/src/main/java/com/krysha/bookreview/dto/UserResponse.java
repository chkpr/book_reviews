package com.krysha.bookreview.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

import com.krysha.bookreview.model.User;

@Data
@AllArgsConstructor
public class UserResponse {
	private Long id;
	private String email;
	private String username;
	private String name;
	private String firstname;
	private LocalDate birthDate;
	
	public static UserResponse from(User user) {
		return new UserResponse(
				user.getId(),
				user.getEmail(),
				user.getUsername(),
				user.getName(),
				user.getFirstname(),
				user.getBirthDate()
				);
	}
	

}
