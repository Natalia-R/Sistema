package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import entites.Disciplina;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.DisciplinaServices;

public class GerenciarDisciplinasController implements Initializable {
	
	@FXML
	private Button buttonVoltarMenu;
	
	@FXML
	private Button buttonAdicionarDisciplina;
	
	@FXML
	private Button buttonPesquisar;
	
	@FXML
	private TextField  textFieldPesquisar;
	
	@FXML
	private Label  labelErro;
	
	@FXML
	private TableView<Disciplina> tableViewDisciplinas;
	
	@FXML
	private TableColumn<Disciplina, String> columnCodigo;
	
	@FXML
	private TableColumn<Disciplina, String> columnDisciplina;
	
	@FXML
	private TableColumn<Disciplina, Disciplina> columnAcoes;
	
	@FXML
	private TableColumn<Disciplina, Disciplina> columnAcoes2;
	
	private ObservableList<Disciplina> obsList;

	private static Main main = new Main();
	
	private DisciplinaServices disciplinasServices = new DisciplinaServices();
	
    private static Disciplina linha;
	
	public static Disciplina getLinha() {
		return linha;
	}
	
	
	@FXML
	public void onbuttonButtonMenu() {
		main.mudarTela("/gui/MenuGestorView.fxml");
	}
   
	@FXML
	public void onbuttonButtonAdicionarDisciplina() {
		main.mudarTela("/gui/CadastrodeDisciplinasView.fxml");
	}
	
	@FXML
	public void onButtonPesquisar() {
		labelErro.setText("");
		if(!textFieldPesquisar.getText().equals("")) {
			try {
				String nome = textFieldPesquisar.getText();
				List<Disciplina> discBusca = disciplinasServices.buscarDisciplinaNome(nome);
				if (discBusca.size() > 0) {
					obsList = FXCollections.observableArrayList(discBusca);
					tableViewDisciplinas.setItems(obsList);
					initButtonsExcluir();
					initButtonsEditar();
				}
				else {
					labelErro.setText("Disciplina não encontrado");
					atualizarTable();
				}
			}
			catch(RuntimeException e) {
				e.getMessage();
			}
		}
	}
	
	@FXML
	public void atualizarTable() {
		try {
		  List<Disciplina> list = disciplinasServices.disciplinasCadastradas();
		  obsList = FXCollections.observableArrayList(list);
		  tableViewDisciplinas.setItems(obsList);
		  initButtonsExcluir();
		  initButtonsEditar();
		}
		catch(RuntimeException e ){
			System.out.println(e.getMessage());
			e.getMessage();
		}
	}
	@FXML
	public void iniciarTable() {
		try {
		  List<Disciplina> list =  disciplinasServices.disciplinasCadastradas();
		  obsList = FXCollections.observableArrayList(list);
		  tableViewDisciplinas.setItems(obsList);
		  initButtonsExcluir();
		  initButtonsEditar();
		}
		catch(RuntimeException e ){
			System.out.println(e.getMessage());
			e.getMessage();
		}
	}
	@FXML
	private void initButtonsExcluir() {
		columnAcoes.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		columnAcoes.setCellFactory(param -> new TableCell<Disciplina, Disciplina>() {
			private final Button button = new Button("x Excluir");
		
			@Override
			protected void updateItem(Disciplina obj, boolean empty) {
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
	            dialogoExe.setContentText("Deseja mesmo remover esta disciplina?");
	            dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
	            dialogoExe.showAndWait().ifPresent(b -> {
	                if (b == btnSim) {
	                     disciplinasServices.removerDisciplinaCadastrada(obj);
	                     atualizarTable();
	                } else if (b == btnNao) {
	                } 
	            });});
			}
						
		});
	}
	
	private void initButtonsEditar() {
		columnAcoes2.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		columnAcoes2.setCellFactory(param -> new TableCell<Disciplina, Disciplina>() {
			private final Button button = new Button("Editar");
			@Override
			protected void updateItem(Disciplina obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> {
					linha = obj;
					main.mudarTela("/gui/AtualizarDisciplinasView.fxml");
				});
			}
		});
	}
	@FXML
	private void iniciarColunas() {
		columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		columnDisciplina.setCellValueFactory(new PropertyValueFactory<>("nome"));
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		iniciarColunas();
		iniciarTable();
	}


}
