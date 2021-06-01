package com.com.jalesalvesti.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.jalesalvesti.cursomc.domain.Categoria;
import com.com.jalesalvesti.cursomc.repositories.CategoriaRepository;
import com.com.jalesalvesti.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria Buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName(), null));
 		
	}
}
