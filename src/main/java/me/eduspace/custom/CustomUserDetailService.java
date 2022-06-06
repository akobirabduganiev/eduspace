package me.eduspace.custom;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.eduspace.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG =
            "user with phone %s not found";
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        return new CustomUserDetails(userRepository.findByPhone(phone)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(USER_NOT_FOUND_MSG, phone)
                )));
    }
}
