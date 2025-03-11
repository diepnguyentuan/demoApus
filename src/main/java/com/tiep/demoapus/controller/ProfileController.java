package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.ProfileRequestDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.ProfileResponseDTO;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<?> createProfile(@RequestBody ProfileRequestDTO  profile) {
        ProfileResponseDTO data = profileService.addProfile(profile);
        return ResponseEntity.ok(new ResponseWrapper(data.getId()));
    }

    @GetMapping("/list")
    public ResponseEntity<?> listProfiles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search
    ) {
        PageableResponse<ProfileResponseDTO> data = profileService.getAllProfiles(page, size, sort, search);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable Long id) {
        ProfileResponseDTO data = profileService.getProfileById(id);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }

    @PutMapping
    public ResponseEntity<?> updateProfile(@RequestBody ProfileRequestDTO profileRequestDTO) {
        ProfileResponseDTO updateProfile = profileService.updateProfile(profileRequestDTO);
        return ResponseEntity.ok(new ResponseWrapper(updateProfile.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfileById(id);
        return ResponseEntity.ok("Deleted profile with id " + id);
    }

}
