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
import javafx.fxml.Initializable;
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
public class AlteraDepartamentoController implements Initializable {

    @FXML private StackPane stackAltera;
    @FXML private Pane painelAlteraDepartamento;
    @FXML private JFXButton btnLimpar;
    @FXML private JFXButton btnEditar;
    @FXML private JFXDatePicker inicio;
    @FXML private JFXTextField nomeDepartamento;
    @FXML private JFXTextField numeroFuncionarios;
    @FXML private JFXTextField responsavel;
    
    Departamento departamento = new Departamento();
    DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    
    @FXML
    public void editaCampos(ActionEvent event) {
        departamento.setNomeDepartamento(nomeDepartamento.getText());
        departamento.setNumeroFuncionario(Integer.parseInt(numeroFuncionarios.getText()));
        departamento.setResponsavelDepartamento(responsavel.getText());
        departamento.setInicioAtividades(inicio.getValue().toString());
        
        if((departamentoDAO.update(departamento)) && !camposVazios()){
            JFXDialogLayout alerta = new JFXDialogLayout();
            alerta.setBody(new Text("Edição realizada com Sucesso"));
            alerta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            JFXDialog caixa = new JFXDialog(stackAltera, alerta, JFXDialog.DialogTransition.CENTER);
            JFXButton sair = new JFXButton("OK");
            sair.setOnAction(e -> {limpaCampos(null);caixa.close();});
            alerta.setActions(sair);
            caixa.show();
        }
        else{
            JFXDialogLayout alerta = new JFXDialogLayout();
            alerta.setBody(new Text("Os campos estão vazios"));
            alerta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            JFXDialog caixa = new JFXDialog(stackAltera, alerta, JFXDialog.DialogTransition.CENTER);
            JFXButton sair = new JFXButton("Não alterar nada");
            sair.setOnAction(e -> {caixa.close();});
            alerta.setActions(sair);
            caixa.show();
        }
    }
    
    @FXML
    public void limpaCampos(ActionEvent event) {
        inicio.setValue(null);
        nomeDepartamento.clear();
        numeroFuncionarios.clear();
        responsavel.clear();
    }
    private boolean camposVazios(){
        boolean erro = false;
        if(nomeDepartamento.getText().equals(""))
                erro  = true;
        if(responsavel.getText().equals(""))
                erro = true;
        if(numeroFuncionarios.getText().equals(""))
                erro = true;
        if(inicio.getValue() == null)
                erro = true;

        return erro;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
                
    }
    
}
