package com.example.regionkommune.kommune.repository;

import com.example.regionkommune.kommune.model.Kommune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface KommuneRepository extends JpaRepository<Kommune, String> {

    @Query("SELECT k FROM Kommune k WHERE k.kode = :kode")
    Kommune findKommuneAsEntityUsingKode(String kode);

    Kommune findKommuneByNavn(String navn);

}
