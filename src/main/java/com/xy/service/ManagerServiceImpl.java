package com.xy.service;

import com.xy.entity.Manager;
import com.xy.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements IManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public void addManager(Manager manager) {

        managerMapper.addManager(manager);
    }

    @Override
    public Manager queryBy(String mname) {
        return managerMapper.queryBy(mname);
    }
}
