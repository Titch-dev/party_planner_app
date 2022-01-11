package com.ldp.partyplanner.services;

import com.ldp.partyplanner.data.AdminRepository;
import com.ldp.partyplanner.data.GuestRepository;
import com.ldp.partyplanner.models.Admin;
import com.ldp.partyplanner.models.Guest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository repository;

    public AdminService(AdminRepository adminRepository){
        this.repository = adminRepository;
    }

    public List<Admin> findAll(){
        return repository.findAll();
    }

    public Admin findById(int adminId){
        return repository.findById(adminId);
    }

    public Result<Admin> add(Admin admin){
        Result<Admin> result = validate(admin);
        if(!result.isSuccess()){
            return result;
        }

        admin = repository.add(admin);
        result.setPayload(admin);
        return result;
    }

    public Result<Admin> update(Admin admin){
        Result<Admin> result = validate(admin);

        if(!result.isSuccess()){
            return result;
        }

        if(!repository.update(admin)) {
            result.addMessage(String.format("adminId: %s - not found", admin.getAdminId()), ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int adminId){
        return repository.deleteById(adminId);
    }

    private Result<Admin> validate(Admin admin){
        Result<Admin> result = new Result<>();
        if(result == null){
            result.addMessage("Admin cannot be null", ResultType.INVALID);
            return result;
        }

        if(Validations.isNullOrBlank(admin.getEmail())){
            result.addMessage("Admin email cannot be blank or null", ResultType.INVALID);
        }

        if(Validations.isNullOrBlank(admin.getFirstName())){
            result.addMessage("Admin must have a firstName", ResultType.INVALID);
        }

        if(Validations.isNullOrBlank(admin.getLastName())){
            result.addMessage("Admin must have a last name", ResultType.INVALID);
        }

        return result;
    }

}
