package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import model.bean.Cliente;
import model.dao.ClienteDAO;

public class AlteraClienteController implements Initializable {
    @FXML private StackPane stackCliente;
    @FXML private JFXButton btnEditar;
    @FXML private JFXButton btnLimpar;
    @FXML private JFXTextField razaoSocial;
    @FXML private JFXTextField CNPJ;
    @FXML private JFXDatePicker dataNasc;
    @FXML private JFXTextField endereco;
    @FXML private JFXTextField cep;
    @FXML private JFXTextField bairro;
    @FXML private JFXTextField cidade;
    @FXML private JFXComboBox<String> estado;

    Cliente cliente = new Cliente();
    ClienteDAO clienteDAO = new ClienteDAO();
    
    @FXML
    public void editarCampos(ActionEvent event){
        cliente.setNomeCliente(razaoSocial.getText());
        cliente.setCpf(CNPJ.getText());
        cliente.setDataNascimento(dataNasc.getValue().toString());
        cliente.setEndereco(endereco.getText());
        cliente.setBairro(bairro.getText());
        cliente.setCep(cep.getText());
        cliente.setCidade(cidade.getText());
        cliente.setEstado(estado.getSelectionModel().getSelectedItem().toString());
        if((clienteDAO.update(cliente)) && !camposVazios()){
            JFXDialogLayout alerta = new JFXDialogLayout();
            alerta.setBody(new Text("Edição realizada com Sucesso"));
            alerta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            JFXDialog caixa = new JFXDialog(stackCliente, alerta, JFXDialog.DialogTransition.CENTER);
            JFXButton sair = new JFXButton("OK");
            sair.setOnAction(e -> {limpaCampos(null);caixa.close();});
            alerta.setActions(sair);
            caixa.show();
        }
        else{            
            JFXDialogLayout alerta = new JFXDialogLayout();
            alerta.setBody(new Text("Os campos estão vazios"));
            alerta.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            JFXDialog caixa = new JFXDialog(stackCliente, alerta, JFXDialog.DialogTransition.CENTER);
            JFXButton sair = new JFXButton("Não alterar nada");
            sair.setOnAction(e -> {caixa.close();});
            alerta.setActions(sair);
            caixa.show();
        }   
    }

    @FXML
    public void limpaCampos(ActionEvent event) {
        estado.setValue(null);
        cidade.clear();
        endereco.clear();
        CNPJ.clear();
        razaoSocial.clear();
        cep.clear();
        dataNasc.setValue(null);
        bairro.clear();
    }
    private boolean camposVazios(){
        boolean erro = false;
        if(razaoSocial.getText().equals(""))
            erro = true;
        if(CNPJ.getText().equals(""))
            erro = true;
        if(dataNasc.getValue() == null)
            erro = true;
        if(endereco.getText().equals(""))
            erro = true;
        if(bairro.getText().equals(""))
            erro = true;
        if(cep.getText().equals(""))
            erro = true;
        if(cidade.getText().equals(""))
            erro = true;
        if(estado.getSelectionModel().getSelectedItem() == null)
            erro = true;

        return erro;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        estado.getItems().addAll("AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO");
    } 
}
