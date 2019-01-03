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
public class TelaPrincipalProjetoController implements Initializable {

    @FXML
    private Pane painelProjetos;
    @FXML
    private JFXButton btnCadastroProjetos;
    @FXML
    private JFXButton btnAlterarProjeto;
    @FXML
    private JFXButton btnConsultarProjeto;

    @FXML
    public void telaAlteraProjeto(ActionEvent event) {
        carregaTelaAlteraProjeto();
    }

    @FXML
    public void telaCadastrarProjeto(ActionEvent event) throws IOException {
        carregaTelaCadastrarProjeto();
    }

    @FXML
    public void telaConsultarProjeto(ActionEvent event) {
        carregaTelaConsultarProjeto();
    }

    private void carregaTelaAlteraProjeto() {
        try {
            Pane painelAltera = FXMLLoader.load(getClass().getResource("../view/AlteraProjeto.fxml"));
            painelProjetos.getChildren().setAll(painelAltera);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    private void carregaTelaCadastrarProjeto() throws IOException {
        try {
            Pane painelCadastro = FXMLLoader.load(getClass().getResource("../view/CadastroProjetos.fxml"));
            painelProjetos.getChildren().setAll(painelCadastro);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    private void carregaTelaConsultarProjeto() {
        try {
            Pane painelConsulta = FXMLLoader.load(getClass().getResource("../view/ConsultaProjetos.fxml"));
            painelProjetos.getChildren().setAll(painelConsulta);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
}
