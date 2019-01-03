/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import model.dao.FuncionarioDAO;
import model.dao.UsuarioDAO;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.bean.Funcionario;
import model.bean.Usuario;

/**
 * FXML Controller class
 *
 * @author gabrielmodesto
 */
public class PerfilGerenteController implements Initializable {

    @FXML private StackPane stackGerente;
    @FXML private Pane paneGerente;
    @FXML private JFXButton botaoFoto;
    @FXML private ImageView fotoPerfil;
    @FXML private JFXTextField nome;
    @FXML private JFXTextField endereco;
    @FXML private JFXTextField cidade;
    @FXML private JFXTextField estado;
    @FXML private JFXTextField login;
    @FXML private JFXPasswordField senha;
    @FXML private JFXButton mostraSenha;
    @FXML private JFXButton btnEditar;
  
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    String caminhoFoto="";
    File arquivo;
    @FXML
    public void editarPerfil(ActionEvent event) {

    }

    @FXML
    public void mostrarSenha(ActionEvent event) {
        senha.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        for(Funcionario funcionario : funcionarioDAO.readGerente()){
            for(Usuario usuario : usuarioDAO.read()){
                if(funcionario.getIdFuncionario() == usuario.getIdUsuario()){
                    nome.setText(funcionario.getNomeFuncionario());
                    endereco.setText(funcionario.getEndereco());
                    cidade.setText(funcionario.getCidade());
                    estado.setText(funcionario.getEstado());
                    caminhoFoto=funcionario.getFotoPerfil();
                    login.setText(usuario.getLogin());
                    senha.setText(usuario.getSenha());
                   //System.out.println(funcionario.getNomeFuncionario() + " " + usuario.getIdUsuario() + " " + funcionario.getIdFuncionario());
                }
                
            }        
        }
        arquivo = new File(caminhoFoto);
        try {
            fotoPerfil.setImage(new Image(arquivo.toURI().toURL().toString()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(PerfilGerenteController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }  
    
}
