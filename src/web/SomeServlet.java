package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpDAOJdbcImpl;
import dao.EmployeeDAO;
import entity.Employee;

public class SomeServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
	    throws ServletException, IOException {
		// 获取url地址
		String url = req.getRequestURI();
		String path = url.substring(url.lastIndexOf("/"), url.lastIndexOf("."));
		// 根据地址 执行相对应的数据库操作
		if (path.equals("/add")) {
			req.setCharacterEncoding("utf-8");
			EmployeeDAO dao = new EmpDAOJdbcImpl();
			Employee eps = new Employee();
			eps.setName(req.getParameter("name"));
			eps.setSalary(Double.parseDouble(req.getParameter("salary")));          
			eps.setAge(Integer.parseInt(req.getParameter("age")));
			try {
				dao.save(eps);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect("list.do");
		}else if (path.equals("/list")) {
			req.setCharacterEncoding("utf-8");
			// 使用dao访问数据库
			EmployeeDAO dao = new EmpDAOJdbcImpl();
			// 通过dao找到所有员工
			List<Employee> emps;
			try {
				emps = dao.findAll();
				resp.setContentType("text/html;charset=utf-8");
				PrintWriter out = resp.getWriter();
				out.println("<table border='1' width='60%'>");
				out.println("<tr><td>id</td><td>姓名</td><td>薪水</td><td>年龄</td></tr>");
				for (Employee e : emps) {
					out.println("<tr><td>" + e.getId() + "</td><td>"
							+ e.getName() + "</td><td>" + e.getSalary()
							+ "</td><td>" + e.getAge()
							+ "</td><td><a href='del.do?id=" + e.getId()
							+ "'>删除</a><a href='edit.do?id=" + e.getId()
							+ "'>修改</a></td></tr>");
				}
				out.println("</table>");
				out.println("<a href='addEmp.html'>新增员工</a>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (path.equals("/del")) {
			try {
				resp.setContentType("text/html;charset=utf-8");
				EmployeeDAO dao = new EmpDAOJdbcImpl();
				Long id = Long.parseLong(req.getParameter("id"));
				dao.delete(id);
				resp.sendRedirect("list.do");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (path.equals("/edit")) {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			EmployeeDAO dao = new EmpDAOJdbcImpl();
			long id = Long.parseLong(req.getParameter("id"));
			try {
				Employee eps = dao.findById(id);

				PrintWriter out = resp.getWriter();
				out.println("<form action='update.do?id=" + eps.getId()
						+ "' method='post'>");
				out.println("id:" + eps.getId() + "<br/>");
				out.println("姓名:<input name='name' value='" + eps.getName()
						+ "'/>" + "<br/>");
				out.println("薪水:<input name='salary' value='" + eps.getSalary()
						+ "'/>" + "<br/>");
				out.println("年龄:<input name='age' value='" + eps.getAge()
						+ "'/>" + "<br/>");
				out.println("<input type='submit' value='确认'/>");
				out.println("</form>");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (path.equals("/update")) {
			req.setCharacterEncoding("utf-8");
			Long id = Long.parseLong(req.getParameter("id"));
			String name = req.getParameter("name");
			double salary = Double.parseDouble(req.getParameter("salary"));
			int age = Integer.parseInt(req.getParameter("age"));
			Employee eps = new Employee();
			eps.setId(id);
			eps.setName(name);
			eps.setSalary(salary);
			eps.setAge(age);
			EmployeeDAO dao = new EmpDAOJdbcImpl();
			try {
				dao.update(eps);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect("list.do");
		}
	}
}
