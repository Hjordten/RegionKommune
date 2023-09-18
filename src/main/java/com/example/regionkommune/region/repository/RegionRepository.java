package com.example.regionkommune.region.repository;

import com.example.regionkommune.region.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegionRepository extends JpaRepository <Region, String> {

    @Query("SELECT r FROM Region r WHERE r.kode = :kode")
    Region findRegionAsEntityUsingKode(String kode);
}
