<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Inserisci Nuovo Elemento</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
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
				        <h5>Inserisci nuovo elemento</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form method="post" action="ExecuteInsertClienteServlet" class="row g-3" novalidate="novalidate">
							
							
								<div class="col-md-6">
									<label for="nome" class="form-label">Nome</label>
									<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" value="${insert_cliente_attr.nome }">
								</div>
								
								<div class="col-md-6">
									<label for="cognome" class="form-label">Cognome</label>
									<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" value="${insert_cliente_attr.cognome }">
								</div>
							
								<div class="col-md-6">
									<label for="indirizzo" class="form-label">Indirizzo</label>
									<input type="text" name="indirizzo" id="indirizzo" class="form-control" placeholder="Inserire l' indirizzo" value="${insert_cliente_attr.indirizzo }">
								</div>
								
								<div class="col-md-3">
									<label for="attivo" class="form-label">Stato Cliente<span class="text-danger">*</span></label>
								    <select class="form-select" id="attivo" name="attivo" required>
								    	<option value="" selected> - Selezionare - </option>
								      	<option value="true" ${insert_cliente_attr.attivo == 'true'?'selected':''} >CLIENTE ATTIVO</option>
								      	<option value="false" ${insert_cliente_attr.attivo == 'false'?'selected':''} >CLIENTE NON ATTIVO</option>
								    </select>
								</div>
								
								<div class="col-12">
									<button type="submit" name="insertSubmit" value="insertSubmit" id="insertSubmit" class="btn btn-primary">Conferma</button>
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
			<jsp:include page="./footer.jsp"/>
	  </body>
</html>