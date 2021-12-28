package dao.sql;

import domain.Instructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao_layer.DaoException;
import dao_layer.InstructorDao;


public class InstructorDaoImpl extends BaseDaoImpl implements InstructorDao {

    @Override
    public List<Instructor> getInstructors() throws DaoException {
        String sql = "SELECT id, surname, name FROM instructors";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            List<Instructor> instructors = new ArrayList<>();
            while (resultSet.next()) {
                Instructor instructor = new Instructor();
                instructor.setId(resultSet.getLong("id"));
                instructor.setSurname(resultSet.getString("surname"));
                instructor.setName(resultSet.getString("name"));
                instructors.add(instructor);
            }
            return instructors;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public List<Instructor> getBySurname(String surname) throws DaoException {
        String sql = "SELECT id, surname, name FROM instructors WHERE surname = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, surname);
            resultSet = statement.executeQuery();
            List<Instructor> instructors = new ArrayList<>();
            while (resultSet.next()) {
                Instructor instructor = new Instructor();
                instructor.setId(resultSet.getLong("id"));
                instructor.setSurname(resultSet.getString("surname"));
                instructor.setName(resultSet.getString("name"));
                instructors.add(instructor);
            }
            return instructors;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }

    @Override
    public Long create(Instructor instructor) throws DaoException {
        String sql = "INSERT INTO instructors (id, surname, name) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, instructor.getId());
            statement.setString(2, instructor.getSurname());
            statement.setString(3, instructor.getName());
            statement.executeUpdate();
            return instructor.getId();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }

    @Override
    public Instructor read(Long id) throws DaoException {
        String sql = "SELECT id, surname, name FROM instructors WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Instructor instructor = null;
            if (resultSet.next()) {
                instructor = new Instructor();
                instructor.setId(resultSet.getLong("id"));
                instructor.setSurname(resultSet.getString("surname"));
                instructor.setName(resultSet.getString("name"));
            }
            return instructor;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            	e.printStackTrace();           
            }
        }
    }

    @Override
    public void update(Instructor instructor) throws DaoException {
        String sql = "UPDATE instructors SET surname = ?, name = ? WHERE id = ?";
        PreparedStatement statement = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, instructor.getSurname());
            statement.setString(2, instructor.getName());
            statement.setLong(3, instructor.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }
    
	@Override
	public Instructor getById(Long id) throws DaoException {
		String sql = "SELECT surname, name FROM instructors WHERE id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Instructor instructor = new Instructor();
            while (resultSet.next()) {
                instructor.setId(id);
                instructor.setSurname(resultSet.getString("surname"));
                instructor.setName(resultSet.getString("name"));
            }
            return instructor;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
            	returnConnection(connection);
                statement.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
	}
}