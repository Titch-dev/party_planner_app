package com.ldp.partyplanner.services;

import com.ldp.partyplanner.data.GroupRepository;
import com.ldp.partyplanner.models.Group;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    private GroupRepository repository;

    public GroupService(GroupRepository groupRepository){
        this.repository = groupRepository;
    }

    public Group findById(int groupId){
        return repository.findById(groupId);
    }

    public Result<Group> add(Group group){
        Result<Group> result = validate(group);

        if(!result.isSuccess()){
            return result;
        }

        group = repository.add(group);
        result.setPayload(group);
        return result;
    }

    public Result<Group> update(Group group){
        Result<Group> result = validate(group);

        if(!result.isSuccess()){
            return result;
        }

        if(!repository.update(group)){
            result.addMessage(String.format("groupId: %s - not found", group.getGroupId()), ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int groupId){
        return repository.deleteById(groupId);
    }

    private Result<Group> validate(Group group){
        Result<Group> result = new Result<>();
        if(group == null){
            result.addMessage("Group cannot be null", ResultType.INVALID);
            return result;
        }

        if(Validations.isNullOrBlank(group.getEmail())){
            result.addMessage("Group email cannot be null or empty", ResultType.INVALID);
        }

        if(Validations.isNullOrBlank(group.getPassword())){
            result.addMessage("Group password cannot be null or empty", ResultType.INVALID);
        }

        if(group.getGroupSize() <= 0){
            result.addMessage("Group must have a size greater than 1", ResultType.INVALID);
        }

        return result;
    }

}
