package com.konzerra.selim_server.domain.gate_category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GateCategoryRepository extends JpaRepository<GateCategory, Long> {
}