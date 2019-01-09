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
 * Servlet implementation class NewLogin
 */
@WebServlet("/NewLoginServlet")
public class NewLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newuser.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		 	request.setCharacterEncoding("UTF-8");

			// リクエストパラメータの入力項目を取得
			String loginId = request.getParameter("loginId");
			String password = request.getParameter("password");
			String cfpassword = request.getParameter("cfpassword");
			String name = request.getParameter("name");
			String birthDate=request.getParameter("birthDate");
			UserDao userDao = new UserDao();
			User user = userDao.findByLoginInfo(loginId);
			if(user!=null) {
				request.setAttribute("errMsg", "登録に失敗しました。");

				// ログインjspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newuser.jsp");
				dispatcher.forward(request, response);
				return;
			}
			if(!password.equals(cfpassword)) {
				request.setAttribute("errMsg", "登録に失敗しました。");

				// ログインjspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newuser.jsp");
				dispatcher.forward(request, response);
				return;
			}
			if(loginId.length()==0||password.length()==0||cfpassword.length()==0||name.length()==0||birthDate.length()==0) {
				request.setAttribute("errMsg", "登録に失敗しました。");

				// ログインjspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newuser.jsp");
				dispatcher.forward(request, response);
				return;
			}

			userDao.NewLoginInfo(loginId,password,name,birthDate);



			response.sendRedirect("UserListServlet");
	}

}
