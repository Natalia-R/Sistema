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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.AlocacaoServices;
import table.AlocacaoTable;

public class Alocacao6Controller implements Initializable{
	@FXML
	private Button buttonMenuPrincipal;
	
	@FXML
	private Button buttonAceitarResultado;
	
	@FXML
	private Button buttonRejeitarResultado;
	
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
	
	private ObservableList<AlocacaoTable> obsList;

	private static Main main = new Main();
	
	private AlocacaoServices alocacaoServices = new AlocacaoServices();

	@FXML
	public void onbuttonButtonMenuPrincipal() {
		main.mudarTela("/gui/MenuGestorView.fxml");
	}
	
	
	@FXML
	public void onbuttonAceitarResultado() {
		alocacaoServices.salavarResultado();
		limparDados();
		main.mudarTela("/gui/MenuGestorView.fxml");
	}
	
	@FXML
	public void onbuttonRejeitarResultado() {
		limparDados();
		main.mudarTela("/gui/MenuGestorView.fxml");
	}
	
	private void limparDados() {
		RealizarAlocacoesController.getListProfessores().clear();
		Alocacao2Controller.getListTurma().clear();
		Alocacoes4Controller.getListAlocacaoFixos().clear();
	}

	@FXML
	public void iniciarTable() {
		List<AlocacaoTable> list = alocacaoServices.realizarAlocacao(RealizarAlocacoesController.getListProfessores(), 
				Alocacao2Controller.getListTurma(), Alocacao3Controller.getAno(),Alocacoes4Controller.getListAlocacaoFixos());
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
