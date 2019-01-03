/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import model.bean.Suporte;
import model.dao.SuporteDAO;

/**
 * FXML Controller class
 *
 * @author gabrielmodesto
 */
public class HistoricoSuporteController implements Initializable {

    @FXML private JFXTextField tipoReclamacao;
    @FXML private JFXTreeTableView<Suport> tabelaResultadosSuporte;

    SuporteDAO suporteDAO = new SuporteDAO();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JFXTreeTableColumn<Suport, String> codigoProjetoCol = new JFXTreeTableColumn<>("Cod Projeto");
        codigoProjetoCol.setPrefWidth(100);
        codigoProjetoCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Suport, String> param) -> param.getValue().getValue().codigoProjeto);
        JFXTreeTableColumn<Suport, String> tipoReclamacaoCol = new JFXTreeTableColumn<>("Tipo Reclamação");
        tipoReclamacaoCol.setPrefWidth(120);
        tipoReclamacaoCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Suport, String> param) -> param.getValue().getValue().tipoReclamacao);
        JFXTreeTableColumn<Suport, String> atendenteCol = new JFXTreeTableColumn<>("Atendente");
        atendenteCol.setPrefWidth(120);
        atendenteCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Suport, String> param) -> param.getValue().getValue().atendente);
        JFXTreeTableColumn<Suport, String> motivoCol = new JFXTreeTableColumn<>("Motivo");
        motivoCol.setPrefWidth(460);
        motivoCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Suport, String> param) -> param.getValue().getValue().motivo);
        ObservableList<Suport> suports = FXCollections.observableArrayList();
        for(Suporte suporte : suporteDAO.read()){
            suports.add(new Suport(suporte.getCodProjeto(),suporte.getTipoReclamacao(),suporte.getCpfFuncionario(),suporte.getMotivoReclamacao()));
        }
        final TreeItem<Suport> root = new RecursiveTreeItem<Suport>(suports, RecursiveTreeObject::getChildren);
        tabelaResultadosSuporte.getColumns().setAll(codigoProjetoCol,tipoReclamacaoCol,atendenteCol,motivoCol);
        tabelaResultadosSuporte.setRoot(root);
        tabelaResultadosSuporte.setShowRoot(false);
        tipoReclamacao.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            tabelaResultadosSuporte.setPredicate((TreeItem<Suport> suport) -> {
                Boolean flag = suport.getValue().tipoReclamacao.getValue().contains(newValue);
                return flag;
            });
        });
    }    

    class Suport extends RecursiveTreeObject<Suport> {
        StringProperty codigoProjeto;
        StringProperty tipoReclamacao;
        StringProperty atendente;
        StringProperty motivo;
        public Suport(int codigoProjeto, String tipoReclamacao, String atendente, String motivo) {
            this.codigoProjeto = new SimpleStringProperty(String.valueOf(codigoProjeto));
            this.tipoReclamacao = new SimpleStringProperty(tipoReclamacao);
            this.atendente = new SimpleStringProperty(atendente);
            this.motivo = new SimpleStringProperty(motivo);
        }
    }
    
}
