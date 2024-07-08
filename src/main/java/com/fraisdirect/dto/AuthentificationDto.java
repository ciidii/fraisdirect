package tech.arona.avis.dto;

import org.springframework.security.core.Authentication;

public record AuthentificationDto(String username, String password)  {
}
