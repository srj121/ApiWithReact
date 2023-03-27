package com.main.ApiWithReact.controller;


import com.main.ApiWithReact.entity.Employees;
import com.main.ApiWithReact.service.ServiceLayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@RestController
//@CrossOrigin(value = {"http://localhost:3000"})
@CrossOrigin
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
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
    @DeleteMapping("/deleteEmpById/{id}")
    public ResponseEntity<Employees>  deleteById( @PathVariable String id){
        logger.info(id);
        serviceLayer.deleteById(id);
        return new  ResponseEntity<>(HttpStatus.OK);
    }
    //..................................................................................................................

    @GetMapping("/getEmpById")
    public ModelAndView getEmpById(@RequestParam String id){
        logger.info(id);
        ModelAndView mav = new ModelAndView();                      //**Get By ID API
        mav.addObject("emp",serviceLayer.getById(id));
        return mav;
    }
    //..................................................................................................................
    @PostMapping("/addEmp/{id}/{name}/{address}")
    public ResponseEntity<Employees> addEmp(@PathVariable String id, @PathVariable String name, @PathVariable String address){
        System.out.println(id);
        System.out.println(name);
        System.out.println(address);
        Employees emp = new Employees(id, name, address);
        logger.info("{}",emp);                                              //**Post API
        serviceLayer.saveEmp(emp);
        return new ResponseEntity<>(emp, HttpStatus.CREATED);

    }
//......................................................................................................................

}
