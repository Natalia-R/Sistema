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
import services.ProfessorServices;
import table.ProfessorTable;

public class RealizarAlocacoesController implements Initializable {
	@FXML
	private Button buttonVoltarMenu;
	
	@FXML
	private Label labelErro;

	@FXML
	private MenuButton menuButton;

	@FXML
	private Button buttonAdicionarProfessor;

	@FXML
	private Button buttonLimparTela;

	@FXML
	private Button buttonPasso2;

	@FXML
	private TableView<ProfessorTable> tableViewProfessores;

	@FXML
	private TableColumn<ProfessorTable, String> columnRegistro;

	@FXML
	private TableColumn<ProfessorTable, String> columnNome;

	@FXML
	private TableColumn<ProfessorTable, Integer> columnCargaMin;

	@FXML
	private TableColumn<ProfessorTable, Integer> columnCargaMax;

	@FXML
	private TableColumn<ProfessorTable, String> columnCargoadm;

	@FXML
	private TableColumn<ProfessorTable, ProfessorTable> columnAcoes;

	private ObservableList<ProfessorTable> obsList;

	private static Main main = new Main();

	private String valorItem;

	private static ProfessorServices professorServices = new ProfessorServices();
	
	private static List<ProfessorTable> listProfessores = new ArrayList<>();


	public static List<ProfessorTable> getListProfessores() {
		return listProfessores;
	}
	
	public static ProfessorServices getProfessorServices() {
		return professorServices;
	}

	@FXML
	public void onbuttonButtonMenu() {
		main.mudarTela("/gui/MenuGestorView.fxml");
	}

	@FXML
	public void onbuttonButtonPasso2() {
		labelErro.setText("");
		if(professorServices.getListTable().size() > 0) {
			listProfessores = professorServices.getListTable();
			main.mudarTela("/gui/Alocacoes2View.fxml");
		}
		else {
			labelErro.setText("Voce deve adicionar pelo menos 1 professor para continuar");
		}		
	}

	@FXML
	public void onButtonLimparLista() {
		try {
			if (!valorItem.equals("")) {
				obsList.clear();
				tableViewProfessores.setItems(obsList);
			}
		} catch (RuntimeException e) {
			e.getMessage();
		}
	}

	@FXML
	public void onButtonAdicionarProfessor() {
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
		if (professorServices.itemRepetido(valorItem) == false) {
			List<ProfessorTable> list = professorServices.adicionarProfessorTable(valorItem);
			obsList = FXCollections.observableArrayList(list);
			tableViewProfessores.setItems(obsList);
			initButtonsExcluir();
		}

	}
	@FXML
	private void initButtonsExcluir() {
		columnAcoes.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		columnAcoes.setCellFactory(param -> new TableCell<ProfessorTable, ProfessorTable>() {
			private final Button button = new Button("x Excluir");

			@Override
			protected void updateItem(ProfessorTable obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> {
					Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
					ButtonType btnSim = new ButtonType("Sim");
					ButtonType btnNao = new ButtonType("Não");

					dialogoExe.setTitle(null);
					dialogoExe.setHeaderText("Confirmação");
					dialogoExe.setContentText("Deseja mesmo remover este professor da distribuição?");
					dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
					dialogoExe.showAndWait().ifPresent(b -> {
						if (b == btnSim) {
							professorServices.removerProfessor(obj);
							atualizarTable();
						} else if (b == btnNao) {
						}
					});
				});
			}
		});
	}


	@FXML
	public void atualizarTable() {
		List<ProfessorTable> list = professorServices.getListTable();
		obsList = FXCollections.observableArrayList(list);
		tableViewProfessores.setItems(obsList);
		initButtonsExcluir();
	}
	
	@FXML
	public void adicionarItens() {
		List<MenuItem> list = converterLista();
		menuButton.getItems().addAll(list);
	}

	@FXML
	public List<MenuItem> converterLista() {
		List<String> professores = ProfessorServices.professoresCadastradosLista();
		List<MenuItem> novaList = new ArrayList<>();
		for (String e : professores) {
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
	private void iniciarColunas() {
		columnRegistro.setCellValueFactory(new PropertyValueFactory<>("registro"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		columnCargaMax.setCellValueFactory(new PropertyValueFactory<>("cargaMaxA"));
		columnCargaMin.setCellValueFactory(new PropertyValueFactory<>("cargaMaxA"));
		columnCargoadm.setCellValueFactory(new PropertyValueFactory<>("cargoAdm"));
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		adicionarItens();
		valorItem = "";
		iniciarColunas();
	}

}
