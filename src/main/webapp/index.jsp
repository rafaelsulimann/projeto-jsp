<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Sulimann JSP</title>
	</head>
	<body>
		<header>
			<h1>Sulimann JSP</h1>
		</header>
		<main>
			<section id="login" class="generic-section">
				<div class="container">
					<div class="form-login">
						<form action="ServletLogin" method="post">
						<input type="hidden" value="<%= request.getParameter("url") %>" name="url">
							<div class="form-login-input">
								<div class=form-login-input-user>
									<label for=ctrl-user>Usuario</label>
									<div class="form-login-input-user-text">
										<input id=ctrl-user type="text" name="usuario" placeholder="Insira seu usuário">
									</div>							
								</div>	
								<div class=form-login-input-password>
									<label for=ctrl-password>Senha</label>
									<div class="form-login-input-password-text">
										<input id=ctrl-password type="password" name="senha" placeholder="Insira sua senha">
									</div>							
								</div>						
							</div>
							<div class="form-login-submit">
								<input type="submit" value="Enviar">
							</div>					
						</form>
					</div>
					<div class=form-login-user-error-msg>
						<h5>${msg}</h5>
					</div>
				</div>				
			</section>	
		</main>
	</body>
</html>