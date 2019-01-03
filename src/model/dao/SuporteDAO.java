/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.db.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Suporte;

/**
 *
 * @author gabrielmodesto
 */
public class SuporteDAO {
    public boolean create(Suporte s) {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        boolean create = false;
        try {
            stmt = com.prepareStatement("INSERT INTO SUPORTE (tipoReclamacao, motivoReclamacao, nomeCliente,"
                    + "cpfCliente, cpfFuncionario, codProjeto) VALUES(?, ?, ?, ?, ?, ?)");
            stmt.setString(1, s.getTipoReclamacao());
            stmt.setString(2, s.getMotivoReclamacao());
            stmt.setString(3, s.getNomeCliente());
            stmt.setString(4, s.getCpfCNPJ());
            stmt.setString(5, s.getCpfFuncionario());
            stmt.setInt(6, s.getCodProjeto());

            stmt.executeUpdate();
            create = true;
        } catch (SQLException ex) {
            Logger.getLogger(SuporteDAO.class.getName()).log(Level.SEVERE, null, ex);
            create = false;
        } finally {
            ConnectionFactory.closeConection(com, stmt);
        }
        return create;
    }

    public ArrayList<Suporte> read() {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Suporte> suportes = new ArrayList<>();

        try {
            stmt = com.prepareStatement("SELECT * FROM SUPORTE");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Suporte suporte = new Suporte();
                suporte.setTipoReclamacao(rs.getString("tipoReclamacao"));
                suporte.setMotivoReclamacao(rs.getString("motivoReclamacao"));
                suporte.setNomeCliente(rs.getString("nomeCliente"));
                suporte.setCpfCNPJ("cpfCNPJ");
                suporte.setCpfFuncionario(rs.getString("cpfFuncionario"));
                suporte.setCodProjeto(rs.getInt("codProjeto"));
                
                suportes.add(suporte);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SuporteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConection(com, stmt, rs);
        }
        return suportes;
    }
    
    public boolean update(Suporte s){
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        boolean update = false;
        try{
            stmt = com.prepareStatement("UPDATE SUPORTE SET tipoReclamacao = ?, motivoReclamacao = ?,"
                    + "nomeCliente = ?, cpfCliente = ?, cpfFuncionario = ?, codProjeto = ? WHERE codAtendimento = ?");
            
            stmt.setString(1, s.getTipoReclamacao());
            stmt.setString(2, s.getMotivoReclamacao());
            stmt.setString(3, s.getNomeCliente());
            stmt.setString(4, s.getCpfCNPJ());
            stmt.setString(5, s.getCpfFuncionario());
            stmt.setInt(6, s.getCodProjeto());
            stmt.setInt(7, s.getCodAtendimento());
            
            stmt.executeUpdate();
            update = true;
        }catch (SQLException ex) {
            Logger.getLogger(SuporteDAO.class.getName()).log(Level.SEVERE, null, ex);
            update = false;
        } finally {
            ConnectionFactory.closeConection(com, stmt);
        }
        return update;
    }
    
    public boolean delete(Suporte s){
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        boolean delete = false;
        try{
            stmt = com.prepareStatement("DELETE FROM SUPORTE WHERE codAtendimento = ?");
            stmt.setInt(1, s.getCodAtendimento());
            stmt.executeUpdate();
            delete = true;
            
        }catch (SQLException ex) {
            Logger.getLogger(SuporteDAO.class.getName()).log(Level.SEVERE, null, ex);
            delete = false;
        } finally {
            ConnectionFactory.closeConection(com, stmt);
        }
        return delete;
    }
    public ArrayList<Suporte> buscaSuporte(String pesquisa) {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Suporte> suportes = new ArrayList<>();
        try {
            stmt = com.prepareStatement("SELECT * FROM Suporte WHERE nomeCliente LIKE ? or cpfFuncionario LIKE ?");
            stmt.setString(1, pesquisa+"%");
            stmt.setString(2, pesquisa+"%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Suporte suporte = new Suporte();
                suporte.setTipoReclamacao(rs.getString("tipoReclamacao"));
                suporte.setMotivoReclamacao(rs.getString("motivoReclamacao"));
                suporte.setNomeCliente(rs.getString("nomeCliente"));
                suporte.setCpfFuncionario(rs.getString("cpfFuncionario"));
                suporte.setCodProjeto(rs.getInt("codProjeto"));             
                suportes.add(suporte);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SuporteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConection(com, stmt, rs);
        }
        return suportes;
    }
}
