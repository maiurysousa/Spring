package br.org.generation.farmacia.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.org.generation.farmacia.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Long> {
	public List <Produto> findAllByNomeContainingIgnoreCase(String nome);
	// Busca nome do produto e do laboratório
	// MySQL: select * from tb_produtos where nome = "produto" and laboratorio = "laboratorio";
	 
	public List <Produto> findByNomeAndLaboratorio(String nome, String laboratorio);	
	// Método Personalizado - Busca por Nome do Produto e Nome do Laboratório
 	// MySQL: select * from tb_produtos where nome = "produto" and laboratorio = "laboratorio";

	public List <Produto> findByNomeOrLaboratorio(String nome, String laboratorio);
	// Busca por Nome do Produto ou do Laboratório
	// MySQL: select * from tb_produtos where nome = "produto" or laboratorio = "laboratorio";
	 
	@Query(value = "select * from tb_produtos where preco between :inicio and :fim", nativeQuery = true) // consulta nativa, ou seja SQL raiz
	public List <Produto> buscarProdutosEntre(@Param("inicio") BigDecimal inicio, @Param("fim") BigDecimal fim); //@param mapeia os parametros
	
	public List <Produto> findByPrecoGreaterThanOrderByPreco (BigDecimal preco);
	
	public List <Produto> findByPrecoLessThanOrderByPrecoDesc (BigDecimal preco);
	
}
