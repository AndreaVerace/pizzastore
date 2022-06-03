<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="./header.jsp" />
	   
	   <title>Inserisci Nuovo Ordine </title>
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
		
		
							<form method="post" action="ExecuteInsertOrdineServlet" class="row g-3" novalidate="novalidate">
							
							
								<div class="col-md-6">
									<label for="codice" class="form-label">Codice</label>
									<input type="text" name="codice" id="codice" class="form-control" placeholder="Inserire il codice" value="${insert_ordine_attr.codice }">
								</div>
								
								<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${insert_ordine_attr.dataOrdine}' />
								<div class="col-md-6">
									<label for="dataOrdine" class="form-label">Data di Pubblicazione</label>
	                        		<input class="form-control" id="dataOrdine" type="date" placeholder="dd/MM/yy" 
	                        				title="formato : gg/mm/aaaa"  name="dataOrdine" value="${parsedDate}" >
								</div>
								
								<div class="col-md-3">
									<label for="closed" class="form-label">Stato Ordine<span class="text-danger">*</span></label>
								    <select class="form-select" id="closed" name="closed" required>
								    	<option value="" selected> - Selezionare - </option>
								      	<option value="true" ${insert_ordine_attr.closed == 'true'?'selected':''} >Stato Ordine ATTIVO</option>
								      	<option value="false" ${insert_ordine_attr.closed == 'false'?'selected':''} >Stato Ordine NON ATTIVO</option>
								    </select>
								</div>
								
								<div class="col-md-6">
									<label for="utente.id">Fattorino</label>
								    <select class="form-select" id="utente.id" name="utente.id">
								    	<option value="" selected> -- Selezionare una voce -- </option>
								      	<c:forEach items="${ utenti_list_attribute }" var="utenteItem">
								      		<option value="${utenteItem.id}" ${insert_ordine_attr.utente.id == utenteItem.id?'selected':''} >${utenteItem.nome } ${utenteItem.cognome }</option>
								      	</c:forEach>
								    </select>
								</div>
								
								<div class="col-md-6">
									<label for="cliente.id">Cliente</label>
								    <select class="form-select" id="cliente.id" name="cliente.id">
								    	<option value="" selected> -- Selezionare una voce -- </option>
								      	<c:forEach items="${clienti_list_attribute }" var="clienteItem">
								      		<option value="${clienteItem.id}" ${insert_ordine_attr.cliente.id == clienteItem.id?'selected':''} >${clienteItem.nome } ${clienteItem.cognome }</option>
								      	</c:forEach>
								    </select>
								</div>
															
								
								<div class="btn-group" role="group" aria-label="Basic checkbox toggle button group">
								<c:forEach items="${pizze_list_attribute }" var="pizzaItem">
								  <input type="checkbox" class="btn-check" id="btncheck1" autocomplete="off">
								  <label class="btn btn-outline-primary" for="btncheck1"> ${pizzaItem.descrizione }</label>
								  </c:forEach>
								  
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