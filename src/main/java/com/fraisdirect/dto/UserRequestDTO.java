package com.fraisdirect.dto;

import com.fraisdirect.entity.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class UserRequestDTO {
    private String password;
    private String nom;
    private String email;
    private Boolean active;
    private String Address;

    @OneToOne(cascade = CascadeType.ALL)
    private Role role;
}
