package com.krysha.bookreview.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LoginRequest {
	
	@NotBlank
	@Email(message="L'email est obligatoire")
	private String email;
	
	@NotBlank(message="Le mot de passe est obligatoire")
	private String password;
}
