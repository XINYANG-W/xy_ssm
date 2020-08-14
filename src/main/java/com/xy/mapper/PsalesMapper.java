package com.xy.mapper;

import com.xy.entity.Psales;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PsalesMapper {

    public List<Psales> selSales();

    public  void addPsales(List<Psales> resultList);

    public List<Map<String,Object>> PsalesCurrentMonth(String substring);

    public List<Map<String, Object>> queryTeamMember_one(int i);

    public void RemoveMemeber(int gid);

    public List<Map<String, Object>> queryAllTeam();

    public void UpdateTeam(@Param("gid") int gid, @Param("tid") int tid);

    public void delGid(int gid);
}
