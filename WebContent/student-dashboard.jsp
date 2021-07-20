<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
</head>
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp" />
		<h5>Available Books</h5>
		<table class="table" id="book-table">
			<tr>
				<th>S.N</th>
				<th>Book Title</th>
				<th>Author</th>
				<th>Publisher</th>
				<th>Published Year</th>
			</tr>
		</table>
		<h5>Books you have</h5>
	</div>
	
	

	<!-- Modal -->
	<div class="modal fade" id="book-dialog" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form title="Add Book" class="form">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Request Book
							As</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div class="row mb-3">
							<div class="col-sm-10">
								<input type="text" class="form-control" id="book-title"
									name="book-title" readonly>
							</div>
						</div>
						<div class="row mb-3">
							<div class="col-sm-10">
								<input type="text" class="form-control" id="book-author"
									name="book-author" readonly>
							</div>
						</div>
						<div class="row mb-3">
							<div class="col-sm-10">
								<input type="text" class="form-control" id="book-publisher"
									name="book-publisher" readonly>
							</div>
						</div>
						<div class="row mb-3">
							<div class="col-sm-10">
								<div class='input-group date'>
									<input type='hidden' class="form-control" name="published-year"
										id='published-year' readonly /> <span
										class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
						<div class="row mb-3">
							<div class="col-sm-10">
								<input type="hidden" class="form-control" id="no-Of-Pages"
									name="no-Of-Pages" readonly>
							</div>
						</div>
						<div class="row mb-3">
							<label for="take-book-for" class="col-sm-2 col-form-label">Take Book For(*days)</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="take-book-for"
									name="take-book-for" required>
							</div>
						</div>
						<fieldset class="row mb-3">
							<legend class="col-form-label col-sm-2 pt-0">Request
								Book As</legend>
							<div class="col-sm-10">
								<div class="form-check">
									<input class="form-check-input" type="radio" name="book-status"
										id="book-status-loan" value="Onloan" checked> <label
										class="form-check-label" for="book-status-loan"> Loan
									</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="book-status"
										id="book-status-requested" value="Onrequested"> <label
										class="form-check-label" for="book-status-requested">
										Requested </label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="book-status"
										id="book-status-shelf" value="Onshelf"> <label
										class="form-check-label" for="book-status-shelf">
										Shelf </label>
								</div>
							</div>
						</fieldset>
					</div>
					<div class="modal-footer">
						<input type="hidden" id="book-id" name="book-id" />
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary" id="btn-save">Submit</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	var baseUrl = window.location.origin + "/lms/";
		swal({
			text : "Processing",
			button : false,
			timer : 1000
		});

		$(document)
				.ready(
						function() {
							$.getJSON("server/list-book.jsp", function(data) {
								$table = $("#book-table");
								$.each(data, function(i, book) {
									var $tr = $(
											"<tr data-id='"+book.bookId+"'/>")
											.css("cursor", "pointer");
									$("<td/>").html(book.bookId).appendTo($tr);
									$("<td/>").html(book.bookTitle).appendTo(
											$tr);
									$("<td data-title='name'/>").html(
											book.bookAuthor).appendTo($tr);
									$("<td/>").html(book.publisher).appendTo(
											$tr);
									$("<td/>").html(book.publishedYear)
											.appendTo($tr);
									$tr.on("click", function() {
										$this = $(this);
										populateBook($this.attr('data-id'));
									});
									$tr.appendTo($table);

								});

							});

							$('#btn-save')
									.on(
											'click',
											function() {
												 var formdata = {
													bookId : $("#book-id")
															.val(),
													bookStatus : $("input[type='radio'][name='book-status']:checked").val(),
													bookTakenFor:$('#take-book-for').val()
												};
												$
														 .ajax({
															type : "POST",
															url : baseUrl
																	+ "order",
															contentType : "application/json",
															data : JSON
																	.stringify(formdata),
															dataType : "json",
															success : function(
																	result) {
																swal({
																	text : "Ordered successfully",
																	button : false,
																	icon : "success",
																	timer : 1000
																});
															},
															error : function(e) {
																swal({
																	text : "Cannot Order",
																	icon : "warning",
																	button : Ok,
																	timer : 2000
																});
															}

														}); 
											}); 

						});

		function populateBook(id) {
			$.getJSON("server/list-book.jsp", {
				id : id
			}, function(data) {
				console.log(data);
				var bookStatus = "book-status-";
				if (data.bookStatusType === "Onloan") {
					bookStatus += "loan";
				}
				if (data.bookStatusType === "Onrequested") {
					bookStatus += "requested";
				}
				if (data.bookStatusType === "Onshelf") {
					bookStatus += "shelf";
				}
				$("#book-title").val(data.bookTitle);
				$("#book-author").val(data.bookAuthor);
				$("#book-publisher").val(data.publisher);
				$("#published-year").val(data.publishedYear);
				$("#no-Of-Pages").val(data.nOfPages);
				$("#book-publisher").val(data.publisher);
				$("#book-id").val(data.bookId);
				$("#" + bookStatus).prop("checked", data.bookStatusType);
				$("#book-dialog").modal('show');
			});
		}
		
	</script>

</body>
</html>