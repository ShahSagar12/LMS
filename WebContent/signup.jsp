<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign up</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="login-form bg-light mt-4 p-4">
					<form action="/lms/signup" method="post" class="row g-3">
						<h4>Register Here</h4>
						<div class="col-12">
							<label>First Name</label> <input type="text" name="firstname"
								class="form-control" placeholder="first name">
						</div>
						<div class="col-12">
							<label>Last Name</label> <input type="text" name="lastname"
								class="form-control" placeholder="last name">
						</div>
						<div class="col-12">
							<label>Email</label> <input type="email" name="email"
								class="form-control" placeholder="email">
						</div>

						<div class="col-12">
							<label>Password</label> <input type="password" name="password"
								class="form-control" placeholder="Password">
						</div>
						
						<div class="col-auto">
							<label for="autoSizingSelect">Register As</label>
							<select class="form-select" id="autoSizingSelect" name="role">
								<option selected>Choose...</option>
								<option value="admin">Admin</option>
								<option value="user">User</option>
							</select>
						</div>
						
						<div class="col-12">
							<button type="submit" class="btn btn-dark float-end">Signup</button>
						</div>
					</form>
					<hr class="mt-4">
					<div class="col-12">
						<p class="text-center mb-0">
							Already have account? <a href="login.jsp">Login</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://www.markuptag.com/bootstrap/5/js/bootstrap.bundle.min.js"></script>

</body>
</html>