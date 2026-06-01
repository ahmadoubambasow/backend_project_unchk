package com.unchk.backend.promotions.repository;

import com.unchk.backend.promotions.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    Optional<Promotion> findByName(String name);
}
