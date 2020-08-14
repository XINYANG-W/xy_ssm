package com.xy.service;

import com.xy.entity.Psales;
import com.xy.mapper.PsalesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PsalesServiceImpl implements IPsalesService {

    @Autowired
    private PsalesMapper psalesMapper;

    @Override
    public List<Psales> selSales() {
        return psalesMapper.selSales();
    }

    @Override
    public void addPsales(List<Psales> resultList) {
         psalesMapper.addPsales(resultList);
    }

    @Override
    public List<Map<String, Object>> PsalesCurrentMonth(String substring) {
        return psalesMapper.PsalesCurrentMonth(substring);
    }

    @Override
    public List<Map<String, Object>> queryTeamMember_one(int i) {
        return psalesMapper.queryTeamMember_one(i);

    }

    @Override
    public void RemoveMemeber(int gid) {
         psalesMapper.RemoveMemeber(gid);
    }

    @Override
    public List<Map<String, Object>> queryAllTeam() {
        return  psalesMapper.queryAllTeam();
    }

    @Override
    public void UpdateTeam(int gid, int tid) {
       psalesMapper.UpdateTeam(gid,tid);
    }

    @Override
    public void delGid(int gid) {
        psalesMapper.delGid(gid);
    }


}
