package com.api.agendafacil.facade;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.agendafacil.models.UBS;
import com.api.agendafacil.services.UBSService;
import com.api.agendafacil.services.UsuarioService;

@Service
public class Facade {
	
	//UBS--------------------------------------------------------------------------------------------------
	@Autowired
	private UBSService ubsService;
	
	public UBS save(UBS ubs) {
		//caso catch do erro (precisa ser pensado a respeito)
		/*try {
			return ubsService.save(ubs);
		} catch (Exception e) {
			throw new RuntimeException("erro ao salvar", e)
		}
		*/
		return ubsService.save(ubs);
	}
	
	public List<UBS> findAll() {
		return ubsService.findAll();
	}

	public Optional<UBS> findById(UUID id) {
		return ubsService.findById(id);
	}

	public void delete(UBS ubs) {
		ubsService.delete(ubs);
	}
	
	//metodos como existsByNome não são necessarios aqui
	//Usuario----------------------------------------------------------
	private UsuarioService usuarioService;
	
}
