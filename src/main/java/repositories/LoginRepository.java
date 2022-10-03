package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnection;
import models.LoginModel;

public class LoginRepository {
	
	private Connection connection;
	
	public LoginRepository() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean validarAutenticacao(LoginModel loginModel) throws Exception {
		String sql = "select * from tb_logins where usuario = ? and senha = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, loginModel.getUsuario());
		statement.setString(2, loginModel.getSenha());
		
		ResultSet result = statement.executeQuery();
		
		if(result.next()) {
			return true;
		}
		return false;		
	}

}
