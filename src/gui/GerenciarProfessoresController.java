package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import entites.Professor;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ProfessorServices;

public class GerenciarProfessoresController implements Initializable {
	@FXML
	private Button buttonVoltarMenu;
	
	@FXML
	private Button buttonAdicionarProfessor;
	
	@FXML
	private TableView<Professor> tableViewProfessores;
	
	@FXML
	private TableColumn<Professor, String> columnRegistro;
	
	@FXML
	private TableColumn<Professor, String> columnNome;
	
	@FXML
	private TableColumn<Professor, Integer> columnCargaMinA;
	
	@FXML
	private TableColumn<Professor, Integer> columnCargaMaxA;
	
	@FXML
	private TableColumn<Professor, Integer> columnCargaMinS;
	
	@FXML
	private TableColumn<Professor, Integer> columnCargaMaxS;
	
	@FXML
	private TableColumn<Professor, String> columnCargoAdm;
	
	@FXML
	private TableColumn<Professor, Professor> columnAcoes;
	
	
	private static Main main = new Main();
	
	private ProfessorServices professorServices = new ProfessorServices();
	
	private ObservableList<Professor> obsList;
	
	@FXML
	public void onbuttonButtonMenu() {
		main.mudarTela("/gui/MenuGestorView.fxml");
	}
   
	@FXML
	public void onbuttonButtonAdicionarProfessor() {
		main.mudarTela("/gui/CadastroProfessorView.fxml");
	}

	@FXML
	private void initButtonsExcluir() {
		columnAcoes.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		columnAcoes.setCellFactory(param -> new TableCell<Professor, Professor>() {
			private final Button button = new Button("x Excluir");

			@Override
			protected void updateItem(Professor obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> {Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
	            ButtonType btnSim = new ButtonType("Sim");
	            ButtonType btnNao = new ButtonType("Não");

	            dialogoExe.setTitle(null);
	            dialogoExe.setHeaderText("Confirmação");
	            dialogoExe.setContentText("Deseja mesmo remover este professor?");
	            dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
	            dialogoExe.showAndWait().ifPresent(b -> {
	                if (b == btnSim) {
	                     professorServices.removerProfessorCadastradoLista(obj);
	                     atualizarTable();
	                } else if (b == btnNao) {
	                } 
	            });});
			}
		});
	}
	
	@FXML
	public void atualizarTable() {
		try {
		  List<Professor> list = professorServices.getListTableProfessoresCadastrados();
		  obsList = FXCollections.observableArrayList(list);
		  tableViewProfessores.setItems(obsList);
		  initButtonsExcluir();
		}
		catch(RuntimeException e ){
			e.getMessage();
		}
	}
	 
	@FXML
	public void iniciarTable() {
		try {
		  professorServices.adicionarProfessoresCadastrados();
		  List<Professor> list = professorServices.getListTableProfessoresCadastrados();
		  obsList = FXCollections.observableArrayList(list);
		  tableViewProfessores.setItems(obsList);
		  initButtonsExcluir();
		}
		catch(RuntimeException e ){
			e.getMessage();
		}
	}

	@FXML
	private void iniciarColunas() {
		columnRegistro.setCellValueFactory(new PropertyValueFactory<>("registro"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnCargaMaxA.setCellValueFactory(new PropertyValueFactory<>("cargaMaxA"));
		columnCargaMinA.setCellValueFactory(new PropertyValueFactory<>("cargaMinA"));
		columnCargaMaxS.setCellValueFactory(new PropertyValueFactory<>("cargaMaxS"));
		columnCargaMinS.setCellValueFactory(new PropertyValueFactory<>("cargaMinS"));
		columnCargoAdm.setCellValueFactory(new PropertyValueFactory<>("cargoAdm"));
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		iniciarColunas();
        iniciarTable();
	}

	
}
