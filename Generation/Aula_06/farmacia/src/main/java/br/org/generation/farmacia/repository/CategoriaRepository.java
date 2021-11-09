package br.org.generation.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.farmacia.model.Categoria;

@Repository // indica que a classe se trata de um repositório
public interface CategoriaRepository extends JpaRepository <Categoria, Long>{
	
	public List <Categoria> findAllByCategoriaContainingIgnoreCase(String categoria); // Faz a busca independentemente de ser maiúscula ou minuscula

}
