<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Pagina dei Risultati</title>
	 </head>
	 
	<body class="d-flex flex-column h-100">
	 
		<!-- Fixed navbar -->
		<jsp:include page="../navbar.jsp"></jsp:include>
	 
	
		<!-- Begin page content -->
		<main class="flex-shrink-0">
		  <div class="container">
		  
		  		<div class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }" role="alert">
				  ${successMessage}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
				<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
				  Esempio di operazione fallita!
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
				<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
				  Aggiungere d-none nelle class per non far apparire
				   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
		  
		  
		  
		  		<div class='card'>
				    <div class='card-header'>
				        <h5>Lista dei risultati</h5> 
				    </div>
				    <div class='card-body'>
				    	
				    
				        <div class='table-responsive'>
				            <table class='table table-striped ' >
				                <thead>
				                    <tr>
			                         	<th>Codice</th>
				                        <th>Data Ordine</th>
				                        <th>Closed?</th>
				                        <th>Fattorino id :</th>
				                        <th>Cliente id :</th>
				                        
				                        <th>Costo Totale Ordine</th>
				                        <th> Azioni </th>
				                    </tr>
				                </thead>
				                <tbody>
				                	<c:forEach items="${ordini_list_attribute}" var="ordineItem">
										<tr>
											<td>${ordineItem.codice }</td>
											<td>${ordineItem.dataOrdine }</td>
											<td>${ordineItem.closed }</td>
											<td>${ordineItem.utente.id }</td>
											<td>${ordineItem.cliente.id }</td>
											
											<td>${ordineItem.costoTotaleOrdine }</td>
											<td>
									
												<a class="btn  btn-sm btn-outline-secondary" href="ExecuteVisualizzaFattorinoServlet?idOrdine=${ordineItem.id }">Visualizza</a>
												
												<a class="btn btn-outline-danger btn-sm" href="PrepareChiudiOrdineFattorinoServlet?idOrdine=${ordineItem.id }">Delete</a>
											</td>
										</tr>
									</c:forEach>
				                </tbody>
				            </table>
				        </div>
					</div>
				   
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