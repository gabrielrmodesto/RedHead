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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import model.bean.Projeto;
import model.dao.ProjetoDAO;

/**
 * FXML Controller class
 *
 * @author gabrielmodesto
 */
public class ConsultaProjetoController implements Initializable {

    @FXML private JFXTextField codProjeto;
    @FXML private JFXTextField nomeProjeto;
    @FXML private JFXTextField cpfClienteRequerente;
    @FXML private JFXTextField nomeRequerente; 
    @FXML private AnchorPane anchorResultados;
    @FXML private JFXTreeTableView<Project> tabelaResultadosProjetos;
    @FXML private JFXButton pesquisar;
    @FXML private StackPane stackResultadoProjeto;
    
    ProjetoDAO projetoDAO = new ProjetoDAO();
    @FXML
    public void consultarProjetos(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        JFXTreeTableColumn<Project, String> codProjetoCol = new JFXTreeTableColumn<>("Cod Projeto");
        codProjetoCol.setPrefWidth(70);
        codProjetoCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Project, String>, ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Project, String> param) {
               return param.getValue().getValue().codigoProjeto;
            } 
        });
        JFXTreeTableColumn<Project, String> nomeProjetoCol = new JFXTreeTableColumn<>("Nome Projeto");
        nomeProjetoCol.setPrefWidth(250);
        nomeProjetoCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Project, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Project, String> param) {
                return param.getValue().getValue().nomeProjeto;
            }
        });
        JFXTreeTableColumn<Project, String> prazoCol = new JFXTreeTableColumn<>("Prazo de Entrega");
        prazoCol.setPrefWidth(150);
        prazoCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Project, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Project, String> param) {
                return param.getValue().getValue().prazo;
            }
        });
        JFXTreeTableColumn<Project, String> valorCol = new JFXTreeTableColumn<>("Valor Projeto");
        valorCol.setPrefWidth(150);
        valorCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Project, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Project, String> param) {
                return param.getValue().getValue().valor;
            }
        });
        JFXTreeTableColumn<Project, String> nomeClienteCol = new JFXTreeTableColumn<>("Nome Cliente");
        nomeClienteCol.setPrefWidth(150);
        nomeClienteCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Project, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Project, String> param) {
                return param.getValue().getValue().nomeCliente;
            }
        });
        JFXTreeTableColumn<Project, String> cpfClienteCol = new JFXTreeTableColumn<>("CPF Cliente");
        cpfClienteCol.setPrefWidth(150);
        cpfClienteCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Project, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Project, String> param) {
                return param.getValue().getValue().cpfCliente;
            }
        });
        
        ObservableList<Project> projects = FXCollections.observableArrayList();
        for(Projeto projeto : projetoDAO.read()){
            projects.add(new Project(projeto.getCodProjeto(),projeto.getNomeProjeto(),projeto.getPrazo(),projeto.getValor(),projeto.getNomeCliente(),projeto.getCpfCliente()));
        }
        final TreeItem<Project> root = new RecursiveTreeItem<Project>(projects, RecursiveTreeObject::getChildren);
        tabelaResultadosProjetos.getColumns().setAll(codProjetoCol,nomeProjetoCol,prazoCol,valorCol,nomeClienteCol);
        tabelaResultadosProjetos.setRoot(root);
        tabelaResultadosProjetos.setShowRoot(false);
        codProjeto.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tabelaResultadosProjetos.setPredicate(new Predicate<TreeItem<Project>>(){
                    @Override
                    public boolean test(TreeItem<Project> project) {
                        Boolean flag = project.getValue().codigoProjeto.getValue().contains(newValue);
                        return flag;
                    }                   
                });
            }           
        });
        nomeProjeto.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tabelaResultadosProjetos.setPredicate(new Predicate<TreeItem<Project>>(){
                    @Override
                    public boolean test(TreeItem<Project> project) {
                        Boolean flag = project.getValue().nomeProjeto.getValue().contains(newValue);
                        return flag;
                    }                   
                });
            }           
        });
        cpfClienteRequerente.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tabelaResultadosProjetos.setPredicate(new Predicate<TreeItem<Project>>(){
                    @Override
                    public boolean test(TreeItem<Project> project) {
                        Boolean flag = project.getValue().cpfCliente.getValue().contains(newValue);
                        return flag;
                    }                   
                });
            }           
        });  
        nomeRequerente.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tabelaResultadosProjetos.setPredicate(new Predicate<TreeItem<Project>>(){
                    @Override
                    public boolean test(TreeItem<Project> project) {
                        Boolean flag = project.getValue().nomeCliente.getValue().contains(newValue);
                        return flag;
                    }                   
                });
            }           
        });     
    }

    class Project extends RecursiveTreeObject<Project> {
        StringProperty codigoProjeto;
        StringProperty nomeProjeto;
        StringProperty prazo;
        StringProperty valor;
        StringProperty nomeCliente;
        StringProperty cpfCliente;
        
        public Project(int codigoProjeto, String nomeProjeto, String prazo, float valor, String nomeCliente, String cpfCliente) {
            this.codigoProjeto = new SimpleStringProperty(String.valueOf(codigoProjeto));
            this.nomeProjeto = new SimpleStringProperty(nomeProjeto);
            this.prazo = new SimpleStringProperty(prazo);
            this.valor = new SimpleStringProperty(String.valueOf(valor));
            this.nomeCliente = new SimpleStringProperty(nomeCliente);
            this.cpfCliente = new SimpleStringProperty(cpfCliente);
        }
    } 
    
}
