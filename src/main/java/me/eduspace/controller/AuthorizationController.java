package me.eduspace.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.eduspace.dto.authorization.AuthDTO;
import me.eduspace.dto.user.UserDTO;
import me.eduspace.service.AuthorizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/authorization/")
@Api(tags = "Authorization")
public class AuthorizationController {
    private final AuthorizationService authorizationService;

    @PostMapping("/login")
    @ApiOperation(value = "Login", notes = "method for login ")
    public ResponseEntity<UserDTO> login(@RequestBody AuthDTO dto) {
        return ResponseEntity.ok(authorizationService.login(dto));
    }
}
