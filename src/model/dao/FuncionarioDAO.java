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
import model.bean.Funcionario;
/**
 *
 * @author gabrielmodesto
 */
public class FuncionarioDAO {
    public boolean create(Funcionario f) {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        boolean cadastro = false;
        try {
            stmt = com.prepareStatement("INSERT INTO FUNCIONARIO(nomeFuncionario,"
                    + "cpf, endereco, registro, dataNascimento, salario, cargo, codDepartamento,"
                    + "cep, cidade, estado, fotoPerfil, idFuncionario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

            stmt.setString(1, f.getNomeFuncionario());
            stmt.setString(2, f.getCpf());
            stmt.setString(3, f.getEndereco());
            stmt.setInt(4, f.getRegistro());
            stmt.setString(5, f.getDataNascimento());
            stmt.setFloat(6, f.getSalario());
            stmt.setString(7, f.getCargo());
            stmt.setInt(8, f.getCodDepartamento());
            stmt.setString(9, f.getCep());
            stmt.setString(10, f.getCidade());
            stmt.setString(11, f.getEstado());
            stmt.setString(12, f.getFotoPerfil());
            stmt.setInt(13, f.getIdFuncionario());
            stmt.executeUpdate();
            cadastro = true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            cadastro = false;
        } finally {
            ConnectionFactory.closeConection(com, stmt);
        }
        return cadastro;
    }

    public ArrayList<Funcionario> read() {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        try {
            stmt = com.prepareStatement("SELECT * FROM FUNCIONARIO");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setRegistro(rs.getInt("registro"));
                funcionario.setDataNascimento(rs.getString("dataNascimento"));
                funcionario.setSalario(rs.getFloat("salario"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setCodDepartamento(rs.getInt("codDepartamento"));
                funcionario.setCep(rs.getString("cep"));
                funcionario.setCidade(rs.getString("cidade"));
                funcionario.setEstado(rs.getString("estado"));
                funcionario.setFotoPerfil(rs.getString("fotoPerfil"));
                funcionario.setIdFuncionario(rs.getInt("idFuncionario"));

                funcionarios.add(funcionario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConection(com, stmt, rs);
        }
        return funcionarios;
    }
    public ArrayList<Funcionario> readGerente() {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        try {
            stmt = com.prepareStatement("SELECT * FROM FUNCIONARIO,USUARIO WHERE FUNCIONARIO.idFuncionario = USUARIO.idUsuario");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setRegistro(rs.getInt("registro"));
                funcionario.setDataNascimento(rs.getString("dataNascimento"));
                funcionario.setSalario(rs.getFloat("salario"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setCodDepartamento(rs.getInt("codDepartamento"));
                funcionario.setCep(rs.getString("cep"));
                funcionario.setCidade(rs.getString("cidade"));
                funcionario.setEstado(rs.getString("estado"));                
                funcionario.setFotoPerfil(rs.getString("fotoPerfil"));                
                funcionario.setIdFuncionario(rs.getInt("idFuncionario"));

                funcionarios.add(funcionario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConection(com, stmt, rs);
        }
        return funcionarios;
    }
    public boolean update(Funcionario f){
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        boolean update = false;
        try{
            stmt = com.prepareStatement("UPDATE FUNCIONARIO SET nomeFuncionario = ?,"
                    + "endereco = ?, registro = ?, dataNascimento = ?, salario = ?, cargo = ?, codDepartamento = ?,"
                    + "cep = ?, cidade = ?, estado = ?, fotoPerfil = ?, idFuncionario = ? WHERE cpf = ?");
            
            stmt.setString(1, f.getNomeFuncionario());
            stmt.setString(2, f.getEndereco());
            stmt.setInt(3, f.getRegistro());
            stmt.setString(4, f.getDataNascimento());
            stmt.setFloat(5, f.getSalario());
            stmt.setString(6, f.getCargo());
            stmt.setInt(7, f.getCodDepartamento());
            stmt.setString(8, f.getCep());
            stmt.setString(9, f.getCidade());
            stmt.setString(10, f.getEstado());
            stmt.setString(11, f.getFotoPerfil());
            stmt.setInt(12, f.getIdFuncionario());
            stmt.setString(13, f.getCpf());
            stmt.executeUpdate();
            update = true;
        }
        catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            update = false;
        } finally {
            ConnectionFactory.closeConection(com, stmt);
        }
        return update;
    }
    public boolean delete(Funcionario f){
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        boolean delete = false;
        try{
            stmt = com.prepareStatement("DELETE FROM FUNCIONARIO WHERE cpf = ?");
            stmt.setString(1, f.getCpf());
            
            stmt.executeUpdate();
            delete = true;
        }catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            delete = false;
        } finally {
            ConnectionFactory.closeConection(com, stmt);
        }
        return delete;
    }
    public ArrayList<Funcionario> buscaFuncionario(String pesquisa) {

        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        try {            
            stmt = com.prepareStatement("SELECT * FROM FUNCIONARIO WHERE nomeFuncionario LIKE ? OR cpf LIKE ?");
            stmt.setString(1, pesquisa+"%");
            stmt.setString(2, pesquisa+"%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setRegistro(rs.getInt("registro"));
                funcionario.setDataNascimento(rs.getString("dataNascimento"));
                funcionario.setSalario(rs.getFloat("salario"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setCodDepartamento(rs.getInt("codDepartamento"));
                funcionario.setCep(rs.getString("cep"));
                funcionario.setCidade(rs.getString("cidade"));
                funcionario.setEstado(rs.getString("estado"));
                funcionario.setFotoPerfil(rs.getString("fotoPerfil"));
                funcionario.setIdFuncionario(rs.getInt("idFuncionario"));

                funcionarios.add(funcionario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConection(com, stmt, rs);
        }

        return funcionarios;
    }

    /**
     *
     * @return
     */
    public ArrayList<Funcionario> buscaGerente(int idLogado) {

        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        try {            
            stmt = com.prepareStatement("SELECT * FROM FUNCIONARIO WHERE idFuncionario = ?");
            stmt.setInt(1, idLogado);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setRegistro(rs.getInt("registro"));
                funcionario.setDataNascimento(rs.getString("dataNascimento"));
                funcionario.setSalario(rs.getFloat("salario"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setCodDepartamento(rs.getInt("codDepartamento"));
                funcionario.setCep(rs.getString("cep"));
                funcionario.setCidade(rs.getString("cidade"));
                funcionario.setEstado(rs.getString("estado"));
                funcionario.setFotoPerfil(rs.getString("fotoPerfil"));
                funcionario.setIdFuncionario(rs.getInt("idFuncionario"));

                funcionarios.add(funcionario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConection(com, stmt, rs);
        }

        return funcionarios;
    }
    public int countFuncionario() {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int qtd = 0;

        try {
            stmt = com.prepareStatement("SELECT * FROM FUNCIONARIO");
            rs = stmt.executeQuery();
            while (rs.next()) {
                qtd++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return qtd;
    }
}
