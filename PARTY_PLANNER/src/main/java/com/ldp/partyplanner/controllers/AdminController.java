package com.ldp.partyplanner.controllers;

import com.ldp.partyplanner.models.Admin;
import com.ldp.partyplanner.services.AdminService;
import com.ldp.partyplanner.services.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService adminService){
        this.service = adminService;
    }

    @GetMapping
    public List<Admin> findAll(){
        return service.findAll();
    }

    @GetMapping("/{adminId}")
    public ResponseEntity<Admin> findById(@PathVariable int adminId){
        Admin admin = service.findById(adminId);
        if(admin == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(admin);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Admin admin){

        Result<Admin> result = service.add(admin);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);
    }

    @PutMapping("/{adminId}")
    public ResponseEntity<Object> update(@PathVariable int adminId, @RequestBody Admin admin){
        if(adminId != admin.getAdminId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Admin> result = service.update(admin);
        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{adminId}")
    public ResponseEntity<Void> deleteById(@PathVariable int adminId){
        if(service.deleteById(adminId)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
