package com.example.demo2;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping("/population/country")
public class CountryController {

    RestTemplate restTemplate;
    CountryRepository countryRepository;
    ObjectMapper mapper;

    @Autowired
    public CountryController(RestTemplate restTemplate, CountryRepository countryRepository) {//Constructor
        this.restTemplate = restTemplate;
        this.countryRepository = countryRepository;
        mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //ignore no relative field
    }

    /* Create new country and store him into the database */
    @PostMapping(path = "/newCountry")
    public void createCountry(@RequestBody final Country country){
        countryRepository.save(country);
    }

    /* Create new countries and store them into the database */
    @PostMapping(path = "/listCountries")
    public List<Country> addCountry(@RequestBody final List<Country> list) {
        countryRepository.saveAll(list);
        return list;
    }

    /* Respond the number of all countries stored at server */
    @GetMapping(path = "/count")
    public long count(){
        return countryRepository.count();
    }

    /* Respond with a JSON representation of all the exists countries in the database */
    @GetMapping(path = "/list")
    public List<Country> getAll(){
        return countryRepository.findAll();
    }

    /* Respond with a JSON representation of the specific country with the requested ID from the database */
    @GetMapping(path = "/id/{id}")
    public Country getOne(@PathVariable final int id) {
        return countryRepository.findById(id).orElseGet(Country::new);
    }

    /* Delete country with requested ID from the database */
    @DeleteMapping(path = "/id/{id}")
    public void delete(@PathVariable final int id){
        countryRepository.deleteById(id);
    }
}
