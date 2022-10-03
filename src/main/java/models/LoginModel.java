package models;

import java.io.Serializable;

public class LoginModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private String senha;
	
	public LoginModel() {
	}
	
	public LoginModel(String usuario, String senha) {
		super();
		this.usuario = usuario;
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "LoginModel [usuario=" + usuario + ", senha=" + senha + "]";
	}	

}
