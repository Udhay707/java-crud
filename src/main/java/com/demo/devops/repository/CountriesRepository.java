package com.demo.devops.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.devops.entity.Country;
import org.springframework.data.jpa.repository.Query;

public interface CountriesRepository extends JpaRepository<Country,Integer>{

    @Query("SELECT c from Country c where c.name= :name")
    Optional<Country> findByName(String name);

    @Query("SELECT c from Country c where c.id= :id")
    Optional<Country> findById(Integer id);
    
}
