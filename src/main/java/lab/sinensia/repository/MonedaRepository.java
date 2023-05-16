package lab.sinensia.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lab.sinensia.model.Moneda;
import lab.sinensia.repository.impl.IRepository;

@Repository
public class MonedaRepository implements IRepository<Moneda> {

	@Autowired
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public int save(Moneda m) {
		String name = m.getNombre();
		String sqlCheck;
		do {
			sqlCheck = "SELECT nombre FROM Moneda WHERE nombre='" + name + "'";
			if(template.queryForList(sqlCheck).size() == 0) {
				String sql = "insert into Moneda(id,idtipomoneda,nombre,precio) values(" + m.getId() + "," + m.getIdtipomoneda() + ",'" + m.getNombre() + "', " + m.getPrecio() + ")";
				return template.update(sql);
			}
		} while(true);
	}

	@Override
	public int update(Moneda m) {
		String sql = "update Moneda set idtipomoneda=" + m.getIdtipomoneda() + ", nombre='" + m.getNombre() + "', precio= " + m.getPrecio() + " where id=" + m.getId() + "";
		return template.update(sql);
	}

	@Override
	public int deleteById(int id) {
		String sql = "delete from Moneda where id=" + id + "";
		return template.update(sql);
	}

	@Override
	public Moneda findById(int id) {
		String sql = "select * from Moneda where id=?";
		
		return template.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Moneda>(Moneda.class));
	}

	@Override
	public List<Moneda> findAll() {
		return template.query("select * from Moneda", new RowMapper<Moneda>() {

			public Moneda mapRow(ResultSet rs, int row) throws SQLException {
				Moneda m = new Moneda();
				m.setId(rs.getInt(1));
				m.setIdtipomoneda(rs.getInt(2));
				m.setNombre(rs.getString(3));
				m.setPrecio(rs.getDouble(4));
				return m;

			}
		});
	}

}
