package es.curso.prototipo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.curso.prototipo.repository.CursoRepository;
import jakarta.validation.Valid;

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

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("cursoForm", new CursoForm());
		return "cursos/formulario";
	}

	@PostMapping
	public String guardar(@Valid @ModelAttribute("cursoForm") CursoForm cursoForm, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "cursos/formulario";
		}
		cursoRepository.guardar(cursoForm.toCurso());
		redirectAttributes.addFlashAttribute("mensaje", "Curso añadido correctamente");
		return "redirect:/cursos";
	}
}
