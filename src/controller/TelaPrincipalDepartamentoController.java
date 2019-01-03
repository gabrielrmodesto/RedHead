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
public class TelaPrincipalDepartamentoController implements Initializable {

    @FXML
    private Pane painelDepartamento;
    @FXML
    private JFXButton btnCadastarDepartamento;
    @FXML
    private JFXButton btnConsultarDepartamento;
    @FXML
    private JFXButton btnAlterarDepartamento;

    @FXML
    public void telaAlterarDepartamento(ActionEvent event) throws IOException {
        carregaTelaAltera();
    }

    @FXML
    public void telaCadastrarDepartamento(ActionEvent event) throws IOException {
        carregaTelaCadastro();
    }

    @FXML
    public void telaConsultarDepartamento(ActionEvent event) throws IOException {
        carregaTelaConsulta();
    }

    private void carregaTelaCadastro() throws IOException {
        Pane painelCadastro = FXMLLoader.load(getClass().getResource("../view/CadastroDepartamento.fxml"));
        painelDepartamento.getChildren().setAll(painelCadastro);
    }

    private void carregaTelaConsulta() throws IOException {
        try {
            Pane painelConsulta = FXMLLoader.load(getClass().getResource("../view/ConsultaDepartamento.fxml"));
            painelDepartamento.getChildren().setAll(painelConsulta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    private void carregaTelaAltera() throws IOException {
        Pane painelAltera = FXMLLoader.load(getClass().getResource("../view/AlteraDepartamento.fxml"));
        painelDepartamento.getChildren().setAll(painelAltera);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
}
