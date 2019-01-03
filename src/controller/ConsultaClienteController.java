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
import javafx.util.Callback;
import model.bean.Cliente;
import model.dao.ClienteDAO;
/**
 * FXML Controller class
 *
 * @author gabrielmodesto
 */
public class ConsultaClienteController implements Initializable {

    @FXML private JFXTextField cpfCNPJ;
    @FXML private JFXTextField nomeCliente;
    @FXML private JFXButton pesquisar;
    @FXML private AnchorPane anchorResultados;
    @FXML private JFXTreeTableView<Client> tabelaResultados;

    Cliente cliente = new Cliente();
    ClienteDAO clienteDAO = new ClienteDAO();
    @FXML
    public void consultarCliente(ActionEvent event) {
     
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        JFXTreeTableColumn<Client,String> idClienteCol = new JFXTreeTableColumn<>("ID");
        idClienteCol.setPrefWidth(50);
        idClienteCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
               return param.getValue().getValue().idCliente;
           }
        });
        JFXTreeTableColumn<Client,String> clienteNomeCol = new JFXTreeTableColumn<>("Nome");
        clienteNomeCol.setPrefWidth(240);
        clienteNomeCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
               return param.getValue().getValue().clienteNome;
           }
        });
        JFXTreeTableColumn<Client,String> cpfClienteCol = new JFXTreeTableColumn<>("CPF");
        cpfClienteCol.setPrefWidth(150);
        cpfClienteCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
               return param.getValue().getValue().cpfCliente;
           }
        });
        JFXTreeTableColumn<Client,String> codigoProjetoCol = new JFXTreeTableColumn<>("Código Projeto");
        codigoProjetoCol.setPrefWidth(100);
        codigoProjetoCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
               return param.getValue().getValue().codigoProjeto;
           }
        });
        JFXTreeTableColumn<Client,String> cidadeClienteCol = new JFXTreeTableColumn<>("Cidade");
        cidadeClienteCol.setPrefWidth(208);
        cidadeClienteCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
               return param.getValue().getValue().cidadeCliente;
           }
        });
        JFXTreeTableColumn<Client,String> estadoClienteCol = new JFXTreeTableColumn<>("Estado");
        estadoClienteCol.setPrefWidth(50);
        estadoClienteCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Client, String>, ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Client, String> param) {
               return param.getValue().getValue().estadoCliente;
           }
        });
        ObservableList<Client> clients = FXCollections.observableArrayList();
        for(Cliente cliente : clienteDAO.read()){  
            clients.add(new Client(cliente.getIdCliente(),cliente.getNomeCliente(),cliente.getCpf(),cliente.getCodProjeto(),cliente.getCidade(),cliente.getEstado()));
        }    
        final TreeItem<Client> root = new RecursiveTreeItem<Client>(clients, RecursiveTreeObject::getChildren);
        tabelaResultados.getColumns().setAll(idClienteCol,clienteNomeCol,cpfClienteCol,codigoProjetoCol,cidadeClienteCol,estadoClienteCol);
        tabelaResultados.setRoot(root);
        tabelaResultados.setShowRoot(false);
        cpfCNPJ.textProperty().addListener(new ChangeListener<String>(){
           @Override
           public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               tabelaResultados.setPredicate(new Predicate<TreeItem<Client>>(){
                   @Override
                   public boolean test(TreeItem<Client> client) {
                       Boolean flag = client.getValue().cpfCliente.getValue().contains(newValue);
                       return flag;
                   }   
               });
           }            
        });
        nomeCliente.textProperty().addListener(new ChangeListener<String>(){
           @Override
           public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               tabelaResultados.setPredicate(new Predicate<TreeItem<Client>>(){
                   @Override
                   public boolean test(TreeItem<Client> client) {
                       Boolean flag = client.getValue().clienteNome.getValue().contains(newValue);
                       return flag;
                   }   
               });
           }           
        });
    }

    class Client extends RecursiveTreeObject<Client> {
        StringProperty idCliente;
        StringProperty clienteNome;
        StringProperty cpfCliente;
        StringProperty codigoProjeto;
        StringProperty cidadeCliente;
        StringProperty estadoCliente;
        
        public Client(int idCliente, String clienteNome, String cpfCliente, int codigoProjeto, String cidadeCliente, String estadoCliente) {
            this.idCliente = new SimpleStringProperty(String.valueOf(idCliente));
            this.clienteNome = new SimpleStringProperty(clienteNome);
            this.cpfCliente = new SimpleStringProperty(cpfCliente);
            this.codigoProjeto = new SimpleStringProperty(String.valueOf(codigoProjeto));
            this.cidadeCliente = new SimpleStringProperty(cidadeCliente);
            this.estadoCliente = new SimpleStringProperty(estadoCliente);    
        }
    }
    
}
