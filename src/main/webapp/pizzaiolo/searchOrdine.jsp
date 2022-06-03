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
	   		<jsp:include page="./navbar.jsp"></jsp:include>
	    
			
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
		
							<form method="post" action="ExecuteSearchOrdineServlet" class="row g-3" >
							
							
								<div class="col-md-6">
									<label for="codice" class="form-label">Codice</label>
									<input type="text" name="codice" id="codice" class="form-control" placeholder="Inserire il codice" value="${search_ordine_attr.codice }">
								</div>
								
								<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${search_ordine_attr.dataOrdine}' />
								<div class="col-md-6">
									<label for="dataOrdine" class="form-label">Data di Pubblicazione</label>
	                        		<input class="form-control" id="dataOrdine" type="date" placeholder="dd/MM/yy" 
	                        				title="formato : gg/mm/aaaa"  name="dataOrdine" value="${parsedDate}" >
								</div>
								
								<div class="col-md-3">
									<label for="closed" class="form-label">Stato Ordine<span class="text-danger">*</span></label>
								    <select class="form-select" id="closed" name="closed" required>
								    	<option value="" selected> - Selezionare - </option>
								      	<option value="true" ${search_ordine_attr.closed == 'true'?'selected':''} >Stato Ordine ATTIVO</option>
								      	<option value="false" ${search_ordine_attr.closed == 'false'?'selected':''} >Stato Ordine NON ATTIVO</option>
								    </select>
								</div>
								
								
								<div class="col-12">
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
									<a class="btn btn-outline-primary ml-2" href="PrepareInsertOrdineServlet">Add New</a>
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
			<jsp:include page="./footer.jsp" />
	  </body>
</html>