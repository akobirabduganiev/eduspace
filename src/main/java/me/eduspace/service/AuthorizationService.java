package me.eduspace.service;

import lombok.RequiredArgsConstructor;
import me.eduspace.dto.authorization.AuthDTO;
import me.eduspace.dto.authorization.AuthResponseDTO;
import me.eduspace.dto.user.UserResponseDTO;
import me.eduspace.exceptions.AppBadRequestException;
import me.eduspace.repository.UserRepository;
import me.eduspace.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthorizationService {
    private final UserRepository userRepository;

    public AuthResponseDTO login(AuthDTO dto) {
        var encoder = new BCryptPasswordEncoder();

        var profile = userRepository
                .findByEmailAndIsDeleted(dto.getEmail(), false)
                .orElseThrow(() -> new AppBadRequestException("Login or Password not valid."));

        if (!encoder.matches(dto.getPassword(), profile.getPassword()))
            throw new AppBadRequestException("Login or Password not valid");
        if (!profile.getEnabled())
            throw new AppBadRequestException("email not confirmed!");

        var jwt = JwtUtil.createJwt(profile.getId(), profile.getEmail());

        return AuthResponseDTO.builder()
                .id(profile.getId())
                .name(profile.getName())
                .surname(profile.getSurname())
                .email(profile.getEmail())
                .jwt(jwt)
                .build();
    }
}
