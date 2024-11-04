package com.kindsonthegenius.inventoryms_springboot_api.security.repositories;

import com.kindsonthegenius.inventoryms_springboot_api.security.models.SecureToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecureTokenRepository extends JpaRepository<SecureToken, Long > {
    SecureToken findByToken(final String token);
    void removeByToken(SecureToken token);
}
