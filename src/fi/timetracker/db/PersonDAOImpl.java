package fi.timetracker.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

import fi.timetracker.entity.Entity;
import fi.timetracker.entity.Person;

public class PersonDAOImpl extends AbstractDAO implements PersonDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }	

	@Override
	public Entity getById(int id) {
		return this.getPerson(id);
	}

	@Override
	public List<Person> findPersons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getPerson(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person savePerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static class PersonRowMapper implements RowMapper<Person>{ 
    
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = Person.createInstance(rs.getString("role").charAt(0), rs.getInt("id"));            
            person.setFirstname(rs.getString("firstname"));
            person.setLastname(rs.getString("lastname"));
            return person;
        }
    };
}