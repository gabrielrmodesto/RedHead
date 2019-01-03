/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author gabrielmodesto
 */
public class TelaPerfilController implements Initializable {

    @FXML private StackPane stackPerfil;
    @FXML private JFXTextField nomePerfil;
    @FXML private JFXTextField enderecoPerfil;
    @FXML private JFXTextField cidadePerfil;
    @FXML private JFXButton escolherFot;
    @FXML private JFXButton escolheFoto;
    @FXML private JFXComboBox<String> estadoPerfil;
    @FXML private JFXTextField loginPerfil;
    @FXML private JFXPasswordField senhaPefil;
    @FXML private JFXButton btnSalvar;
    @FXML private JFXButton btnLimpar;
    @FXML private ImageView imgPerfil;
    
    FileChooser escolher;
    @FXML
    public void escolherFoto(ActionEvent event) {
        try {
            escolher =  new FileChooser();
            escolher.setTitle("Abrir arquivo");
            File arquivo = escolher.showOpenDialog(stackPerfil.getScene().getWindow());
            if(arquivo != null){
                imgPerfil.setImage(new Image(arquivo.toURI().toURL().toExternalForm()));
            }
            else{
                JFXDialogLayout alerta = new JFXDialogLayout();
                alerta.setHeading(new Text("ERRO"));
                alerta.setBody(new Text("DESCULPE, arquivo não encontrado"));
                alerta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                JFXDialog caixa = new JFXDialog(stackPerfil, alerta, JFXDialog.DialogTransition.CENTER);
                JFXButton sair = new JFXButton("OK");
                sair.setOnAction(e -> {caixa.close();});
                alerta.setActions(sair);
                caixa.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void limpaCampos(ActionEvent event) {
        nomePerfil.clear();
        enderecoPerfil.clear();
        cidadePerfil.clear();
        estadoPerfil.setValue(null);
        loginPerfil.clear();
        senhaPefil.clear();
    }

    @FXML
    public void salvarAlteracao(ActionEvent event) {
            
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        estadoPerfil.getItems().addAll("AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO");

    }    
    
}
