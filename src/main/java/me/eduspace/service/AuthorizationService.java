package me.eduspace.service;

import lombok.RequiredArgsConstructor;
import me.eduspace.dto.authorization.AuthDTO;
import me.eduspace.dto.user.UserDTO;
import me.eduspace.exceptions.AppBadRequestException;
import me.eduspace.repository.UserRepository;
import me.eduspace.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthorizationService {
    private final UserRepository userRepository;

    public UserDTO login(AuthDTO dto) {
        var encoder = new BCryptPasswordEncoder();

        var profile = userRepository
                .findByPhone(dto.getPhone())
                .orElseThrow(() -> new AppBadRequestException("Login or Password not valid."));

        if (!encoder.matches(dto.getPassword(), profile.getPassword()))
            throw new AppBadRequestException("Login or Password not valid");

        var jwt = JwtUtil.createJwt(profile.getId(), profile.getPhone());

        var user = new UserDTO();
        user.setName(profile.getName());
        user.setSurname(profile.getSurname());
        user.setPhone(profile.getPhone());
        user.setJwt(jwt);

        return user;
    }
}
