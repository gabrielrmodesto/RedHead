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
import model.bean.Departamento;
/**
 *
 * @author gabrielmodesto
 */
public class DepartamentoDAO {
    public boolean create(Departamento d) {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        boolean cadastro = false;
        try {
            stmt = com.prepareStatement("INSERT INTO DEPARTAMENTO (nomeDepartamento, "
                    + "numeroFuncionario, responsavelDepartamento, inicioAtividades) VALUES (?, ?, ?, ?)");
            stmt.setString(1, d.getNomeDepartamento());
            stmt.setInt(2, d.getNumeroFuncionario());
            stmt.setString(3, d.getResponsavelDepartamento());
            stmt.setString(4, d.getInicioAtividades());
            
            stmt.executeUpdate();
            cadastro = true;
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            cadastro = false;
        } finally {
            ConnectionFactory.closeConection(com, stmt);
        }
        return cadastro;
    }

    public ArrayList<Departamento> read() {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Departamento> departamentos = new ArrayList<>();

        try {
            stmt = com.prepareStatement("SELECT * FROM DEPARTAMENTO");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Departamento departamento = new Departamento();
                departamento.setCodDepartamento(rs.getInt("codDepartamento"));
                departamento.setNomeDepartamento(rs.getString("nomeDepartamento"));
                departamento.setNumeroFuncionario(rs.getInt("numeroFuncionario"));
                departamento.setResponsavelDepartamento(rs.getString("responsavelDepartamento"));
                departamento.setInicioAtividades(rs.getString("inicioAtividades"));
                
                departamentos.add(departamento);

            }

        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConection(com, stmt, rs);
        }
        return departamentos;
    }

    public boolean update(Departamento d) {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        boolean atualizado = false;
        
        try {
            stmt = com.prepareStatement("UPDATE DEPARTAMENTO SET nomeDepartamento = ?, "
                    + "numeroFuncionario = ?, responsavelDepartamento = ?, inicioAtividades = ? WHERE codDepartamento = ?");
            stmt.setString(1, d.getNomeDepartamento());
            stmt.setInt(2, d.getNumeroFuncionario());
            stmt.setString(3, d.getResponsavelDepartamento());
            stmt.setString(4, d.getInicioAtividades());

            stmt.executeUpdate();
            atualizado = true;
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            atualizado = false;
        } finally {
            ConnectionFactory.closeConection(com, stmt);
        }
        return atualizado;
    }

    public boolean delete(Departamento d) {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        boolean delete = false;
        
        try {
            stmt = com.prepareStatement("DELETE FROM DEPARTAMENTO WHERE codDepartamento = ?");
            stmt.setInt(1, d.getCodDepartamento());

            stmt.executeUpdate();
            delete = true;
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            delete = false;
        } finally {
            ConnectionFactory.closeConection(com, stmt);
        }
        return delete;
    }
    public ArrayList<Departamento> buscaDepartamento(String pesquisa) {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Departamento> departamentos = new ArrayList<>();

        try {
            stmt = com.prepareStatement("SELECT * FROM Departamento WHERE codDepartamento LIKE ? or nomeDepartamento LIKE ?");
            stmt.setString(1, pesquisa+"%");
            stmt.setString(2, pesquisa+"%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Departamento departamento = new Departamento();
                departamento.setCodDepartamento(rs.getInt("codDepartamento"));
                departamento.setNomeDepartamento(rs.getString("nomeDepartamento"));
                departamento.setNumeroFuncionario(rs.getInt("numeroFuncionario"));
                departamento.setResponsavelDepartamento(rs.getString("responsavelDepartamento"));
                departamento.setInicioAtividades(rs.getString("inicioAtividades"));               
                departamentos.add(departamento);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConection(com, stmt, rs);
        }
        return departamentos;
    }
}
