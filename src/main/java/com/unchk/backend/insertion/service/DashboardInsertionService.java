package com.unchk.backend.insertion.service;

import com.unchk.backend.insertion.dto.DashboardInsertionResponseDTO;
import com.unchk.backend.insertion.entity.GraduateInsertionStatus;
import com.unchk.backend.insertion.entity.InternshipStatus;
import com.unchk.backend.insertion.repository.GraduateInsertionRepository;
import com.unchk.backend.insertion.repository.InternshipRepository;
import com.unchk.backend.insertion.repository.PartnerRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardInsertionService {

    private final PartnerRepository
            partnerRepository;

    private final InternshipRepository
            internshipRepository;

    private final GraduateInsertionRepository
            graduateInsertionRepository;

    public DashboardInsertionResponseDTO
    getDashboard() {

        return DashboardInsertionResponseDTO

                .builder()

                .partners(
                        partnerRepository.count()
                )

                .internships(
                        internshipRepository.count()
                )

                .ongoingInternships(

                        internshipRepository

                                .findAll()

                                .stream()

                                .filter(i ->

                                        i.getStatus() ==
                                                InternshipStatus.ONGOING
                                )

                                .count()
                )

                .completedInternships(

                        internshipRepository

                                .findAll()

                                .stream()

                                .filter(i ->

                                        i.getStatus() ==
                                                InternshipStatus.COMPLETED
                                )

                                .count()
                )

                .insertions(
                        graduateInsertionRepository.count()
                )

                .salaried(

                        graduateInsertionRepository

                                .findAll()

                                .stream()

                                .filter(i ->

                                        i.getStatus() ==
                                                GraduateInsertionStatus.SALARIED
                                )

                                .count()
                )

                .autoEmployed(

                        graduateInsertionRepository

                                .findAll()

                                .stream()

                                .filter(i ->

                                        i.getStatus() ==
                                                GraduateInsertionStatus.AUTO_EMPLOYED
                                )

                                .count()
                )

                .furtherStudies(

                        graduateInsertionRepository

                                .findAll()

                                .stream()

                                .filter(i ->

                                        i.getStatus() ==
                                                GraduateInsertionStatus.FURTHER_STUDIES
                                )

                                .count()
                )

                .unemployed(

                        graduateInsertionRepository

                                .findAll()

                                .stream()

                                .filter(i ->

                                        i.getStatus() ==
                                                GraduateInsertionStatus.UNEMPLOYED
                                )

                                .count()
                )

                .build();
    }
}