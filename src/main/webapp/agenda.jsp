<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "model.Contato" %>
    <%@ page import = "java.util.ArrayList" %>
    <% ArrayList <Contato> lista =(ArrayList <Contato>) request.getAttribute("contatos");%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Meus Contatos</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="./js/confirmacao.js"></script>
</head>
<body class="bg-dark">
	<div class="container-fluid p-3 bg-dark text-white text-center">
            <h1>Agenda de contatos</h1>
            <nav class="navbar navbar-expand-sm bg-dark justify-content-center navbar-dark">
			    <ul class="navbar-nav">
			    	<li class="nav-item">
			        	<a class="nav-link" href="/agenda">Início</a>
			      	</li>
			      	<li class="nav-item">
			        	<a class="nav-link" href="/agenda/novo.html">Cadastro de Contatos</a>
			      	</li>
			    </ul>
			</nav>
        </div>
        <div class="container-fluid mt-0 p-5">
	        <table class="table table-dark table-hover">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nome</th>
                        <th>Telefone</th>
                        <th>Email</th>
                        <th colspan="2">Opções</th>
                    
                    </tr>
                
                </thead>
                <tbody>
                    <% for(int i=0; i<lista.size(); i++){ %>
                        <tr>
                            <td><%= lista.get(i).getIdCon() %></td>
                            <td><%= lista.get(i).getNome() %></td>
                            <td><%= lista.get(i).getTelefone() %></td>
                            <td><%= lista.get(i).getEmail() %></td> 
                            <td><a href="/agenda/select?idCon=<%= lista.get(i).getIdCon() %>"><button type="button" class="btn btn-outline-warning btn-sm">Editar</button></a>
                            <td><a href="javascript:confirmar(<%= lista.get(i).getIdCon() %>)"><button type="button" class="btn btn-outline-danger btn-sm">Excluir</button></a>
                            
                        </tr>
                    <% } %>
                </tbody>	
            </table>
        </div>      
        
        <!-- The Modal -->
        <div class="modal fade" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content bg-dark">
            
                    <!-- Modal Header -->
                    <div class="modal-header">
                    <h4 class="modal-title text-white">Editar Contato</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
            
                    <!-- Modal body -->
                    <div class="modal-body">
                        <form action="#" method="post">
                            <fieldset>
                                <div class="row text-white">
                                	<div class="col">
                                        <label>Id:</label>
                                        <input type="text" class="form-control" name="id" disabled="disabled" placeholder="Id">
                                    </div>
                                </div>
                                <div class="row text-white">
                                    <div class="col">
                                        <label>Nome:</label>
                                        <input type="text" class="form-control" name="nome" placeholder="Digite o nome do contato">
                                    </div>
                                </div>
                                <div class="row text-white">
                                    <div class="col">
                                        <label>Telefone:</label>
                                        <input type="text" class="form-control" name="telefone" placeholder="Digite o telefone do contato">
                                    </div>
                                </div>
                                <div class="row text-white">
                                    <div class="col">
                                        <label>email:</label>
                                        <input type="text" class="form-control" name="email" placeholder="Digite o e-mail do contato">
                                    </div>
                                </div>
                                <div class="row mt-3">                                                
                                    <button type="submit" class="btn btn-success" name="Salvar">Salvar</button>
                                </div>
                            </fieldset>
                        </form>
                    </div>
            
                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success" data-bs-dismiss="modal" name="Salvar">Salvar</button>
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Fechar</button>
                    </div>
            
                </div>
            </div>
        </div>
</body>
</html>
