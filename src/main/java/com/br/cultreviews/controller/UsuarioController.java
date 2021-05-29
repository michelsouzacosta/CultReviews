package com.br.cultreviews.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.cultreviews.dto.UsuarioDTO;
import com.br.cultreviews.dto.UsuarioPerfilDTO;
import com.br.cultreviews.model.User;
import com.br.cultreviews.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	
	@Autowired
	private UsuarioRepository repository;
	
	
	@GetMapping("/cadastro")
	public String cadastroPage() {
		return "cadastro";
	}
	
	@PostMapping("/cadastrar")
	public String cadastrar(@Valid UsuarioDTO request, BindingResult result, Model model) {
		
		if(result.hasErrors())
		{
			return "cadastro";
		}
		
		try {
			
		User usuario = request.toUsuario();
		
		
		repository.save(usuario);
		
		
		
		}catch (Exception e) {

		}
		
		return "redirect:/login";
	}

	
	@GetMapping("/perfil")
	 public String perfilPage(Model model, Principal principal) throws ParseException {
		String nomeUsuario = principal.getName();
		User user = repository.findByNomeUsuario(nomeUsuario);
		
		String data = user.getDataNascimento().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		model.addAttribute("usuario", user);
		model.addAttribute("dataNasc", data);
		
		return "perfil";
	} 
	 
	@PostMapping("/perfil")
	public String atualizarPerfil(@Valid UsuarioPerfilDTO request, BindingResult result) {
		
		System.out.println("Entrei no metodo atualizar");
		
		if(result.hasErrors())
		{
			System.out.println("deu erro");
			return "perfil";
		}
		
		
		try {
					
		User usuario = request.toUsuario();
		User user = repository.findByNomeUsuario(usuario.getNomeUsuario());
		
		usuario.setSenha(user.getSenha());
		
		
		repository.save(usuario);
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
}
