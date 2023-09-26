package com.example.regionkommune.kommune.service.implementation;

import com.example.regionkommune.kommune.model.Kommune;
import com.example.regionkommune.kommune.repository.KommuneRepository;
import com.example.regionkommune.kommune.service.interfaces.KommuneServiceInterface;
import com.example.regionkommune.region.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class KommuneImplementation implements KommuneServiceInterface {

    private final RestTemplate restTemplate;

    @Autowired
    private KommuneRepository kommuneRepository;

    private KommuneImplementation(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }



    @Override
    public List<Kommune> getKommunerFromDatabase() {
        return kommuneRepository.findAll();
    }

    @Override
    public Kommune save(Kommune kommune) {
        return kommuneRepository.save(kommune);
    }


    @Override
    public Kommune findKommuneAsEntityUsingKode(String kode) {
        return kommuneRepository.findKommuneAsEntityUsingKode(kode);
    }

    @Override
    public void deleteKommuneUsingKode(String kode) {
        kommuneRepository.deleteById(kode);
    }

    @Override
    public Kommune findKommuneAsEntityUsingNavn(String navn) {
        return kommuneRepository.findKommuneAsEntityUsingNavn(navn);
    }



}
