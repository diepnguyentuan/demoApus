package com.tiep.demoapus.controller;

import com.tiep.demoapus.dto.request.HiringTypeRequestDTO;
import com.tiep.demoapus.dto.response.HiringTypeResponseDTO;
import com.tiep.demoapus.dto.response.PageableResponse;
import com.tiep.demoapus.dto.response.ResponseWrapper;
import com.tiep.demoapus.service.HiringTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/hiring-type")
@RequiredArgsConstructor
public class HiringTypeController {

    private final HiringTypeService hiringTypeService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllHiringTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt:DESC") String sort,
            @RequestParam(required = false) String search
    ) {
        PageableResponse<HiringTypeResponseDTO> data = hiringTypeService.getAllHiringTypes(page, size, sort, search);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HiringTypeResponseDTO> getHiringTypeById(@PathVariable Long id) {
        HiringTypeResponseDTO data = hiringTypeService.getHiringTypeById(id);
        return ResponseEntity.ok(data);
    }
    @PostMapping
    public ResponseEntity<?> addHiringType(@Valid @RequestBody HiringTypeRequestDTO hiringTypeRequestDTO) {
        HiringTypeResponseDTO data = hiringTypeService.addHiringType(hiringTypeRequestDTO);
        return ResponseEntity.ok(new ResponseWrapper(Map.of("id", data.getId())));
    }

    @PutMapping
    public ResponseEntity<?> updateHiringType(@Valid @RequestBody HiringTypeRequestDTO hiringTypeRequestDTO) {
        HiringTypeResponseDTO data = hiringTypeService.updateHiringType(hiringTypeRequestDTO);
        return ResponseEntity.ok(new ResponseWrapper(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHiringTypeById(@PathVariable Long id) {
        hiringTypeService.deleteHiringType(id);
        return ResponseEntity.ok().build();
    }
}

