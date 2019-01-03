/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;

import javafx.fxml.Initializable;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import model.bean.Departamento;
import model.dao.DepartamentoDAO;

/**
 * FXML Controller class
 *
 * @author gabrielmodesto
 */
public class CadastroDepartamentoController implements Initializable {

    @FXML private StackPane stackCadastrar;
    @FXML private Pane painelCadastroDepartamento;
    @FXML private JFXButton btnLimparCampos;
    @FXML private JFXButton btnCadastrarDepartamento;
    @FXML private JFXDatePicker inicio;
    @FXML private JFXTextField nomeDepartamento;
    @FXML private JFXTextField responsavelDepartamento;
    @FXML private JFXTextField numeroFuncionarios;
    
    Departamento dpt = new Departamento();
    DepartamentoDAO departamento = new DepartamentoDAO();
    
    @FXML
    public void confirmaCadastro(ActionEvent event){
        dpt.setNomeDepartamento(nomeDepartamento.getText());
        dpt.setNumeroFuncionario(Integer.parseInt(numeroFuncionarios.getText()));
        dpt.setResponsavelDepartamento(responsavelDepartamento.getText());
        dpt.setInicioAtividades(inicio.getValue().toString());
        
        if(departamento.create(dpt) && !camposVazios()){
            JFXDialogLayout alerta = new JFXDialogLayout();
            alerta.setBody(new Text("Cadastro Realizado com Sucesso"));
            alerta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            JFXDialog caixa = new JFXDialog(stackCadastrar, alerta, JFXDialog.DialogTransition.CENTER);
            JFXButton sair = new JFXButton("OK");
            JFXButton voltar = new JFXButton("Cadastrar mais um?");
            sair.setOnAction(e -> {caixa.close();});
            voltar.setOnAction(e -> {limparCampos(null);caixa.close();});
            alerta.setActions(sair,voltar);			
            caixa.show();
        }
        else{
            JFXDialogLayout alerta = new JFXDialogLayout();
            alerta.setBody(new Text("Campos Obrigatórios Vazios"));
            alerta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            JFXDialog caixa = new JFXDialog(stackCadastrar, alerta, JFXDialog.DialogTransition.CENTER);
            JFXButton sair = new JFXButton("OK");
            sair.setOnAction(e -> {caixa.close();});
            alerta.setActions(sair);			
            caixa.show();           
        }
    }	
    @FXML
    public void limparCampos(ActionEvent event){
        nomeDepartamento.clear();
        responsavelDepartamento.clear();
        numeroFuncionarios.clear();
        inicio.setValue(null);
    }
    private boolean camposVazios(){
        boolean erro = false;
        if(nomeDepartamento.getText().equals(""))
                erro  = true;
        if(responsavelDepartamento.getText().equals(""))
                erro = true;
        if(numeroFuncionarios.getText().equals(""))
                erro = true;
        if(inicio.getValue() == null)
                erro = true;

        return erro;
    }
    private void validarCampos(){
        RequiredFieldValidator validator = new RequiredFieldValidator();
        NumberValidator number = new NumberValidator();
        nomeDepartamento.getValidators().add(validator);
        responsavelDepartamento.getValidators().add(validator);
        numeroFuncionarios.getValidators().add(number);
        validator.setMessage("Campo Obrigatório");
        number.setMessage("Apenas Números");
        nomeDepartamento.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                nomeDepartamento.validate();		
            }
        });
        responsavelDepartamento.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                responsavelDepartamento.validate();		
            }
        });
        numeroFuncionarios.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                numeroFuncionarios.validate();
            }
        });
    }
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        // TODO
        validarCampos();
    }    
    
}
