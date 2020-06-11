package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.PreferenciasServices;
import table.PreferenciaTable;

public class GerenciarPreferenciasController implements Initializable {
	@FXML
	private Button buttonMenuPrincipal;
	
	@FXML
	private Button buttonCadastrarPreferencias;
	
	@FXML
	private TableView<PreferenciaTable> tableViewPreferencia;
	
	@FXML
	private TableColumn<PreferenciaTable, String> columnRegistro;
	
	@FXML
	private TableColumn<PreferenciaTable, String> columnNomeProfessor;
	
	@FXML
	private TableColumn<PreferenciaTable, String> columnTurma1;
	
	@FXML
	private TableColumn<PreferenciaTable, String> columnTurma2;
	
	@FXML
	private TableColumn<PreferenciaTable, String> columnTurma3;
	
	@FXML
	private TableColumn<PreferenciaTable, String> columnDescarte1;
	
	@FXML
	private TableColumn<PreferenciaTable, String> columnDescarte2;
	
	@FXML
	private TableColumn<PreferenciaTable, String> columnDescarte3;
	
	@FXML
	private TableColumn<PreferenciaTable, PreferenciaTable> columnAcoes;
	
	private ObservableList<PreferenciaTable> obsList;
	
	private static Main main = new Main();
	
	private static PreferenciaTable linha;
	
	public static PreferenciaTable getLinha() {
		return linha;
	}
	
	@FXML
	public void onbuttonCadastroPreferencias() {
		 main.mudarTela("/gui/CadastrodePreferenciasView.fxml");
	}	
	@FXML
	public void onbuttonMenuPrincipal() {
		 main.mudarTela("/gui/MenuDoProfessorView.fxml");
	}
	
	@FXML
	public void adicionarTable() {
	 try {
		List<PreferenciaTable> list = PreferenciasServices.PreferenciasDoProfessor();
		if (list.size() > 0) {
			obsList = FXCollections.observableArrayList(list);
			tableViewPreferencia.setItems(obsList);
	        initButtonsEditar();
		}
	 }
	 catch(RuntimeException e) {
		 e.getMessage();
	 }
	}
	
	private void initButtonsEditar() {
		columnAcoes.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		columnAcoes.setCellFactory(param -> new TableCell<PreferenciaTable, PreferenciaTable>() {
			private final Button button = new Button("Editar");

			@Override
			protected void updateItem(PreferenciaTable obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> {
					linha = obj;
					main.mudarTela("/gui/AtualizarPreferenciasView.fxml");
				});
			}
		});
	}

	@FXML
	private void iniciarColunas() {
        columnRegistro.setCellValueFactory(new PropertyValueFactory<>("registroProfessor"));
		columnNomeProfessor.setCellValueFactory(new PropertyValueFactory<>("nomeProfessor"));
		columnTurma1.setCellValueFactory(new PropertyValueFactory<>("turma1"));
		columnTurma2.setCellValueFactory(new PropertyValueFactory<>("turma2"));
		columnTurma3.setCellValueFactory(new PropertyValueFactory<>("turma3"));
		columnDescarte1.setCellValueFactory(new PropertyValueFactory<>("descarte1"));
		columnDescarte2.setCellValueFactory(new PropertyValueFactory<>("descarte2"));
		columnDescarte3.setCellValueFactory(new PropertyValueFactory<>("descarte3"));
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
        iniciarColunas();		
        adicionarTable();
	}

}
