package com.demo.devops.controller;

import com.demo.devops.entity.Country;
import com.demo.devops.repository.CountriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
public class SimpleController {

    @Autowired
    private CountriesRepository countriesRepository;

    @GetMapping("/country/all")
    public List<Country> getAllCountries(){
        log.info("Getting all countries");
        return countriesRepository.findAll();
    }

    @GetMapping("/country")
    public Country getCountryById(@RequestParam("id") Integer id){
        countryIdRequestCheck(id);
        log.info("Getting country by id: {}", id);
        return countriesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found"));
    }

    @GetMapping("/country/{name}")
    public Country getCountryByName(@PathVariable("name") String name){
        countryNameRequestCheck(name);
        log.info("Getting country by id: {}", name);
        return countriesRepository.findByName(name.toLowerCase()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found"));
    }

    @PostMapping("/country/saveOrUpdate")
    public String saveCountry(@RequestBody Country country){
        validateRequest(country);
        log.info("Saving country: {}", country);
        countriesRepository.saveAndFlush(country);
        return "Country is saved successFully";
    }

    private void validateRequest(Country country) {

        if(Objects.isNull(country)){
            String reason = "Request is empty";
            logAndThrow(reason);
        }
        countryIdRequestCheck(country.getId());
        countryNameRequestCheck(country.getName());
        country.setName(country.getName().toLowerCase());
    }

    private void countryNameRequestCheck(String name) {
        if(Objects.isNull(name) || name.isBlank()){
            String reason = "Country name is invalid";
            logAndThrow(reason);
        }
    }

    private void countryIdRequestCheck(Integer id) {
        if(id== null){
            String reason = "ID is invalid";
            logAndThrow(reason);
        }
    }

    private void logAndThrow(String reason) {
        log.warn(reason);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, reason);
    }


}
