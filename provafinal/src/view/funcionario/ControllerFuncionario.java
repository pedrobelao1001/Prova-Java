package view.funcionario;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.FuncionarioDao;

import java.sql.Date;

import entity.Funcionario;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerFuncionario extends Application implements Initializable{



	@FXML
    private Button BTSalvar;

    @FXML
    private Button BTEditar;

    @FXML
    private Button BTExcluir;

    @FXML
    private TextField PrNome;

    @FXML
    private TextField PrCargo;

    @FXML
    private TextField PrRg;

    @FXML
    private TextArea textAreaLista;

    @FXML
    private TextField PrId;

    @FXML
    private Label Labelid;

    @FXML
    private Label LabelLabel;

    @FXML
    private Button BTBuscarID;

    @FXML
    private DatePicker datePickerEmissao;



    @FXML
    void buscarID(ActionEvent event) {
    	String idString = PrId.getText();
    	Funcionario funcionario = null;
    	if(!idString.equals("")) {
    		try {
    			int id = Integer.valueOf(idString);
    			funcionario = new FuncionarioDao().findByID(id);
    		} catch (Exception e) {
    	
    		}
    		if(funcionario != null) {
    			Labelid.setVisible(true);
    			LabelLabel.setVisible(true);
    			LabelLabel.setText(funcionario.getId()+  "");
    			PrNome.setText(funcionario.getNome());
    			PrCargo.setText(funcionario.getCargo());
    			PrRg.setText(funcionario.getRg());
    			datePickerEmissao.setValue(funcionario.getDataemissao().toLocalDate());
    		}
    	}
    	PrId.clear();
    }
    
    @FXML
    void ExcluirFuncionario(ActionEvent event) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
  	 alert.setTitle("ATENÇÃO");
	 alert.setHeaderText("EXCLUIR DADOS");
	 alert.setContentText("VOCÊ TEM CERTEZA QUE DESEJA EXCLUIR O FUNCIONARIO?");
       Optional<ButtonType> result = alert.showAndWait();
       
        if (result.get() == ButtonType.OK){
        	System.out.println("olá");
            Funcionario funcionario= obtemDadosID();
            int qtde = new FuncionarioDao().deletar(funcionario.getId());
            limpaCampo();
            listarFuncionario();
            
        }
    }

    @FXML
    void SalvarFuncionario(ActionEvent event) {
    	Funcionario funcionario = obtemDados();
    	limpaCampo();
    	int qtde = new FuncionarioDao().inserir(funcionario);
    	listarFuncionario();
    	System.out.println(qtde);
    }
    
    @FXML
    void EditarFuncionario(ActionEvent event) {
    	Funcionario funcionario = obtemDadosID();
    	limpaCampo();
    	int qtde = new FuncionarioDao().alterar(funcionario);
    	listarFuncionario();
    }
    
    private void limpaCampo() {
    	PrNome.clear();
    	PrCargo.clear();
    	PrRg.clear();
    	datePickerEmissao.setValue(null);
    			
    	PrNome.requestFocus();
    	
    	LabelLabel.setVisible(false);
    	LabelLabel.setText("");
    	Labelid.setVisible(false);
    }
    
    
    private Funcionario obtemDados() {
    	return new Funcionario(PrNome.getText(), PrCargo.getText(), PrRg.getText(), java.sql.Date.valueOf(datePickerEmissao.getValue()));
    }

    private Funcionario obtemDadosID() {
    	
    	return new Funcionario(Integer.valueOf(LabelLabel.getText()), PrNome.getText(), PrCargo.getText(), PrRg.getText(), java.sql.Date.valueOf(datePickerEmissao.getValue()));
    }
    
    private void listarFuncionario() {
    	textAreaLista.clear();
    	List<Funcionario> listaFuncionario = new FuncionarioDao().listAll();
    listaFuncionario.forEach(funcionario -> {
    	textAreaLista.appendText(funcionario.toString() +"\n");
    });
    textAreaLista.appendText("Listando total de funcionarios:"+ listaFuncionario.size());
    }
    
    public void execute() {
        launch();
    }

    @Override
    public void start(Stage stage) {

        try {
            AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("FuncionarioView.fxml"));
            Scene sc = new Scene(pane);
            stage.setScene(sc);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }   	

    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listarFuncionario();
		
	}	 
}