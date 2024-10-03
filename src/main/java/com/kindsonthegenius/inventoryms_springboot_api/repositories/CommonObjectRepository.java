package com.kindsonthegenius.inventoryms_springboot_api.repositories;

import com.kindsonthegenius.inventoryms_springboot_api.models.CommonObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonObjectRepository extends JpaRepository<CommonObject, Long> {
    List<CommonObject> findByObjectType(String objectName);
}
