package com.ldp.partyplanner.services;

import com.ldp.partyplanner.data.GuestRepository;
import com.ldp.partyplanner.models.Guest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {

        private final GuestRepository repository;

        public GuestService(GuestRepository guestRepository){
            this.repository = guestRepository;
        }

        public List<Guest> findAll(){
            return repository.findAll();
        }

        public List<Guest> findAllLeads(){
            return repository.findAllLeads();
        }

        public Guest findById(int guestId){
            return repository.findById(guestId);
        }

        public Result<Guest> add(Guest guest){
            Result<Guest> result = validate(guest);

            if(!result.isSuccess()){
                return result;
            }

            guest = repository.add(guest);
            result.setPayload(guest);
            return result;
        }

        public Result<Guest> update(Guest guest){
            Result<Guest> result = validate(guest);

            if(!result.isSuccess()){
                return result;
            }

            if(!repository.update(guest)) {
                result.addMessage(String.format("guestId: %s - not found", guest.getGuestId()), ResultType.NOT_FOUND);
            }

            return result;

        }

        public boolean deleteById(int guestId){
            return repository.deleteById(guestId);
        }

        private Result<Guest> validate(Guest guest){
            Result<Guest> result = new Result<>();
            if (guest == null){
                result.addMessage("Guest cannot be null", ResultType.INVALID);
                return result;
            }

            if (Validations.isNullOrBlank(guest.getFirstName())){
                result.addMessage("Guest must have a firstName", ResultType.INVALID);
            }

            if (Validations.isNullOrBlank(guest.getLastName())){
                result.addMessage("Guest must have a lastName", ResultType.INVALID);
            }

            if (guest.getGroupId() <= 0){
                result.addMessage("Guest must have a groupId", ResultType.INVALID);
            }

            return result;
        }

}
