package br.org.generation.mygames.controller;

import java.util.List;
import java.util.Optional;

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

import br.org.generation.mygames.model.Usuario;
import br.org.generation.mygames.model.UsuarioLogin;
import br.org.generation.mygames.repository.UsuarioRepository;
import br.org.generation.mygames.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	/* Faz uma injeção de dependência da classe de Serviço UsuarioService
	 * para ter acesso aos métodos do CRUD com regras de negócio*/
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/listar") //SELECT * FROM tb_usuarios;
	public ResponseEntity <List<Usuario>> getAll(){
		
		return ResponseEntity.ok(usuarioRepository.findAll());
	}
	
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> loginUsuario(@RequestBody Optional <UsuarioLogin> usuarioLogin){
		
		return usuarioService.autenticarUsuario(usuarioLogin)
			.map(respostaLogin -> ResponseEntity.status(HttpStatus.OK).body(respostaLogin))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
		
		/* Executa o método altenticarUsuario da classe de serviço para efetuar o 
		 * login na api. O método da classe Controladora checa se 
		 * deu certo e exibe as mensagens (Response Status) pertinentes. 
		 * 
		 * Caso o login tenha sido bem sucedido, os dados do usuário e o token 
		 * são exibidos. */
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario){
		
		return usuarioService.cadastrarUsuario(usuario)
			.map(respostaCadastrar -> ResponseEntity.status(HttpStatus.CREATED).body(respostaCadastrar))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

		/* Executa o método cadastrarUsuario da classe de serviço para criar
		 * um novo usuário na api. O método da classe Controladora checa se 
		 * deu certo e exibe as mensagens (Response Status) pertinentes. 
		 * 
		 * Caso cadastro tenha sido bem sucedido, os dados do usuário são 
		 * exibidos. */
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.atualizarUsuario(usuario)
			.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
		
		/* Executa o método atualizarUsuario da classe de serviço para atualizar
		 * os dados de um usuário na api. O método da classe Controladora checa 
		 * se deu certo e exibe as mensagens (Response Status) pertinentes. 
		 * 
		 * Caso a atualização tenha sido bem sucedida, os dados do usuário 
		 * atualizados são exibidos. */
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePostagem(@PathVariable long id) {
		
		return usuarioRepository.findById(id)
			.map(resposta -> {
				usuarioRepository.deleteById(id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			})
			.orElse(ResponseEntity.notFound().build());
		/*O método deleta um usuário passado pelo id e retorna uma
		 *  responsta de NO_CONTENT, avisando que o usuário não existe mais na API*/
	}

}
