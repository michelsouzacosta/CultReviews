package com.br.cultreviews.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.cultreviews.model.Filme;
import com.br.cultreviews.model.User;
import com.br.cultreviews.repository.FilmeRepository;
import com.br.cultreviews.repository.UsuarioRepository;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private FilmeRepository filmeRepository;
		
	
	@GetMapping
	public String homePage(Principal principal,Model model) {
		String username = principal.getName();
		User user = userRepository.findByNomeUsuario(username);
		
		model.addAttribute("usuario",user.getNome());	
		
		return "home";
	}
	
	@GetMapping("pesquisar")
	public String pesquisar(String titulo,Model model) {
		String msg ="";
		
		Optional<Filme> filme = filmeRepository.findById(titulo);
		
		if(filme.isPresent()) {
			return "redirect:/filmes/comentarios/"+titulo;
		}
		
		msg = "Pesquisa não encontrada";
		model.addAttribute("msg",msg);
		
		return "home";
	}
	
}
