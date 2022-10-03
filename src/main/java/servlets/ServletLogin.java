package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.LoginModel;
import repositories.LoginRepository;

@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"})
public class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private LoginRepository loginRepository = new LoginRepository();
       
    public ServletLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		try {
			if(usuario != null && !usuario.isEmpty() && senha != null && !senha.isEmpty()) {
				LoginModel login = new LoginModel(usuario, senha);
				if(loginRepository.validarAutenticacao(login)) {
					request.getSession().setAttribute("usuario", login);
					
					if(url == null || url.equals("null")) {
						url = "principal/principal.jsp";
					}
					
					RequestDispatcher dispatcher = request.getRequestDispatcher(url);
					dispatcher.forward(request, response);
					System.out.println(login);
				}else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
					request.setAttribute("msg", "Usuario/Senha incorretos");
					dispatcher.forward(request, response);
				}
				
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Informe os campos corretamente");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			request.setAttribute("msg", e.getMessage());
			dispatcher.forward(request, response);
		}	
		
	}

}
