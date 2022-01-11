package com.ldp.partyplanner.services;

import com.ldp.partyplanner.data.DietRepository;
import com.ldp.partyplanner.models.Diet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietService {

    private DietRepository repository;

    public DietService(DietRepository dietRepository){
        this.repository = dietRepository;
    }

    public List<Diet> findAll(){
        return repository.findAll();
    }

    public Diet findById(int dietId){
        return repository.findById(dietId);
    }

    public Result<Diet> add(Diet diet){
        Result<Diet> result = validate(diet);
        if(!result.isSuccess()){
            return result;
        }

        diet = repository.add(diet);
        result.setPayload(diet);
        return result;
    }

    public boolean deleteById(int groupId){
        return repository.deleteById(groupId);
    }

    private Result<Diet> validate(Diet diet){
        Result<Diet> result = new Result<>();
        if(diet == null){
            result.addMessage("Diet cannot be null", ResultType.INVALID);
            return result;
        }

        if(Validations.isNullOrBlank(diet.getRequirement())){
            result.addMessage("dietry requirement cannot be empty or blank", ResultType.INVALID);
        }

        return result;
    }
}
