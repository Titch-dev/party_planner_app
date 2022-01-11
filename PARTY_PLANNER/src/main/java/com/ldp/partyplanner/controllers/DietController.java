package com.ldp.partyplanner.controllers;

import com.ldp.partyplanner.models.Diet;
import com.ldp.partyplanner.services.DietService;
import com.ldp.partyplanner.services.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/diet")
public class DietController {

    private DietService service;

    public DietController(DietService dietService){
        this.service = dietService;
    }

    @GetMapping
    public List<Diet> findAll(){
        return service.findAll();
    }

    @GetMapping("/{dietId}")
    public ResponseEntity<Diet> findById(@PathVariable int dietId){
        Diet diet = service.findById(dietId);
        if(diet == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(diet);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Diet diet){
        Result<Diet> result = service.add(diet);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(),HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);

    }

    @DeleteMapping("/{dietId}")
    public ResponseEntity<Void> deleteById(@PathVariable int dietId){
        if(service.deleteById(dietId)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
