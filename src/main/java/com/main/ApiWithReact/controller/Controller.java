package com.main.ApiWithReact.controller;


import com.main.ApiWithReact.entity.Employees;
import com.main.ApiWithReact.service.ServiceLayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(value = {"http://localhost:3000"})
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private static final String page = "list-employees";
    private final ServiceLayer serviceLayer;
     Controller(ServiceLayer serviceLayer)
     {this.serviceLayer = serviceLayer;}


    //..................................................................................................................



    @GetMapping("/data")
    public List<Employees> getData() {
         return serviceLayer.getEmpService();

    }

    //..................................................................................................................
    @GetMapping("/getEmpByName/{name}")
    public List<Employees> getEmpByName(@PathVariable("name") String name){
    logger.info(name);
    logger.info(serviceLayer.getbyName(name).toString());

       return serviceLayer.getbyName(name);                    //** get by Name

    }
    //..................................................................................................................
    @GetMapping("/deleteEmpById")
    public ModelAndView  deleteById( @RequestParam String id){
        logger.info(id);
        ModelAndView mav = new ModelAndView(page);                      //** Delete By Id
        mav.addObject("emp", serviceLayer.deleteById(id));
        return mav;
    }
    //..................................................................................................................

    @GetMapping("/getEmpById")
    public ModelAndView getEmpById(@RequestParam String id){
        logger.info(id);
        ModelAndView mav = new ModelAndView(page);                      //**Get By ID API
        mav.addObject("emp",serviceLayer.getById(id));
        return mav;
    }
    //..................................................................................................................
    @GetMapping("/addEmp")
    public ModelAndView addEmp(@RequestParam String id,@RequestParam String name, @RequestParam String address){
        ModelAndView mav = new ModelAndView(page);
        Employees emp = new Employees(id,name,address);
        logger.info("{}",emp);                                              //**Post API
        mav.addObject("emp",emp);
        serviceLayer.saveEmp(emp);
        return mav;
    }
//......................................................................................................................

}
