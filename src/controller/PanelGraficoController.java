/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.dao.ClienteDAO;
import model.dao.FuncionarioDAO;
import model.dao.ProjetoDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author gabrielmodesto
 */
public class PanelGraficoController implements Initializable {

    @FXML
    private Label lbFun;
    @FXML
    private Label lbCli;
    @FXML
    private Label lbDin;
    @FXML
    private PieChart projetoGrafico;
    @FXML
    private LineChart<String, Number> rendaGrafico;

    FuncionarioDAO fDao = new FuncionarioDAO();
    ClienteDAO cDao = new ClienteDAO();
    ProjetoDAO projetoDao = new ProjetoDAO();

    private void createGraficos() {
        //Pie chart
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("Projetos finalizados", 96),
                        new PieChart.Data("Projetos em Andamento", 27),
                        new PieChart.Data("Projetos Futuros", 152));
        projetoGrafico.setTitle("Projetos");
        projetoGrafico.setData(pieChartData);
        projetoGrafico.setLegendSide(Side.BOTTOM);
        projetoGrafico.setLabelLineLength(10);
        Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial");
//        for(PieChart.Data d : projetoGrafico.getData()){
//            d.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
//                caption.setTranslateX(e.getSceneX());
//                caption.setTranslateY(e.getSceneY());
//                caption.setText(String.valueOf(d.getPieValue()) + "%");
//            });
//        }

        //Line chart
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Janeiro", 100));
        series.getData().add(new XYChart.Data("Fevereiro", 200));
        series.getData().add(new XYChart.Data("Março", 50));
        series.getData().add(new XYChart.Data("Abril", 75));
        series.getData().add(new XYChart.Data("Maio", 110));
        series.getData().add(new XYChart.Data("Junho", 300));
        series.getData().add(new XYChart.Data("Julho", 111));
        series.getData().add(new XYChart.Data("Agosto", 30));
        series.getData().add(new XYChart.Data("Setembro", 75));
        series.getData().add(new XYChart.Data("Outubro", 55));
        series.getData().add(new XYChart.Data("Novembro", 225));
        series.getData().add(new XYChart.Data("Dezembro", 99));

        rendaGrafico.getData().add(series);
        
    }

    private void setInfo() {
        int f = fDao.countFuncionario();
        int c = cDao.countCliente();
        float v = projetoDao.getRendaBruta();
        lbFun.setText(String.valueOf(f));
        lbCli.setText(String.valueOf(c));
        lbDin.setText(String.valueOf(v));
        //  System.out.println(String.valueOf(v));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setInfo();
        createGraficos();
    } 
    
}
