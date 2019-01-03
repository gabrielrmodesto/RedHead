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
public class TelaPrincipalFuncionarioController implements Initializable {

    @FXML
    private Pane painelFuncionarios;
    @FXML
    private JFXButton btnAltera;
    @FXML
    private JFXButton btnCadastro;
    @FXML
    private JFXButton btnConsulta;

    @FXML
    public void telaAlteraFuncionario(ActionEvent event) {
        carregaTelaAlteraFuncionario();
    }

    @FXML
    public void telaCadastroFuncionario(ActionEvent event) throws IOException {
        carregaCadastroFuncionario();
    }

    @FXML
    public void telaConsultaFuncionario(ActionEvent event) {
        carregaTelaConsultaFuncionario();
    }

    private void carregaCadastroFuncionario() throws IOException {
        try {
            Pane painelCadastrar = FXMLLoader.load(getClass().getResource("../view/CadastroFuncionario.fxml"));
            painelFuncionarios.getChildren().setAll(painelCadastrar);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }
    }

    private void carregaTelaAlteraFuncionario() {
        try {
            Pane painelAlterar = FXMLLoader.load(getClass().getResource("../view/AlteraFuncionario.fxml"));
            painelFuncionarios.getChildren().setAll(painelAlterar);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    private void carregaTelaConsultaFuncionario() {
        try {
            Pane painelConsultar = FXMLLoader.load(getClass().getResource("../view/ConsultarFuncionario.fxml"));
            painelFuncionarios.getChildren().setAll(painelConsultar);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
}
