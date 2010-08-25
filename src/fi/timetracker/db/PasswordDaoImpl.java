package fi.timetracker.db;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Formatter;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import fi.timetracker.db.PersonDAOImpl.PersonRowMapper;
import fi.timetracker.entity.Person;

/** 
 * @author Petteri Parviainen 
 */
public class PasswordDaoImpl implements PasswordDAO {

	private static final String GET_PERSON = "SELECT * FROM person WHERE "
			+ "email = ? AND password = ?";
	
	private static final String UPDATE_PASSWORD = "UPDATE person SET password = ?" +
			" WHERE id = ?";
	
	private static final String UPDATE_LASTLOGIN = "UPDATE Person SET last_login = ? WHERE id = ?";

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Person login(String login, String password) {
		String hash = hashPassword(password);
		Person person = null;
		try{
			person = (Person) this.jdbcTemplate.queryForObject(GET_PERSON, new Object[]{login, hash}, new PersonRowMapper());
			this.jdbcTemplate.update(UPDATE_LASTLOGIN, new Object[]{new Date(), person.getId()});
		} catch(EmptyResultDataAccessException erdae){
			// Salasana tai käyttäjätunnus oli väärin,
			//joten ei tehda mitään, annetaan NULLin palautua
		}
		return person;
	}

	@Override
	public boolean changePassword(Person person, String oldPassword,
			String newPassword) {
		Person check = null;
		check = this.login(person.getEmail(), oldPassword);		
		if(check != null && (check.getId().equals(person.getId()))){
			newPassword = hashPassword(newPassword);
			this.changePassword(person.getId(), newPassword);
			return true;
		}
		return false;
	}

	@Override
	public String generatePassword(Person person) {
		String password = hashPassword(""+System.currentTimeMillis());
		password = password.substring(0, 8);
		String hash = hashPassword(password);
		this.changePassword(person.getId(), hash);
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