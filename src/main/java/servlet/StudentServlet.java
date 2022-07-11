package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Student;

@WebServlet(name = "studentdb", urlPatterns = { "/" })
public class StudentServlet extends HttpServlet {

	private static List<Student> list = new ArrayList<>();

	protected void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest", "root", "root");
			Statement stmt = con1.createStatement();
			ResultSet rs = stmt.executeQuery("select id,name,mobile,DATE_FORMAT(dob,'%Y-%m-%d') from mytest.students;");
			list.clear();
			while (rs.next()) {
				Student s = new Student();
				s.setId(rs.getInt(1));
				s.setName(rs.getString(2));
				s.setMobile(rs.getLong(3));
				s.setDob(rs.getDate(4));
				list.add(s);

			}
			rs.close();
			stmt.close();
			con1.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/student_view.jsp");
		request.setAttribute("list", list);
		dispatcher.forward(request, response);
	}

	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/student_add.jsp");
		dispatcher.forward(request, response);
	}

	protected void addSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest", "root", "root");
			Statement stmt = con1.createStatement();
			String db = request.getParameter("dob");
			stmt.execute("insert into students values (null,'" + request.getParameter("name") + "',"
					+ request.getParameter("mobile") + ",str_to_date('" + db + "','%Y-%m-%d'))");
			stmt.close();
			con1.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		response.sendRedirect("studentdb?type=view");
	}

	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/data/student_edit.jsp");
//        dispatcher.forward(request, response);
		int studentid = Integer.parseInt(request.getParameter("id"));
		Student stud = null;
		for (Student student : list) {
			if (studentid == student.getId()) {
				stud = student;
				break;
			}
		}
		if (stud != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/student_edit.jsp");

			request.setAttribute("stud", stud);
			dispatcher.forward(request, response);
		}
	}

	protected void editsave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest", "root", "root");
			Statement stmt = con1.createStatement();
			String db = request.getParameter("dob");
			String sql = "UPDATE students SET name= '" + (request.getParameter("name")) + " ', mobile="
					+ request.getParameter("mobile") + ",dob=str_to_date('" + db + "','%Y-%m-%d'	)" + "WHERE id="
					+ Integer.parseInt(request.getParameter("id"));
			stmt.execute(sql);
			stmt.close();
			con1.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		response.sendRedirect("studentdb?type=view");
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//        int studentid = Integer.parseInt(request.getParameter("id"));
//        Student stud = null;
//        for (Student student : list) {
//            if (studentid == student.getId()) {
//                stud = student;
//                break;
//            }
//        }
//        if (stud != null) {
//            list.remove(stud);
//        }
//DELETE FROM Customers WHERE CustomerName='Alfreds Futterkiste';
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest", "root", "root");
			Statement stmt = con1.createStatement();
			stmt.execute("DELETE FROM students where id='" + Integer.parseInt(request.getParameter("id")) + "';");
			stmt.close();
			con1.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		response.sendRedirect("studentdb?type=view");
	}

//    protected void muldelete(HttpServletRequest request, HttpServletResponse response)
//              throws ServletException, IOException {
//        String[] ids = request.getParameterValues("ids");
//        for (String id : ids) {
//            System.out.println("id " + id);
//            if () {
//            }
//        }
//        int studentid = Integer.parseInt(request.getParameter("id"));
//        Student stud = null;
//        for (Student student : list) {
//            if (studentid == student.getId()) {
//                stud = student;
//                break;
//            }
//        }
//        if (stud != null) {
//            list.remove(stud);
//        }
//        response.sendRedirect("studentdb?type=view");
//    }
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		if (type == null || type.equalsIgnoreCase("view")) {
			view(request, response);
		} else if (type.equalsIgnoreCase("add")) {
			add(request, response);
		} else if (type.equalsIgnoreCase("edit")) {
			edit(request, response);
		} else if (type.equalsIgnoreCase("delete")) {
			delete(request, response);
		} else if (type.equalsIgnoreCase("error")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/data/student_error.jsp");
			dispatcher.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		if (type.equalsIgnoreCase("addSave")) {
			addSave(request, response);
		} else if (type.equalsIgnoreCase("editsave")) {
			editsave(request, response);
		} else if (type.equalsIgnoreCase("muldelete")) {
			String[] ids = request.getParameterValues("ids");

			try {
				Class.forName("com.mysql.cj.jdbc.Drivers");
				Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest", "root", "root");
				Statement stmt = con1.createStatement();
				String sql = "DELETE FROM students where id in (" + String.join(",", ids) + ")";
				System.out.println("delete sql " + sql);
				stmt.execute(sql);
				stmt.close();
				con1.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			
//	            // method 1
//	            for (String id : ids) {
//	                System.out.println("id " + id);
//	                try {
//	                    Class.forName("com.mysql.jdbc.Driver");
//	                    Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest",
//	                              "root", "root");
//	                    Statement stmt = con1.createStatement();
//	                    stmt.execute("DELETE FROM customer where id='" + id + "'");
//	                    stmt.close();
//	                    con1.close();
//	                } catch (Exception e) {
//	                    System.out.println(e);
//	                }
//	            }
			response.sendRedirect("studentdb?type=view");
		}
	}

}
