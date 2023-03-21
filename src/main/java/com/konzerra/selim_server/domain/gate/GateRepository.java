package com.konzerra.selim_server.domain.gate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GateRepository extends JpaRepository<Gate, Long> {
    public List<Gate> findAllByCategory_Id(Long category_id);
    public Page<Gate> findAllByCategory_Id(Long category_id, Pageable pageable);
}
