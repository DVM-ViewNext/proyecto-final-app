package lab.sinensia.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lab.sinensia.model.Moneda;
import lab.sinensia.model.TipoMoneda;
import lab.sinensia.model.Transaccion;
import lab.sinensia.model.Usuario;
import lab.sinensia.model.Wallet;
import lab.sinensia.repository.impl.IRepository;

@Controller
public abstract class AbstractController {

	@Autowired
	protected IRepository<Usuario> usuarioRepository;

	@Autowired
	protected IRepository<Transaccion> transaccionRepository;

	@Autowired
	protected IRepository<Wallet> walletRepository;
	
	@Autowired
	protected IRepository<Moneda> monedaRepository;
	
	@Autowired
	protected IRepository<TipoMoneda> tipoMonedaRepository;

	/**
	 * Devuelve el repositorio con su template de conexión a DB con {@link Usuario}
	 * 
	 * @return IRepository<{@link Usuario}>
	 */
	protected IRepository<Usuario> getUsuarioRepository() {
		return usuarioRepository;
	}

	/**
	 * Devuelve el repositorio con su template de conexión a DB con
	 * {@link Transaccion}
	 * 
	 * @return IRepository<{@link Transaccion}>
	 */
	protected IRepository<Transaccion> getTransaccionRepository() {
		return transaccionRepository;
	}

	/**
	 * Devuelve el repositorio con su template de conexión a DB con {@link Wallet}
	 * 
	 * @return IRepository<{@link Wallet}>
	 */
	protected IRepository<Wallet> getWalletRepository() {
		return walletRepository;
	}

	/**
	 * Devuelve el repositorio con su template de conexión a DB con {@link Wallet}
	 * 
	 * @return IRepository<{@link Wallet}>
	 */
	protected IRepository<Moneda> getMonedaRepository() {
		return monedaRepository;
	}

	/**
	 * Devuelve el repositorio con su template de conexión a DB con {@link Wallet}
	 * 
	 * @return IRepository<{@link Wallet}>
	 */
	protected IRepository<TipoMoneda> getTipoMonedaRepository() {
		return tipoMonedaRepository;
	}
	
	

	
	
}
