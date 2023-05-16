package lab.sinensia.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lab.sinensia.controller.impl.AbstractController;
import lab.sinensia.controller.impl.IController;
import lab.sinensia.model.Usuario;

@RestController
@RequestMapping("/api")
public class UsuarioController extends AbstractController implements IController<Usuario> {

	@GetMapping("/usuarios")
	@Override
	public ResponseEntity<List<Usuario>> getAll() {
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();

			usuarioRepository.findAll().forEach(usuarios::add);

			if (usuarios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/usuarios/{id}")
	@Override
	public ResponseEntity<Usuario> getById(int id) {
		Usuario usuario = usuarioRepository.findById(id);

		if (usuario != null) {
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/usuarios")
	@Override
	public ResponseEntity<String> create(Usuario usuario) {
		System.out.println("API: " + usuario.toString());
		try {
			usuarioRepository.save(new Usuario(usuario.getNombre(), usuario.getUsuario(), usuario.getPassword(),
					usuario.getEmail(), usuario.getDni()));
			return new ResponseEntity<>("Usuario creado satisfactoriamente.", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/usuarios/{id}")
	@Override
	public ResponseEntity<String> update(int id, Usuario usuario) {
		Usuario user = usuarioRepository.findById(id);

		if (user != null) {
			user.setId(id);
			user.setNombre(usuario.getNombre());
			user.setUsuario(usuario.getUsuario());
			user.setPassword(usuario.getPassword());
			user.setEmail(usuario.getEmail());
			user.setDni(usuario.getDni());

			usuarioRepository.update(user);
			return new ResponseEntity<>("Usuario actualizado satisfactoriamente.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No se encontro usuario con id= " + id, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/usuarios/{id}")
	@Override
	public ResponseEntity<String> delete(int id) {
		try {
			int result = usuarioRepository.deleteById(id);
			if (result == 0) {
				return new ResponseEntity<>("No se encontro usuario con id=" + id, HttpStatus.OK);
			}
			return new ResponseEntity<>("Usuario eliminado satisfactoramente.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("No se puede eliminar el usuario.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
