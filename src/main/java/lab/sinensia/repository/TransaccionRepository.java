package lab.sinensia.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lab.sinensia.model.Transaccion;
import lab.sinensia.repository.impl.IRepository;

@Repository
public class TransaccionRepository implements IRepository<Transaccion> {

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
	public int save(Transaccion tx) {
		String sql = "insert into Transaccion(idwallet,idestatustransaccion,idtipotransaccion,referencia,monto,concepto,fecha,canal,address) "
				+ "values(" + tx.getIdwallet() + "," + tx.getIdestatustransaccion() + "," + tx.getIdtipotransaccion()
				+ "," + tx.getReferencia() + "," + tx.getMonto() + ",'" + tx.getConcepto() + "','" + tx.getFecha()
				+ "','" + tx.getCanal() + "','" + tx.getAddress() + "')";
		return template.update(sql);
	}

	@Override
	public int update(Transaccion tx) {
		String sql = "update Transaccion set idestatustransaccion=" + tx.getIdestatustransaccion() + ", monto="
				+ tx.getMonto() + ", concepto='" + tx.getConcepto() + "',fecha='" + tx.getFecha() + "',canal='"
				+ tx.getCanal() + "',address='" + tx.getAddress() +  "'  where id=" + tx.getId() + "";
		return template.update(sql);
	}

	@Override
	public int deleteById(int id) {
		String sql = "delete from Transaccion where id=" + id + "";
		return template.update(sql);
	}

	@Override
	public Transaccion findById(int id) {
		String sql = "select * from Transaccion where id=?";
		return template.queryForObject(sql, new Object[] { id },
				new BeanPropertyRowMapper<Transaccion>(Transaccion.class));
	}

	@Override
	public List<Transaccion> findAll() {
		return template.query("select * from Transaccion", new RowMapper<Transaccion>() {

			public Transaccion mapRow(ResultSet rs, int row) throws SQLException {
				Transaccion tx = new Transaccion();
				tx.setId(rs.getInt(1));
				tx.setIdwallet(rs.getInt(2));
				tx.setIdestatustransaccion(rs.getInt(3));
				tx.setIdtipotransaccion(rs.getInt(4));
				tx.setReferencia(rs.getInt(5));
				tx.setMonto(rs.getDouble(6));
				tx.setConcepto(rs.getString(7));
				tx.setFecha(rs.getString(8));
				tx.setCanal(rs.getString(9));
				tx.setAddress(rs.getString(10));
				return tx;

			}
		});
	}

}
