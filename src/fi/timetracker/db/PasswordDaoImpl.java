package fi.timetracker.db;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Formatter;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import fi.timetracker.entity.Person;

/** 
 * @author Petteri Parviainen 
 */
public class PasswordDaoImpl implements PasswordDAO {

	private static final String GET_PERSON = "SELECT id FROM person WHERE "
			+ "email = ? AND password = ? AND status = 'A'";
	
	private static final String UPDATE_PASSWORD = "UPDATE person SET password = ?" +
			" WHERE id = ?";
	
	private static final String UPDATE_LASTLOGIN = "UPDATE Person SET last_login = ? WHERE id = ?";

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Integer login(String login, String password) {
		String hash = hashPassword(password);
		Integer id = null;
		try{			
			id = (Integer) this.jdbcTemplate.queryForObject(GET_PERSON, new Object[]{login, hash}, Integer.class);			
			this.jdbcTemplate.update(UPDATE_LASTLOGIN, new Object[]{new Date(), id});
		} catch(EmptyResultDataAccessException erdae){
			// Salasana tai käyttäjätunnus oli väärin,
			//joten ei tehda mitään, annetaan NULLin palautua
		}		
		return id;
	}

	@Override
	public boolean changePassword(Person person, String oldPassword,
			String newPassword) {
		Integer check = null;
		check = this.login(person.getEmail(), oldPassword);		
		if(check != null && (check.equals(person.getId()))){
			newPassword = hashPassword(newPassword);
			this.changePassword(person.getId(), newPassword);
			return true;
		}
		return false;
	}

	@Override
	public String generatePassword(Integer id) {
		String password = hashPassword(""+System.currentTimeMillis());
		password = password.substring(0, 6);
		String hash = hashPassword(password);
		this.changePassword(id, hash);
		return password;
	}
	
	private void changePassword(Integer id, String password){
		this.jdbcTemplate.update(UPDATE_PASSWORD, new Object[]{password, id});
	}

	private static String hashPassword(String str) {
		try {
			MessageDigest sha1 = MessageDigest.getInstance("SHA1");
			sha1.update(str.getBytes());

			Formatter formatter = new Formatter();
			for (byte b : sha1.digest()) {
				formatter.format("%02x", b);
			}
			return formatter.toString();
		} catch (NoSuchAlgorithmException nse) {
			throw new RuntimeException("Salausalgoritm1 SHA1 ei toimi? WTF!", nse);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(hashPassword("nimda"));
	}
}