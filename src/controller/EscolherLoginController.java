/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import model.bean.Usuario;
import model.dao.UsuarioDAO;

/**
 * FXML Controller class
 *
 * @author gabrielmodesto
 */
public class EscolherLoginController implements Initializable {

    @FXML private StackPane painelLogin;
    @FXML private JFXButton btnEnviar;
    @FXML private JFXButton btnLmpar;
    @FXML private JFXPasswordField campoSenha;
    @FXML private JFXPasswordField campoConfirmaSenha;
    @FXML private JFXTextField campoLogin;
    Usuario user = new Usuario();
    UsuarioDAO usuario = new UsuarioDAO();
    
    @FXML
    public void confirmaLogin(ActionEvent event){
        user.setLogin(campoLogin.getText());
        user.setSenha(campoSenha.getText());
        if(usuario.create(user)){
            JFXDialogLayout alerta = new JFXDialogLayout();
            alerta.setBody(new Text("Cadastro Realizado com Sucesso"));
            alerta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            JFXDialog caixa = new JFXDialog(painelLogin, alerta, JFXDialog.DialogTransition.CENTER);
            JFXButton sair = new JFXButton("OK");
            sair.setOnAction(e -> {limpaDados(null);caixa.close();voltaCadastro();});
            alerta.setActions(sair);
            caixa.show();
            }
        else{
            JFXDialogLayout alerta = new JFXDialogLayout();
            alerta.setBody(new Text("Cadastro Não Realizado"));
            alerta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            JFXDialog caixa = new JFXDialog(painelLogin, alerta, JFXDialog.DialogTransition.CENTER);
            JFXButton sair = new JFXButton("Tente Novamente");
            sair.setOnAction(e -> {caixa.close();});
            alerta.setActions(sair);
            caixa.show();
        }
    }
    
    private void voltaCadastro() {
        try {
            Pane painelCadastro = FXMLLoader.load(getClass().getResource("../view/CadastroFuncionario.fxml"));
            painelLogin.getChildren().setAll(painelCadastro);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    @FXML
    public void limpaDados(ActionEvent event) {
    	campoConfirmaSenha.clear();
    	campoSenha.clear();
    	campoLogin.clear();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
}
