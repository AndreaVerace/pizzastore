<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Ricerca</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					
					<div class="alert alert-warning alert-dismissible fade show " role="alert">
					  Attenzione!!! Funzionalità ancora non implementata. Sulla 'Conferma' per il momento parte il 'listAll'
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Ricerca elementi</h5> 
				    </div>
				    <div class='card-body'>
		
							<form method="post" action="${pageContext.request.contextPath}/pizzaiolo/ExecuteSearchPizzaServlet" class="row g-3" >
							
							
								<div class="col-md-6">
									<label for="descrizione" class="form-label">Descrizione</label>
									<input type="text" name="descrizione" id="descrizione" class="form-control" placeholder="Inserire la descrizione" >
								</div>
								
								<div class="col-md-6">
									<label for="ingredienti" class="form-label">Ingredienti</label>
									<input type="text" name="ingredienti" id="ingredienti" class="form-control" placeholder="Inserire gli ingredienti" >
								</div>
							
								<div class="col-md-6">
									<label for="prezzoBase" class="form-label">Prezzo Base</label>
									<input type="number" name="prezzoBase" id="prezzoBase" class="form-control" placeholder="Inserire minimo prezzoBase" >
								</div>
					
								
								<div class="col-12">
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
									<a class="btn btn-outline-primary ml-2" href="PrepareInsertPizzaServlet">Add New</a>
									<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
								</div>
								
						</form>
  
				    
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>