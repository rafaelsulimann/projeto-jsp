package filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnection;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import models.LoginModel;

@WebFilter(urlPatterns = {"/principal/*"}) //Intercepta todas as requisições que vierem do projeto ou mapeamento
public class FilterAutentication extends HttpFilter implements Filter {
       
	private static final long serialVersionUID = 1L;
	
	private static Connection connection;

	public FilterAutentication() {
        super();
    }

	//Encerra os processo quando servidor é parado
	//Mataria os processos de conexão com o banco
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Intercepta as requisições e as respostas no sistema
	//Tudo que fizer no sistema vai fazer tudo por aqui
	//Validação de autenticação
	//Dar commit e rollback de transações com o banco de dados
	//Validar e fazer o redirecionamento de páginas
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			LoginModel usuarioLogado = (LoginModel) session.getAttribute("usuario");
			
			String urlParaAutenticar = req.getServletPath();
			
			if(usuarioLogado == null && !urlParaAutenticar.contains("/principal/ServletLogin")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor realize o login");
				dispatcher.forward(request, response);
				return;
			}else {
				chain.doFilter(request, response);
			}
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	//Inicia os processos ou recursos quando o servidor sobe o projeto
	//Iniciar a conexão com o banco de dados
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnection.getConnection();
	}

}
