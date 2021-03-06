<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
<head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Inserisci Nuovo Elemento</title>
	 </head>
<body>
	<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
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
		
		
							<form method="post" action="${pageContext.request.contextPath}/admin/ExecuteUpdateClienteServlet" class="row g-3" novalidate="novalidate">
							
							
								<div class="col-md-6">
									<label for="nome" class="form-label">Nome</label>
									<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" value="${edit_cliente_attr.nome }">
								</div>
								
								<div class="col-md-6">
									<label for="cognome" class="form-label">Cognome</label>
									<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" value="${edit_cliente_attr.cognome }">
								</div>
							
								<div class="col-md-6">
									<label for="indirizzo" class="form-label">Indirizzo</label>
									<input type="text" name="indirizzo" id="indirizzo" class="form-control" placeholder="Inserire l' indirizzo" value="${edit_cliente_attr.indirizzo }">
								</div>
								
								<div class="col-md-3">
									<label for="attivo" class="form-label">Stato Cliente<span class="text-danger">*</span></label>
								    <select class="form-select" id="attivo" name="attivo" required>
								    	<option value="" selected> - Selezionare - </option>
								      	<option value="true" ${edit_cliente_attr.attivo == 'true'?'selected':''} >CLIENTE ATTIVO</option>
								      	<option value="false" ${edit_cliente_attr.attivo == 'false'?'selected':''} >CLIENTE NON ATTIVO</option>
								    </select>
								</div>
								
								<input type="hidden" name="idCliente" value="${edit_cliente_attr.id}">
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
			<jsp:include page="../footer.jsp" />
</body>
</html>