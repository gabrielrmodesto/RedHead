/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;


/**
 * FXML Controller class
 *
 * @author gabrielmodesto
 */
public class TelaPrincipalSuporteController implements Initializable {

   @FXML
    private Pane painelSuporte;
    @FXML
    private JFXButton btnRegistrarSuporte;
    @FXML
    private JFXButton btnEstaticaSuporte;
    @FXML
    private JFXButton btnHistoricoSuporte;

    @FXML
    public void telaEstaticaSuporte(ActionEvent event) {
        carregaTelaEstaticaSuporte();
    }

    @FXML
    public void telaHistoricoSuporte(ActionEvent event) {
        carregaTelaHistoricoSuporte();
    }

    @FXML
    public void telaRegistrarSuporte(ActionEvent event) throws IOException {
        carregaTelaRegistrarSuporte();
    }

    private void carregaTelaEstaticaSuporte() {
        // TODO Auto-generated method stub

    }

    private void carregaTelaHistoricoSuporte() {
        try {
            Pane painelRegistrar = FXMLLoader.load(getClass().getResource("HistoricoSuporte.fxml"));
            painelSuporte.getChildren().setAll(painelRegistrar);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }

    }

    private void carregaTelaRegistrarSuporte() throws IOException {
        try {
            Pane painelRegistrar = FXMLLoader.load(getClass().getResource("RegistroSuporte.fxml"));
            painelSuporte.getChildren().setAll(painelRegistrar);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
