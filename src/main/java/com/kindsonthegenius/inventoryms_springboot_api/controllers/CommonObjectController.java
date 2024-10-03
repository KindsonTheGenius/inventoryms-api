package com.kindsonthegenius.inventoryms_springboot_api.controllers;

import com.kindsonthegenius.inventoryms_springboot_api.models.CommonObject;
import com.kindsonthegenius.inventoryms_springboot_api.services.CommonObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommonObjectController {

    private final CommonObjectService commonObjectService;

    @Autowired
    public CommonObjectController(CommonObjectService commonObjectService) {
        this.commonObjectService = commonObjectService;
    }

    @GetMapping("/commonObjects")
    public List<CommonObject> getCommonObjects(){
        return commonObjectService.getAllCommonObjects();
    }

    @GetMapping("/commonObjects/objectType")
    public List<CommonObject> getByObjectType(String objectType){
        return commonObjectService.findByObjectType(objectType);
    }

    @GetMapping("/commonObject/{id}")
    public CommonObject getCommonObject(@PathVariable("id") Long id){
        return commonObjectService.getCommonObjectById(id);
    }

    @PutMapping("/commonObject/{id}")
    public CommonObject updateCommonObject(@RequestBody() CommonObject commonObject, @PathVariable("id") Long id){
        return commonObjectService.save(commonObject);
    }

    @PostMapping("/commonObjects")
    public CommonObject addNew(@RequestBody() CommonObject commonObject){
        return commonObjectService.save(commonObject);
    }

    @DeleteMapping("/commonObject/{id}")
    public void deleteCommonObject(@PathVariable("id") Long id){
        commonObjectService.deleteCommonObject(id);
    }

}
