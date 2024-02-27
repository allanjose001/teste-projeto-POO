package com.api.agendafacil.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.api.agendafacil.Exceptions.NomeUBSExistsException;
import com.api.agendafacil.models.UBS;

public interface UBSServiceInterface {
	UBS saveUBS(UBS ubs) throws NomeUBSExistsException ;
	Optional<UBS> findUBSById(UUID id);
	void deleteUBS(UBS ubs);
	List<UBS> getAllUBS();

}
