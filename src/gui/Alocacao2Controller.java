package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.TurmaServices;
import table.TurmaTable;

public class Alocacao2Controller implements Initializable {
	@FXML
	private Button buttonVoltarMenu;
	
	@FXML
	private Label labelErro;

	@FXML
	private MenuButton menuButton;
	
	@FXML 
	private Button buttonAdicionarTurmas;
	
	@FXML 
	private Button buttonLimparLista;
	
	@FXML 
	private Button buttonPasso1;
	
	@FXML 
	private Button buttonPasso3;
	
	@FXML
	private TableView<TurmaTable> tableViewTurma;
	
	@FXML
	private TableColumn<TurmaTable, String> columnCodigo;
	
	@FXML
	private TableColumn<TurmaTable, String> columnDisciplina;
	
	@FXML
	private TableColumn<TurmaTable, String> columnCurso;
	
	@FXML
	private TableColumn<TurmaTable, TurmaTable> columnAcoes;
	
	private ObservableList<TurmaTable> obsList;

    private static TurmaServices  turmaServices= new TurmaServices();
	
	private static Main main = new Main();
	
	private String valorItem;
	
	private static  ArrayList<TurmaTable> listTurma = new ArrayList<>();
	
		
	public static ArrayList<TurmaTable> getListTurma() {
		return listTurma;
	}

	public static TurmaServices getTurmaServices() {
		return turmaServices;
	}

	public static void setTurmaServices(TurmaServices turmaServices) {
		Alocacao2Controller.turmaServices = turmaServices;
	}

	@FXML
	public void onbuttonButtonMenu() {
		main.mudarTela("/gui/MenuGestorView.fxml");
	}
	
	@FXML
	public void onbuttonButtonPasso1() {
		main.mudarTela("/gui/RealizarAlocacoesView.fxml");
	}
	
	@FXML
	public void onbuttonButtonPasso3() {
		labelErro.setText("");
		if(turmaServices.getListTable().size()> 0) {
	        listTurma = (ArrayList<TurmaTable>) turmaServices.getListTable();
			main.mudarTela("/gui/Alocacoes3View.fxml");
		}
		else {
			labelErro.setText("Voce deve adicionar pelo menos uma turma");
		}
		
	}
	
	@FXML
	public void onButtonLimparLista() {
		try {
			if (!valorItem.equals("")) {
				obsList.clear();
				tableViewTurma.setItems(obsList);
			}
		} catch (RuntimeException e) {
			e.getMessage();
		}
	}
	
	@FXML
    public void onButtonAdicionarTurma() {
		try {
			if (!valorItem.equals("")) {
				adicionarnaTable();
			}
		} catch (RuntimeException e) {
			e.getMessage();
		}
		
	}
	
	@FXML
	public void adicionarnaTable() {
		if (turmaServices.repetido(valorItem) == false) {
			List<TurmaTable> list = turmaServices.adicionarTurma(valorItem);
			obsList = FXCollections.observableArrayList(list);
			tableViewTurma.setItems(obsList);
			 initButtonsExcluir();
		}
		
	}
	
	@FXML
	public List<MenuItem> converterLista() {
		TurmaServices turmaServices = new TurmaServices();
		List<String> turmas = turmaServices.turmasCadastradasList();
		List<MenuItem> novaList = new ArrayList<>();
		
		for (String e: turmas) { 
			MenuItem item = new MenuItem(e);
			item.setOnAction(event -> {
				menuButton.setText(e);
				novaList.remove(item);
			    valorItem = e;
			});
			
			novaList.add(item);
			
		}
		return novaList;
	}
	
	@FXML
	public void adicionarItens() {
		List<MenuItem> list = converterLista();
		menuButton.getItems().addAll(list);
	}
	
	@FXML
	public void atualizarTable() {
			List<TurmaTable> list = turmaServices.getListTable();
			obsList = FXCollections.observableArrayList(list);
			tableViewTurma.setItems(obsList);
			 initButtonsExcluir();
	}
	
	private void initButtonsExcluir() {
		columnAcoes.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		columnAcoes.setCellFactory(param -> new TableCell<TurmaTable, TurmaTable>() {
			private final Button button = new Button("x Excluir");

			@Override
			protected void updateItem(TurmaTable obj, boolean empty) {
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
	            dialogoExe.setContentText("Deseja mesmo remover esta turma da distribuição?");
	            dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
	            dialogoExe.showAndWait().ifPresent(b -> {
	                if (b == btnSim) {
	                    turmaServices.removerTurma(obj);
	                    atualizarTable();
	                } else if (b == btnNao) {
	                } 
	            });});
			}
		});
	}
	
	private void iniciarColunas() {
          columnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		  columnDisciplina.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
		  columnCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));	
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		iniciarColunas();
		valorItem = " ";
		adicionarItens();	
	}
}
