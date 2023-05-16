package lab.sinensia.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lab.sinensia.model.Wallet;
import lab.sinensia.repository.impl.IRepository;

@Repository
public class WalletRepository implements IRepository<Wallet> {

	@Autowired
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public int save(Wallet w) {
		String address;
		String sqlCheck;
		do {
			address = UUID.randomUUID().toString();
			sqlCheck = "SELECT address FROM wallet WHERE address='" + address + "'";
			if(template.queryForList(sqlCheck).size() == 0) {
				String sql = "insert into Wallet(idmoneda,idusuario,code,balance,address,moneda) values("
						+ w.getIdmoneda() + "," + w.getIdusuario() + ",'" + w.getCode() + "'," + w.getBalance() + ",'"
						+ address + "','" + w.getMoneda() + "')";
				return template.update(sql);
			}
		} while(true);
	}

	@Override
	public int update(Wallet w) {
		String sql = "update Wallet set balance=" + w.getBalance() + " where id=" + w.getId() + "";
		return template.update(sql);
	}

	@Override
	public int deleteById(int id) {
		String sql = "delete from Wallet where id=" + id + "";
		return template.update(sql);
	}

	@Override
	public Wallet findById(int id) {
		String sql = "select * from Wallet where id=?";
		
		return template.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Wallet>(Wallet.class));
	}

	@Override
	public List<Wallet> findAll() {
		return template.query("select * from Wallet", new RowMapper<Wallet>() {

			public Wallet mapRow(ResultSet rs, int row) throws SQLException {
				Wallet w = new Wallet();
				w.setId(rs.getInt(1));
				w.setIdmoneda(rs.getInt(2));
				w.setIdusuario(rs.getInt(3));
				w.setCode(rs.getString(4));
				w.setBalance(rs.getDouble(5));
				w.setAddress(rs.getString(6));
				w.setMoneda(rs.getString(7));

				return w;

			}
		});
	}

}
