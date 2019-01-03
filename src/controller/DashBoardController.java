/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author gabrielmodesto
 */
public class DashBoardController implements Initializable {

    @FXML private JFXButton imageHome;
    @FXML private AnchorPane anchorFixo;
    @FXML private JFXButton btnDepartamento;
    @FXML private JFXButton btnFuncionarios;
    @FXML private JFXButton btnClientes;
    @FXML private JFXButton btnProjetos;
    @FXML private JFXButton btnSuporte;
    @FXML private JFXButton botaoSair;
    @FXML private StackPane stackDash;
    @FXML private JFXButton botaoImagemUser;
    @FXML private ImageView imagemUser;

    @FXML
    public void telaDepartamentos(ActionEvent event) throws IOException {
        carregaTelaDepartamento();
    }

    @FXML
    public void telaCientes(ActionEvent event) throws IOException {
        carregaTelaClientes();
    }

    @FXML
    public void telaFuncionarios(ActionEvent event) throws IOException {
        carregaTelaFuncionarios();
    }

    @FXML
    public void telaProjetos(ActionEvent event) throws IOException {
        carregaTelaProjetos();
    }

    @FXML
    public void telaSuporte(ActionEvent event) throws IOException {
        carregaTelaSuporte();
    }

    @FXML
    public void telaDash(ActionEvent event) throws IOException {
        carregaDash();
    }
    @FXML
    public void carregaTelaPerfil(ActionEvent event) throws IOException {
        telaPerfil();
    }
    @FXML
    public void sairPrograma(ActionEvent event) {
        JFXDialogLayout alerta = new JFXDialogLayout();
        alerta.setBody(new Text("Deseja realmente sair?"));
        alerta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        JFXDialog caixa = new JFXDialog(stackDash, alerta, JFXDialog.DialogTransition.CENTER);
        JFXButton sair = new JFXButton("SIM");
        JFXButton voltar = new JFXButton("NÃO");
        sair.setOnAction(e -> {caixa.close();Platform.exit();});
        voltar.setOnAction(e -> {caixa.close();});
        alerta.setActions(sair,voltar);			
        caixa.show();	
    }
    private void carregaTelaDepartamento() throws IOException {
        try {
            Pane painelDepartamento = FXMLLoader.load(getClass().getResource("../view/TelaPrincipalDepartamento.fxml"));
            anchorFixo.getChildren().setAll(painelDepartamento);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    private void carregaTelaClientes() throws IOException {
        try {
            Pane painelClientes = FXMLLoader.load(getClass().getResource("../view/TelaPrincipalCliente.fxml"));
            anchorFixo.getChildren().setAll(painelClientes);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    private void carregaTelaFuncionarios() throws IOException {
        try {
            Pane painelFuncionarios = FXMLLoader.load(getClass().getResource("../view/TelaPrincipalFuncionario.fxml"));
            anchorFixo.getChildren().setAll(painelFuncionarios);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    private void carregaTelaProjetos() throws IOException {
        try {
            Pane painelProjetos = FXMLLoader.load(getClass().getResource("../view/TelaPrincipalProjeto.fxml"));
            anchorFixo.getChildren().setAll(painelProjetos);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    private void carregaTelaSuporte() throws IOException {
        try {
            Pane painelSuporte = FXMLLoader.load(getClass().getResource("../view/TelaPrincipalSuporte.fxml"));
            anchorFixo.getChildren().setAll(painelSuporte);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void carregaDash() throws IOException {
        try {
            Pane painelPrincipal = FXMLLoader.load(getClass().getResource("../view/PaneGrafico.fxml"));
            anchorFixo.getChildren().setAll(painelPrincipal);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        // TODO
        try {
            carregaDash();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void telaPerfil() throws IOException{
        try {
            Pane painelPerfil = FXMLLoader.load(getClass().getResource("../view/perfilGerente.fxml"));
            anchorFixo.getChildren().setAll(painelPerfil);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    
}
