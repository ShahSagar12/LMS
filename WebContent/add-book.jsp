<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>add-book</title>
</head>
<body>
	<div class="container">
		<jsp:include page="header.jsp" />

		<table class="table" id="book-table">
			<tr>
				<th>S.N</th>
				<th>Book Title</th>
				<th>Author</th>
				<th>Publisher</th>
				<th>Published Year</th>
			</tr>
		</table>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal"
			data-bs-target="#book-dialog" id="btn-add">Add Book</button>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="book-dialog" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
			<form title="Add Book" action="/lms/books"
						method="post" class="form">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Book</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">

					

						<div class="row mb-3">
							<label for="inputEmail3" class="col-sm-2 col-form-label">Book
								Title</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="book-title"
									name="book-title" required>
							</div>
						</div>
						<div class="row mb-3">
							<label for="inputPassword3" class="col-sm-2 col-form-label">Book
								Author</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="book-author"
									name="book-author" required>
							</div>
						</div>
						<div class="row mb-3">
							<label for="inputPassword3" class="col-sm-2 col-form-label">Book
								Publisher</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="book-publisher"
									name="book-publisher" required>
							</div>
						</div>
						<div class="row mb-3">
							<label for="published-year" class="col-sm-2 col-form-label ">Published
								Year</label>
							<div class="col-sm-10">
								<div class='input-group date' >
									<input type='text' class="form-control" name="published-year" id='published-year' required/>
									<span class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
						<div class="row mb-3">
							<label for="no-Of-Pages" class="col-sm-2 col-form-label">No.
								Of Pages</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="no-Of-Pages"
									name="no-Of-Pages">
							</div>
						</div>
						<fieldset class="row mb-3">
							<legend class="col-form-label col-sm-2 pt-0">Radios</legend>
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
				<input type="hidden" id="book-id" name="book-id"/>
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<button type="button" class="btn btn-danger"
						 id="btn-delete">Delete</button>
					<button type="submit" class="btn btn-primary" id="btn-save">Save Changes</button>
				</div>
				</form>
			</div>
		</div>
	</div>



	<script type="text/javascript">
	
		$(document).ready(
				function() {
					$.getJSON("server/list-book.jsp", function(data) {
						$table = $("#book-table");
						$.each(data, function(i, book) {
							var $tr = $("<tr data-id='"+book.bookId+"'/>").css(
									"cursor", "pointer");
							$("<td/>").html(book.bookId).appendTo($tr);
							$("<td/>").html(book.bookTitle).appendTo($tr);
							$("<td data-title='name'/>").html(book.bookAuthor)
									.appendTo($tr);
							$("<td/>").html(book.publisher).appendTo($tr);
							$("<td/>").html(book.publishedYear).appendTo($tr);
							$tr.on("click", function() {
								$this = $(this);
								populateBook($this.attr('data-id'));
							});
							$tr.appendTo($table);

						});

					});
					
					$('#btn-add').on('click',function(){
						$("#book-id").val();
						$("#book-title").val();
						$("#book-author").val();
						$("#book-publisher").val();
						$("#published-year").val();
						$("#no-Of-Pages").val();
						$("#book-publisher").val();
						$("#book-dialog").modal('show');
					});
					
					
				});
		
		function populateBook(id) {
			$.getJSON("server/list-book.jsp", {
				id : id
			}, function(data) {
				console.log(data);
				var bookStatus="book-status-";
				if(data.bookStatusType==="Onloan"){
					bookStatus+="loan";
				}
				if(data.bookStatusType==="Onrequested"){
					bookStatus+="requested";
				}
				if(data.bookStatusType==="Onshelf"){
					bookStatus+="shelf";
				}
				$("#book-title").val(data.bookTitle);
				$("#book-author").val(data.bookAuthor);
				$("#book-publisher").val(data.publisher);
				$("#published-year").val(data.publishedYear);
				$("#no-Of-Pages").val(data.nOfPages);
				$("#book-publisher").val(data.publisher);
				$("#book-id").val(data.bookId);
				$("#"+bookStatus).prop("checked", data.bookStatusType);
				$("#book-dialog").modal('show');
			});
		}
	</script>
	
</body>
</html>