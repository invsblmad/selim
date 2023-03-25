package com.konzerra.selim_server.domain.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    public List<Review> findAllByGateCategory_Id(Long gateCategory_id);
    Page<Review> findAllByGateCategory_Id(Long gateCategory_id, Pageable pageable);
}
