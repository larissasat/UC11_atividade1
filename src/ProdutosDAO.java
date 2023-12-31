/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;




public class ProdutosDAO {
    
    

    private Connection conn;
    
    
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
       
    public void cadastrarProduto (ProdutosDTO produto){
        
        
        conn = new conectaDAO().connectDB();
        
        
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES "
                        + "(?, ?, ?)"; 
                try {
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                    stmt.setString(1, produto.getNome());
                    stmt.setInt(2, produto.getValor());
                    stmt.setString(3, produto.getStatus());
                    stmt.execute();
            
                } catch (Exception e) {
                    System.out.println("Erro ao inserir produto: " + e.getMessage());
                }
         
        }
        
    
        public ProdutosDTO listarProdutos(String nome) {
            
            conn = new conectaDAO().connectDB();
            
            String sql = "SELECT * FROM produtos WHERE nome LIKE ?";
                try {
                        PreparedStatement stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                                               
                        stmt.setString(1, "%" + nome + "%"); 
                            
                        ResultSet rs = stmt.executeQuery();
                        rs.next();
                        
                        ProdutosDTO produto = new ProdutosDTO();   
                        rs.first(); 
                        
                        produto.setId(rs.getInt("id"));
                        produto.setNome(rs.getString("nome"));
                        produto.setValor(rs.getInt("valor"));
                        produto.setStatus(rs.getString("status"));
                       
                        
                        return produto;
                        
                        
                } catch (Exception e) {
                        System.out.println("erro: " + e.getMessage());
                        return null;
                    }
        
        }
    

        public ArrayList<ProdutosDTO> listarProdutos(){
            
            conn = new conectaDAO().connectDB();
            
            String sql = "SELECT id, nome, valor, status FROM produtos";
            try {
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery();                           
                    
                    List<ProdutosDTO> listagem = new ArrayList<>();
                    
                    while (rs.next()) { 
                        ProdutosDTO produto = new ProdutosDTO();
                        
                        produto.setId(rs.getInt("id"));
                        produto.setNome(rs.getString("nome"));
                        produto.setValor(rs.getInt("valor"));
                        produto.setStatus(rs.getString("status"));
                        
                        listagem.add(produto);    
                    }
                        
                    return (ArrayList<ProdutosDTO>) listagem;
    
    
            } catch (Exception e) {
                return null;
        }    
    }

    public void venderProduto(ProdutosDTO produto) {
        
        conn = new conectaDAO().connectDB(); 
        
        String sql = "UPDATE produtos SET status=? WHERE id=?";
                
        try {
                PreparedStatement stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
                
                stmt.setString(1, produto.getStatus());                               
                stmt.setInt(2, produto.getId()); 
               
                stmt.execute();
            
                } catch (Exception e) {
                    System.out.println("Erro ao vender o produto: " + e.getMessage());
                }

    }

    
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
            
            conn = new conectaDAO().connectDB();
            
            String sql = "SELECT id, nome, valor, status FROM produtos WHERE status LIKE ?;";
            try {
                    PreparedStatement stmt = this.conn.prepareStatement(sql);
                String status = "Vendido";
                    stmt.setString(1, "%" + status + "%");
                    ResultSet rs = stmt.executeQuery();                           
                    
                    List<ProdutosDTO> listagem = new ArrayList<>();
                    
                    while (rs.next()) { 
                        ProdutosDTO produto = new ProdutosDTO();
                        
                        produto.setId(rs.getInt("id"));
                        produto.setNome(rs.getString("nome"));
                        produto.setValor(rs.getInt("valor"));
                        produto.setStatus(rs.getString("status"));
                        
                        listagem.add(produto);    
                    }
                        
                    return (ArrayList<ProdutosDTO>) listagem;
    
    
            } catch (Exception e) {
                return null;
        }    
    }
        
}
