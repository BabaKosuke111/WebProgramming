package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login1.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
		        throws ServletException, IOException{
	        // リクエストパラメータの文字コードを指定
	        request.setCharacterEncoding("UTF-8");

			// リクエストパラメータの入力項目を取得
			String loginId = request.getParameter("loginId");
			String password = request.getParameter("password");
			UserDao userDao = new UserDao();
			User user = userDao.findByLoginInfo(loginId, password);
			if (user == null) {
				// リクエストスコープにエラーメッセージをセット
				request.setAttribute("errMsg", "ログインに失敗しました。");

				// ログインjspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login1.jsp");
				dispatcher.forward(request, response);
				return;
			}
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", user);

			response.sendRedirect("UserListServlet");
	 }
}
