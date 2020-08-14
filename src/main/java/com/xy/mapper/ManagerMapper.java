package com.xy.mapper;

import com.xy.entity.Manager;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManagerMapper {


    public void addManager(Manager manager);

    public Manager queryBy(String mname);
}
