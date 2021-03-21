<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>

<head>
<title>New Plan Form</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/new-plan-form.css">
	
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/js/jquery.datetimepicker.min.css">
	
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.datetimepicker.full.js"></script>
	
</head>


<body>

	<h2>New Plan Form</h2>

	<div class="container">
		<form:form action="saveNewPlan" modelAttribute="newPlan" method="POST">
		<!-- need to associate this data with plan id -->
			<form:hidden path="id" />

			<div class="row">
				<div class="col-25">
					<label for="fname">Title</label>
				</div>
				<div class="col-75">
					<form:input path="title" id="title" name="title"
						placeholder="Title" />
				</div>
			</div>
			

			<div class="row">
				<div class="col-25">
					<label for="subject">Description</label>
				</div>
				<div class="col-75">
					<form:textarea path="description" id="description"
						name="description" placeholder="Write about plan in detail"
						style="height:200px" />

				</div>
			</div>
			
			<div class="row">
				<div class="col-25">
					<label for="lname">Begin Date</label>
				</div>
				<div class="col-75">
					<form:input path="beginDate" id="beginDate" name="beginDate"
						placeholder="When you will begin to realize this plan" />
						<script type="text/javascript">
						  $("#beginDate").datetimepicker();
						</script>
				</div>
			</div>
			
			<div class="row">
				<div class="col-25">
					<label for="lname">End Date</label>
				</div>
				<div class="col-75">
					<form:input path="endDate" id="endDate" name="endDate"
						placeholder="Until when you should finish this plan" />
						<script type="text/javascript">
						  $("#endDate").datetimepicker();
						</script>
				</div>
			</div>
			
			<div class="row">
				<input type="submit" value="Submit">
			</div>

		</form:form>
		
		<p>
			<a href="${pageContext.request.contextPath}/user/getPlansById">Back to list</a>
			</p>


	</div>

</body>



</html>