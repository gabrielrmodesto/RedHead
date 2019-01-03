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
import model.bean.Usuario;

/**
 *
 * @author gabrielmodesto
 */
public class UsuarioDAO {

    public boolean create(Usuario u) {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        boolean create = false;
        try {
            stmt = com.prepareStatement("INSERT INTO USUARIO(login, senha) VALUES(?, ?)");
            stmt.setString(1, u.getLogin());
            stmt.setString(2, u.getSenha());

            stmt.executeUpdate();
            create = true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            create = false;
        } finally {
            ConnectionFactory.closeConection(com, stmt);
        }
        return create;
    }

    public ArrayList<Usuario> read() {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            stmt = com.prepareStatement("SELECT * FROM USUARIO");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));

                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConection(com, stmt, rs);
        }
        return usuarios;
    }

    public boolean update(Usuario u) {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        boolean update = false;
        try {
            stmt = com.prepareStatement("UPDATE USUARIO SET senha = ? WHERE login = ?");
            stmt.setString(1, u.getSenha());
            stmt.setString(2, u.getLogin());

            stmt.executeUpdate();
            update = true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            update = false;
        } finally {
            ConnectionFactory.closeConection(com, stmt);
        }
        return update;
    }

    public boolean delete(Usuario u) {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        boolean delete = false;
        try {
            stmt = com.prepareStatement("DELETE FROM USUARIO WHERE login = ? AND senha = ?");
            stmt.setString(1, u.getLogin());
            stmt.setString(2, u.getSenha());

            stmt.executeUpdate();
            delete = true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            delete = false;
        } finally {
            ConnectionFactory.closeConection(com, stmt);
        }
        return delete;
    }

    public boolean autenticaLogin(String login, String senha) {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean chek = false;

        try {
            stmt = com.prepareStatement("SELECT * FROM USUARIO WHERE login = ? AND senha = ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                chek = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConection(com, stmt, rs);
        }
        return chek;
    }

    public ArrayList<Usuario> buscaUsuario(String pesquisa) {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            stmt = com.prepareStatement("SELECT * FROM Usuario WHERE login LIKE ?");
            stmt.setString(1, pesquisa + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));

                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConection(com, stmt, rs);
        }

        return usuarios;
    }

    public int getUsuarioLogado(String login, String senha) {
        Connection com = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;
        try {
            stmt = com.prepareStatement("SELECT idUsuario FROM USUARIO WHERE login = ? AND senha = ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idUsuario"));

                id = usuario.getIdUsuario();
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConection(com, stmt, rs);
        }
        return id;
    }
}
