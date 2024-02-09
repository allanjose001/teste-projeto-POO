package com.api.agendafacil.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.api.agendafacil.models.UBS;

public interface UBSServiceInterface {
	UBS save(UBS ubs);
	List<UBS> findAll();
	Optional<UBS> findById(UUID id);
	void delete(UBS ubs);

}
