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
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import model.bean.Projeto;
import model.dao.ProjetoDAO;
/**
 * FXML Controller class
 *
 * @author gabrielmodesto
 */
public class CadastroProjetoController implements Initializable {

    @FXML private StackPane stackCadastro;
    @FXML private Pane painelCadastro;
    @FXML private JFXButton btnCadastrar;
    @FXML private JFXButton btnLimpar;
    @FXML private JFXTextField nomeProjeto;
    @FXML private JFXTextField equipe;
    @FXML private JFXDatePicker prazoEntrega;
    @FXML private JFXTextField valor;
    @FXML private JFXTextField cpfFuncionario;      
    @FXML private JFXTextField cpfCliente;
    @FXML private JFXTextField codDepartamento;
    @FXML private JFXTextField nomeCliente;

    Projeto projeto = new Projeto();
    ProjetoDAO projetoDAO = new ProjetoDAO();
    @FXML
    public void confirmaCadastro(ActionEvent event) {
        projeto.setNomeProjeto(nomeProjeto.getText());
        projeto.setPrazo(prazoEntrega.getValue().toString());
        projeto.setEquipe(Integer.parseInt(equipe.getText()));
        projeto.setValor(Float.parseFloat(valor.getText()));
        projeto.setCpfFuncionario(cpfFuncionario.getText());
        projeto.setCodDepartamento(Integer.parseInt(codDepartamento.getText()));
        projeto.setCpfCliente(cpfCliente.getText());
        projeto.setNomeCliente(nomeCliente.getText());
    	if(projetoDAO.create(projeto) && !camposVazios()){
            JFXDialogLayout alerta = new JFXDialogLayout();
            alerta.setBody(new Text("Cadastro Realizado com Sucesso"));
            alerta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            JFXDialog caixa = new JFXDialog(stackCadastro, alerta, JFXDialog.DialogTransition.CENTER);
            JFXButton sair = new JFXButton("OK");
            JFXButton voltar = new JFXButton("Cadastrar mais um?");
            sair.setOnAction(e -> {limpaCampos(null);caixa.close();});
            voltar.setOnAction(e -> {limpaCampos(null);caixa.close();});
            alerta.setActions(sair,voltar);
            caixa.show();            
    	}
    	else{
            JFXDialogLayout alerta = new JFXDialogLayout();
            alerta.setBody(new Text("Campos Obrigatórios Vazios"));
            alerta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            JFXDialog caixa = new JFXDialog(stackCadastro, alerta, JFXDialog.DialogTransition.CENTER);
            JFXButton sair = new JFXButton("OK");
            sair.setOnAction(e -> {caixa.close();});
            alerta.setActions(sair);
            caixa.show();
    	}
    }

    @FXML
    public void limpaCampos(ActionEvent event) {
    	valor.clear();
    	nomeCliente.clear();
    	equipe.clear();
    	nomeProjeto.clear();
    	prazoEntrega.setValue(null);
        cpfCliente.clear();
        cpfFuncionario.clear();
        codDepartamento.clear();
    }
    private boolean camposVazios(){
    	boolean erro = false;
    	if(nomeCliente.getText().equals(""))
            erro = true;
    	if(equipe.getText().equals(""))
            erro = true;
    	if(nomeProjeto.getText().equals(""))
            erro = true;
    	if(prazoEntrega.getValue() == null)
            erro = true;
        if(cpfFuncionario.getText().equals(""))
            erro = true;
        if(cpfCliente.getText().equals(""))
            erro = true;
        if(codDepartamento.getText().equals(""))
            erro = true;
        if(valor.getText().equals(""))
            erro = true;
        
    	return erro;
    }
    private void validarCampos(){
    	RequiredFieldValidator validator = new RequiredFieldValidator();
        NumberValidator number = new NumberValidator();
    	nomeCliente.getValidators().add(validator);
    	equipe.getValidators().add(validator);
    	nomeProjeto.getValidators().add(validator);
        cpfCliente.getValidators().add(number);
        cpfFuncionario.getValidators().add(number);
        valor.getValidators().add(number);
    	validator.setMessage("Campo Obrigatório");
        number.setMessage("Apenas Números");
    	nomeCliente.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                nomeCliente.validate();	
            }
            });
    	equipe.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                equipe.validate();	
            }
            });
    	nomeProjeto.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                nomeProjeto.validate();	
            }
            });
        cpfFuncionario.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                cpfFuncionario.validate();	
            }
            });
        cpfCliente.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                cpfCliente.validate();	
            }
            });
        valor.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                valor.validate();	
            }
            });
    }
	@Override
    public void initialize(URL url, ResourceBundle resources) {
        //TODO
        validarCampos();
    }
    
}
