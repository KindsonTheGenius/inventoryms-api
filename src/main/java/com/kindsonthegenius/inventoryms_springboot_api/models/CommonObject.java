package com.kindsonthegenius.inventoryms_springboot_api.models;

import com.kindsonthegenius.inventoryms_springboot_api.security.models.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class CommonObject extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String objectType;
	private String description;
	private String details;
}
