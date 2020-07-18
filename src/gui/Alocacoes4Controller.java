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
import services.AlocacaoServices;
import services.ProfessorServices;
import services.TurmaServices;
import table.AlocacaoTable;

public class Alocacoes4Controller implements Initializable{
	@FXML
	private Button buttonMenuPrincipal;

	@FXML
	private Button passo3;
	
	@FXML
	private Button passo5;
	
	@FXML 
	private Button buttonFixarProfessoresTurmas;
	
	@FXML 
	private Button buttonHelp;
	
	@FXML
	private MenuButton menuButtonProfessor;
	
	@FXML
	private MenuButton menuButtonTurma;
		
	@FXML 
	private Button buttonLimparLista;
	
	@FXML
	private Label labelErro;
	
	@FXML
	private TableView<AlocacaoTable> tableViewFixos;
	
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
	private TableColumn<AlocacaoTable, AlocacaoTable> columnAcoes;
	
	private ObservableList<AlocacaoTable> obsList;
	
	private static Main main = new Main();
	
	private String valorItemProfessor = " ";
	
	private String valorItemTurma = " ";
	
	private TurmaServices  turmaServices= new TurmaServices();
	
	private ProfessorServices  professorServices= new ProfessorServices();
	
	private static AlocacaoServices  alocacaoServices= new AlocacaoServices();
	
	private static List<AlocacaoTable> listAlocacaoFixos = new ArrayList<>();

	
	public static List<AlocacaoTable> getListAlocacaoFixos() {
		return listAlocacaoFixos;
	}

	
	public void setListAlocacao(List<AlocacaoTable> listAlocacao) {
		listAlocacaoFixos = listAlocacao;
	}

	public static AlocacaoServices getAlocacaoServicesFixos() {
		return alocacaoServices;
	}

	public static void setAlocacaoServices(AlocacaoServices alocacaoServices) {
		Alocacoes4Controller.alocacaoServices = alocacaoServices;
	}

	@FXML
	public void onbuttonButtonMenu() {
		main.mudarTela("/gui/MenuGestorView.fxml");
	}
	
	@FXML
	public void onbuttonButtonPasso3() {
		main.mudarTela("/gui/Alocacoes3View.fxml");
	}
	
	@FXML
	public void onbuttonButtonPasso5() {
		if(alocacaoServices.getListAlocacao().size() > 0) {
			listAlocacaoFixos = alocacaoServices.getListAlocacao();
			main.mudarTela("/gui/Alocacoes5View.fxml");
		}
		else {
			main.mudarTela("/gui/Alocacoes5View.fxml");
		}
	}
	
	@FXML
	public void onMenuProfessor() {
		List<MenuItem> list = converterListaProfessores();
		menuButtonProfessor.getItems().addAll(list);
	}
	
	@FXML
	public void onButtonLimparLista() {
		try {
			if(!valorItemTurma.equals("") && !valorItemProfessor.equals("")) {
				obsList.clear();
				tableViewFixos.setItems(obsList);
			}
		} catch (RuntimeException e) {
			e.getMessage();
		}
		
	}	
	
	@FXML
	public void onButtonHelp() {
		 Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
         dialogoInfo.setHeaderText("Help");
         dialogoInfo.setWidth(500);
         dialogoInfo.setHeight(500);
         dialogoInfo.setContentText("Ao fixar um professor devem ser alocadas todas as turmas deste, pois o mesmo não participará da distribuição com os demais professores.");
         dialogoInfo.showAndWait();
	}
	
	@FXML
    public void onButtonFixarTurmasProfessores() {
		try {
		  if (!valorItemProfessor.equals("") && !valorItemTurma.equals("")) {
				adicionarnaTable();
			}
		} catch (RuntimeException e) {
			e.getMessage();
		}
		
	}
	
	@FXML
	public void adicionarnaTable() {
		if (alocacaoServices.repetido(valorItemTurma, valorItemProfessor) == false) {
			List<AlocacaoTable> list = alocacaoServices.adicionarTurmaProfessor(professorServices.dadosProfessor(valorItemProfessor), 
					turmaServices.dadosTurma(valorItemTurma), Alocacao3Controller.getAno());
			obsList = FXCollections.observableArrayList(list);
			tableViewFixos.setItems(obsList);
			 initButtonsExcluir();
		}
		
	}
	
	@FXML
	public List<MenuItem> converterListaProfessores() {
		List<String> professores = ProfessorServices.professoresCadastradosLista();
		List<MenuItem> novaList = new ArrayList<>();
		for (String e: professores) { 
			MenuItem item = new MenuItem(e);
			item.setOnAction(event -> {
				menuButtonProfessor.setText(e);
				novaList.remove(item);
				valorItemProfessor = e;
			});
			
			novaList.add(item);
			
		}
		return novaList;
	}
	
	@FXML
	public void onMenuTurma() {
		List<MenuItem> list = converterListaTurma();
		menuButtonTurma.getItems().addAll(list);
	}
	
	@FXML
	public List<MenuItem> converterListaTurma() {
		TurmaServices turmaServices = new TurmaServices();
		List<String> turmas = turmaServices.turmasCadastradasList();
		List<MenuItem> novaList = new ArrayList<>();
		
		for (String e: turmas) { 
			MenuItem item = new MenuItem(e);
			item.setOnAction(event -> {
				menuButtonTurma.setText(e);
				novaList.remove(item);
			    valorItemTurma = e;
			});
			
			novaList.add(item);
			
		}
		return novaList;
	}
	
	@FXML
	public void atualizarTable() {
			List<AlocacaoTable> list = alocacaoServices.getListAlocacao();
			obsList = FXCollections.observableArrayList(list);
			tableViewFixos.setItems(obsList);
			initButtonsExcluir();
	}
	
	private void initButtonsExcluir() {
		columnAcoes.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		columnAcoes.setCellFactory(param -> new TableCell<AlocacaoTable, AlocacaoTable>() {
			private final Button button = new Button("x Excluir");

			@Override
			protected void updateItem(AlocacaoTable obj, boolean empty) {
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
	                	alocacaoServices.removerAlocacaoFixos(obj);
	                    atualizarTable();
	                } else if (b == btnNao) {
	                } 
	            });});
			}
		});
	}
	
	private void iniciarColunas() {
		columnCodigoTurma.setCellValueFactory(new PropertyValueFactory<>("codigoTurma"));
		columnDisciplina.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		columnAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
		columnRegistro.setCellValueFactory(new PropertyValueFactory<>("registro"));
	}

	@Override
	public void initialize(URL url, ResourceBundle arg1) {
		onMenuTurma();
		onMenuProfessor();
		iniciarColunas();
		valorItemProfessor = "";
		valorItemTurma = "";
	}

}
