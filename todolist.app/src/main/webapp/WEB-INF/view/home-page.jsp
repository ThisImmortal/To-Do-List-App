
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>

<head>
<title>To Do List App</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/landing-page1.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/update-and-delete-button.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/menu-nav.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>




</head>

<body>
	<jsp:useBean id="now" class="java.util.Date" />





	<div class="split left">

		<div class="icon-bar">
			<h1 style="display: inline; margin-right: 300px;">
				<span style="color: white; font-size: 25px;">Welcome,
					${user.firstName} ${user.lastName}</span>
			</h1>


			<h1 style="display: inline;">
				<a href="${pageContext.request.contextPath}/user/getPlansById">
				<i class="glyphicon glyphicon-calendar" style="margin-top: 7px;"></i>
				</a>
			</h1>
			
			<h1 style="display: inline;">
				<a href="${pageContext.request.contextPath}/user/showDeletedPlans"><i
					class="fa fa-trash" style="margin-top: 7px;"></i></a>
			</h1>
		</div>

		<a href="${pageContext.request.contextPath}/logout"
			class="btn btn-info btn-lg"> <span
			class="glyphicon glyphicon-log-out"></span> Log out
		</a>




		<div class="centered">
			<form:form
				action="${pageContext.request.contextPath}/user/showNewPlanForm"
				method="GET">

				<button type="submit" width="100px" height="100px" class="button4"
					style="vertical-align: middle">
					<span>Add a new Plan</span>
				</button>


			</form:form>


		</div>
	</div>

	<div class="split right">
		<c:choose>
			<c:when test="${empty planList}">
				<c:choose>
					<c:when test="${planlistSource == 'actualPlanlist'}">
						<h1 style="text-align: center; margin-top: 40%;">No plans yet.</h1>
					</c:when>


					<c:when test="${deletedPlanlistSource == 'deletedPlanlist'}">
						<h1 >No deleted plans yet.</h1>
					</c:when>

				</c:choose>
			</c:when>

			<c:otherwise>

				<table id="plansTable">
					<tr>
						<th>Title</th>
						<th>Description</th>
						<th>Begin date</th>
						<th>Deadline</th>
						<th>Status</th>
						<th>Action</th>

					</tr>

					<c:forEach var="tempPlan" items="${planList}">

						<!-- construct an update link with plan id -->
						<c:url var="updateLink" value="/user/showFormForUpdate">

							<c:param name="planId" value="${tempPlan.id}"></c:param>

						</c:url>

						<!-- construct a delete link with plan id -->
						<c:url var="deleteLink" value="/user/delete">

							<c:param name="planId" value="${tempPlan.id}"></c:param>

						</c:url>

						<!-- construct a restore link with plan id -->
						<c:url var="restoreLink" value="/user/restore">

							<c:param name="planId" value="${tempPlan.id}"></c:param>

						</c:url>


						<tr>

							<td><ul>
									<li>${tempPlan.title}</li>
								</ul></td>
							<td>${tempPlan.description}</td>
							<td>${tempPlan.beginDate}</td>
							<td>${tempPlan.endDate}</td>



							<c:choose>

								<c:when
									test="${now lt tempPlan.endDate && now ge tempPlan.beginDate}">
									<td style="color: green;">In process...</td>
								</c:when>
								<c:when test="${now gt tempPlan.endDate}">
									<td style="color: red">Expired</td>
								</c:when>
								<c:otherwise>
									<td style="color: orange;">Waiting the beginning time.</td>
								</c:otherwise>
							</c:choose>

							<td><c:choose>

									<c:when test="${tempPlan.available == 1}">

										<!-- display the update link -->
										<a href="${updateLink}" style="text-decoration: none;"><button
												class="button button2">Update</button></a>

										<!-- display the delete link -->
										<a href="${deleteLink}" style="text-decoration: none;"
											onclick="if(!(confirm('Are you sure you want to delete this plan?')))return false"><button
												class="button button3">Delete</button></a>

									</c:when>

									<c:otherwise>
										<!-- display the restore link -->
										<a href="${restoreLink}" style="text-decoration: none;"
											onclick="if(!(confirm('Are you sure you want to restore this plan?')))return false"><button
												class="button button5">Restore</button></a>

									</c:otherwise>

								</c:choose></td>

						</tr>


					</c:forEach>

				</table>

			</c:otherwise>

		</c:choose>



	</div>


</body>

</html>