package com.example.project.service;

import com.example.project.domain.model.Group;
import com.example.project.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public void createGroup(Group group){
        this.groupRepository.save(group);
    }
}
