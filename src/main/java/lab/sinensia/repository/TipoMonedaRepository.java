package lab.sinensia.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lab.sinensia.model.TipoMoneda;
import lab.sinensia.repository.impl.IRepository;

@Repository
public class TipoMonedaRepository implements IRepository<TipoMoneda> {

	@Autowired
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public int save(TipoMoneda tm) {
		String name = tm.getNombre();
		String sqlCheck;
		do {
			sqlCheck = "SELECT nombre FROM TipoMoneda WHERE nombre='" + name + "'";
			if(template.queryForList(sqlCheck).size() == 0) {
				String sql = "insert into TipoMoneda(id,nombre) values(" + tm.getId() + ",'" + tm.getNombre() + "')";
				return template.update(sql);
			}
		} while(true);
	}

	@Override
	public int update(TipoMoneda tm) {
		String sql = "update TipoMoneda set nombre=" + tm.getNombre() + " where id=" + tm.getId() + "";
		return template.update(sql);
	}

	@Override
	public int deleteById(int id) {
		String sql = "delete from TipoMoneda where id=" + id + "";
		return template.update(sql);
	}

	@Override
	public TipoMoneda findById(int id) {
		String sql = "select * from TipoMoneda where id=?";
		
		return template.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<TipoMoneda>(TipoMoneda.class));
	}

	@Override
	public List<TipoMoneda> findAll() {
		return template.query("select * from TipoMoneda", new RowMapper<TipoMoneda>() {

			public TipoMoneda mapRow(ResultSet rs, int row) throws SQLException {
				TipoMoneda tm = new TipoMoneda();
				tm.setId(rs.getInt(1));
				tm.setNombre(rs.getString(2));

				return tm;

			}
		});
	}

}
