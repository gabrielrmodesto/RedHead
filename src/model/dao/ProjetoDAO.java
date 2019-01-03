/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.bean.Projeto;
import model.db.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author gabrielmodesto
 */
public class ProjetoDAO {
    public boolean create(Projeto p){
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        boolean create = false;
        
        try{
            stmt = com.prepareStatement("INSERT INTO PROJETO (nomeProjeto, prazo, equipe, valorTotal,"
                    + "cpfFuncionario, codDepartamento, cpfCliente, nomeCliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, p.getNomeProjeto());
            stmt.setString(2, p.getPrazo());
            stmt.setInt(3, p.getEquipe());
            stmt.setFloat(4, p.getValor());
            stmt.setString(5, p.getCpfFuncionario());
            stmt.setInt(6, p.getCodDepartamento());
            stmt.setString(7, p.getCpfCliente());
            stmt.setString(8, p.getNomeCliente());

            stmt.executeUpdate();
            create = true;
        }
         catch(SQLException ex){
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
            create = false;
        }
        finally{
            ConnectionFactory.closeConection(com, stmt);
        }
        return create;
    }
    
    public ArrayList<Projeto> read(){
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Projeto> projetos = new ArrayList<>();
        
        try{
            stmt = com.prepareStatement("SELECT * FROM PROJETO");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Projeto projeto = new Projeto();
                projeto.setCodProjeto(rs.getInt("codProjeto"));
                projeto.setNomeProjeto(rs.getString("nomeProjeto"));
                projeto.setPrazo(rs.getString("prazo"));
                projeto.setEquipe(rs.getInt("equipe"));
                projeto.setValor(rs.getFloat("valorTotal"));
                projeto.setCpfFuncionario(rs.getString("cpfFuncionario"));
                projeto.setCodDepartamento(rs.getInt("codDepartamento"));
                projeto.setCpfCliente(rs.getString("cpfCliente"));
                projeto.setNomeCliente(rs.getString("nomeCliente"));
                
                
                projetos.add(projeto);
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            ConnectionFactory.closeConection(com, stmt, rs);
        }
        return projetos;
    }
    
    public boolean update(Projeto p){
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        boolean update = false;
        
        try{
            stmt = com.prepareStatement("UPDATE PROJETO SET nomeProjeto = ?, prazo = ?, equipe = ?, "
                    + "valorTotal = ?, cpfFuncionario = ?, codDepartamento = ?, cpfCliente = ?, nomeCliente = ? WHERE codProjeto = ?");
            stmt.setString(1, p.getNomeProjeto());
            stmt.setString(2, p.getPrazo());
            stmt.setInt(3, p.getEquipe());
            stmt.setFloat(4,p.getValor());
            stmt.setString(5, p.getCpfFuncionario());
            stmt.setInt(6, p.getCodDepartamento());
            stmt.setString(7, p.getCpfCliente());
            stmt.setString(8, p.getNomeCliente());
          
            
            stmt.executeUpdate();
            update = true;
            
        }
         catch (SQLException ex) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
            update = false;
        } 
        finally {
            ConnectionFactory.closeConection(com, stmt);
        }
        return update;
    }
    public boolean delete(Projeto p){
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        boolean delete = false;
        
        try{
            stmt = com.prepareStatement("DELETE FROM PROJETO WHERE codProjeto = ?");
            stmt.setInt(1, p.getCodProjeto());
            
            stmt.executeUpdate();
            delete = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
            delete = false;
        } finally {
            ConnectionFactory.closeConection(com, stmt);
        }
        return delete;
    }
    public ArrayList<Projeto> buscaProjetos(String pesquisa){
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Projeto> projetos = new ArrayList<>();
       
        try{
            stmt = com.prepareStatement("SELECT * FROM Projeto WHERE nomeProjeto LIKE ? or cpfCliente LIKE ?");
            stmt.setString(1, pesquisa+"%");
            stmt.setString(2, pesquisa+"%");
            rs = stmt.executeQuery();
          
            while(rs.next()){
                Projeto projeto = new Projeto();
                projeto.setNomeProjeto(rs.getString("nomeProjeto"));
                projeto.setPrazo(rs.getString("prazo"));
                projeto.setEquipe(rs.getInt("equipe"));
                projeto.setValor(rs.getFloat("valorTotal"));
                projeto.setCpfFuncionario(rs.getString("cpfFuncionario"));
                projeto.setCodDepartamento(rs.getInt("codDepartamento"));
                projeto.setCpfCliente(rs.getString("cpfCliente"));              
                projetos.add(projeto);
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            ConnectionFactory.closeConection(com, stmt, rs);
        }
        return projetos;
    }
    
    public float getRendaBruta() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        float renda = 0;

        try {
            stmt = con.prepareStatement("SELECT valorTotal FROM PROJETO");
            rs = stmt.executeQuery();
            while (rs.next()) {
                renda += rs.getFloat("valorTotal");
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return renda;
    }
}
