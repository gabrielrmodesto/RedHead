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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import model.bean.Funcionario;
import model.dao.FuncionarioDAO;

/**
 * FXML Controller class
 *
 * @author gabrielmodesto
 */
public class ConsultaFuncionarioController implements Initializable {

    @FXML private JFXTextField cpfFuncionario;
    @FXML private JFXTextField nomeFuncionario;
    @FXML private JFXTreeTableView<Func> tabelaResultados;
    @FXML private JFXButton pesquisar;

    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        JFXTreeTableColumn<Func,String> nomeFuncionarioCol = new JFXTreeTableColumn<>("Nome Funcionario");
        nomeFuncionarioCol.setPrefWidth(200);
        nomeFuncionarioCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Func, String> param) -> param.getValue().getValue().nomeFuncionario);
        JFXTreeTableColumn<Func,String> cpfFuncionarioCol = new JFXTreeTableColumn<>("CPF");
        cpfFuncionarioCol.setPrefWidth(100);
        cpfFuncionarioCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Func, String> param) -> param.getValue().getValue().cpfFuncionario);
        JFXTreeTableColumn<Func,String> dataNascCol = new JFXTreeTableColumn<>("Data Nascimento");
        dataNascCol.setPrefWidth(100);
        dataNascCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Func, String> param) -> param.getValue().getValue().dataNasc);
        JFXTreeTableColumn<Func,String> salarioCol = new JFXTreeTableColumn<>("Salario");
        salarioCol.setPrefWidth(100);
        salarioCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Func, String> param) -> param.getValue().getValue().salario);
        JFXTreeTableColumn<Func,String> cargoCol = new JFXTreeTableColumn<>("Cargo");
        cargoCol.setPrefWidth(100);
        cargoCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Func, String> param) -> param.getValue().getValue().cargo);
        JFXTreeTableColumn<Func,String> codigoDepartamentoCol = new JFXTreeTableColumn<>("Departamento");
        codigoDepartamentoCol.setPrefWidth(150);
        codigoDepartamentoCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Func, String> param) -> param.getValue().getValue().codigoDepartamento);
        JFXTreeTableColumn<Func,String> registroCol = new JFXTreeTableColumn<>("Registro");
        registroCol.setPrefWidth(50);
        registroCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Func, String> param) -> param.getValue().getValue().registro);
        ObservableList<Func> funcs = FXCollections.observableArrayList();
        for(Funcionario funcionario : funcionarioDAO.read()){  
            funcs.add(new Func(funcionario.getNomeFuncionario(),funcionario.getCpf(),funcionario.getDataNascimento(),funcionario.getSalario(),funcionario.getCargo(),funcionario.getCodDepartamento(),funcionario.getRegistro()));
        }    
        final TreeItem<Func> root = new RecursiveTreeItem<>(funcs, RecursiveTreeObject::getChildren);
        tabelaResultados.getColumns().setAll(nomeFuncionarioCol,cpfFuncionarioCol,dataNascCol,salarioCol,cargoCol,codigoDepartamentoCol,registroCol);
        tabelaResultados.setRoot(root);
        tabelaResultados.setShowRoot(false);
        cpfFuncionario.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            tabelaResultados.setPredicate((TreeItem<Func> func) -> {
                Boolean flag = func.getValue().cpfFuncionario.getValue().contains(newValue);
                return flag;
            });
        });
        nomeFuncionario.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            tabelaResultados.setPredicate((TreeItem<Func> func) -> {
                Boolean flag = func.getValue().nomeFuncionario.getValue().contains(newValue);
                return flag;
            });
        });
    }

    class Func extends RecursiveTreeObject<Func> {
        StringProperty nomeFuncionario;
        StringProperty cpfFuncionario;
        StringProperty dataNasc;
        StringProperty salario;
        StringProperty cargo;
        StringProperty codigoDepartamento;
        StringProperty registro;

        public Func(String nomeFuncionario, String cpfFuncionario, String dataNasc, float salario, String cargo, int codigoDepartamento, int registro){
            this.nomeFuncionario = new SimpleStringProperty(nomeFuncionario);
            this.cpfFuncionario = new SimpleStringProperty(cpfFuncionario);
            this.dataNasc = new SimpleStringProperty(dataNasc);
            this.salario = new SimpleStringProperty(String.valueOf(salario));
            this.cargo = new SimpleStringProperty(cargo);
            this.codigoDepartamento = new SimpleStringProperty(String.valueOf(codigoDepartamento));
            this.registro = new SimpleStringProperty(String.valueOf(registro));
        }
    }   
    
}
