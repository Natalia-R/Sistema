package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import entites.Horario;
import entites.Turma;
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
import services.TurmaServices;

public class GerenciarTurmaController  implements Initializable {
	
	@FXML
	private Button buttonVoltarMenu;
	
	@FXML
	private Button buttonAdicionarTurma;
	
	@FXML
	private Button buttonPesquisar;
	
	@FXML
	private TextField  textFieldPesquisar;
	
	@FXML
	private Label  labelErro;
	
	@FXML
	private TableView<Turma> tableViewTurmas;
	
	@FXML
	private TableColumn<Turma, String> columnCodigoTurma;
	
	@FXML
	private TableColumn<Turma, String> columnDisciplina;
	
	@FXML
	private TableColumn<Turma, String> columnCurso;
	
	@FXML
	private TableColumn<Turma, Integer> columnCargaTotal;
	
	@FXML
	private TableColumn<Turma, Integer> columnCargaSemanal;
	
	@FXML
	private TableColumn<Turma, Integer> columnSemestre;
	
	@FXML
	private TableColumn<Turma, Turma> columnAcoes;
	
	@FXML
	private TableColumn<Turma, Turma> columnAcoes2;
	
	@FXML
	private TableColumn<Turma, Turma> columnAcoes3;
	
	private ObservableList<Turma> obsList;

	private static Main main = new Main();
	
	private TurmaServices turmaServices = new TurmaServices();
	
    private static Turma linha;
    
    private static Turma linhaHorario;
	
	public static Turma getLinha() {
		return linha;
	}
	
	public static Turma getLinhaHorario() {
		return linhaHorario;
	}
		
	@FXML
	public void onbuttonButtonMenu() {
		main.mudarTela("/gui/MenuGestorView.fxml");
	}
   
	@FXML
	public void onbuttonButtonAdicionarTurma() {
		main.mudarTela("/gui/CadastroTurmaView.fxml");
	}
	
	@FXML
	public void onButtonPesquisar() {
		labelErro.setText("");
		if(!textFieldPesquisar.getText().equals("")) {
			try {
				String codigo = textFieldPesquisar.getText();
			    List<Turma> codigoTurmaBusca = turmaServices.buscarTurmaCodigo(codigo);
				if (codigoTurmaBusca.size() > 0) {
					obsList = FXCollections.observableArrayList(codigoTurmaBusca);
					tableViewTurmas.setItems(obsList);
					initButtonsExcluir();
					initButtonsEditar();
				}
				else {
					labelErro.setText("Turma não encontrado");
					atualizarTable();
				}
			}
			catch(RuntimeException e) {
				System.out.println(e.getMessage());
				e.getMessage();
			}
		}
	}
	
	public void iniciarTable() {
		try {
		   List<Turma> list =  turmaServices.turmasCadastradasTable();
		   obsList = FXCollections.observableArrayList(list);
		   tableViewTurmas.setItems(obsList);
		   initButtonsExcluir();
		   initButtonsEditar();
		   initButtonsHorarios();
		}
		catch(RuntimeException e ){
			System.out.println(e.getMessage());
			e.getMessage();
		}
	}
	
	@FXML
	private void initButtonsExcluir() {
		columnAcoes.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		columnAcoes.setCellFactory(param -> new TableCell<Turma, Turma>() {
			private final Button button = new Button("x Excluir");
		
			@Override
			protected void updateItem(Turma obj, boolean empty) {
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
	                    turmaServices.removerTurmaCadastrada(obj);
	                    atualizarTable();
	                } else if (b == btnNao) {
	                } 
	            });});
			}
						
		});
	}
	
	private void initButtonsEditar() {
		columnAcoes2.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		columnAcoes2.setCellFactory(param -> new TableCell<Turma, Turma>() {
			private final Button button = new Button("Editar");
			@Override
			protected void updateItem(Turma obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> {
			    linha = obj;
				main.mudarTela("/gui/AtualizarTurmaView.fxml");
				});
			}
		});
	}
	
	private void initButtonsHorarios() {
		columnAcoes3.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		columnAcoes3.setCellFactory(param -> new TableCell<Turma, Turma>() {
			private final Button button = new Button("Ver Horarios");
			@Override
			protected void updateItem(Turma obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> {
			    linhaHorario = obj;
				main.mudarTela("/gui/GerenciarHorarioTurmaView.fxml");
				});
			}
		});
	}
	
	@FXML
	public void atualizarTable() {
		try {
		  List<Turma> list = turmaServices.turmasCadastradasTable();
		  obsList = FXCollections.observableArrayList(list);
		  tableViewTurmas.setItems(obsList);
		  initButtonsExcluir();
		  initButtonsEditar();
		  initButtonsHorarios();
		}
		catch(RuntimeException e ){
			System.out.println(e.getMessage());
			e.getMessage();
		}
	}
	
	@FXML
	private void iniciarColunas() {
		columnCodigoTurma.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		columnDisciplina.setCellValueFactory(new PropertyValueFactory<>("nomeDisciplina"));
		columnCurso.setCellValueFactory(new PropertyValueFactory<>("nomeCurso"));
		columnCargaTotal.setCellValueFactory(new PropertyValueFactory<>("cargaTotal"));
		columnCargaSemanal.setCellValueFactory(new PropertyValueFactory<>("cargaSemanal"));
		columnSemestre.setCellValueFactory(new PropertyValueFactory<>("semestre"));
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		iniciarColunas();
		iniciarTable();
		initButtonsHorarios();
	}

}
