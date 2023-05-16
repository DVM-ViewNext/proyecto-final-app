package lab.sinensia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lab.sinensia.controller.impl.AbstractController;
import lab.sinensia.controller.impl.IController;
import lab.sinensia.model.Transaccion;

@RestController
@RequestMapping("/api")
public class TransaccionController extends AbstractController implements IController<Transaccion> {

	@GetMapping("/transacciones")
	@Override
	public ResponseEntity<List<Transaccion>> getAll() {
		try {
			List<Transaccion> transacciones = new ArrayList<Transaccion>();

			transaccionRepository.findAll().forEach(transacciones::add);

			if (transacciones.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(transacciones, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/transacciones/{id}")
	@Override
	public ResponseEntity<Transaccion> getById(int id) {
		Transaccion transaccion = transaccionRepository.findById(id);

		if (transaccion != null) {
			return new ResponseEntity<>(transaccion, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/transacciones")
	@Override
	public ResponseEntity<String> create(Transaccion tx) {
		try {
			transaccionRepository
					.save(new Transaccion(tx.getIdwallet(), tx.getIdestatustransaccion(), tx.getIdtipotransaccion(),
							tx.getReferencia(), tx.getMonto(), tx.getConcepto(), tx.getFecha(), tx.getCanal(), tx.getAddress()));
			return new ResponseEntity<>("Transaccion creada satisfactoriamente.", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/transacciones/{id}")
	@Override
	public ResponseEntity<String> update(int id, Transaccion transaccion) {
		Transaccion tx = transaccionRepository.findById(id);

		if (tx != null) {
			tx.setId(id);
			tx.setIdwallet(transaccion.getIdwallet());
			tx.setIdestatustransaccion(transaccion.getIdestatustransaccion());
			tx.setIdtipotransaccion(transaccion.getIdtipotransaccion());
			tx.setReferencia(transaccion.getReferencia());
			tx.setMonto(transaccion.getMonto());
			tx.setConcepto(transaccion.getConcepto());
			tx.setFecha(transaccion.getFecha());
			tx.setCanal(transaccion.getCanal());

			transaccionRepository.update(tx);
			return new ResponseEntity<>("Transaccion actualizado satisfactoriamente.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No se encontro transaccion con id= " + id, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/transacciones/{id}")
	@Override
	public ResponseEntity<String> delete(int id) {
		try {
			int result = transaccionRepository.deleteById(id);
			if (result == 0) {
				return new ResponseEntity<>("No se encontro transaccion con id=" + id, HttpStatus.OK);
			}
			return new ResponseEntity<>("Transaccion eliminada satisfactoramente.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("No se puede eliminar la transaccion.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
