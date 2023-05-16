package lab.sinensia.controller.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IController<T> {

	/**
	 * Devuelve una lista con todos los registros de este tipo
	 * 
	 * @return ResponseEntity<{@link List<{@link T}>>
	 */
	public ResponseEntity<List<T>> getAll();

	/**
	 * Devuelve un registro de este tipo
	 * 
	 * @return ResponseEntity<{@link T}>
	 */
	public ResponseEntity<T> getById(@PathVariable("id") int id);

	/**
	 * Devuelve el {@link ResponseEntity} con el status obtenido
	 * 
	 * @return ResponseEntity<{@link String}>
	 */
	public ResponseEntity<String> create(@RequestBody T t);

	/**
	 * Devuelve el {@link ResponseEntity} con el status obtenido
	 * 
	 * @return ResponseEntity<{@link String}>
	 */
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody T t);

	/**
	 * Devuelve el {@link ResponseEntity} con el status obtenido
	 * 
	 * @return ResponseEntity<{@link String}>
	 */
	public ResponseEntity<String> delete(@PathVariable("id") int id);

}
