package com.itcube.journal.util;

import com.itcube.journal.model.Groups;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

@Component
public class GroupBinding {

    public <T> void updateGroups(
        T entity,
        List<Groups> currentGroups,
        List<Groups> newGroups,
        BiConsumer<T, Groups> addGroup,
        BiConsumer<T, Groups> removeGroup
    ) {
        for (Groups group : new ArrayList<>(currentGroups)) {
            if (!newGroups.contains(group)) {
                removeGroup.accept(entity, group);
            }
        }

        newGroups.forEach(groups -> addGroup.accept(entity, groups));
    }
}
