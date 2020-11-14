package com.uliasz.irms.backend.users;

import com.uliasz.irms.internal.database.entities.AppUserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usersApi")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@Slf4j
public class AppUserController {


    /**
     * to do remove - update
     */
    private final AppUserService appUserService;

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
