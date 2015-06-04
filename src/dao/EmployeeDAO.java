package dao;

import java.util.List;

import entity.Employee;

/** DAO接口 规范了对数据库的所有操作 */
public interface EmployeeDAO {
	// 增加员工
	public void save(Employee e)throws Exception;

	// 删除员工
	public void delete(long id)throws Exception;

	// 根据id查找
	public Employee findById(long id)throws Exception;

	// 修改
	public void update(Employee e)throws Exception;

	// 查询所有员工
	public List<Employee> findAll() throws Exception;
}
