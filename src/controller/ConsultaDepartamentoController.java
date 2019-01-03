/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import model.bean.Departamento;
import model.dao.DepartamentoDAO;
/**
 * FXML Controller class
 *
 * @author gabrielmodesto
 */
public class ConsultaDepartamentoController implements Initializable {

    @FXML private Pane painelConsultaDepartamento;
    @FXML private JFXTextField codDepartamento;
    @FXML private JFXButton pesquisar;
    @FXML private JFXTextField nomeDepartamento;
    @FXML private JFXTreeTableView<Dpt> tabelaResultados;
    DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    @FXML
    public void consultarDepartamento(ActionEvent event) {
        
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JFXTreeTableColumn<Dpt,String> idDptCol = new JFXTreeTableColumn<>("ID");
        idDptCol.setPrefWidth(75);
        idDptCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Dpt, String>, ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Dpt, String> param) {
               return param.getValue().getValue().idDepartamento;
           }
        });
        JFXTreeTableColumn<Dpt,String> nomeDepartamentoCol = new JFXTreeTableColumn<>("Nome Departamento");
        nomeDepartamentoCol.setPrefWidth(260);
        nomeDepartamentoCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Dpt, String>, ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Dpt, String> param) {
               return param.getValue().getValue().nomeDepartamento;
           }
        });
        JFXTreeTableColumn<Dpt,String> numeroFuncionarioCol = new JFXTreeTableColumn<>("Nº Funcionario");
        numeroFuncionarioCol.setPrefWidth(100);
        numeroFuncionarioCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Dpt, String>, ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Dpt, String> param) {
               return param.getValue().getValue().numeroFuncionario;
           }
        });
        JFXTreeTableColumn<Dpt,String> responsavelDepartamentoCol = new JFXTreeTableColumn<>("Responsavel Departamento");
        responsavelDepartamentoCol.setPrefWidth(260);
        responsavelDepartamentoCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Dpt, String>, ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Dpt, String> param) {
               return param.getValue().getValue().responsavelDepartamento;
           }
        });
        JFXTreeTableColumn<Dpt,String> inicioCol = new JFXTreeTableColumn<>("Inicio");
        inicioCol.setPrefWidth(100);
        inicioCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Dpt, String>, ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Dpt, String> param) {
               return param.getValue().getValue().inicio;
           }
        });
        ObservableList<Dpt> dpts = FXCollections.observableArrayList();
        for(Departamento dept : departamentoDAO.read()){
            dpts.add(new Dpt(dept.getCodDepartamento(),dept.getNomeDepartamento(),dept.getNumeroFuncionario(),dept.getResponsavelDepartamento(),dept.getInicioAtividades()));
        }
        final TreeItem<Dpt> root = new RecursiveTreeItem<Dpt>(dpts, RecursiveTreeObject::getChildren);
        tabelaResultados.getColumns().setAll(idDptCol,nomeDepartamentoCol,numeroFuncionarioCol,responsavelDepartamentoCol,inicioCol);
        tabelaResultados.setRoot(root);
        tabelaResultados.setShowRoot(false);
        codDepartamento.textProperty().addListener(new ChangeListener<String>(){
           @Override
           public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               tabelaResultados.setPredicate(new Predicate<TreeItem<Dpt>>(){
                   @Override
                   public boolean test(TreeItem<Dpt> dpt) {
                       Boolean flag = dpt.getValue().idDepartamento.getValue().contains(newValue);
                       return flag;
                   }   
               });
           }
        });
        nomeDepartamento.textProperty().addListener(new ChangeListener<String>(){
           @Override
           public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               tabelaResultados.setPredicate(new Predicate<TreeItem<Dpt>>(){
                   @Override
                   public boolean test(TreeItem<Dpt> dpt) {
                       Boolean flag = dpt.getValue().nomeDepartamento.getValue().contains(newValue);
                       return flag;
                   }   
               });
           }          
        });
    }

    class Dpt extends RecursiveTreeObject<Dpt> {
        StringProperty idDepartamento;
        StringProperty nomeDepartamento;
        StringProperty numeroFuncionario;
        StringProperty responsavelDepartamento;
        StringProperty inicio;
        
        public Dpt(int idDepartamento, String nomeDepartamento, int numeroFuncionario, String responsavelDepartamento, String inicio) {
            this.idDepartamento = new SimpleStringProperty(String.valueOf(idDepartamento));
            this.nomeDepartamento = new SimpleStringProperty(nomeDepartamento);
            this.numeroFuncionario = new SimpleStringProperty(String.valueOf(numeroFuncionario));
            this.responsavelDepartamento = new SimpleStringProperty(responsavelDepartamento);
            this.inicio = new SimpleStringProperty(inicio);
        }
    }
    
}
