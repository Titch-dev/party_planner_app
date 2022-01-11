package com.ldp.partyplanner.controllers;

import com.ldp.partyplanner.models.Event;
import com.ldp.partyplanner.services.EventService;
import com.ldp.partyplanner.services.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/event")
public class EventController {

    public final EventService service;

    public EventController(EventService eventService){
        this.service = eventService;
    }

    @GetMapping
    public List<Event> findAll(){
        return service.findAll();
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> findById(@PathVariable int eventId){
        Event event = service.findById(eventId);
        if(event == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Event event){

        Result<Event> result = service.add(event);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<Object> update(@PathVariable int eventId, @RequestBody Event event){
        if(eventId != event.getEventId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Event> result = service.update(event);
        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);

    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteById(@PathVariable int eventId){
        if(service.deleteById(eventId)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
