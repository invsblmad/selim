package com.konzerra.selim_server.domain.gate;

import com.konzerra.selim_server.domain.gate.dto.GateResponseDto;
import com.konzerra.selim_server.domain.gate.dto.GateSaveDto;
import com.konzerra.selim_server.domain.gate.dto.GateUpdateDto;
import com.konzerra.selim_server.domain.gate_category.GateCategoryApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class GateController {

    private final GateService gateService;

    @Autowired
    public GateController(GateService gateService) {
        this.gateService = gateService;
    }

    @GetMapping(GateApi.getAll)
    public List<GateResponseDto> getAllGates() {
        return gateService.getAllGates();
    }

    @PostMapping(GateApi.save)
    public ResponseEntity<GateResponseDto> saveGate(@RequestBody GateSaveDto gateSaveDto) {
        gateService.saveGate(gateSaveDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(GateApi.update)
    public GateResponseDto updateGate(@RequestBody GateUpdateDto gateUpdateDto) {
        return gateService.updateGate(gateUpdateDto);
    }

    @DeleteMapping(GateApi.deleteById)
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        gateService.deleteGate(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(GateApi.getAllByCategoryId)
    public List<GateResponseDto> getGatesByCategory(@PathVariable Long categoryId) {
        return gateService.getGatesByCategory(categoryId);
    }

    @GetMapping(GateApi.getAllPaginated)
    public Page<GateResponseDto> getPaginatedGates(
            @PathVariable Long categoryId,
            @PathVariable int pageNumber,
            @PathVariable int pageSize,
            @PathVariable String sortDirection,
            @PathVariable String sortField
    ) {
        return gateService.getPaginatedGates(categoryId,pageNumber, pageSize, sortDirection, sortField);
    }

}

