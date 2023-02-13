package com.mousam.todo.controllers;

import com.mousam.todo.dto.AppUserRequest;
import com.mousam.todo.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/")
public class AppUserController {
    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping(value = "{user_name}")
    public ResponseEntity getAppUser(@PathVariable String user_name) {
        AppUserRequest appUserRequest = new AppUserRequest();
        appUserRequest.setName(user_name);
        try {
            return new ResponseEntity(appUserService.getItems(appUserRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return new ResponseEntity(appUserService.getALl(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping
    public ResponseEntity createAppUser(@RequestBody AppUserRequest appUserRequest) {
        try {
            return new ResponseEntity(appUserService.addAppUser(appUserRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping
    public ResponseEntity updateAppUser(@RequestBody AppUserRequest appUserRequest) {
        try {
            return new ResponseEntity(appUserService.addOrUpdateItem(appUserRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteItem(@RequestBody AppUserRequest appUserRequest) {
        try {
            return new ResponseEntity(appUserService.delItem(appUserRequest), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}
