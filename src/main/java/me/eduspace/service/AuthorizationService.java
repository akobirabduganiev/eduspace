package me.eduspace.service;

import lombok.RequiredArgsConstructor;
import me.eduspace.dto.authorization.AuthDTO;
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

    public UserResponseDTO login(AuthDTO dto) {
        var encoder = new BCryptPasswordEncoder();

        var profile = userRepository
                .findByEmail(dto.getEmail())
                .orElseThrow(() -> new AppBadRequestException("Login or Password not valid."));

        if (!encoder.matches(dto.getPassword(), profile.getPassword()))
            throw new AppBadRequestException("Login or Password not valid");

        var jwt = JwtUtil.createJwt(profile.getId(), profile.getEmail());

        var user = new UserResponseDTO();
        user.setName(profile.getName());
        user.setSurname(profile.getSurname());
        user.setPhone(profile.getEmail());
        user.setJwt(jwt);

        return user;
    }
}
