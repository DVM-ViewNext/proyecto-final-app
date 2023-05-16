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
import lab.sinensia.model.Wallet;

@RestController
@RequestMapping("/api")
public class WalletController extends AbstractController implements IController<Wallet> {

	@GetMapping("/wallets")
	@Override
	public ResponseEntity<List<Wallet>> getAll() {
		try {
			List<Wallet> wallets = new ArrayList<Wallet>();

			walletRepository.findAll().forEach(wallets::add);

			if (wallets.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(wallets, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/wallets/{id}")
	@Override
	public ResponseEntity<Wallet> getById(int id) {
		Wallet wallet = walletRepository.findById(id);

		if (wallet != null) {
			return new ResponseEntity<>(wallet, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/wallets")
	@Override
	public ResponseEntity<String> create(Wallet w) {
		try {
			walletRepository
					.save(new Wallet(w.getIdmoneda(), w.getIdusuario(), w.getCode(), w.getBalance(), w.getAddress(), w.getMoneda()));

			return new ResponseEntity<>("Wallet creada satisfactoriamente.", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/wallets/{id}")
	@Override
	public ResponseEntity<String> update(int id, Wallet wallet) {
		Wallet w = walletRepository.findById(id);

		if (w != null) {
			w.setId(id);
			w.setIdusuario(wallet.getIdusuario());
			w.setCode(wallet.getCode());
			w.setBalance(wallet.getBalance());
			w.setAddress(wallet.getAddress());
			w.setMoneda(wallet.getMoneda());

			walletRepository.update(w);
			return new ResponseEntity<>("Wallet actualizado satisfactoriamente.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No se encontro wallet con id= " + id, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/wallets/{id}")
	@Override
	public ResponseEntity<String> delete(int id) {
		try {
			int result = walletRepository.deleteById(id);
			if (result == 0) {
				return new ResponseEntity<>("No se encontro wallet con id=" + id, HttpStatus.OK);
			}
			return new ResponseEntity<>("Wallet eliminado satisfactoramente.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("No se puede eliminar el wallet.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
