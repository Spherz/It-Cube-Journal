package com.itcube.journal.utils;

import com.itcube.journal.model.Group;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

@Component
public class GroupBinding {

    public <T> void updateGroups(
        T entity,
        List<Group> currentGroups,
        List<Group> newGroups,
        BiConsumer<T, Group> addGroup,
        BiConsumer<T, Group> removeGroup
    ) {
        for (Group group : new ArrayList<>(currentGroups)) {
            if (!newGroups.contains(group)) {
                removeGroup.accept(entity, group);
            }
        }

        newGroups.forEach(groups -> addGroup.accept(entity, groups));
    }
}
