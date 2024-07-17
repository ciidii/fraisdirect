package com.fraisdirect.controller;
import com.fraisdirect.dto.AuthentificationDto;
import com.fraisdirect.entity.Utilisateur;
import com.fraisdirect.security.JwtService;
import com.fraisdirect.service.Impl.UtilisateurService;
import com.fraisdirect.utils.ResponseVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UtilisateurControleur {

    private AuthenticationManager authenticationManager;
    private UtilisateurService utilisateurService;
    private JwtService jwtService;

    @PostMapping(path = "inscription")
    public ResponseEntity<ResponseVO<Void>> inscription(@RequestBody Utilisateur utilisateur) {
      return   this.utilisateurService.inscription(utilisateur);
    }

    @PostMapping(path = "activation")
    public ResponseEntity<ResponseVO<Void>> activation(@RequestParam("code") String code) {
        Map<String, String> activationCode = new HashMap<>();
        activationCode.put("code",code);
       return this.utilisateurService.activation(activationCode);
    }

    @PostMapping(path = "connexion")
    public Map<String, String> connexion(@RequestBody AuthentificationDto authentificationDTO) {
        final Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authentificationDTO.username(), authentificationDTO.password())
        );

        if(authenticate.isAuthenticated()) {
            return this.jwtService.generate(authentificationDTO.username());
        }
        return null;
    }
}
