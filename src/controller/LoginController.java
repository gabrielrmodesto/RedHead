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
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.bean.Usuario;
import model.dao.UsuarioDAO;

/**
 * FXML Controller class
 *
 * @author gabrielmodesto
 */
public class LoginController implements Initializable {

    @FXML private AnchorPane anchorLogin;
    @FXML private JFXTextField txtLogin;
    @FXML private JFXPasswordField txtSenha;
    @FXML private JFXButton btnLogin;
    @FXML private StackPane stackLogin;
    
    Usuario user = new Usuario();
    UsuarioDAO usuario = new UsuarioDAO();
    
    @FXML
    private void clicaLogin(ActionEvent event){
        if(usuario.autenticaLogin(txtLogin.getText(), txtSenha.getText())){
            try{
                Parent btn_login_parent = FXMLLoader.load(getClass().getResource("../view/DashBoard.fxml"));
                Scene btn_login_scene = new Scene(btn_login_parent);
                Stage btn_login_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                btn_login_stage.close();
                btn_login_stage.setMaximized(true);
                btn_login_stage.setScene(btn_login_scene);
                btn_login_stage.show();
            }catch(Exception e){
                    e.printStackTrace();
            }
        }
        else{
            JFXDialogLayout alerta = new JFXDialogLayout();
            alerta.setBody(new Text("Login e Senha Inválidos"));
            alerta.setStyle("-fx-font-size: 16px;");
            JFXDialog caixa = new JFXDialog(stackLogin, alerta, JFXDialog.DialogTransition.CENTER);
            JFXButton sair = new JFXButton("OK");
            sair.setOnAction(e -> {caixa.close();});
            alerta.setActions(sair);			
            caixa.show();
        }	
    }	
    private void validarCampos(){
        RequiredFieldValidator validatorLogin = new RequiredFieldValidator();
        RequiredFieldValidator validatorSenha = new RequiredFieldValidator();
        txtLogin.getValidators().add(validatorLogin);
        txtSenha.getValidators().add(validatorSenha);
        validatorLogin.setMessage("Login em Branco");
        validatorSenha.setMessage("Senha em Branco");
        txtLogin.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                txtLogin.validate();		
            }
        });
        txtSenha.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                txtSenha.validate();		
            }
        });
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub	
        validarCampos();
    }
    
}
