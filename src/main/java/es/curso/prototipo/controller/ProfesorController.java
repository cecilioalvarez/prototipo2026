package es.curso.prototipo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.curso.prototipo.repository.ProfesorRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/profesores")
public class ProfesorController {

	private final ProfesorRepository profesorRepository;

	public ProfesorController(ProfesorRepository profesorRepository) {
		this.profesorRepository = profesorRepository;
	}

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("profesores", profesorRepository.buscarTodos());
		return "profesores/lista";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("profesorForm", new ProfesorForm());
		return "profesores/formulario";
	}

	@PostMapping
	public String guardar(@Valid @ModelAttribute("profesorForm") ProfesorForm profesorForm, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (!result.hasFieldErrors("dni")
				&& profesorRepository.buscarPorDni(profesorForm.dniNormalizado()).isPresent()) {
			result.rejectValue("dni", "duplicado", "Ya existe un profesor con ese DNI");
		}
		if (result.hasErrors()) {
			return "profesores/formulario";
		}
		profesorRepository.guardar(profesorForm.toProfesor());
		redirectAttributes.addFlashAttribute("mensaje", "Profesor añadido correctamente");
		return "redirect:/profesores";
	}
}
