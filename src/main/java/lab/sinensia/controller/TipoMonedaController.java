package lab.sinensia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lab.sinensia.controller.impl.AbstractController;
import lab.sinensia.controller.impl.IController;
import lab.sinensia.model.TipoMoneda;

@RestController
@RequestMapping("/api")
public class TipoMonedaController extends AbstractController implements IController<TipoMoneda> {

	@Override
	public ResponseEntity<List<TipoMoneda>> getAll() {
		try {
			List<TipoMoneda> tipoMoneda = new ArrayList<TipoMoneda>();

			tipoMonedaRepository.findAll().forEach(tipoMoneda::add);

			if (tipoMoneda.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tipoMoneda, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<TipoMoneda> getById(int id) {
		TipoMoneda tipoMoneda = tipoMonedaRepository.findById(id);

		if (tipoMoneda != null) {
			return new ResponseEntity<>(tipoMoneda, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<String> create(TipoMoneda tm) {
		try {
			tipoMonedaRepository.save(new TipoMoneda(tm.getNombre()));
			return new ResponseEntity<>("TipoMoneda creado satisfactoriamente.", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<String> update(int id, TipoMoneda tm) {
		TipoMoneda tipoMoneda = tipoMonedaRepository.findById(id);

		if (tipoMoneda != null) {
			tipoMoneda.setId(id);
			tipoMoneda.setNombre(tm.getNombre());

			tipoMonedaRepository.update(tipoMoneda);
			return new ResponseEntity<>("TipoMoneda actualizado satisfactoriamente.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No se encontro usuario con id= " + id, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<String> delete(int id) {
		try {
			int result = tipoMonedaRepository.deleteById(id);
			if (result == 0) {
				return new ResponseEntity<>("No se encontro TipoMoneda con id=" + id, HttpStatus.OK);
			}
			return new ResponseEntity<>("TipoMoneda eliminado satisfactoramente.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("No se puede eliminar el TipoMoneda.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
}
