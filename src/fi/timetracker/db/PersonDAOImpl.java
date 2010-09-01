package fi.timetracker.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import fi.timetracker.entity.Entity;
import fi.timetracker.entity.Person;
/** 
 * @author Petteri Parviainen
 */
public class PersonDAOImpl extends AbstractDAO implements PersonDAO {

	private JdbcTemplate jdbcTemplate;
/*
	private static final String INSERT = "INSERT INTO Person (status, firstname,"
			+ " lastname, title, birthday, social_security_suffix, address, "
			+ "postalcode, city, country, role, email, phone, creator) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?";
*/
	private static final String UPDATE = "UPDATE Person SET status = ?, "
			+ "firstname = ?, lastname = ?, title = ?, birthday = ?, "
			+ "social_security_suffix = ?, address = ?, postalcode = ?, city = ?,"
			+ " country = ?, role = ?, email = ?, phone = ?, updated = CURRENT_TIMESTAMP WHERE id = ?";
	private static final String GET_PERSON = "SELECT * FROM Person WHERE id = ?";
	private static final String FIND = "SELECT DISTINCT person.* FROM person LEFT JOIN person_projects ON person.id = person_projects.person_id WHERE firstname " +
			"ILIKE ? AND lastname ILIKE ? AND email ILIKE ?";
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Entity getById(int id) {
		return this.getPerson(id);
	}

	@Override
	public List<Person> findPersons(String firstname, String lastname, 
			String email, Set<Integer> projects){
		String query = FIND;
		LinkedList<Object> args = new LinkedList<Object>();
		args.add(firstname);
		args.add(lastname);
		args.add(email);
		if(projects != null && projects.size() > 0){
			query += generateInCriteria(projects.size());
			for(Integer i:projects){
				args.add(i);
			}		
		}
		return this.jdbcTemplate.query(query, args.toArray(), new PersonRowMapper());		
	}

	@Override
	public Person getPerson(int id) {
		return (Person) this.jdbcTemplate.queryForObject(GET_PERSON, new Object[]{id}, new PersonRowMapper());		
	}

	@Override
	public Integer savePerson(Person person) {
		int id = -1;
		if (person.getId() == null) {
			 id = this.insertPerson(person);
		} else {
			this.optimisticLockingCheck(person); //OptimisticLocking
			id = this.updatePerson(person);
		}
		return id;
	}

	private int updatePerson(Person person) {
		LinkedList<Object> args = new LinkedList<Object>();
		args.add(person.getStatus().getCode());
		args.add(person.getFirstname());
		args.add(person.getLastname());
		args.add(person.getTitle());
		args.add(person.getDateOfBirth());
		args.add(person.getSocialSecuritySuffix());
		args.add(person.getAddress());
		args.add(person.getPostalcode());
		args.add(person.getCity());
		args.add(person.getCountry());
		args.add(""+person.getRole().getCode());
		args.add(person.getEmail());
		args.add(person.getPhone());
		args.add(person.getId());
		
		this.jdbcTemplate.update(UPDATE, args.toArray());
		return person.getId();
	}

	private int insertPerson(Person person) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate)
				.withTableName("Person").usingGeneratedKeyColumns("id");
		jdbcInsert.usingColumns("status","firstname","lastname","title","birthday",
				"social_security_suffix","address","postalcode","city","country","role","email","phone");
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("status", person.getStatus().getCode());
		parameters.addValue("firstname", "" + person.getFirstname());
		parameters.addValue("lastname", "" + person.getLastname());
		parameters.addValue("title", "" + person.getTitle());
		parameters.addValue("birthday", person.getDateOfBirth());
		parameters.addValue("social_security_suffix", person.getSocialSecuritySuffix());
		parameters.addValue("address", person.getAddress());
		parameters.addValue("postalcode", person.getPostalcode());
		parameters.addValue("city", person.getCity());
		parameters.addValue("country", person.getCountry());
		parameters.addValue("role", ""+person.getRole().getCode());
		parameters.addValue("email", person.getEmail());
		parameters.addValue("phone", person.getPhone());
		Number id = jdbcInsert.executeAndReturnKey(parameters);
		return id.intValue();
	}
	
	protected static class PersonRowMapper implements RowMapper {

		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person = new Person(rs.getInt("id"));
			person.setRoleCode(rs.getString("role"));
			person.setStatusCode(rs.getString("status"));
			person.setFirstname(rs.getString("firstname"));
			person.setLastname(rs.getString("lastname"));
			person.setTitle(rs.getString("title"));
			person.setEmail(rs.getString("email"));
			person.setPhone(rs.getString("phone"));
			person.setDateOfBirth(rs.getDate("birthday"));
			person.setSocialSecuritySuffix(rs
					.getString("social_security_suffix"));
			person.setAddress(rs.getString("address"));
			person.setPostalcode(rs.getString("postalcode"));
			person.setCity(rs.getString("city"));
			person.setCountry(rs.getString("country"));
			person.setLastlogin(rs.getTimestamp("last_login"));
			person.setCreated(rs.getTimestamp("created"));
			person.setUpdated(rs.getTimestamp("updated"));
			return person;
		}
	};
}