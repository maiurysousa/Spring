package br.org.generation.farmacia.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.farmacia.model.Produto;
import br.org.generation.farmacia.repository.ProdutoRepository;

@RestController //informa p/ o Spring q a classe é uma controladora
@RequestMapping("/produtos") // URI pela qual a classe será acessada - caminho da requisição
@CrossOrigin(origins = "*", allowedHeaders = "*")// Classe vai aceitar requisições de qualquer origem
public class ProdutoController {

	@Autowired // garante que os serviçoes da interface seja acessado a patir do controller
	private ProdutoRepository produtoRepository;
	
	@GetMapping //retorna a lista com todos os recursos que estão no endereço produtos
	public ResponseEntity<List<Produto>> getAll(){ 
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	@GetMapping("/{id}") //retorna um recurso específico indentificado pelo id
	public ResponseEntity<Produto> getById(@PathVariable long id){
		return produtoRepository.findById(id)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")//retorna um recurso específico indentificado pelo nome
	public ResponseEntity<List<Produto>> getByTitulo(@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping //insere novo recurso
	public ResponseEntity<Produto> postProduto(@RequestBody Produto produto){ 
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}
	
	@PutMapping // atualiza um recurso
	public ResponseEntity<Produto> putProduto(@Valid @RequestBody Produto produto) { 
	/*RequestBoody pega objeto que está sendo mandado para a requisição do put e @Valid checa 
	 *se o objeto passado está dentro das regras criadas na model*/
		
		return produtoRepository.findById(produto.getId())
				.map(resposta -> ResponseEntity.ok(produtoRepository.save(produto))) // já joga dentro do ok a resposta do método
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")  //deleta um recurso pelo id, caso o recurso não exista retorna um notFound
	public ResponseEntity<?> deleteProduto(@PathVariable long id) { //informa qual o id do produto que quero apagar
		return produtoRepository.findById(id)
				.map(resposta -> {
					produtoRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}/oulaboratorio/{laboratorio}")	// Consulta por nome ou laboratório
	public ResponseEntity<List<Produto>> getByNomeOuLaboratorio(@PathVariable String nome, @PathVariable String laboratorio){
		return ResponseEntity.ok(produtoRepository.findByNomeOrLaboratorio(nome, laboratorio));
	}
	
	@GetMapping("/nome/{nome}/elaboratorio/{laboratorio}")// Consulta por nome e laboratório
	public ResponseEntity<List<Produto>> getByNomeELaboratorio(@PathVariable String nome, @PathVariable String laboratorio){
		return ResponseEntity.ok(produtoRepository.findByNomeAndLaboratorio(nome, laboratorio));
	}
	
	@GetMapping("/preco_inicial/{inicio}/preco_final/{fim}") // Consulta por preço entre dois valores (Between)
	public ResponseEntity<List<Produto>> getByPrecoEntre(@PathVariable BigDecimal inicio, @PathVariable BigDecimal fim){
		return ResponseEntity.ok(produtoRepository.buscarProdutosEntre(inicio, fim));
	}
	
	@GetMapping("/preco_maior/{preco}")
	public ResponseEntity<List<Produto>> getPrecoMaior(@PathVariable BigDecimal preco){
		return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThanOrderByPreco(preco));
	}
	
	@GetMapping("/preco_menor/{preco}")
	public ResponseEntity<List<Produto>> getPrecoMenor(@PathVariable BigDecimal preco){
		return ResponseEntity.ok(produtoRepository.findByPrecoLessThanOrderByPrecoDesc(preco));
	}
}

