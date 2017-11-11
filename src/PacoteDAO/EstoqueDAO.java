package PacoteDAO;

import PacoteModelo.Estoque;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class EstoqueDAO {
        static String usuario = "root";
    static String senha = "1940";
    static String caminho =  "jdbc:mysql://localhost:3306/estoque"; 
    PreparedStatement pstmt;
    Connection con;    
    String sSql;
    
    public Connection getConexao() {   
        Connection c;
        c = null;
        try {
            c = DriverManager.getConnection(
                    caminho, usuario, senha);
        }
        catch ( SQLException ex) {
           System.out.println("Erro ao efetuar "
                   + "conexão com o banco de dados: " + ex);     
        }
        return c;
    }
    
    public void cadastrarProduto(Estoque p){
        try {
            con = getConexao();
            pstmt = con.prepareStatement("INSERT " + "INTO PRODUTO(pro_codigo,pro_nome,pro_tipo,pro_descricao,pro_valorUni,pro_dataFab)"
                    + " VALUES(?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, p.getCodigo());
            pstmt.setString(2, p.getNome());
            pstmt.setString(3, p.getTipo());
            pstmt.setString(4, p.getDescricao());
            pstmt.setDouble(5, p.getValorUnitario());
            pstmt.setString(6, p.getFabricacao());
            pstmt.executeUpdate();           
            pstmt.close();
            con.close();
    }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao efetuar cadastro! " + erro.getMessage());
        }      
    }
    
        public void alterarProduto(Estoque p){
        try {
            con = getConexao();
            pstmt = con.prepareStatement("UPDATE PRODUTO SET pro_codigo = ?,pro_nome = ?, pro_tipo=?,pro_valorUni=?,pro_dataFab=?,pro_descricao=? WHERE pro_codigo = ?");
                   
            pstmt.setInt(1, p.getCodigo());
            pstmt.setString(2, p.getNome());
            pstmt.setString(3, p.getTipo());            
            pstmt.setDouble(4, p.getValorUnitario());
            pstmt.setString(5, p.getFabricacao());
            pstmt.setString(6, p.getDescricao());
            pstmt.setInt(7, p.getCodigo());
            pstmt.executeUpdate();           
            pstmt.close();
            con.close();
    }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao efetuar cadastro! " + erro.getMessage());
        }      
    }
        
        public void excluirProduto(Estoque p){
                try{
                    con = getConexao();
                    pstmt = con.prepareStatement("DELETE FROM produto WHERE pro_codigo = ?");
                    pstmt.setInt(1, p.getCodigo());
                    
                    pstmt.executeUpdate();           
                    pstmt.close();
                    con.close();
                }
                catch (SQLException erro){
                    JOptionPane.showMessageDialog(null, "Erro ao efetuar exclusão! " + erro.getMessage());        
                }                
            }
        
        public Estoque consultarProduto(Integer codigo) {
        ResultSet r;
        Estoque p;
        p = new Estoque();
        con = null;
        try{
            con = getConexao();
            pstmt = con.prepareStatement("SELECT pro_codigo,pro_tipo,pro_nome,pro_descricao,pro_dataFab,pro_valorUni,pro_quantidade FROM produto WHERE pro_codigo = ? ");
            pstmt.setInt(1, codigo);
            r = pstmt.executeQuery();
              if (r.next()) {
               p.setCodigo(r.getInt("pro_codigo"));
               p.setTipo(r.getString("pro_tipo"));
               p.setNome(r.getString("pro_nome"));
               p.setDescricao(r.getString("pro_descricao"));
               p.setFabricacao(r.getString("pro_dataFab"));               
               p.setQuantidade(r.getDouble("pro_quantidade"));
               p.setValorUni(r.getDouble("pro_valorUni"));
                          
            }
            else {
               p = null; 
            } 
        }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Falha na pesquisa do produto - Tente Novamente" + erro.getMessage());
        }
        return p;
    }
        
        public Estoque consultarApProduto(String nome) {
        ResultSet r;
        Estoque p;
        p = new Estoque();
        con = null;
        try{
            con = getConexao();
            pstmt = con.prepareStatement("SELECT pro_codigo,pro_valorUni,pro_quantidade FROM produto WHERE pro_nome LIKE ?");
            pstmt.setString(1, nome + "%");
            r = pstmt.executeQuery();
              if (r.next()) {                  
               p.setCodigo(r.getInt("pro_codigo"));      
               p.setValorUni(r.getDouble("pro_valorUni"));
               p.setQuantidade(r.getDouble("pro_quantidade"));
                          
            }
            else {
               p = null; 
            } 
        }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Falha na pesquisa do produto - Tente Novamente" + erro.getMessage());
        }
        return p;
    }
       
        public void qtdProduto(Estoque p){
        try {
            con = getConexao();
            pstmt = con.prepareStatement("UPDATE produto SET pro_quantidade = ? WHERE pro_codigo = ? ");
            pstmt.setDouble(1, p.getQuantidade());
            pstmt.setInt(2, p.getCodigo());            
            pstmt.executeUpdate();           
            pstmt.close();
            con.close();
    }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao efetuar alteração! " + erro.getMessage());
        }      
    }
    
    public void cadastrarMTP(Estoque p){
        try {
            con = getConexao();
            pstmt = con.prepareStatement("INSERT " + "INTO MATERIA_PRIMA(mtp_codigo,mtp_nome,mtp_tipo,for_codigo,mtp_dataVal,mtp_quantidade)" +
                    " VALUES(?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, p.getCodigo());
            pstmt.setString(2, p.getNome());
            pstmt.setString(3, p.getTipo());
            pstmt.setInt(4, p.getForcod());
            pstmt.setString(5, p.getValidade());
            pstmt.setDouble(6, p.getQuantidade());
            
            
            pstmt.executeUpdate();           
            pstmt.close();
            con.close();
    }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao efetuar cadastro! " + erro.getMessage());
        }      
    }
    
            public Estoque consultarMtp(Integer codigo) {
        ResultSet r;
        Estoque p;
        p = new Estoque();
        con = null;
        try{
            con = getConexao();
            pstmt = con.prepareStatement("SELECT materia_prima.mtp_codigo,materia_prima.mtp_nome,materia_prima.mtp_tipo,fornecedor.for_codigo,materia_prima.mtp_dataVal,materia_prima.mtp_quantidade FROM materia_prima,fornecedor WHERE mtp_codigo = ? AND materia_prima.mtp_codigo = fornecedor.for_codigo");
            pstmt.setInt(1, codigo);
            r = pstmt.executeQuery();
              if (r.next()) {
               p.setCodigo(r.getInt("mtp_codigo"));
               p.setNome(r.getString("mtp_nome"));
               p.setTipo(r.getString("mtp_tipo"));               
               p.setForcod(r.getInt("for_codigo"));
               p.setValidade(r.getString("mtp_dataVal"));  
               p.setQuantidade(r.getInt("mtp_quantidade"));
            }
            else {
               p = null; 
            } 
        }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Falha na pesquisa do produto - Tente Novamente" + erro.getMessage());
        }
        return p;
    }
            
            
            public void alterarMtp(Estoque p){
        try {
            con = getConexao();
            pstmt = con.prepareStatement("UPDATE materia_prima,fornecedor SET materia_prima.mtp_codigo = ?,materia_prima.mtp_nome = ?,materia_prima.mtp_tipo=?,fornecedor.for_codigo =?,materia_prima.mtp_dataVal = ? WHERE materia_prima.mtp_codigo = ? AND materia_prima.for_codigo = fornecedor.for_codigo");
                   
            pstmt.setInt(1, p.getCodigo());
            pstmt.setString(2, p.getNome());
            pstmt.setString(3, p.getTipo());            
            pstmt.setDouble(4, p.getForcod());
            pstmt.setString(5, p.getValidade());
            pstmt.setInt(6, p.getCodigo());            
            pstmt.executeUpdate();           
            pstmt.close();
            con.close();
    }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao efetuar cadastro! " + erro.getMessage());
        }      
    }
            
            public void qtdMtp(Estoque p){
        try {
            con = getConexao();
            pstmt = con.prepareStatement("UPDATE materia_prima SET mtp_quantidade = ? WHERE mtp_codigo = ?");
                   
            pstmt.setDouble(1, p.getQuantidade());
            pstmt.setInt(2, p.getCodigo());
             
            pstmt.executeUpdate();           
            pstmt.close();
            con.close();
    }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao efetuar alteração! " + erro.getMessage());
        }      
    }
            public void excluirMtp(Estoque p){
                try{
                    con = getConexao();
                    pstmt = con.prepareStatement("DELETE FROM materia_prima WHERE mtp_codigo = ?");
                    pstmt.setInt(1, p.getCodigo());
                    
                    pstmt.executeUpdate();           
                    pstmt.close();
                    con.close();
                }
                catch (SQLException erro){
                    JOptionPane.showMessageDialog(null, "Erro ao efetuar exclusão! " + erro.getMessage());        
                }                
            }
    
    public void cadastrarFornecedor(Estoque p){
        try {
            con = getConexao();
            pstmt = con.prepareStatement("INSERT " + "INTO FORNECEDOR(for_codigo,for_nome,for_cnpj,for_endereco,for_cep,for_cidade,for_estado,for_email,for_telefone)" + 
               "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, p.getForcod());
            pstmt.setString(2, p.getNome());
            pstmt.setString(3, p.getCnpj());
            pstmt.setString(4, p.getEndereco());
            pstmt.setString(5, p.getCep());
            pstmt.setString(6, p.getCidade());
            pstmt.setString(7,p.getEstado());
            pstmt.setString(8,p.getEmail());
            pstmt.setString(9,p.getTelefone());           
            
            pstmt.executeUpdate();           
            pstmt.close();
            con.close();
    }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao efetuar cadastro! " + erro.getMessage());
        }      
    }
    
            public Estoque consultarFornecedor(Integer codigo) {
        ResultSet r;
        Estoque p;
        p = new Estoque();
        con = null;
        try{
            con = getConexao();
            pstmt = con.prepareStatement("SELECT for_codigo,for_nome,for_cnpj,for_endereco,for_cep,for_cidade,for_estado,for_email,for_telefone  FROM fornecedor WHERE for_codigo = ? ");
            pstmt.setInt(1, codigo);
            r = pstmt.executeQuery();
              if (r.next()) {
               p.setCodigo(r.getInt("for_codigo"));
               p.setNome(r.getString("for_nome"));
               p.setCnpj(r.getString("for_cnpj"));
               p.setEndereco(r.getString("for_endereco"));  
               p.setCep(r.getString("for_cep"));
               p.setCidade(r.getString("for_cidade"));               
               p.setEstado(r.getString("for_estado"));
               p.setEmail(r.getString("for_email"));
               p.setTelefone(r.getString("for_telefone"));
                          
            }
            else {
               p = null; 
            } 
        }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Falha na pesquisa do fornecedor - Tente Novamente" + erro.getMessage());
        }
        return p;
    }
     public void alterarFornecedor(Estoque p){
        try {
            con = getConexao();
            pstmt = con.prepareStatement("UPDATE fornecedor SET for_codigo=?,for_nome=?,for_cnpj=?,for_endereco=?,for_cep=?,for_cidade=?,for_estado=?,for_email=?,for_telefone=? WHERE for_codigo = ? ");
            pstmt.setInt(1, p.getCodigo()); 
            pstmt.setString(2,p.getNome());
            pstmt.setString(3,p.getCnpj());
            pstmt.setString(4,p.getEndereco());
            pstmt.setString(5,p.getCep());
            pstmt.setString(6,p.getCidade());
            pstmt.setString(7,p.getEstado());
            pstmt.setString(8,p.getEmail());
            pstmt.setString(9,p.getTelefone());
            pstmt.setInt(10, p.getCodigo()); 
            pstmt.executeUpdate();           
            pstmt.close();
            con.close();
    }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao efetuar alteração! " + erro.getMessage());
        }      
    }
                public void excluirFornecedor(Estoque p){
                try{
                    con = getConexao();
                    pstmt = con.prepareStatement("DELETE FROM fornecedor WHERE for_codigo = ?");
                    pstmt.setInt(1, p.getCodigo());
                    
                    pstmt.executeUpdate();           
                    pstmt.close();
                    con.close();
                }
                catch (SQLException erro){
                    JOptionPane.showMessageDialog(null, "Erro ao efetuar exclusão! " + erro.getMessage());        
                }                
            }       
        
        public void cadastrarCliente(Estoque p){
        try {
            con = getConexao();
            pstmt = con.prepareStatement("INSERT " + "INTO CLIENTE(cli_codigo,cli_nome,cli_cnpj,cli_endereco,cli_cep,cli_cidade,cli_estado,cli_email,cli_telefone)" + 
               "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, p.getCodigo());
            pstmt.setString(2, p.getNome());
            pstmt.setString(3, p.getCnpj());
            pstmt.setString(4, p.getEndereco());
            pstmt.setString(5, p.getCep());
            pstmt.setString(6, p.getCidade());
            pstmt.setString(7,p.getEstado());
            pstmt.setString(8,p.getEmail());
            pstmt.setString(9,p.getTelefone());           
            
            pstmt.executeUpdate();           
            pstmt.close();
            con.close();
    }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao efetuar cadastro! " + erro.getMessage());
        } 
    } 
        public Estoque consultarCliente(Integer codigo) {
        ResultSet r;
        Estoque p;
        p = new Estoque();
        con = null;
        try{
            con = getConexao();
            pstmt = con.prepareStatement("SELECT cli_codigo,cli_nome,cli_cnpj,cli_endereco,cli_cep,cli_cidade,cli_estado,cli_email,cli_telefone  FROM cliente WHERE cli_codigo = ? ");
            pstmt.setInt(1, codigo);
            r = pstmt.executeQuery();
              if (r.next()) {
               p.setCodigo(r.getInt("cli_codigo"));
               p.setNome(r.getString("cli_nome"));
               p.setCnpj(r.getString("cli_cnpj"));
               p.setEndereco(r.getString("cli_endereco"));  
               p.setCep(r.getString("cli_cep"));
               p.setCidade(r.getString("cli_cidade"));               
               p.setEstado(r.getString("cli_estado"));
               p.setEmail(r.getString("cli_email"));
               p.setTelefone(r.getString("cli_telefone"));
                          
            }
            else {
               p = null; 
            } 
        }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Falha na pesquisa do cliente - Tente Novamente" + erro.getMessage());
        }
        return p;
    }
        
        public Estoque consultarApCliente(String nome) {
        ResultSet r;
        Estoque p;
        p = new Estoque();
        con = null;
        try{
            con = getConexao();
            pstmt = con.prepareStatement("SELECT cli_codigo FROM cliente WHERE cli_nome LIKE ?");
            pstmt.setString(1, nome + "%");
            r = pstmt.executeQuery();
              if (r.next()) {                  
               p.setCodigo(r.getInt("cli_codigo"));                       
            }
            else {
               p = null; 
            } 
        }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Falha na pesquisa do cliente - Tente Novamente" + erro.getMessage());
        }
        return p;
    }
                public void alterarCliente(Estoque p){
        try {
            con = getConexao();
            pstmt = con.prepareStatement("UPDATE cliente SET cli_codigo=?,cli_nome=?,cli_cnpj=?,cli_endereco=?,cli_cep=?,cli_cidade=?,cli_estado=?,cli_email=?,cli_telefone=? WHERE cli_codigo = ? ");
            pstmt.setInt(1, p.getCodigo()); 
            pstmt.setString(2,p.getNome());
            pstmt.setString(3,p.getCnpj());
            pstmt.setString(4,p.getEndereco());
            pstmt.setString(5,p.getCep());
            pstmt.setString(6,p.getCidade());
            pstmt.setString(7,p.getEstado());
            pstmt.setString(8,p.getEmail());
            pstmt.setString(9,p.getTelefone());
            pstmt.setInt(10, p.getCodigo()); 
            pstmt.executeUpdate();           
            pstmt.close();
            con.close();
    }
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao efetuar alteração! " + erro.getMessage());
        }      
    }
            public void excluirCliente(Estoque p){
                try{
                    con = getConexao();
                    pstmt = con.prepareStatement("DELETE FROM cliente WHERE cli_codigo = ?");
                    pstmt.setInt(1, p.getCodigo());
                    
                    pstmt.executeUpdate();           
                    pstmt.close();
                    con.close();
                }
                catch (SQLException erro){
                    JOptionPane.showMessageDialog(null, "Erro ao efetuar exclusão! " + erro.getMessage());        
                }                
            }
  
       public List<Estoque> listarP(){  //adicionar valores para uma tabela utilizando uma lista
            con = getConexao();
            pstmt = null;
            ResultSet r;
            List<Estoque> produtos = new ArrayList<>();
            try {
                pstmt = con.prepareStatement("SELECT * FROM produto ORDER BY pro_tipo,pro_codigo;");
                r = pstmt.executeQuery();
                
                while(r.next()){        //enquanto houver linha é feita a verificação
                    Estoque produto = new Estoque();
                    produto.setCodigo(r.getInt("pro_codigo"));
                    produto.setNome(r.getString("pro_nome"));
                    produto.setTipo(r.getString("pro_tipo"));
                    produto.setFabricacao(r.getString("pro_dataFab"));
                    produto.setValorUni(r.getDouble("pro_valorUni"));
                    produto.setQuantidade(r.getDouble("pro_quantidade"));
                    produto.setDescricao(r.getString("pro_descricao"));
                    produtos.add(produto);
                }
                
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Falha na pesquisa do produto - Tente Novamente" + erro.getMessage());
            }
            return produtos;
       }
       
              public List<Estoque> listarMtp(){
            con = getConexao();
            pstmt = null;
            ResultSet r;
            List<Estoque> mtp = new ArrayList<>();
            try {
                pstmt = con.prepareStatement("SELECT * FROM materia_prima ORDER BY mtp_tipo,mtp_codigo;");
                r = pstmt.executeQuery();
                
                while(r.next()){
                    Estoque produto = new Estoque();
                    produto.setCodigo(r.getInt("mtp_codigo"));
                    produto.setNome(r.getString("mtp_nome"));
                    produto.setTipo(r.getString("mtp_tipo"));
                    produto.setForcod(r.getInt("for_codigo"));                    
                    produto.setValidade(r.getString("mtp_dataVal"));                    
                    produto.setQuantidade(r.getDouble("mtp_quantidade"));                    
                    mtp.add(produto);
                }
                
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Falha na pesquisa do produto - Tente Novamente" + erro.getMessage());
            }
            return mtp;
       }
        
}

