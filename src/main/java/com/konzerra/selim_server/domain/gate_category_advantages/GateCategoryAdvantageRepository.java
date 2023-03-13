package com.konzerra.selim_server.domain.gate_category_advantages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GateCategoryAdvantageRepository extends JpaRepository<GateCategoryAdvantage, Long> {
}
