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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author gabrielmodesto
 */
public class TelaPrincipalClienteController implements Initializable {

     @FXML
    private Pane painelClientes;
    @FXML
    private JFXButton btnAlterarClientes;
    @FXML
    private JFXButton btnCadastrarClientes;
    @FXML
    private JFXButton btnConsultarClientes;

    @FXML
    public void telaCadastrarCliente(ActionEvent event) throws IOException {
        carregaTelaCadastrarCliente();
    }

    @FXML
    public void telaConsultarCliente(ActionEvent event) throws IOException {
        try {
            carregaTelaConsultarCliente();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    public void telaAlterarCliente(ActionEvent event) throws IOException {
        carregaTelaAlterarCliente();
    }

    private void carregaTelaCadastrarCliente() throws IOException {
        Pane painelCadastrar = FXMLLoader.load(getClass().getResource("../view/CadastroClientes.fxml"));
        painelClientes.getChildren().setAll(painelCadastrar);

    }

    private void carregaTelaConsultarCliente() throws IOException {
        Pane painelConsultar = FXMLLoader.load(getClass().getResource("../view/ConsultarClientes.fxml"));
        painelClientes.getChildren().setAll(painelConsultar);

    }

    private void carregaTelaAlterarCliente() throws IOException {
        try {
            Pane painelAlterar = FXMLLoader.load(getClass().getResource("../view/AlterarCliente.fxml"));
            painelClientes.getChildren().setAll(painelAlterar);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
}
