package com.konzerra.selim_server.domain.gate.impl;

import com.konzerra.selim_server.exception.NotFoundException;
import com.konzerra.selim_server.domain.gate.Gate;
import com.konzerra.selim_server.domain.gate.GateMapper;
import com.konzerra.selim_server.domain.gate.GateRepository;
import com.konzerra.selim_server.domain.gate.GateService;
import com.konzerra.selim_server.domain.gate.dto.GateResponseDto;
import com.konzerra.selim_server.domain.gate.dto.GateSaveDto;
import com.konzerra.selim_server.domain.gate.dto.GateUpdateDto;
import com.konzerra.selim_server.domain.gate_category.GateCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GateServiceImpl implements GateService {

    private final GateRepository gateRepository;
    private final GateCategoryRepository gateCategoryRepository;
    private final GateMapper mapper;

    @Autowired
    public GateServiceImpl(GateRepository gateRepository, GateCategoryRepository gateCategoryRepository, GateMapper mapper) {
        this.gateRepository = gateRepository;
        this.gateCategoryRepository = gateCategoryRepository;
        this.mapper = mapper;
    }

    @Override
    public List<GateResponseDto> getAllGates() {
        List<Gate> gates = gateRepository.findAll();
        return mapper.toDtoList(gates);
    }

    @Override
    public GateResponseDto saveGate(GateSaveDto gateSaveDto) {
        Gate gate = mapper.toEntity(gateSaveDto);
        gate.setCategory(gateCategoryRepository.findById(gateSaveDto.getCategoryId()).orElseThrow(
                () -> new NotFoundException(
                        "GateCategory not found with id: "+gateSaveDto.getCategoryId()
                )
                )
        );
        Gate savedGate = gateRepository.save(gate);
        return mapper.toDto(savedGate);
    }



    @Override
    public GateResponseDto updateGate(GateUpdateDto gateUpdateDto) {
        Gate gate = gateRepository.findById(gateUpdateDto.getId()).orElseThrow(() -> new NotFoundException("Gate not found with id: "+ gateUpdateDto.getId()));
        gate.setName(gateUpdateDto.getName());
        gate.setImage(gateUpdateDto.getImage());
        gate.setCategory(gateCategoryRepository.findById(gateUpdateDto.getCategoryId()).orElseThrow(() -> new NotFoundException("GateCategory not found with id: "+ gateUpdateDto.getId())));
        Gate updatedGate = gateRepository.save(gate);
        return mapper.toDto(updatedGate);
    }

    @Override
    public void deleteGate(Long id) {
        Gate gate = gateRepository.findById(id).orElseThrow(() -> new NotFoundException("Gate not found with id: "+ id));
        gateRepository.delete(gate);
    }

    @Override
    public List<GateResponseDto> getGatesByCategory(Long categoryId) {
        List<Gate> gates = gateRepository.findAllByCategory_Id(categoryId);
        return mapper.toDtoList(gates);
    }



    @Override
    public Page<GateResponseDto> getPaginatedGates(
            Long categoryId,
            int pageNumber,
            int pageSize,
            String sortDirection,
            String sortField) {
        Sort sort = Sort.by(
                sortDirection.equals("ascending") ? Sort.Direction.ASC : Sort.Direction.DESC,
                sortField);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Gate> page = gateRepository.findAllByCategory_Id(categoryId,pageable);



        return page.map(mapper::toDto);
    }
}

