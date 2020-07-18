package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import entites.HorarioTurma;
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
import services.HorarioTurmaServices;

public class GerenciamentoHorarioTurmaController implements Initializable {
	
	@FXML
	private Button buttonGerenciarTurma;
	
	@FXML
	private Button buttonVincularHorario;
	
	@FXML
	private Button buttonPesquisar;
	
	@FXML
	private TextField  textFieldPesquisar;
	
	@FXML
	private Label  labelErro;
	
	@FXML
	private TableView<HorarioTurma> tableViewHorarios;
	
	@FXML
	private TableColumn<HorarioTurma, String> columnCodigoTurma;
	
	@FXML
	private TableColumn<HorarioTurma, String> columnDia;
	
	@FXML
	private TableColumn<HorarioTurma, String> columnAula;
	
	@FXML
	private TableColumn<HorarioTurma, HorarioTurma> columnAcoes;
	
	private ObservableList<HorarioTurma> obsList;

	private static Main main = new Main();
	
	private HorarioTurmaServices horarioturmaServices = new HorarioTurmaServices();
	
	private Turma dadosTurma = GerenciarTurmaController.getLinhaHorario();
  	
	@FXML
	public void onbuttonGerenciarTurma() {
		main.mudarTela("/gui/GerenciarTurmasView.fxml");
	}
   
	@FXML
	public void onbuttonButtonVincularHorario() {
		main.mudarTela("/gui/VincularHorariosView.fxml");
	}
	
	
	@FXML
	public void onButtonPesquisar() {
		labelErro.setText("");
		if(!textFieldPesquisar.getText().equals("")) {
			try {
				String dia = textFieldPesquisar.getText();
			    List<HorarioTurma> horarioBusca = horarioturmaServices.horariosBuscaDia(dadosTurma.getCodigo(), dia);
				if (horarioBusca.size() > 0) {
					obsList = FXCollections.observableArrayList(horarioBusca);
					tableViewHorarios.setItems(obsList);
					initButtonsExcluir();
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
	
	public void iniciarTable() {
		try {
		
		   List<HorarioTurma> list = horarioturmaServices.horariosCadastrados(dadosTurma.getCodigo());
		   obsList = FXCollections.observableArrayList(list);
		   tableViewHorarios.setItems(obsList);
		   initButtonsExcluir();
		}
		catch(RuntimeException e){
			System.out.println(e.getMessage());
			e.getMessage();
		}
	}
	
	
	@FXML
	private void initButtonsExcluir() {
		columnAcoes.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		columnAcoes.setCellFactory(param -> new TableCell<HorarioTurma, HorarioTurma>() {
			private final Button button = new Button("x Excluir");
		
			@Override
			protected void updateItem(HorarioTurma obj, boolean empty) {
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
	                   horarioturmaServices.removerHorarioTurmaCadastrado(obj);
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
		  List<HorarioTurma> list = horarioturmaServices.horariosCadastrados(dadosTurma.getCodigo());
		  obsList = FXCollections.observableArrayList(list);
		  tableViewHorarios.setItems(obsList);
		  initButtonsExcluir();
		}
		catch(RuntimeException e ){
			System.out.println(e.getMessage());
			e.getMessage();
		}
	}
	
	@FXML
	private void iniciarColunas() {
		columnCodigoTurma.setCellValueFactory(new PropertyValueFactory<>("codigoTurma"));
		columnDia.setCellValueFactory(new PropertyValueFactory<>("nomeDia"));
		columnAula.setCellValueFactory(new PropertyValueFactory<>("aula"));
		
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		iniciarColunas();
		iniciarTable();
	}
}
