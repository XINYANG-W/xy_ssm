package com.xy.controller;

import com.xy.entity.Manager;
import com.xy.service.IManagerService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManagerController {
    @Autowired
    private IManagerService managerService;
    @RequestMapping("/addManager")
    public String addManager(Manager manager){
        String s = DigestUtils.md5Hex(manager.getMname() + manager.getMpass());
        manager.setMpass(s);
        System.out.println(s+":::::::::::::");
        managerService.addManager(manager);

        return "forward:/success.jsp";
    }
    @RequestMapping("/login")
    public String login(Manager manager){
        String mname = manager.getMname();
        String s = DigestUtils.md5Hex(manager.getMname() + manager.getMpass());

        Manager manager1 =managerService.queryBy(mname);
        String mpass = manager1.getMpass();
        System.out.println(mpass+":::::::::::::::");
        System.out.println(s+";;;;;;;;;;;;;;;;;;");
        if (s.equals(mpass)){
            return "forward:/success.jsp";
        }
        return "forward:/error.jsp";
    }

}
