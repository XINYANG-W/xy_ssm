package com.xy.service;

import com.xy.entity.Psales;

import java.util.List;
import java.util.Map;

public interface IPsalesService {

   public  List<Psales> selSales();

    public  void addPsales(List<Psales> resultList);


    public List<Map<String,Object>> PsalesCurrentMonth(String substring);

    public List<Map<String, Object>> queryTeamMember_one(int i);

    public void RemoveMemeber(int gid);

    public List<Map<String, Object>> queryAllTeam();

    public void UpdateTeam(int gid, int tid);

    public void delGid(int gid);
}
