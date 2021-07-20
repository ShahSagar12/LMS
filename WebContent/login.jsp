<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="login-form bg-light mt-4 p-4">
					<form action="/lms/login" method="post" class="row g-3">
						<h4>Welcome Back</h4>
						<div class="col-12">
							<label>Email</label> <input type="text" name="email"
								class="form-control" placeholder="Email">
						</div>
						<div class="col-12">
							<label>Password</label> <input type="password" name="password"
								class="form-control" placeholder="Password">
						</div>
						<div class="col-auto">
							<label for="autoSizingSelect">Login As</label>
							<select class="form-select" id="autoSizingSelect" name="role">
								<option selected>Choose...</option>
								<option value="Admin">Admin</option>
								<option value="User">User</option>
							</select>
						</div>

						<div class="col-12">
							<button type="submit" class="btn btn-dark float-end">Login</button>
						</div>
					</form>
					<hr class="mt-4">
					<div class="col-12">
						<p class="text-center mb-0">
							Have not account yet? <a href="/lms/signup">Signup</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	
</body>
</html>