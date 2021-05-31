package com.com.jalesalvesti.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.jalesalvesti.cursomc.domain.Produto;
import com.com.jalesalvesti.cursomc.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	public Produto Buscar(Integer id) {
		Optional<Produto> obj = repo.findById(id); 
		return obj.orElse(null); 		
	}
}
