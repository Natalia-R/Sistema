package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import entites.Horario;
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
import services.HorarioServices;

public class GerenciarHorarioController implements Initializable {
	
	@FXML
	private Button buttonVoltarMenu;
	
	@FXML
	private Button buttonAdicionarHorario;
	
	@FXML
	private Button buttonPesquisar;
	
	@FXML
	private TextField  textFieldPesquisar;
	
	@FXML
	private Label  labelErro;
	
	@FXML
	private TableView<Horario> tableViewHorario;
	
	@FXML
	private TableColumn<Horario, Integer> columnId;
	
	@FXML
	private TableColumn<Horario, String> columnDia;
	
	@FXML
	private TableColumn<Horario, String> columnAula;
	
	@FXML
	private TableColumn<Horario, Horario> columnAcoes;
	
	@FXML
	private TableColumn<Horario, Horario> columnAcoes2;
	
	private ObservableList<Horario> obsList;

	private static Main main = new Main();
	
	private HorarioServices horarioServices = new HorarioServices();
	
    private static Horario linha;
	
	public static Horario getLinha() {
		return linha;
	}
	
	
	@FXML
	public void onbuttonButtonMenu() {
		main.mudarTela("/gui/MenuGestorView.fxml");
	}
   
	@FXML
	public void onbuttonButtonAdicionarHorario() {
		main.mudarTela("/gui/CadastrodeHorarioView.fxml");
	}
	
	@FXML
	public void onButtonPesquisar() {
		labelErro.setText("");
		if(!textFieldPesquisar.getText().equals("")) {
			try {
				String dia = textFieldPesquisar.getText();
			    List<Horario> horarioBusca = horarioServices.buscarCursoDia(dia);
				if (horarioBusca.size() > 0) {
					obsList = FXCollections.observableArrayList(horarioBusca);
					tableViewHorario.setItems(obsList);
					initButtonsExcluir();
					initButtonsEditar();
				}
				else {
					labelErro.setText("Dia não encontrado");
					atualizarTable();
				}
			}
			catch(RuntimeException e) {
				e.getMessage();
			}
		}
	}
	
	@FXML
	public void iniciarTable() {
		try {
		  List<Horario> list =  horarioServices.horariosCadastrados();
		  obsList = FXCollections.observableArrayList(list);
		  tableViewHorario.setItems(obsList);
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
		columnAcoes.setCellFactory(param -> new TableCell<Horario, Horario>() {
			private final Button button = new Button("x Excluir");
		
			@Override
			protected void updateItem(Horario obj, boolean empty) {
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
	                     horarioServices.removerHorarioCadastrada(obj);
	                     atualizarTable();
	                } else if (b == btnNao) {
	                } 
	            });});
			}
						
		});
	}
	
	private void initButtonsEditar() {
		columnAcoes2.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		columnAcoes2.setCellFactory(param -> new TableCell<Horario, Horario>() {
			private final Button button = new Button("Editar");
			@Override
			protected void updateItem(Horario obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> {
			    linha = obj;
			    main.mudarTela("/gui/AtualizarHorarioView.fxml");
				});
			}
		});
	}
	
	@FXML
	public void atualizarTable() {
		try {
		  List<Horario> list = horarioServices.horariosCadastrados();
		  obsList = FXCollections.observableArrayList(list);
		  tableViewHorario.setItems(obsList);
		  initButtonsExcluir();
		  initButtonsEditar();
		}
		catch(RuntimeException e ){
			System.out.println(e.getMessage());
			e.getMessage();
		}
	}
	
	@FXML
	private void iniciarColunas() {
		columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnDia.setCellValueFactory(new PropertyValueFactory<>("nomeDia"));
		columnAula.setCellValueFactory(new PropertyValueFactory<>("aula"));
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		iniciarColunas();
		iniciarTable();
	}

}
