/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Cliente;
import model.db.ConnectionFactory;

/**
 *
 * @author gabrielmodesto
 */
public class ClienteDAO {

    public boolean create(Cliente c) {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        boolean cadastro = false;
        try {
            stmt = com.prepareStatement("INSERT INTO CLIENTE (nomeCliente, cpf, endereco, dataNascimento, codProjeto, cep, bairro, cidade, estado) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            stmt.setString(1, c.getNomeCliente());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getEndereco());
            stmt.setString(4, c.getDataNascimento());
            stmt.setInt(5, c.getCodProjeto());
            stmt.setString(6, c.getCep());
            stmt.setString(7, c.getBairro());
            stmt.setString(8, c.getCidade());
            stmt.setString(9, c.getEstado());

            stmt.executeUpdate();
            cadastro = true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            cadastro = false;
        } finally {
            ConnectionFactory.closeConection(com, stmt);
        }
        return cadastro;
    }

    public ArrayList<Cliente> read() {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            stmt = com.prepareStatement("SELECT * FROM CLIENTE");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNomeCliente(rs.getString("nomeCliente"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setDataNascimento(rs.getString("dataNascimento"));
                cliente.setCodProjeto(rs.getInt("codProjeto"));
                cliente.setCep(rs.getString("cep"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));

                clientes.add(cliente);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConection(com, stmt, rs);
        }
        return clientes;
    }

    public boolean update(Cliente c) {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        boolean atualizado = false;
        try {
            stmt = com.prepareStatement("UPDATE CLIENTE SET nomeCliente = ?, cpf = ?, endereco = ?, "
                    + "dataNascimento = ?, codProjeto = ?, cep = ?, bairro = ?, cidade = ?, estado = ? WHERE idCliente = ?");
            stmt.setString(1, c.getNomeCliente());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getEndereco());
            stmt.setString(4, c.getDataNascimento());
            stmt.setInt(5, c.getCodProjeto());
            stmt.setString(6, c.getCep());
            stmt.setString(7, c.getBairro());
            stmt.setString(8, c.getCidade());
            stmt.setString(9, c.getEstado());
            stmt.setInt(10, c.getIdCliente());

            stmt.executeUpdate();
            atualizado = true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            atualizado = false;
        } finally {
            ConnectionFactory.closeConection(com, stmt);
        }
        return atualizado;
    }

    public boolean delete(Cliente c) {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        boolean deletado = false;
        try {
            stmt = com.prepareStatement("DELETE FROM CLIENTE WHERE idCliente = ?");
            stmt.setInt(1, c.getIdCliente());

            stmt.executeUpdate();
            deletado = true;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            deletado = false;
        } finally {
            ConnectionFactory.closeConection(com, stmt);
        }
        return deletado;
    }

    public ArrayList<Cliente> buscaCliente(String pesquisa) {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            stmt = com.prepareStatement("SELECT * FROM CLIENTE WHERE nomeCliente LIKE ? OR cpf LIKE ?");
            stmt.setString(1, pesquisa + "%");
            stmt.setString(2, pesquisa + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNomeCliente(rs.getString("nomeCliente"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setDataNascimento(rs.getString("dataNascimento"));
                cliente.setCodProjeto(rs.getInt("codProjeto"));
                cliente.setCep(rs.getString("cep"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setEstado(rs.getString("estado"));

                clientes.add(cliente);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConection(com, stmt, rs);
        }
        return clientes;
    }

    public int countCliente() {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int qtd = 0;

        try {
            stmt = com.prepareStatement("SELECT * FROM CLIENTE");
            rs = stmt.executeQuery();
            while (rs.next()) {
                qtd++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return qtd;
    }

}
