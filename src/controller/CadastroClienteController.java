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
import model.bean.Cliente;
import model.dao.ClienteDAO;
/**
 * FXML Controller class
 *
 * @author gabrielmodesto
 */
public class CadastroClienteController implements Initializable {

    @FXML private StackPane stackClientes;
    @FXML private Pane painelCadastroCliente;
    @FXML private JFXButton btnCadastrar;
    @FXML private JFXButton btnLimpar;
    @FXML private JFXTextField campoNome;
    @FXML private JFXTextField campoCPF;
    @FXML private JFXDatePicker campoData;
    @FXML private JFXTextField campoEndereco;
    @FXML private JFXTextField campoCEP;
    @FXML private JFXTextField campoBairro;
    @FXML private JFXTextField campoCidade;
    @FXML private JFXComboBox<String> campoEstado;
    @FXML private JFXTextField codigoProjeto;
    Cliente cliente = new Cliente();
    ClienteDAO clienteDAO = new ClienteDAO();
    @FXML
    public void confirmaCadastro(ActionEvent event){
        cliente.setNomeCliente(campoNome.getText());
        cliente.setCpf(campoCPF.getText());
        cliente.setDataNascimento(campoData.getValue().toString());
        cliente.setEndereco(campoEndereco.getText());
        cliente.setBairro(campoBairro.getText());
        cliente.setCep(campoCEP.getText());
        cliente.setCidade(campoCidade.getText());
        cliente.setCodProjeto(Integer.parseInt(codigoProjeto.getText()));
        cliente.setEstado(campoEstado.getSelectionModel().getSelectedItem().toString());
        if(clienteDAO.create(cliente)  && !camposVazios()){
            JFXDialogLayout alerta = new JFXDialogLayout();
            alerta.setBody(new Text("Cadastro Realizado com Sucesso"));
            alerta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            JFXDialog caixa = new JFXDialog(stackClientes, alerta, JFXDialog.DialogTransition.CENTER);
            JFXButton sair = new JFXButton("OK");
            JFXButton voltar = new JFXButton("Cadastrar novamente?");
            sair.setOnAction(e -> {limpaDados(null);caixa.close();});
            voltar.setOnAction(e -> {limpaDados(null);caixa.close();});
            alerta.setActions(sair,voltar);			
            caixa.show(); 
        }   
        else{
            JFXDialogLayout alerta = new JFXDialogLayout();
            alerta.setBody(new Text("Campos Obrigatórios Vazios"));
            alerta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            JFXDialog caixa = new JFXDialog(stackClientes, alerta, JFXDialog.DialogTransition.CENTER);
            JFXButton sair = new JFXButton("OK");
            sair.setOnAction(e -> {caixa.close();});
            alerta.setActions(sair);			
            caixa.show();	
        }
    }

    @FXML
    public void limpaDados(ActionEvent event) {
        campoNome.clear();
        campoCPF.clear();
        campoBairro.clear();
        campoCEP.clear();
        campoCidade.clear();
        campoEndereco.clear();
        campoNome.clear();
        campoData.setValue(null);
        campoEstado.setValue(null);
        codigoProjeto.clear();
    }
	
    private boolean camposVazios(){
        boolean erro = false;
        if(campoNome.getText().equals("")){
            erro = true;
            campoNome.setStyle("fx-background-color: red;");
        }
        if(campoCPF.getText().equals(""))
            erro = true;
        if(campoData.getValue() == null)
            erro = true;
        if(campoEndereco.getText().equals(""))
            erro = true;
        if(campoBairro.getText().equals(""))
            erro = true;
        if(campoCEP.getText().equals(""))
            erro = true;
        if(campoCidade.getText().equals(""))
            erro = true;
        if(campoEstado.getSelectionModel().getSelectedItem() == null)
            erro = true;
        if(codigoProjeto.getText().equals(""))
            erro = true;

        return erro;
    }
    private void validarCampos(){
        RequiredFieldValidator validator = new RequiredFieldValidator();
        NumberValidator number = new NumberValidator();
        campoNome.getValidators().add(validator);
        campoEndereco.getValidators().add(validator);
        campoCidade.getValidators().add(validator);
        campoBairro.getValidators().add(validator);
        campoCEP.getValidators().add(number);
        campoCPF.getValidators().add(number);
        codigoProjeto.getValidators().add(number);

        validator.setMessage("Campo obrigatório");
        number.setMessage("Apenas Números");
        campoNome.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                campoNome.validate();		
            }
        });
        campoEndereco.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                campoEndereco.validate();		
            }
        });
        campoCidade.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                campoCidade.validate();		
            }
        });
        campoBairro.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                campoBairro.validate();		
            }
        });
        campoCPF.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                campoCPF.validate();		
            }
        });
        campoCEP.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                campoCEP.validate();		
            }
        });
        codigoProjeto.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                codigoProjeto.validate();		
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        // TODO
        validarCampos();
        campoEstado.getItems().addAll("AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO");
    }
    
}
