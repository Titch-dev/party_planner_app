package com.ldp.partyplanner.controllers;

import com.ldp.partyplanner.models.Group;
import com.ldp.partyplanner.services.GroupService;
import com.ldp.partyplanner.services.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/group")
public class GroupController {

    private GroupService service;

    public GroupController(GroupService groupService){
        this.service = groupService;
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<Group> findById(@PathVariable int groupId){
        Group group = service.findById(groupId);
        if(group == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(group);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Group group){

        Result<Group> result = service.add(group);

        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<Object> update(@PathVariable int groupId, @RequestBody Group group){
        if(groupId != group.getGroupId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Group> result = service.update(group);
        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteById(@PathVariable int groupId){
        if(service.deleteById(groupId)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
