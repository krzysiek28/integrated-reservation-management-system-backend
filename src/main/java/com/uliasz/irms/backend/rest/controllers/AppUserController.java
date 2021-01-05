package com.uliasz.irms.backend.rest.controllers;

import com.uliasz.irms.backend.rest.services.AppUserService;
import com.uliasz.irms.internal.common.models.AppUserModel;
import com.uliasz.irms.internal.common.models.PersonalDataModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usersApi")
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<AppUserModel> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(appUserService.getUserById(id));
    }

    @PutMapping(value = "/user/{id}/updateUserPersonalData")
    public ResponseEntity<AppUserModel> updateUserPersonalData(@PathVariable Long id, @RequestBody PersonalDataModel personalDataModel) {
        return ResponseEntity.ok(appUserService.updateUserPersonalData(id, personalDataModel));
    }
}
