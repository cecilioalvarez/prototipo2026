package es.curso.prototipo.controller.alumno;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.curso.prototipo.repository.AlumnoRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {

	private final AlumnoRepository alumnoRepository;

	public AlumnoController(AlumnoRepository alumnoRepository) {
		this.alumnoRepository = alumnoRepository;
	}

	@GetMapping
	public String listar(Model model) {
		model.addAttribute("alumnos", alumnoRepository.buscarTodos());
		return "alumnos/lista";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("alumnoForm", new AlumnoForm());
		return "alumnos/formulario";
	}

	@PostMapping
	public String guardar(@Valid @ModelAttribute("alumnoForm") AlumnoForm alumnoForm, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (!result.hasFieldErrors("dni")
				&& alumnoRepository.buscarPorDni(alumnoForm.dniNormalizado()).isPresent()) {
			result.rejectValue("dni", "duplicado", "Ya existe un alumno con ese DNI");
		}
		if (result.hasErrors()) {
			return "alumnos/formulario";
		}
		alumnoRepository.guardar(alumnoForm.toAlumno());
		redirectAttributes.addFlashAttribute("mensaje", "Alumno añadido correctamente");
		return "redirect:/alumnos";
	}
}
