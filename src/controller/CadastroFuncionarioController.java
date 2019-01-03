/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.bean.Funcionario;
import model.dao.FuncionarioDAO;

/**
 * FXML Controller class
 *
 * @author gabrielmodesto
 */
public class CadastroFuncionarioController implements Initializable {

    @FXML private StackPane stackFuncionario;
    @FXML private JFXButton btnCadastrar;
    @FXML private JFXButton btnLimpar;
    @FXML private JFXButton btnFoto;
    @FXML private JFXButton btnEsolheFoto;
    @FXML private ImageView imgPerfil;
    @FXML private Pane painelFuncionario;
    @FXML private JFXTextField campoCargo=null;
    @FXML private JFXTextField nomeFuncionario;
    @FXML private JFXTextField cpf;
    @FXML private JFXDatePicker dataNasc;
    @FXML private JFXTextField salario;
    @FXML private JFXTextField endereco;
    @FXML private JFXTextField cep;
    @FXML private JFXTextField nomeCidade;
    @FXML private JFXComboBox<String> estado;
    @FXML private JFXTextField codigoDepartamento;
    @FXML private JFXTextField registro;

    String caminhoFoto="";
    FileChooser escolher;
    Funcionario funcionario = new Funcionario();
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    
    @FXML
    public void confirmaCadastro(ActionEvent event){
        funcionario.setNomeFuncionario(nomeFuncionario.getText());
        funcionario.setCpf(cpf.getText());
        funcionario.setDataNascimento(dataNasc.getValue().toString());
        funcionario.setRegistro(Integer.parseInt(registro.getText()));
        funcionario.setEndereco(endereco.getText());
        funcionario.setCep(cep.getText());
        funcionario.setCidade(nomeCidade.getText());
        funcionario.setEstado(estado.getSelectionModel().getSelectedItem().toString());
        funcionario.setCargo(campoCargo.getText());
        funcionario.setSalario(Float.parseFloat(salario.getText()));
        funcionario.setCodDepartamento(Integer.parseInt(codigoDepartamento.getText()));
        funcionario.setFotoPerfil(caminhoFoto);
    	if(funcionarioDAO.create(funcionario) && !camposVazios()){
            if(campoCargo.getText().equals("Gerente de Projetos") || campoCargo.getText().equals("Gerente de RH") || campoCargo.getText().equals("Gerente Geral") || campoCargo.getText().equals("Gerente de Desenvolvimento")){
                carregaLoginGerente();
            }   		
            else{		
                carregaConfirma();
            }       
    	}
    	else{
            JFXDialogLayout alerta = new JFXDialogLayout();
            alerta.setBody(new Text("Campos Obrigatórios Vazios"));
            alerta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            JFXDialog caixa = new JFXDialog(stackFuncionario, alerta, JFXDialog.DialogTransition.CENTER);
            JFXButton sair = new JFXButton("OK");
            sair.setOnAction(e -> {caixa.close();});
            alerta.setActions(sair);
            caixa.show();
    	}
    }
    private boolean camposVazios(){
    	boolean erro = false;
    	if(nomeFuncionario.getText().equals(""))
            erro = true;
    	if(cpf.getText().equals(""))
            erro = true;
    	if(dataNasc.getValue() == null)
            erro = true;
    	if(endereco.getText().equals(""))
            erro = true;
    	if(cep.getText().equals(""))
            erro = true;
    	if(nomeCidade.getText().equals(""))
            erro = true;
    	if(estado.getSelectionModel().getSelectedItem() == null)
            erro = true;
    	if(campoCargo.getText().equals(""))
            erro = true;
    	if(salario.getText().equals(""))
            erro = true;
    	if(codigoDepartamento.getText().equals(""))
            erro = true;
    	
    	return erro;
    }
    private void carregaLoginGerente(){  	
        try {
            Pane painelConsulta = FXMLLoader.load(getClass().getResource("../view/EscolherLogin.fxml"));
            painelFuncionario.getChildren().setAll(painelConsulta);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        }				
    }
    private void carregaConfirma(){
    	JFXDialogLayout alerta = new JFXDialogLayout();
        alerta.setBody(new Text("Cadastro Realizado com Sucesso"));
        alerta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        JFXDialog caixa = new JFXDialog(stackFuncionario, alerta, JFXDialog.DialogTransition.CENTER);
        JFXButton sair = new JFXButton("OK");
        JFXButton voltar = new JFXButton("Cadastrar mais um?");
        sair.setOnAction(e -> {caixa.close();});																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																					
        voltar.setOnAction(e -> {limpaDados(null);caixa.close();});
        alerta.setActions(sair,voltar);
        caixa.show();
    }
    @FXML
    public void limpaDados(ActionEvent event){
        nomeFuncionario.clear();
        cpf.clear();
        nomeCidade.clear();
        salario.clear();
        codigoDepartamento.clear();
        cep.clear();
        campoCargo.clear();
        endereco.clear();
        estado.setValue(null);
        dataNasc.setValue(null);
    }
    @FXML
    public void escolherFoto(ActionEvent event) throws MalformedURLException{
        try {
            escolher =  new FileChooser();
            escolher.setTitle("Abrir arquivo");
            File arquivo = escolher.showOpenDialog(stackFuncionario.getScene().getWindow());
            if(arquivo != null){
                caminhoFoto=arquivo.getAbsolutePath();
                imgPerfil.setImage(new Image(arquivo.toURI().toURL().toExternalForm()));
            }
            else{
                JFXDialogLayout alerta = new JFXDialogLayout();
                alerta.setHeading(new Text("ERRO"));
                alerta.setBody(new Text("DESCULPE, arquivo não encontrado"));
                alerta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
                JFXDialog caixa = new JFXDialog(stackFuncionario, alerta, JFXDialog.DialogTransition.CENTER);
                JFXButton sair = new JFXButton("OK");
                sair.setOnAction(e -> {caixa.close();});
                alerta.setActions(sair);
                caixa.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    private void validarCampos(){
    	RequiredFieldValidator validator = new RequiredFieldValidator();
        nomeFuncionario.getValidators().add(validator);
        nomeCidade.getValidators().add(validator);
        endereco.getValidators().add(validator);
        campoCargo.getValidators().add(validator);
        NumberValidator number = new NumberValidator();
        cpf.getValidators().add(number);
        salario.getValidators().add(number);
        cep.getValidators().add(number);
        codigoDepartamento.getValidators().add(number);
        validator.setMessage("Campo Obrigatório");
        number.setMessage("Apenas Números");
        nomeFuncionario.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                nomeFuncionario.validate();	
            }
            });
        campoCargo.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                campoCargo.validate();
            }
            });
        nomeCidade.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                nomeCidade.validate();	
            }
            });
        endereco.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                endereco.validate();			
            }
            });
        cep.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                cep.validate();	
            }
            });
        cpf.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                cpf.validate();			
            }
            });    
        salario.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                salario.validate();			
            }
            });
        codigoDepartamento.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if(!newValue){
                codigoDepartamento.validate();
            }
            });
    }
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        validarCampos();
        estado.getItems().addAll("AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO");
    }
    
}
