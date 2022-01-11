package com.ldp.partyplanner.services;

import com.ldp.partyplanner.data.EventRepository;
import com.ldp.partyplanner.models.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository eventRepository){
        this.repository = eventRepository;
    }

    public List<Event> findAll(){
        return repository.findAll();
    }

    public Event findById(int eventId){
        return repository.findById(eventId);
    }

    public Result<Event> add(Event event){
        Result<Event> result = validate(event);
        if(!result.isSuccess()){
            return result;
        }

        event = repository.add(event);
        result.setPayload(event);
        return result;

    }

    public Result<Event> update(Event event){
        Result<Event> result = validate(event);

        if(!result.isSuccess()){
            return result;
        }

        if(!repository.update(event)){
            result.addMessage(String.format("eventId: %s - not found", event.getEventId()), ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int eventId){
        return repository.deleteById(eventId);
    }

    private Result<Event> validate(Event event){
        Result<Event> result = new Result<>();
        if(event == null){
            result.addMessage("Event cannot be null", ResultType.INVALID);
            return result;
        }

        if(Validations.isNullOrBlank(event.getEventName())){
            result.addMessage("The Event must have a name", ResultType.INVALID);
        }

        if(Validations.isNullOrBlank(event.getVenueAddress())){
            result.addMessage("The Event must have an addresss", ResultType.INVALID);
        }

        // TODO: validate timestamp is correct format
        return result;

    }

}
