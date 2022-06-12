package me.eduspace.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.eduspace.dto.user.UserDetailDTO;
import me.eduspace.dto.user.UserEmailDTO;
import me.eduspace.dto.user.UserResponseDTO;
import me.eduspace.dto.user.UserStatusDTO;
import me.eduspace.service.UserService;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/user")
@Api(tags = "user")
public class UserController {
    private final UserService userService;
    @ApiOperation(value = "user image upload", notes = "method for user upload image")
    @PostMapping(
            path = "/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserProfileImage(@RequestParam("file") MultipartFile file,
                                       Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        userService.uploadUserProfileImage(userDetails.getUsername(), file);
    }
    @ApiOperation(value = "user image download", notes = "method for user download image")
    @GetMapping("/image/download")
    @PreAuthorize("hasRole('USER')")
    public byte[] downloadUserProfileImage(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userService.downloadUserProfileImage(userDetails.getUsername());
    }

    @ApiOperation(value = "user getById ", notes = "method for user getById")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable("id") Long userId ) {
        return ResponseEntity.ok().body(userService.getById(userId));
    }

    @ApiOperation(value = "get user pagination list ", notes = "method for get user pagination list")
    @GetMapping("/pagination")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageImpl<UserResponseDTO>> getPagination(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                   @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok().body(userService.getPagination(page, size));
    }

    @ApiOperation(value = "user update detail ", notes = "method for user update detail ")
    @PutMapping("/detail")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Boolean> updateDetail(@RequestBody UserDetailDTO dto,
                                                                  Authentication authentication) {
        UserDetails userDetails= (UserDetails) authentication.getDetails();
        return ResponseEntity.ok().body(userService.updateDetail(userDetails.getUsername(), dto));
    }

    @ApiOperation(value = "user update email ", notes = "method for user update email ")
    @PutMapping("/upd-email")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> updateEmail(@RequestBody UserEmailDTO dto,
                                                Authentication authentication) {
        UserDetails userDetails= (UserDetails) authentication.getDetails();
        return ResponseEntity.ok().body(userService.updateEmail(userDetails.getUsername(), dto));
    }

    @ApiOperation(value = "user confirm email ", notes = "method for user confirm email ")
    @PutMapping("/confirm-email/{jwt}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Boolean> confirmEmail(@PathVariable("jwt") String jwt) {
        return ResponseEntity.ok().body(userService.confirmEmail(jwt));
    }

    @ApiOperation(value = "user change status ", notes = "method for user change status ")
    @PutMapping("/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> changeStatus(@RequestBody UserStatusDTO dto) {
        return ResponseEntity.ok().body(userService.changeStatus(dto));
    }
}
