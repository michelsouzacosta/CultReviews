package com.br.cultreviews.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.cultreviews.dto.ComentarioDTO;
import com.br.cultreviews.dto.LivroDTO;
import com.br.cultreviews.dto.NotaDTO;
import com.br.cultreviews.model.Comentario;
import com.br.cultreviews.model.Filme;
import com.br.cultreviews.model.Livro;
import com.br.cultreviews.model.Nota;
import com.br.cultreviews.model.TipoProduto;
import com.br.cultreviews.model.User;
import com.br.cultreviews.repository.ComentarioRepository;
import com.br.cultreviews.repository.LivroRepository;
import com.br.cultreviews.repository.NotaRepository;
import com.br.cultreviews.repository.UsuarioRepository;

@Controller
@RequestMapping("livros")
public class LivroController {

	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private NotaRepository notaRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@GetMapping("/cadastrar")
	public String cadastroPage() {	
		return "livros/livro-cadastro";
	}
	
	@GetMapping
	public String livroPage(Model model,Principal principal) {
	List<Livro> livros = livroRepository.findAll();
		
		model.addAttribute("livros",livros);
		
		return "livros/livros";
	}
	
	@PostMapping("/cadastrar")
	public String cadastrar(LivroDTO request,Principal principal) {
		User user = userRepository.findByNomeUsuario(principal.getName());
		
		Livro livro = request.toLivro(user);
		
		livroRepository.save(livro);

		return "redirect:/livros";
	}

	@GetMapping("/nota/{id}")
	public String notaPage(Model model,@PathVariable String id) {
		Optional<Livro> livro = livroRepository.findById(id);
		
		
		if(livro.isPresent()) {
			model.addAttribute("produto", livro);
			model.addAttribute("tipoProduto",TipoProduto.LIVROS.toString().toLowerCase());
		}
		
		return "avaliar";
	}
	
	@PostMapping("/nota/avaliar")
	public String darNota(NotaDTO request) {
		Optional<Livro> livro = livroRepository.findById(request.getTitulo());
		float media = 0;
		if(livro.isPresent()) {
			
			Nota nota = request.toNota();
			notaRepository.save(nota);
			
			List<Nota>notas = notaRepository.findByIdProduto(livro.get().getTitulo()); 
			media = Nota.mediaNota(notas);
			
			livro.get().setNota(media);
			livroRepository.save(livro.get());
		}
		
		return "redirect:/livros";
	}
	
	
	@GetMapping("/meus")
	public String meuFilmes(Model model,Principal principal) {
	List<Livro> livros = livroRepository.findByUser(principal.getName());
		
		model.addAttribute("livros",livros);
		
		return "livros/livros";
	}
	
	
	
	@GetMapping("comentarios/{id}")
	public String comentariosPage(@PathVariable String id,Model model) {
		Optional<Livro> livro = livroRepository.findById(id);
		
		if(livro.isPresent()) {
		List<Comentario> comentarios = comentarioRepository.findByIdProduto(livro.get().getTitulo());
			
			model.addAttribute("produto",livro.get());
			model.addAttribute("comentarios",comentarios);
		}
		
		return"comentarios";
	}
	
	
	@PostMapping("comentarios/comentar/{id}")
	public String comentariosPage(ComentarioDTO request,@PathVariable String id, Model model,Principal principal) {
		String  usuario = principal.getName();
		
		Optional<Livro> livro = livroRepository.findById(id);
		
		if(livro.isPresent()) {
			Comentario comentario = request.toComentario(usuario,livro.get().getTitulo());
		    comentarioRepository.save(comentario);
		}
		
		return"redirect:/livros/comentarios/"+livro.get().getTitulo();
	}
}
