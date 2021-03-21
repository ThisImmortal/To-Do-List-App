<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">

<head>

<title>Register New User Form</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Reference Bootstrap files -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>

<body>

	<div>

		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">

			<div class="panel panel-primary">

				<div class="panel-heading">
					<div class="panel-title">Register New User</div>
				</div>

				<div style="padding-top: 10px" class="panel-body">

					<!-- Registration Form -->
					<form:form action="registrationProcess" modelAttribute="user"
						class="form-horizontal">

						<!-- Place for messages: error, alert etc ... -->
						<div class="form-group">
							<div class="col-xs-15">
								
									<form:errors path="firstName"
										cssClass="alert alert-danger col-xs-offset-1 col-xs-10" />
									<br>
									<form:errors path="lastName"
										cssClass="alert alert-danger col-xs-offset-1 col-xs-10" />
									<br>
									<form:errors path="email"
										cssClass="alert alert-danger col-xs-offset-1 col-xs-10" />
									<br>
									<form:errors path="password"
										cssClass="alert alert-danger col-xs-offset-1 col-xs-10" />
									<br>
									<form:errors path="rePassword"
										cssClass="alert alert-danger col-xs-offset-1 col-xs-10" />
									<br>


								
							</div>
						</div>

						<!-- First name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>

							<form:input path="firstName" placeholder="First name (*)"
								class="form-control" />
						</div>

						<!-- Last Name -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span>

							<form:input path="lastName" placeholder="Last Name"
								class="form-control" />
						</div>

						<!-- Email -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span>

							<form:input path="email" placeholder="Email (*)"
								class="form-control" />
						</div>

						<!-- Password -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span>

							<form:password path="password" placeholder="Password (*)"
								class="form-control" />
						</div>


						<!-- Confirm password -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>

							<form:password path="rePassword"
								placeholder="Confirm password (*)" class="form-control" />
						</div>





						<!-- Register Button -->
						<div style="margin-top: 10px" class="form-group">
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-primary">Register</button>
							</div>
						</div>

						<a href="${pageContext.request.contextPath}/">Back to Log in
							page</a>



					</form:form>

				</div>

			</div>

		</div>


	</div>

</body>
</html>