package com.uliasz.irms.backend.rest.controllers;

import com.uliasz.irms.backend.rest.services.AppUserService;
import com.uliasz.irms.internal.database.entities.AppUserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usersApi")
@RequiredArgsConstructor
@Slf4j
public class AppUserController {


    /**
     * to do remove - update
     */
    private final AppUserService appUserService;




    @PostMapping(value = "/login")
    public ResponseEntity<Boolean> loginUser(@RequestParam String login, @RequestParam String password) {
        return ResponseEntity.ok(true);
    }




    @GetMapping(value = "/users")
    public ResponseEntity<List<AppUserEntity>> getAllUsers() {
        return ResponseEntity.ok(appUserService.getAppUsers());
    }

    @PutMapping(value = "/users")
    public void saveUser(@RequestBody AppUserEntity entity) {
        appUserService.saveUser(entity);
    }

    @GetMapping(value = "/text")
    public String getTekst() {
        log.info("byłem tu");
        return "tekst";
    }
}
