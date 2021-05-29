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
import com.br.cultreviews.dto.FilmeDTO;
import com.br.cultreviews.dto.NotaDTO;
import com.br.cultreviews.model.Comentario;
import com.br.cultreviews.model.Filme;
import com.br.cultreviews.model.Nota;
import com.br.cultreviews.model.User;
import com.br.cultreviews.repository.ComentarioRepository;
import com.br.cultreviews.repository.FilmeRepository;
import com.br.cultreviews.repository.NotaRepository;
import com.br.cultreviews.repository.UsuarioRepository;

@Controller
@RequestMapping("filmes")
public class FilmesController {

	@Autowired
	private FilmeRepository filmeRepository;
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private NotaRepository notaRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@GetMapping("/cadastrar")
	public String cadastroPage() {	
		return "filmes/filme-cadastro";
	}
	
	@GetMapping
	public String home(Model model,Principal principal) {
	List<Filme> filmes = filmeRepository.findAll();
		
		model.addAttribute("filmes",filmes);
		
		return "filmes/filmes";
	}
	
	@GetMapping("/meus")
	public String meuFilmes(Model model,Principal principal) {
	List<Filme> filmes = filmeRepository.findByUser(principal.getName());
		
		model.addAttribute("filmes",filmes);
		
		return "filmes/filmes";
	}
	
	@PostMapping("/cadastrar")
	public String cadastrar(FilmeDTO request,Principal principal) {
		User user = userRepository.findByNomeUsuario(principal.getName());
		
		Filme filme = request.toFilme(user);
		
		filmeRepository.save(filme);
		
		System.out.println("cadastrado com sucesso!");
		
		return "redirect:/filmes";
	}
	
	@GetMapping("/nota/{id}")
	public String notaPage(Model model,@PathVariable String id) {
		Optional<Filme> filme = filmeRepository.findById(id);
		
		if(filme.isPresent()) {
			model.addAttribute("produto", filme);
		}
		
		return "avaliar";
	}
	
	@PostMapping("/nota/avaliar")
	public String darNota(NotaDTO request) {
		Optional<Filme> filme = filmeRepository.findById(request.getTitulo());
		float media = 0;
		if(filme.isPresent()) {
			
			Nota nota = request.toNota();
			notaRepository.save(nota);
			
			List<Nota>notas = notaRepository.findByIdProduto(filme.get().getTitulo()); 
			media = Nota.mediaNota(notas);
			
			filme.get().setNota(media);
			filmeRepository.save(filme.get());
		}
		
		return "redirect:/filmes";
	}
	
	
	
	@GetMapping("comentarios/{id}")
	public String comentariosPage(@PathVariable String id,Model model) {
		Optional<Filme> filme = filmeRepository.findById(id);
		
		if(filme.isPresent()) {
		List<Comentario> comentarios = comentarioRepository.findByIdProduto(filme.get().getTitulo());
			
			model.addAttribute("produto",filme.get());
			model.addAttribute("comentarios",comentarios);
		}
		
		return"comentarios";
	}
	
	
	@PostMapping("comentarios/comentar/{id}")
	public String comentariosPage(ComentarioDTO request,@PathVariable String id, Model model,Principal principal) {
		String  usuario = principal.getName();
		
		Optional<Filme> filme = filmeRepository.findById(id);
		
		if(filme.isPresent()) {
			Comentario comentario = request.toComentario(usuario,filme.get().getTitulo());
		    comentarioRepository.save(comentario);
		}
		
		return"redirect:/filmes/comentarios/"+filme.get().getTitulo();
	}
}
