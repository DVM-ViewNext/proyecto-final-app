package lab.sinensia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lab.sinensia.controller.impl.AbstractController;
import lab.sinensia.controller.impl.IController;
import lab.sinensia.model.Moneda;

@RestController
@RequestMapping("/api")
public class MonedaController extends AbstractController implements IController<Moneda> {

	@GetMapping("/monedas")
	@Override
	public ResponseEntity<List<Moneda>> getAll() {
		try {
			List<Moneda> moneda = new ArrayList<Moneda>();

			monedaRepository.findAll().forEach(moneda::add);

			if (moneda.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(moneda, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/monedas/{id}")
	@Override
	public ResponseEntity<Moneda> getById(int id) {
		Moneda moneda = monedaRepository.findById(id);

		if (moneda != null) {
			return new ResponseEntity<>(moneda, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/monedas")
	@Override
	public ResponseEntity<String> create(Moneda m) {
		try {
			monedaRepository.save(new Moneda(m.getIdtipomoneda(), m.getNombre(), m.getPrecio()));
			return new ResponseEntity<>("Moneda creada satisfactoriamente.", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/monedas/{id}")
	@Override
	public ResponseEntity<String> update(int id, Moneda m) {
		Moneda moneda = monedaRepository.findById(id);

		if (moneda != null) {
			moneda.setId(id);
			moneda.setIdtipomoneda(m.getIdtipomoneda());
			moneda.setNombre(m.getNombre());
			moneda.setPrecio(m.getPrecio());

			monedaRepository.update(moneda);
			return new ResponseEntity<>("Moneda actualizada satisfactoriamente.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No se encontro moneda con id= " + id, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/monedas/{id}")
	@Override
	public ResponseEntity<String> delete(int id) {
		try {
			int result = monedaRepository.deleteById(id);
			if (result == 0) {
				return new ResponseEntity<>("No se encontro moneda con id=" + id, HttpStatus.OK);
			}
			return new ResponseEntity<>("Moneda eliminada satisfactoramente.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("No se puede eliminar la moneda.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
}
