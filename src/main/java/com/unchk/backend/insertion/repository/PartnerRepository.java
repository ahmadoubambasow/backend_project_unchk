package com.unchk.backend.insertion.repository;

import com.unchk.backend.insertion.entity.Partner;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartnerRepository
        extends JpaRepository<Partner, Long> {



}