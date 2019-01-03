/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import model.dao.SuporteDAO;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import model.bean.Suporte;

/**
 * FXML Controller class
 *
 * @author gabrielmodesto
 */
public class RegistroSuporteController implements Initializable {

    @FXML private StackPane stackRegistro;
    @FXML private Pane painelRegistrar;
    @FXML private TextArea txtMotivo; 
    @FXML private JFXButton btnRegistrar;
    @FXML private JFXButton btnLimpar;
    @FXML private JFXTextField nomeCliente;
    @FXML private JFXTextField cpfCNPJ;
    @FXML private JFXTextField codigo;
    @FXML private JFXComboBox<String> tipo;
    @FXML private JFXTextField atendente;
    Suporte suporte = new Suporte();
    SuporteDAO suporteDAO = new SuporteDAO();
    
    @FXML
    public void limpaCampos(ActionEvent event) {
        atendente.clear();
        codigo.clear();
        nomeCliente.clear();
        cpfCNPJ.clear();
        txtMotivo.clear();
        tipo.setValue(null);
    }
    @FXML
    public void registrarSuporte(ActionEvent event) {
        suporte.setNomeCliente(nomeCliente.getText());
        suporte.setCpfFuncionario(atendente.getText());
        suporte.setTipoReclamacao(tipo.getSelectionModel().getSelectedItem().toString());
        suporte.setMotivoReclamacao(txtMotivo.getText());
        suporte.setCodProjeto(Integer.parseInt(codigo.getText()));
        suporte.setCpfCNPJ(cpfCNPJ.getText());
        if(suporteDAO.create(suporte)){
            JFXDialogLayout alerta = new JFXDialogLayout();
            alerta.setBody(new Text("Suporte Registrado com Sucesso"));
            alerta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            JFXDialog caixa = new JFXDialog(stackRegistro, alerta, JFXDialog.DialogTransition.CENTER);
            JFXButton sair = new JFXButton("OK");
            JFXButton voltar = new JFXButton("Registrar mais um");
            sair.setOnAction(e -> {caixa.close();});
            voltar.setOnAction(e -> {limpaCampos(null);caixa.close();});
            alerta.setActions(sair,voltar);
            caixa.show();
        }
        else{
            JFXDialogLayout alerta = new JFXDialogLayout();
            alerta.setBody(new Text("Suporte Não registrado"));
            alerta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            JFXDialog caixa = new JFXDialog(stackRegistro, alerta, JFXDialog.DialogTransition.CENTER);
            JFXButton sair = new JFXButton("Tentar Novamente");
            sair.setOnAction(e -> {caixa.close();});
            alerta.setActions(sair);
            caixa.show();
        }
    }
    private boolean camposVazios(){
        boolean erro = false;
        if(nomeCliente.getText().equals(""))
            erro = true;
        if(atendente.getText().equals(""))
            erro = true;
        if(tipo.getSelectionModel().getSelectedItem() == null)
            erro = true;
        if(codigo.getText().equals(""))
            erro = true;
        if(txtMotivo.getText().equals(""))
            erro = true;
        if(cpfCNPJ.getText().equals(""))
            erro = true;
        
        return erro;
    }
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        //TODO
        tipo.getItems().addAll("Cadastro","Exclusão","Atualização","Consulta","Operações Básicas","Operações Específicas");
    }
    
}
