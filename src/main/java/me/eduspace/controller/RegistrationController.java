package me.eduspace.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import me.eduspace.dto.registration.RegistrationRequestDTO;
import me.eduspace.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
@Api(tags = "registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping("/")
    @ApiOperation(value = "user registration", notes = "method for user registration")
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequestDTO request) {

        return ResponseEntity.ok(registrationService.registerUser(request));
    }

    @GetMapping(path = "confirm")
    @ApiOperation(value = "sms confirm", notes = "method for sms confirm")
    public ResponseEntity<?> confirm(@RequestParam("sms") String sms) {
        return ResponseEntity.ok(registrationService.confirmSms(sms));
    }
}
