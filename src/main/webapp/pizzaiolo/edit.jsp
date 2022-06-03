<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>modifica Elemento</title>
	 </head>
<body>
	<!-- Fixed navbar -->
	   		<jsp:include page="./navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Modifica  elemento</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form method="post" action="ExecuteUpdatePizzaServlet" class="row g-3" novalidate="novalidate">
							
							
								<div class="col-md-6">
									<label for="descrizione" class="form-label">Descrizione</label>
									<input type="text" name="descrizione" id="descrizione" class="form-control" placeholder="Inserire la descrizione" value="${edit_pizza_attr.descrizione }">
								</div>
								
								<div class="col-md-6">
									<label for="ingredienti" class="form-label">Ingredienti</label>
									<input type="text" name="ingredienti" id="ingredienti" class="form-control" placeholder="Inserire gli ingredienti" value="${edit_pizza_attr.ingredienti }">
								</div>
							
								<div class="col-md-6">
									<label for="prezzoBase" class="form-label">Prezzo Base</label>
									<input type="text" name="prezzoBase" id="prezzoBase" class="form-control" placeholder="Inserire prezzoBase" value="${edit_pizza_attr.prezzoBase }">
								</div>
								
								<div class="col-md-3">
									<label for="attivo" class="form-label">Stato Pizza<span class="text-danger">*</span></label>
								    <select class="form-select" id="attivo" name="attivo" required>
								    	<option value="" selected> - Selezionare - </option>
								      	<option value="true" ${edit_pizza_attr.attivo == 'true'?'selected':''} >STATO PIZZA ATTIVO</option>
								      	<option value="false" ${edit_pizza_attr.attivo == 'false'?'selected':''} >STATO PIZZA NON ATTIVO</option>
								    </select>
								</div>
								
								<input type="hidden" name="idPizza" value="${edit_pizza_attr.id}">
						    	<button type="submit" name="submit" id="submit" class="btn btn-danger">Conferma</button>
		
						</form>
  
				    
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="./footer.jsp" />
</body>
</html>