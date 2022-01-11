package com.ldp.partyplanner.data;

import com.ldp.partyplanner.models.Diet;

import java.util.List;

public interface DietRepository {

    List<Diet> findAll();
    Diet findById(int dietId);
    Diet add(Diet diet);
    boolean deleteById(int dietId);

}
