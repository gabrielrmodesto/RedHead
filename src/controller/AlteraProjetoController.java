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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import model.bean.Projeto;
import model.dao.ProjetoDAO;
/**
 * FXML Controller class
 *
 * @author gabrielmodesto
 */
public class AlteraProjetoController implements Initializable {

    @FXML private StackPane stackEdita;
    @FXML private JFXButton btnEditar;
    @FXML private JFXButton btnLimpar;
    @FXML private JFXTextField nomeProjeto;
    @FXML private JFXTextField equipe;
    @FXML private JFXDatePicker prazo;
    @FXML private JFXTextField valor;
    @FXML private JFXTextField cliente;
    @FXML private JFXTextField cpfFuncionario;
    @FXML private JFXTextField cpfCliente;
    @FXML private JFXTextField codDepartamento;
    
    Projeto projeto = new Projeto();
    ProjetoDAO projetoDAO = new ProjetoDAO();

    @FXML
    public void editarCampos(ActionEvent event) {
        projeto.setNomeProjeto(nomeProjeto.getText());
        projeto.setPrazo(prazo.getValue().toString());
        projeto.setEquipe(Integer.parseInt(equipe.getText()));
        projeto.setValor(Float.parseFloat(valor.getText()));
        projeto.setCpfFuncionario(cpfFuncionario.getText());
        projeto.setCodDepartamento(Integer.parseInt(codDepartamento.getText()));
        projeto.setCpfCliente(cpfCliente.getText());
        projeto.setNomeCliente(cliente.getText());
        
        if((projetoDAO.update(projeto)) && !camposVazios()){
            JFXDialogLayout alerta = new JFXDialogLayout();
            alerta.setBody(new Text("Edição Realizada com Sucesso"));
            alerta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            JFXDialog caixa = new JFXDialog(stackEdita, alerta, JFXDialog.DialogTransition.CENTER);
            JFXButton sair = new JFXButton("OK");
            sair.setOnAction(e -> {limpaCampos(null);caixa.close();});
            alerta.setActions(sair);			
            caixa.show();	
        }
        else{
            JFXDialogLayout alerta = new JFXDialogLayout();
            alerta.setBody(new Text("Campos Vazios"));
            alerta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            JFXDialog caixa = new JFXDialog(stackEdita, alerta, JFXDialog.DialogTransition.CENTER);
            JFXButton sair = new JFXButton("Não alterar nada");
            sair.setOnAction(e -> {caixa.close();});
            alerta.setActions(sair);			
            caixa.show();
        }	
    }

    @FXML
    public void limpaCampos(ActionEvent event) {
    	nomeProjeto.clear();
    	equipe.clear();
    	prazo.setValue(null);
    	valor.clear();
    	cliente.clear();
    }
    private boolean camposVazios(){
        boolean erro = false;
        if(nomeProjeto.getText().equals(""))
            erro = true;
        if(equipe.getText().equals(""))
            erro = true;
        if(valor.getText().equals(""))
            erro = true;
        if(cliente.getText().equals(""))
            erro = true;
        if(prazo.getValue() == null)
            erro = true;
        
        return erro;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            // TODO Auto-generated method stub

    }
    
}
