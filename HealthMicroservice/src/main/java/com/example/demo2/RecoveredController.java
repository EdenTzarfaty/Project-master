package com.example.demo2;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping("/health/recovered")
public class RecoveredController {

     RestTemplate restTemplateRecovered;
     RecoveredRepository recoveredRepository;
     ObjectMapper mapperRecovered;

    @Autowired
    public RecoveredController(RestTemplate restTemplateRecovered, RecoveredRepository recoveredRepository) {
        this.restTemplateRecovered = restTemplateRecovered;
        this.recoveredRepository = recoveredRepository;
        mapperRecovered = new ObjectMapper();
    }

    /* Store recovered from list to the database */
    @PostMapping(path = "/addRecoveredList") //
    public List<Recovered> addRecoveredList(@RequestBody final List<Recovered> list){
        recoveredRepository.saveAll(list);
        return list;
    }

    /* Return all recovered that stored in the database */
    @GetMapping(path = "/getRecoveredList")
    public List<Recovered> returnRecovered(){
        return recoveredRepository.findAll();
    }

    /* Return specific recovered with requested ID */
    @GetMapping(path = "/id/{recoveredId}") //
    public Recovered findRecovered(@PathVariable final int recoveredId) {
        return recoveredRepository.findById(recoveredId).orElseGet(Recovered::new);
    }

    /* Delete recovered with requested ID from the database */
    @DeleteMapping(path = "/id/{id}")
    public void delete(@PathVariable final int id){
        recoveredRepository.deleteById(id);
    }
}
