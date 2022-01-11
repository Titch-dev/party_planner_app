package com.ldp.partyplanner.data;

import com.ldp.partyplanner.models.Group;

public interface GroupRepository {

    Group findById(int groupId);
    Group add(Group group);
    boolean update(Group group);
    boolean deleteById(int groupId);

}
