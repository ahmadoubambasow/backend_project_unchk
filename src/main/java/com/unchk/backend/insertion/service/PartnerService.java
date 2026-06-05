package com.unchk.backend.insertion.service;

import com.unchk.backend.insertion.dto.*;
import com.unchk.backend.insertion.entity.Partner;
import com.unchk.backend.insertion.repository.PartnerRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerRepository repository;

    public PartnerResponseDTO create(
            PartnerRequestDTO request
    ) {

        Partner partner = Partner.builder()

                .name(
                        request.getName()
                )

                .contactPerson(
                        request.getContactPerson()
                )

                .phone(
                        request.getPhone()
                )

                .email(
                        request.getEmail()
                )

                .address(
                        request.getAddress()
                )

                .sector(
                        request.getSector()
                )

                .description(
                        request.getDescription()
                )

                .build();

        partner = repository.save(
                partner
        );

        return map(partner);
    }

    public List<PartnerResponseDTO> getAll() {

        return repository.findAll()

                .stream()

                .map(this::map)

                .toList();
    }

    public PartnerResponseDTO getById(
            Long id
    ) {

        return map(

                repository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Partenaire introuvable"
                                )
                        )
        );
    }

    public PartnerResponseDTO update(

            Long id,

            PartnerRequestDTO request

    ) {

        Partner partner =

                repository

                        .findById(id)

                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Partenaire introuvable"
                                )
                        );

        partner.setName(
                request.getName()
        );

        partner.setContactPerson(
                request.getContactPerson()
        );

        partner.setPhone(
                request.getPhone()
        );

        partner.setEmail(
                request.getEmail()
        );

        partner.setAddress(
                request.getAddress()
        );

        partner.setSector(
                request.getSector()
        );

        partner.setDescription(
                request.getDescription()
        );

        partner = repository.save(
                partner
        );

        return map(partner);
    }

    public void delete(
            Long id
    ) {

        repository.deleteById(id);
    }

    private PartnerResponseDTO map(
            Partner partner
    ) {

        return PartnerResponseDTO

                .builder()

                .id(
                        partner.getId()
                )

                .name(
                        partner.getName()
                )

                .contactPerson(
                        partner.getContactPerson()
                )

                .phone(
                        partner.getPhone()
                )

                .email(
                        partner.getEmail()
                )

                .address(
                        partner.getAddress()
                )

                .sector(
                        partner.getSector()
                )

                .description(
                        partner.getDescription()
                )

                .build();
    }
}