package com.konzerra.selim_server.domain.gate_category;


import com.konzerra.selim_server.ApiPath;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategoryResponseDto;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategorySaveDto;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategoryUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController

public class GateCategoryController {

    private final GateCategoryService gateCategoryService;

    public GateCategoryController(GateCategoryService gateCategoryService) {
        this.gateCategoryService = gateCategoryService;
    }

    @PostMapping(GateCategoryApi.save)
    public ResponseEntity<GateCategoryResponseDto> save(
            @RequestBody GateCategorySaveDto dto
    ) {
        gateCategoryService.save(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(GateCategoryApi.update)
    public ResponseEntity<GateCategoryResponseDto> update(@RequestBody GateCategoryUpdateDto dto) {
        gateCategoryService.update(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping(GateCategoryApi.deleteById)
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        gateCategoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(GateCategoryApi.getById)
    public ResponseEntity<GateCategoryResponseDto> getById(@PathVariable Long id) {
        GateCategoryResponseDto foundDto = gateCategoryService.findById(id);
        return ResponseEntity.ok(foundDto);
    }

    @GetMapping(GateCategoryApi.getAll)
    public ResponseEntity<List<GateCategoryResponseDto>> getAll() {
        List<GateCategoryResponseDto> dtos = gateCategoryService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping(ApiPath.publicPath + "/gate_category/{pageNumber}/{pageSize}/{sort}")
    public ResponseEntity<Page<GateCategoryResponseDto>> getAllPaginated(@PathVariable int pageNumber,
                                                                         @PathVariable int pageSize,
                                                                         @PathVariable String sort) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sort));
        Page<GateCategoryResponseDto> dtos = gateCategoryService.findAllPaginated(pageable);
        return ResponseEntity.ok(dtos);
    }
}
