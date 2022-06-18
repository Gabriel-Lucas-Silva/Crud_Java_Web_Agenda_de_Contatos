package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.Contato;

/**
 * Servlet implementation class Controller
 */
// CAMINHOS VÁLIDOS (QUE CHAMAM O SERVLET)
@WebServlet(urlPatterns={"/Controller","/main","/insert","/select","/update","/delete"})
public class Controller extends HttpServlet {
       
        private static final long serialVersionUID = 1L;
        DAO dao = new DAO();
        Contato contato = new Contato();
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
                response.getWriter().append("Served at: ").append(request.getContextPath());
                //dao.testeConexao();
		
		// RECEBE E ARMAZENA A PARTE DA URL (O CAMINHO) QUE CHAMA O SERVLET
                String action = request.getServletPath();
                //System.out.println(action);
		
		
                if(action.equals("/main")){
		    // SE O CAMINHO QUE CHAMOU O SERVLET FOI "/main", A FUNÇÃO "listarContatos" É CHAMADA
                    listarContatos(request,response);
                }
                else if(action.equals("/insert")){
                    novoContato(request,response);
                }
                else if(action.equals("/select")){
                    exibirContato(request,response);
                }
                else if(action.equals("/update")){
                    editarContato(request,response);
                }
                else if(action.equals("/delete")){
                    excluirContatos(request,response);
                }
                else{
		    // SE O CAMINHO QUE CHAMOU O SERVLET NÃO FOI CAPTADO PELOS TESTES ACIMA, É REALIZADO O REDIRECIONAMENTO PARA A PÁGINA "index.html"
                    response.sendRedirect("index.html");
                }
        }   
        
	// FUNÇÃO PARA PRODUZIR A LISTA COM TODOS OS CONTATOS DO BANCO DE DADOS E ENVIAR ESSA LISTA PARA A VIEW
        protected void listarContatos(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {   
			//DECLARA UMA VARIÁVEL DENOMINADA "lista" DE TIPO ARRAYLIST PARAMETRIZADO PELA CLASSE CONTATO 
        		ArrayList <Contato> lista = null;
			// POPULA A LISTA COM TODOS OS CONTATOS, REPASSADOS PELA FUNÇÃO DE CONSULTA DA CLASSE "dao"
        		lista = dao.listarContatos();
        		// NA REQUISIÇÃO, SETA UM ATRIBUTO NOME "contatos", QUE CONTÉM A LISTA DOS CONTATOS
        		request.setAttribute("contatos", lista);
			// FAZ UM CHAMADO DE UM WRAPPER PARA A PÁGINA "agenda.jsp"
        		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
			// FAZ O ENCAMINHAMENTO DA ROTA
        		rd.forward(request, response);
        }
        
	// FUNÇÃO PARA INSERIR UM NOVO CONTATO
        protected void novoContato(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {  
			// RECEBE DA REQUISIÇÃO O ATRIBUTO "nome" PASSADO COMO PARÂMETRO DO TIPO GET E O PÕE NO OBJETO DO TIPO CONTATO
        		contato.setNome(request.getParameter("nome"));
        		contato.setTelefone(request.getParameter("telefone"));
        		contato.setEmail(request.getParameter("email"));
			// CHAMA A FUNÇÃO PARA INSERÇÃO NO BANCO DE DADOS E PASSA O NOVO CONTATO (OBJETO) COMO PARÂMETRO
        		dao.inserirContato(contato);
        		// REDIRECIONA O USUÁRIO PARA A PÁGINA PRINCIPAL
        		response.sendRedirect("/agenda");  
        }
        
	// FUNÇÃO PARA CONSULTAR AS INFORMAÇÕES DE UM CONTATO ESPECÍFICO E ENVIAR ESSAS INFORMAÇÕES PARA A VIEW 
        protected void exibirContato(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        	// RECEBE DA REQUISIÇÃO O ATRIBUTO "idCon" PASSADO COMO PARÂMETRO DO TIPO GET
		String idCon = request.getParameter("idCon");
		// SETA O ATRIBUTO "idCon" DO OBJETO COM O PARÂMETRO RECEBIDO PELA REQUISIÇÃO
		contato.setIdCon(idCon);
		// CHAMA A FUNÇÃO PARA BUSCAR AS INFORMAÇÕES DO CONTATO (PASSADO COMO PARÂMETRO) NO BANCO DE DADOS
		dao.selecionarContato(contato);
        	
        	request.setAttribute("idCon", contato.getIdCon());
        	request.setAttribute("nome", contato.getNome());
        	request.setAttribute("telefone", contato.getTelefone());
        	request.setAttribute("email", contato.getEmail());
		
        	RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		
    		rd.forward(request, response);
		
        }
        
	// FUNÇÃO PARA ALTERAR AS INFORMAÇÕES DE UM CONTATO ESPECÍFICO
        protected void editarContato(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
		
        	contato.setIdCon(request.getParameter("idCon"));
        	contato.setNome(request.getParameter("nome"));
        	contato.setTelefone(request.getParameter("telefone"));
        	contato.setEmail(request.getParameter("email"));
        	
        	dao.alterarContato(contato);
        	response.sendRedirect("main");
        }
        
	// FUNÇÃO PARA EXCLUIR UM CONTATO ESPECÍFICO
        protected void excluirContatos(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
		
        	String idCon = request.getParameter("idCon");
        	contato.setIdCon(idCon);
        	
        	dao.deletarContato(contato);
        	response.sendRedirect("main");
        }

}
