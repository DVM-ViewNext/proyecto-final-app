package lab.sinensia.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lab.sinensia.model.Usuario;
import lab.sinensia.repository.impl.IRepository;

@Repository
public class UsuarioRepository implements IRepository<Usuario> {

	/*
	 * JdbcTemplate se utiliza para simplificar el acceso a bases de datos
	 * relacionales utilizando JDBC (Java Database Connectivity). Esta clase abstrae
	 * gran parte de la complejidad de JDBC y proporciona una API simple y fácil de
	 * usar para ejecutar consultas SQL y realizar operaciones CRUD en la base de
	 * datos.
	 */
	@Autowired
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public int save(Usuario u) {
		String sql = "insert into Usuario(nombre,usuario,password,email,dni) values('" + u.getNombre() + "','"
				+ u.getUsuario() + "','" + u.getPassword() + "','" + u.getEmail() + "','" + u.getDni() + "')";
		System.out.println(sql);
		return template.update(sql);
	}

	@Override
	public int update(Usuario u) {
		String sql = "update Usuario set nombre='" + u.getNombre() + "', usuario='" + u.getUsuario()
				+ "',password='" + u.getPassword() + "',email='" + u.getEmail() + "',dni='" + u.getDni()
				+ "' where id=" + u.getId() + "";
		return template.update(sql);
	}

	@Override
	public int deleteById(int id) {
		String sql = "delete from Usuario where id=" + id + "";
		return template.update(sql);
	}

	@Override
	public Usuario findById(int id) {
		String sql = "select * from Usuario where id=?";
		return template.queryForObject(sql, new Object[] { id },
				new BeanPropertyRowMapper<Usuario>(Usuario.class));
	}

	@Override
	public List<Usuario> findAll() {
		return template.query("select * from Usuario", new RowMapper<Usuario>() {

			public Usuario mapRow(ResultSet rs, int row) throws SQLException {
				Usuario u = new Usuario();
				u.setId(rs.getInt(1));
				u.setNombre(rs.getString(2));
				u.setUsuario(rs.getString(3));
				u.setPassword(rs.getString(4));
				u.setEmail(rs.getString(5));
				u.setDni(rs.getString(6));

				return u;

			}
		});
	}

}
