package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

import entity.Employee;

public class EmpDAOJdbcImpl implements EmployeeDAO {

	public void delete(long id) throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement stat = conn.createStatement();
		stat.executeUpdate("delete from t_emp where id='" + id + "'");
		conn.close();
	}

	public List<Employee> findAll() throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("select * from t_emp");
		List<Employee> eps = new ArrayList<Employee>();
		while (rs.next()) {
			Employee e = new Employee();
			e.setId(rs.getLong("id"));
			e.setName(rs.getString("name"));
			e.setSalary(rs.getDouble("salary"));
			e.setAge(rs.getInt("age"));
			eps.add(e);
		}
		return eps;
	}

	public void save(Employee e) throws Exception {
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn
				.prepareStatement("insert into t_emp (name,salary,age) values (?,?,?)");
		prep.setString(1, e.getName());
		prep.setDouble(2, e.getSalary());
		prep.setInt(3, e.getAge());
		prep.executeUpdate();
		conn.close();
	}

	public Employee findById(long id) throws Exception {
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn
				.prepareStatement("select * from t_emp where id=?");
		prep.setLong(1, id);
		Employee eps = new Employee();
		ResultSet rs = prep.executeQuery();
		if (rs.next()) {
			eps.setId(rs.getLong("id"));
			eps.setName(rs.getString("name"));
			eps.setSalary(rs.getDouble("salary"));
			eps.setAge(rs.getInt("age"));
		}
		return eps;
	}

	public void update(Employee e) throws Exception {
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn
				.prepareStatement("update t_emp set name=?,salary=?,age=? where id=?");
		prep.setString(1, e.getName());
		prep.setDouble(2, e.getSalary());
		prep.setInt(3, e.getAge());
		prep.setLong(4, e.getId());
		prep.executeUpdate();
		conn.close();
	}

}
