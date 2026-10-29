package es.curso.prototipo.controller.curso;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.curso.prototipo.cargadorescursos.CargadorCursos;
import es.curso.prototipo.model.Curso;
import es.curso.prototipo.repository.CursoRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/cursos")
public class CursoController {

	private final CursoRepository cursoRepository;
	private final List<CargadorCursos> cargadores;

	public CursoController(CursoRepository cursoRepository, List<CargadorCursos> cargadores) {
		this.cursoRepository = cursoRepository;
		this.cargadores = cargadores;
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

	@PostMapping("/cargar")
	public String cargarDesdeFichero(@RequestParam("fichero") MultipartFile fichero,
			RedirectAttributes redirectAttributes) {
		if (fichero.isEmpty()) {
			redirectAttributes.addFlashAttribute("error", "Selecciona un fichero .txt o .csv");
			return "redirect:/cursos";
		}

		Optional<CargadorCursos> cargador = cargadores.stream()
				.filter(c -> c.admite(fichero.getOriginalFilename()))
				.findFirst();
		if (cargador.isEmpty()) {
			redirectAttributes.addFlashAttribute("error", "Formato no admitido: solo ficheros .txt o .csv");
			return "redirect:/cursos";
		}

		try (InputStream entrada = fichero.getInputStream()) {
			List<Curso> cursos = cargador.get().cargarCursos(entrada);
			cursos.forEach(cursoRepository::guardar);
			redirectAttributes.addFlashAttribute("mensaje",
					cursos.size() + " cursos cargados desde " + fichero.getOriginalFilename());
		} catch (IOException | RuntimeException e) {
			redirectAttributes.addFlashAttribute("error", "No se pudieron cargar los cursos: " + e.getMessage());
		}
		return "redirect:/cursos";
	}
}
