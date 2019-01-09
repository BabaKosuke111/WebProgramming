package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserDetailServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
	 	String targetId=request.getParameter("targetId");

		UserDao userDao = new UserDao();
		User user = userDao.findById(targetId);


		request.setAttribute("userDetail", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userupdate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		String password=request.getParameter("password");
		String cfpassword=request.getParameter("cfpassword");
		String name=request.getParameter("name");
		String birthDate=request.getParameter("birthDate");
	 	String targetId=request.getParameter("targetId");
	 	UserDao userDao = new UserDao();

		User user = userDao.findById(targetId);
		request.setAttribute("userDetail", user);

		if(!password.equals(cfpassword)) {
			request.setAttribute("errMsg", "入力された内容は正しくありません。");

			// ログインjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userupdate.jsp");
			dispatcher.forward(request, response);
			return;
		}
		if(name.length()==0||birthDate.length()==0) {
			request.setAttribute("errMsg", "入力された内容は正しくありません。");


			// ログインjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userupdate.jsp");
			dispatcher.forward(request, response);
			return;
		}
		if(password.length()==0) {
			userDao.UpdateInfo(name, birthDate, targetId);
			response.sendRedirect("UserListServlet");
			return;
		}
		else if(!(password.length()==0)) {
			 userDao.UpdateInfo(password,name,birthDate,targetId);
			 response.sendRedirect("UserListServlet");
		}

	}

}
