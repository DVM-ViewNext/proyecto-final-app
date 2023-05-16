package lab.sinensia.repository.impl;

import java.util.List;

public interface IRepository<T> {

	/**
	 * Guarda el objeto en la DB
	 * 
	 * @param T
	 * @return int
	 */
	int save(T t);

	/**
	 * Actualiza el objeto en la DB
	 * 
	 * @param T
	 * @return int
	 */
	int update(T t);

	/**
	 * Busca por ID el tipo en la DB
	 * 
	 * @param id
	 * @return T
	 */
	T findById(int id);

	/**
	 * Borra el tipo por ID
	 * 
	 * @param id
	 * @return int
	 */
	int deleteById(int id);

	/**
	 * Obtiene una lista con todos los registros de la DB de este tipo
	 * 
	 * @return
	 */
	List<T> findAll();

}
