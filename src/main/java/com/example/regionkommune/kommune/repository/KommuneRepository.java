package com.example.regionkommune.kommune.repository;

import com.example.regionkommune.kommune.model.Kommune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface KommuneRepository extends JpaRepository<Kommune, String> {

    @Query("SELECT k FROM Kommune k WHERE k.kode = :kode")
    Kommune findKommuneAsEntityUsingKode(String kode);
    @Query("SELECT k FROM Kommune k WHERE k.navn = :navn")
    Kommune findKommuneAsEntityUsingNavn(String navn);
}
