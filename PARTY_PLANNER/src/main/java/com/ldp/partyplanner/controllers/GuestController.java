package com.ldp.partyplanner.controllers;

import com.ldp.partyplanner.models.Guest;
import com.ldp.partyplanner.services.GuestService;
import com.ldp.partyplanner.services.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/guest")
public class GuestController {

    private final GuestService service;

    public GuestController(GuestService guestService){
        this.service = guestService;
    }

    @GetMapping
    public List<Guest> findAll(){
        return service.findAll();
    }

    @GetMapping("/leads")
    public List<Guest> findAllLeads(){
        return service.findAllLeads();
    }

    @GetMapping("/{guestId}")
    public ResponseEntity<Guest> findById(@PathVariable int guestId){
        Guest guest = service.findById(guestId);
        if(guest == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(guest);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Guest guest){

        Result<Guest> result = service.add(guest);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{guestId}")
    public ResponseEntity<Object> update(@PathVariable int guestId, @RequestBody Guest guest){
        if(guestId != guest.getGuestId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Guest> result = service.update(guest);
        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{guestId}")
    public ResponseEntity<Void> deleteById(@PathVariable int guestId){
        if(service.deleteById(guestId)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
