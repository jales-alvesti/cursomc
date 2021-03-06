package com.com.jalesalvesti.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.com.jalesalvesti.cursomc.domain.Categoria;
import com.com.jalesalvesti.cursomc.dto.CategoriaDTO;
import com.com.jalesalvesti.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)	
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();		
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id){
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();	
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)	
	public ResponseEntity<Categoria> delete(@PathVariable Integer id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();	
	}
	
	@RequestMapping(method=RequestMethod.GET)	
	public ResponseEntity<List<CategoriaDTO>> FindAll() {
		List<CategoriaDTO> list = service.findAll().stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());	
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)	
	public ResponseEntity<Page<CategoriaDTO>> FindPage(
		 	@RequestParam(value="page",defaultValue="0") Integer page, 
		 	@RequestParam(value="linesPerPage",defaultValue="24") Integer linesPerPage, 
		 	@RequestParam(value="orderBy",defaultValue="nome") String orderBy, 
		 	@RequestParam(value="direction",defaultValue="ASC") String direction) {
		
		Page<CategoriaDTO> list = service.findPage(page, linesPerPage, orderBy, direction).map(obj -> new CategoriaDTO(obj));	
		return ResponseEntity.ok().body(list);
	}
}
