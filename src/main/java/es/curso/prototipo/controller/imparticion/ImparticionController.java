package es.curso.prototipo.controller.imparticion;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.curso.prototipo.model.Curso;
import es.curso.prototipo.model.Imparticion;
import es.curso.prototipo.repository.CursoRepository;
import es.curso.prototipo.repository.ImparticionRepository;
import jakarta.validation.Valid;

/** Mantenimiento de las imparticiones de un curso. */
@Controller
@RequestMapping("/cursos/{cursoId}/imparticiones")
public class ImparticionController {

	private final CursoRepository cursoRepository;
	private final ImparticionRepository imparticionRepository;

	public ImparticionController(CursoRepository cursoRepository, ImparticionRepository imparticionRepository) {
		this.cursoRepository = cursoRepository;
		this.imparticionRepository = imparticionRepository;
	}

	@GetMapping
	public String listar(@PathVariable Long cursoId, Model model) {
		model.addAttribute("curso", buscarCurso(cursoId));
		model.addAttribute("imparticiones", imparticionRepository.buscarPorCurso(cursoId));
		return "imparticiones/lista";
	}

	@GetMapping("/nueva")
	public String nueva(@PathVariable Long cursoId, Model model) {
		model.addAttribute("curso", buscarCurso(cursoId));
		model.addAttribute("imparticionForm", new ImparticionForm());
		return "imparticiones/formulario";
	}

	@PostMapping
	public String crear(@PathVariable Long cursoId,
			@Valid @ModelAttribute("imparticionForm") ImparticionForm form, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		Curso curso = buscarCurso(cursoId);
		validarFechas(form, result);
		if (result.hasErrors()) {
			model.addAttribute("curso", curso);
			return "imparticiones/formulario";
		}
		imparticionRepository.guardar(form.toImparticion(curso));
		redirectAttributes.addFlashAttribute("mensaje", "Impartición añadida correctamente");
		return "redirect:/cursos/" + cursoId + "/imparticiones";
	}

	@GetMapping("/{id}/editar")
	public String editar(@PathVariable Long cursoId, @PathVariable Long id, Model model) {
		Imparticion imparticion = buscarImparticion(cursoId, id);
		model.addAttribute("curso", imparticion.getCurso());
		model.addAttribute("imparticionId", id);
		model.addAttribute("imparticionForm", ImparticionForm.desde(imparticion));
		return "imparticiones/formulario";
	}

	@PostMapping("/{id}")
	public String actualizar(@PathVariable Long cursoId, @PathVariable Long id,
			@Valid @ModelAttribute("imparticionForm") ImparticionForm form, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		Imparticion imparticion = buscarImparticion(cursoId, id);
		validarFechas(form, result);
		if (result.hasErrors()) {
			model.addAttribute("curso", imparticion.getCurso());
			model.addAttribute("imparticionId", id);
			return "imparticiones/formulario";
		}
		form.copiarEn(imparticion);
		imparticionRepository.guardar(imparticion);
		redirectAttributes.addFlashAttribute("mensaje", "Impartición modificada correctamente");
		return "redirect:/cursos/" + cursoId + "/imparticiones";
	}

	@PostMapping("/{id}/borrar")
	public String borrar(@PathVariable Long cursoId, @PathVariable Long id, RedirectAttributes redirectAttributes) {
		buscarImparticion(cursoId, id);
		imparticionRepository.borrar(id);
		redirectAttributes.addFlashAttribute("mensaje", "Impartición borrada correctamente");
		return "redirect:/cursos/" + cursoId + "/imparticiones";
	}

	private void validarFechas(ImparticionForm form, BindingResult result) {
		if (form.fechaFinAnteriorAInicio()) {
			result.rejectValue("fechaFin", "fechas", "La fecha de fin no puede ser anterior a la de inicio");
		}
	}

	private Curso buscarCurso(Long cursoId) {
		return cursoRepository.buscarPorId(cursoId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado"));
	}

	/** Busca la impartición y comprueba que pertenece al curso de la URL. */
	private Imparticion buscarImparticion(Long cursoId, Long id) {
		return imparticionRepository.buscarPorId(id)
				.filter(i -> i.getCurso().getId().equals(cursoId))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Impartición no encontrada"));
	}
}
