package com.kindsonthegenius.inventoryms_springboot_api.services;

import com.kindsonthegenius.inventoryms_springboot_api.models.CommonObject;
import com.kindsonthegenius.inventoryms_springboot_api.repositories.CommonObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonObjectService {

    private CommonObjectRepository commonObjectRepository;

    @Autowired
    public CommonObjectService(CommonObjectRepository commonObjectRepository) {
        this.commonObjectRepository = commonObjectRepository;
    }

    public List<CommonObject> getAllCommonObjects(){
        return commonObjectRepository.findAll();
    }

    public List<CommonObject> findByObjectType(String objectType) {
        return commonObjectRepository.findByObjectType(objectType);
    }

    public CommonObject getCommonObjectById(Long id) {
        return commonObjectRepository.findById(id).orElse(null);
    }

    public CommonObject save(CommonObject commonObject) {
        return commonObjectRepository.save(commonObject);
    }

    public void deleteCommonObject(Long id){
        commonObjectRepository.deleteById(id);
    }

}
