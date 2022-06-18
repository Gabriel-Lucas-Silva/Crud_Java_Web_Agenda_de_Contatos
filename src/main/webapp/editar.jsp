<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Editar Contato</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="./js/validation.js"></script>
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
			        <a class="nav-link" href="/agenda/main">Tabela de Contatos</a>
			      </li>
			    </ul>
			</nav>
    </div>
    <div class="container mt-2">
        <div class="row">
            <div class="col-sm-3"></div>
            <div class="col-sm-6">
                <form name="frmContato" action="/agenda/update">
                        <legend class="text-white">Editar Contato</legend>
                        <div class="row">
                            <div class="col text-white">
                                <label>Id:</label>
                                <input type="text" class="form-control" name="idCon" placeholder="Id" value="<% out.println(request.getAttribute("idCon")); %>"><br>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col text-white">
                                <label>Nome:</label>
                                <input type="text" class="form-control" name="nome" placeholder="Digite o nome do contato" value="<% out.println(request.getAttribute("nome")); %>"><br>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col text-white">
                                <label>Telefone:</label>
                                <input type="text" class="form-control" name="telefone" placeholder="Digite o telefone do contato" value="<% out.println(request.getAttribute("telefone")); %>"><br>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col text-white">
                                <label>E-mail:</label>
                                <input type="text" class="form-control" name="email" placeholder="Digite o e-mail do contato" value="<% out.println(request.getAttribute("email")); %>"><br>
                            </div>
                        </div>
                        <div class="row">
                        	<br>
                            <a href="#" type="submit" onClick="validar()" class="btn text-white bg-secondary">Salvar</a>
                        </div>
                </form>
            </div> 
        </div>
    </div>     
</body>
</html>