package com.konzerra.selim_server.domain.gate;

import com.konzerra.selim_server.domain.gate.dto.GateResponseDto;
import com.konzerra.selim_server.domain.gate.dto.GateSaveDto;
import com.konzerra.selim_server.domain.gate.dto.GateUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface GateService {
    List<GateResponseDto> getAllGates();
    GateResponseDto saveGate(GateSaveDto gateSaveDto);
    GateResponseDto updateGate(GateUpdateDto gateUpdateDto);
    void deleteGate(Long id);
    List<GateResponseDto> getGatesByCategory(Long categoryId);
    Page<GateResponseDto> getPaginatedGates(Long categoryId, int pageNumber, int pageSize, String sortDirection, String sortField);
}

