package me.eduspace.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.eduspace.dto.learningcenter.LearningCenterDTO;
import me.eduspace.dto.learningcenter.LearningCenterRequestDTO;
import me.eduspace.service.LearningCenterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/learning-centre")
@Slf4j
@Api("learning-centre")
public class LearningCenterController {
    private final LearningCenterService learningCenterService;

    @ApiOperation(value = "learning-centre create", notes = "method for creating learning-centre")
    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<LearningCenterDTO> create(@RequestBody @Valid LearningCenterRequestDTO dto) {
        return ResponseEntity.ok(learningCenterService.create(dto));
    }

    @ApiOperation(value = "get by id ", notes = "method for get user by id")
    @GetMapping(path = "/get")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<LearningCenterDTO> getById(@RequestParam("id") Long lcId) {
        return ResponseEntity.ok().body(learningCenterService.getById(lcId));
    }
}
