package com.unchk.backend.trainers.repository;

import com.unchk.backend.trainers.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository  extends JpaRepository<Trainer, Long> {
    Optional<Trainer> findByEmail(String email);
}
