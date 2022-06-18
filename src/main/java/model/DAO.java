package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
       
        // INSTANCIAÇÃO DE UMA COLLECTION ARRAYLIST PARA ARMAZENAR TODOS OS OBJETOS DO TIPO CONTATO
        ArrayList<Contato> contatos = new ArrayList<>();
       
        
       // SETA TODAS AS CONFIGURAÇÕES NECESSÁRIAS PARA A CONEXÃO COM O BANCO DE DADOS (DRIVER, ENDEREÇO, USUÁRIO E SENHA)
        private String driver = "com.mysql.cj.jdbc.Driver";
        private String url = "jdbc:mysql://127.0.0.1:3306/agenda_contatos?"
                        + "useTimezone=true&serverTimezone=UTC";
        private String user = "root";
        private String password = "";
       
       // FUNÇÃO PARA ESTABELECER A CONEXÃO COM O BANCO DE DADOS
        private Connection conectar() {
                Connection con = null;
                try {
                       // BUSCA O OBJETO ASSOCIADO À INTERFACE DE CONEXÃO COM O BANCO DE DADOS MYSQL
                        Class.forName(driver);
                       // INICIA A SESSÃO COM O BANCO DE DADOS E A ARMAZENA NA VARIÁVEL "con"
                        con = DriverManager.getConnection(url, user, password);
                       // RETORNA A SESSÃO COM O BANCO
                        return con;
                }catch(Exception e) {
                       // SE NÃO FOI POSSÍVEL ESTABELECER A CONEXÃO COM O BANCO DE DADOS, O ERRO É TRATADO E EXIBIDO NO CONSOLE
                        System.out.println(e);
                        return null;
                }
               
        }
       
       // FUNÇÃO PARA TESTAR SE É POSSÍVEL ESTABELECER CONEXÃO COM O BANCO
        public void testeConexao() {
                try {
                       // CHAMA A FUNÇÃO QUE ESTABELECE A CONEXÃO
                        Connection con = conectar();
                       // IMPRIME NO CONSOLE AS INFORMAÇÕES DA SESSÃO
                        System.out.print(con);
                       // ENCERRA A SESSÃO
                        con.close();
                }catch(Exception e) {
                       // SE NÃO FOI POSSÍVEL ESTABELECER A CONEXÃO COM O BANCO DE DADOS, O ERRO É TRATADO E EXIBIDO NO CONSOLE 
                        System.out.print(e);
                }
        }
       
       // FUNÇÃO PARA INSERIR UM CONTATO NO BANCO DE DADOS
        public void inserirContato(Contato contato) {
               // COMANDO SQL PARA INSERÇÃO
                String create = "INSERT INTO contatos"
                                + "(nome,telefone,email) VALUES (?,?,?)";
                try {
                       // CONECTA COM O BANCO DE DADOS E ARMAZENA A SESSÃO DE CONEXÃO
                        Connection con = conectar();
                       // PRÉ-COMPILA E ARMAZENA A INSTRUÇÃO SQL PARA A POSTERIOR INSERÇÃO DE VALORES
                        PreparedStatement pst = con.prepareStatement(create);
                       // SETA, NA INSTRUÇÃO PRÉ-COMPILADA, NO VALOR DE POSIÇÃO 1 O VALOR DO ATRIBUTO "nome" DO OBJETO "contato" RECEBIDO COMO PARÂMETRO NESTA FUNÇÃO
                        pst.setString(1, contato.getNome());
                        pst.setString(2, contato.getTelefone());
                        pst.setString(3, contato.getEmail());
                       // EXECUTA A INSTRUÇÃO SQL NO BANCO DE DADOS
                        pst.executeUpdate();
                       // ENCERRA A SESSÃO COM O BANCO DE DADOS
                        con.close();                        
                }catch(Exception e) {
                        System.out.println(e);
                }                                
        }
       
       // FUNÇÃO PARA OBTER TODOS OS CONTATOS DO BANCO DE DADOS
        public ArrayList<Contato> listarContatos(){
               // APAGA TODOS OS ITENS DA LISTA DE CONTATOS (COLLECTION ARRAYLIST)
        	  contatos.clear();
               // COMANDO SQL PARA SELECIONAR (CONSULTAR) ORDENANDO OS REGISTROS PELO VALOR DA COLUNA "nome"
                String read = "SELECT * FROM contatos ORDER BY nome";
                try {
                        Connection con = conectar();
                        PreparedStatement pst = con.prepareStatement(read);
                       // EXECUTA A INSTRUÇÃO SQL E ARMAZENA O RETORNO (A TABELA PROVENIENTE DO SELECT) PARA POSTERIOR CONSULTA
                        ResultSet rs = pst.executeQuery();
                       // ENQUANTO HOUVER UM REGISTRO (UMA LINHA) DA TABELA A SER CONSULTADO
                        while(rs.next()){
                               // SETA O VALOR DA STRING "idCon" COM O VALOR PRESENTE NA COLUNA 1 DA TABELA ARMAZENADA
                                String idCon = rs.getString(1);
                                String nome = rs.getString(2);
                                String telefone = rs.getString(3);
                                String email = rs.getString(4);
                               // ADICIONA À LISTA DE CONTATOS O OBJETO CRIADO COM AS INFORMAÇÕES DO RESPECTIVO REGISTRO (LINHA) DA TABELA
                                contatos.add(new Contato(idCon,nome,telefone,email));
                        }
                        con.close();                        
                }catch(Exception e) {
                        System.out.println(e);
                }        
               // RETORNA A LISTA DE CONTATOS PREENCHIDA
                return contatos;
        }
        
       // FUNÇÃO PARA OBTER AS INFORMAÇÕES DE UM CONTATO DO BANCO DE DADOS
        public void selecionarContato(Contato contato){
            String read = "SELECT * FROM contatos WHERE idCon = ?";
            try {
                    Connection con = conectar();
                    PreparedStatement pst = con.prepareStatement(read);
                    pst.setString(1, contato.getIdCon());
                    ResultSet rs = pst.executeQuery();
                    while(rs.next()) {
                    	contato.setIdCon(rs.getString("idCon"));
                        contato.setNome(rs.getString("nome"));
                        contato.setTelefone(rs.getString("telefone"));
                        contato.setEmail(rs.getString("email"));
                    }
                    
                    con.close();                        
            }catch(Exception e) {
                    System.out.println(e);
            }        
        }
        
       // FUNÇÃO PARA ALTERAR AS INFORMAÇÕES DE UM CONTATO NO BANCO DE DADOS
        public void alterarContato(Contato contato) {
            String create = "UPDATE contatos"
                            + " SET nome=?, telefone=?, email=? WHERE idCon = ?";
            try {
                    Connection con = conectar();
                    PreparedStatement pst = con.prepareStatement(create);
                    pst.setString(1, contato.getNome());
                    pst.setString(2, contato.getTelefone());
                    pst.setString(3, contato.getEmail());
                    pst.setString(4,contato.getIdCon());
                    pst.executeUpdate();
                    con.close();                        
            }catch(Exception e) {
                    System.out.println(e);
            }                                
        }

       // FUNÇÃO PARA DELETAR UM CONTATO DO BANCO DE DADOS
        public void deletarContato(Contato contato) {
            String delete = "DELETE FROM contatos WHERE idCon = ?";
            
            try {
                    Connection con = conectar();
                    PreparedStatement pst = con.prepareStatement(delete);
                    pst.setString(1,contato.getIdCon());
                    pst.executeUpdate();
                    con.close();                        
            }catch(Exception e) {
                    System.out.println(e);
            }                                
        }
}
