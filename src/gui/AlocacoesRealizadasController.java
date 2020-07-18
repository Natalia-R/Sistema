package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.AlocacaoServices;
import table.AlocacaoTable;

public class AlocacoesRealizadasController implements Initializable {
	@FXML
	private TableColumn<AlocacaoTable, String> columnCodigoTurma;
	
	@FXML
	private TableColumn<AlocacaoTable, String> columnDisciplina;
	
	@FXML
	private TableColumn<AlocacaoTable, String> columnRegistro;
	
	@FXML
	private TableColumn<AlocacaoTable, String> columnNome;
	
	@FXML
	private TableColumn<AlocacaoTable, String> columnAno;
	
	@FXML
	private TableColumn<AlocacaoTable, String> columnCurso;
	
	@FXML
	private TableView<AlocacaoTable> tableViewFixos;
	
	@FXML
	private Button pesquisar;
	
	@FXML
	private TextField ano;
	
	@FXML
	private Label labelErro;
	
	private ObservableList<AlocacaoTable> obsList;

	@FXML
	private Button buttonMenu;
	
	private static Main main = new Main();
	
	@FXML
	public void onbuttonButtonMenu() {
		main.mudarTela("/gui/MenuGestorView.fxml");
	}
	
	public void onbuttonPesquisar() {
		labelErro.setText("");
		if(!ano.getText().equals("")) {
			try {
				String anoBusca = ano.getText();
			    List<AlocacaoTable> alocacaoBusca = AlocacaoServices.alocacoesBuscaAno(anoBusca);
				if (alocacaoBusca.size() > 0) {
					obsList = FXCollections.observableArrayList(alocacaoBusca);
					tableViewFixos.setItems(obsList);
				}
				else {
					labelErro.setText("Ano não encontrado");
					iniciarTable();
				}
			}
			catch(RuntimeException e) {
				e.getMessage();
			}
		}
		
	}

	@FXML
	public void iniciarTable() {
		List<AlocacaoTable> list = AlocacaoServices.alocacoesCadastradas(); 
		obsList = FXCollections.observableArrayList(list);
		tableViewFixos.setItems(obsList);
	}
	
	private void iniciarColunas() {
		columnCodigoTurma.setCellValueFactory(new PropertyValueFactory<>("codigoTurma"));
		columnDisciplina.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
		columnCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
		columnRegistro.setCellValueFactory(new PropertyValueFactory<>("registro"));
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	   iniciarColunas();
	   iniciarTable();
	}
	
}
