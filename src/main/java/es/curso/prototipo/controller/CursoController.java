package es.curso.prototipo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.curso.prototipo.repository.CursoRepository;

@Controller
@RequestMapping("/cursos")
public class CursoController {

	private final CursoRepository cursoRepository;

	public CursoController(CursoRepository cursoRepository) {
		this.cursoRepository = cursoRepository;
	}

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("cursos", cursoRepository.buscarTodos());
		return "cursos/lista";
	}
}
