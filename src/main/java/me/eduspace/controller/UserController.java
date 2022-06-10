package me.eduspace.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.eduspace.service.UserService;
import org.springframework.http.MediaType;
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
            path = "{userProfileId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserProfileImage(@PathVariable("userProfileId") Long userProfileId,
                                       @RequestParam("file") MultipartFile file) {
        userService.uploadUserProfileImage(userProfileId, file);
    }
    @ApiOperation(value = "user image download", notes = "method for user download image")
    @GetMapping("{userProfileId}/image/download")
    public byte[] downloadUserProfileImage(@PathVariable("userProfileId") Long userProfileId) {
        return userService.downloadUserProfileImage(userProfileId);
    }
}
