package com.krysha.bookreview.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.Data;

@Data
public class RegisterRequest {
	
	@NotBlank(message="L'email est obligatoire")
	@Email(message="L'email n'est pas valide")
	private String email;
	
	@NotBlank(message="Le mot de passe est obligatoire")
	@Size(min = 12, message = "Le mot de passe doit contenir au moins 12 caractères")
	@Pattern(
		    regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{12,}$",
		    message = "Le mot de passe doit contenir au moins 12 caractères, une majuscule, un chiffre et un caractère spécial"
		)
	private String password;
	
	@NotBlank(message = "Le nom d'utilisateur est obligatoire")
	private String username;
	
	@NotBlank(message="Le nom est obligatoire")
	private String name;
	
	@NotBlank(message="Le prénom est obligatoire")
	private String firstname;
	
	@NotNull(message = "La date de naissance est obligatoire")
	private LocalDate birthDate;
}
