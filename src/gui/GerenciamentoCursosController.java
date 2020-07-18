package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import entites.Curso;
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
import services.CursoServices;

public class GerenciamentoCursosController  implements Initializable {
	
	@FXML
	private Button buttonVoltarMenu;
	
	@FXML
	private Button buttonAdicionarCurso;
	
	@FXML
	private Button buttonPesquisar;
	
	@FXML
	private TextField  textFieldPesquisar;
	
	@FXML
	private Label  labelErro;
	
	@FXML
	private TableView<Curso> tableViewCursos;
	
	@FXML
	private TableColumn<Curso, String> columnCodigo;
	
	@FXML
	private TableColumn<Curso, String> columnCurso;
	
	@FXML
	private TableColumn<Curso, Curso> columnAcoes;
	
	@FXML
	private TableColumn<Curso, Curso> columnAcoes2;
	
	private ObservableList<Curso> obsList;

	private static Main main = new Main();
	
	private CursoServices cursoServices = new CursoServices();
	
    private static Curso linha;
	
	public static Curso getLinha() {
		return linha;
	}
	
	
	@FXML
	public void onbuttonButtonMenu() {
		main.mudarTela("/gui/MenuGestorView.fxml");
	}
   
	@FXML
	public void onbuttonButtonAdicionarCurso() {
		main.mudarTela("/gui/CadastrodeCursoView.fxml");
	}
	
	@FXML
	public void onButtonPesquisar() {
		labelErro.setText("");
		if(!textFieldPesquisar.getText().equals("")) {
			try {
				String nome = textFieldPesquisar.getText();
			    List<Curso> cursoBusca = cursoServices.buscarCursoNome(nome);
				if (cursoBusca.size() > 0) {
					obsList = FXCollections.observableArrayList(cursoBusca);
					tableViewCursos.setItems(obsList);
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
	
	
	public void iniciarTable() {
		try {
		   List<Curso> list =  cursoServices.cursoCadastrados();
		   obsList = FXCollections.observableArrayList(list);
		   tableViewCursos.setItems(obsList);
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
		columnAcoes.setCellFactory(param -> new TableCell<Curso, Curso>() {
			private final Button button = new Button("x Excluir");
		
			@Override
			protected void updateItem(Curso obj, boolean empty) {
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
	                     cursoServices.removerCursoCadastrada(obj);
	                     atualizarTable();
	                } else if (b == btnNao) {
	                } 
	            });});
			}
						
		});
	}
	
	private void initButtonsEditar() {
		columnAcoes2.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		columnAcoes2.setCellFactory(param -> new TableCell<Curso, Curso>() {
			private final Button button = new Button("Editar");
			@Override
			protected void updateItem(Curso obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> {
			    linha = obj;
				main.mudarTela("/gui/AtualizarCursoView.fxml");
				});
			}
		});
	}
	
	@FXML
	public void atualizarTable() {
		try {
		  List<Curso> list = cursoServices.cursoCadastrados();
		  obsList = FXCollections.observableArrayList(list);
		  tableViewCursos.setItems(obsList);
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
		columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		columnCurso.setCellValueFactory(new PropertyValueFactory<>("nome"));
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		iniciarColunas();
		iniciarTable();
	}

}
