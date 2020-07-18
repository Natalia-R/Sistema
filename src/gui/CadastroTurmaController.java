package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import services.CursoServices;
import services.DisciplinaServices;
import services.TurmaServices;

public class CadastroTurmaController implements Initializable {
	   @FXML 
	   private Button buttonVoltar;
	   
	   @FXML 
	   private Button buttonCadastrar;
   
	   @FXML 
	   private TextField textCodigo;
	   
	   @FXML
	   private MenuButton menuButtonDisciplina;
	   
	   @FXML
	   private MenuButton menuButtonCurso;
	   
	   @FXML 
	   private TextField textCargaTotal;
	   
	   @FXML
	   private ToggleGroup anual;
	   
	   @FXML
	   private ToggleGroup cargoSemestre;	
	   
	   @FXML 
	   private Label labelErro;
	   
	   private static Main main = new Main();
	   
	   private String valorItemDisciplina;
	   
	   private String valorItemCurso;
	   
	   @FXML
	   public void onButtonVoltar() {
		   main.mudarTela("/gui/GerenciarTurmasView.fxml");
	   }
	   
	   @FXML
	   public void onButtonCadastrar() {
		   labelErro.setText("");
		   if(textCodigo.getText().equals("") && this.valorItemCurso == null && this.valorItemDisciplina == null) {
			   labelErro.setText("TODOS OS CAMPOS DEVEM SER PRENCHIDOS");
		   } 
		   else {
			  String codigo = textCodigo.getText();
			  
			  String separador[] = this.valorItemDisciplina.split(" - ");
			  String nomeDisciplina = separador[1];
			  
			  String cargaTotal = textCargaTotal.getText();
			  
			  RadioButton radio = (RadioButton) anual.getSelectedToggle();
			  String cargoAnual = radio.getText().equals("Sim") ? "true" : "false";
	
			  RadioButton radio2 = (RadioButton) cargoSemestre.getSelectedToggle();
			  String semestre = radio2.getText().equals("1") ? "1" : "2";
			  
			  TurmaServices turmaServices = new TurmaServices();
			  if(turmaServices.cadastrarTurma(codigo, nomeDisciplina, this.valorItemCurso, cargaTotal, cargoAnual, semestre)) {
				 main.mudarTela("/gui/GerenciarTurmasView.fxml");
			  }
			  else {
				  labelErro.setText("ERRO AO CADASTRAR CURSO");
			  }
			  
		   }
		   
		   
	   }

	@FXML
	public List<MenuItem> converterListaCurso() {
		List<String> curso = CursoServices.cursoNomes();
		List<MenuItem> novaList = new ArrayList<>();

		for (String e : curso) {
			MenuItem item = new MenuItem(e);
			item.setOnAction(event -> {
				menuButtonCurso.setText(e);
				novaList.remove(item);
				this.valorItemCurso = e;
			});

			novaList.add(item);

		}
		return novaList;
	}

	@FXML
	public List<MenuItem> converterListaDisciplina() {
		List<String> disciplina = DisciplinaServices.dadosDisciplinas();
		List<MenuItem> novaList = new ArrayList<>();

		 for (String e : disciplina) {
			MenuItem item = new MenuItem(e);
			item.setOnAction(event -> {
				menuButtonDisciplina.setText(e);
				novaList.remove(item);
				this.valorItemDisciplina = e;
			});

			novaList.add(item);

		}
		return novaList;
	}

	@FXML
	public void adicionarItens() {
		 List<MenuItem> list = converterListaDisciplina();
		 menuButtonDisciplina.getItems().addAll(list);
		 
		 List<MenuItem> list2 = converterListaCurso();
		 menuButtonCurso.getItems().addAll(list2);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		adicionarItens();
	}

}
