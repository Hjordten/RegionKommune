package com.example.regionkommune.region.repository;

import com.example.regionkommune.region.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionRepository extends JpaRepository <Region, String> {

}
