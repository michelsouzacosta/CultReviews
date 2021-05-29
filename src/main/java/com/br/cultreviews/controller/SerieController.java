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
import com.br.cultreviews.dto.NotaDTO;
import com.br.cultreviews.dto.SerieDTO;
import com.br.cultreviews.model.Comentario;
import com.br.cultreviews.model.Livro;
import com.br.cultreviews.model.Nota;
import com.br.cultreviews.model.Serie;
import com.br.cultreviews.model.User;
import com.br.cultreviews.repository.ComentarioRepository;
import com.br.cultreviews.repository.NotaRepository;
import com.br.cultreviews.repository.SeriesRepository;
import com.br.cultreviews.repository.UsuarioRepository;

@Controller
@RequestMapping("series")
public class SerieController {
	@Autowired
	private SeriesRepository seriesRepository;
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private NotaRepository notaRepository;
	
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@GetMapping("/cadastrar")
	public String cadastroPage() {	
		return "series/series-cadastro";
	}
	
	@GetMapping
	public String home(Model model,Principal principal) {
	List<Serie> series = seriesRepository.findAll();
		
		model.addAttribute("series",series);
		
		return "series/series";
	}
	
	@GetMapping("/meus")
	public String meuFilmes(Model model,Principal principal) {
	List<Serie> series = seriesRepository.findByUser(principal.getName());
		
		model.addAttribute("series",series);
		
		return "series/series";
	}
	
	@PostMapping("/cadastrar")
	public String cadastrar(SerieDTO request,Principal principal) {
		User user = userRepository.findByNomeUsuario(principal.getName());
		
		Serie serie = request.toSerie(user);
		
		seriesRepository.save(serie);
		
		System.out.println("cadastrado com sucesso!");
		
		return "redirect:/series";
	}
	
	@GetMapping("/nota/{id}")
	public String notaPage(Model model,@PathVariable String id) {
		Optional<Serie> serie = seriesRepository.findById(id);
		
		if(serie.isPresent()) {
			model.addAttribute("produto", serie);
		}
		
		return "avaliar";
	}
	
	@PostMapping("/nota/avaliar")
	public String darNota(NotaDTO request) {
		Optional<Serie> serie = seriesRepository.findById(request.getTitulo());
		float media = 0;
		if(serie.isPresent()) {
			
			Nota nota = request.toNota();
			notaRepository.save(nota);
			
			List<Nota>notas = notaRepository.findByIdProduto(serie.get().getTitulo()); 
			media = Nota.mediaNota(notas);
			
			serie.get().setNota(media);
			seriesRepository.save(serie.get());
		}
		
		return "redirect:/series";
	}
	
	@GetMapping("comentarios/{id}")
	public String comentariosPage(@PathVariable String id,Model model) {
		Optional<Serie> serie = seriesRepository.findById(id);
		
		if(serie.isPresent()) {
		List<Comentario> comentarios = comentarioRepository.findByIdProduto(serie.get().getTitulo());
			
			model.addAttribute("produto",serie.get());
			model.addAttribute("comentarios",comentarios);
		}
		
		return"comentarios";
	}
	
	
	@PostMapping("comentarios/comentar/{id}")
	public String comentariosPage(ComentarioDTO request,@PathVariable String id, Model model,Principal principal) {
		String  usuario = principal.getName();
		
		Optional<Serie> serie = seriesRepository.findById(id);
		
		if(serie.isPresent()) {
			Comentario comentario = request.toComentario(usuario,serie.get().getTitulo());
		    comentarioRepository.save(comentario);
		}
		
		return"redirect:/series/comentarios/"+serie.get().getTitulo();
	}
}
