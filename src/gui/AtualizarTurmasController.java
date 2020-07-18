package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import entites.Turma;
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

public class AtualizarTurmasController implements Initializable {
	@FXML
	private Button buttonVoltar;

	@FXML
	private Button buttonAtualizar;

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
	
	@FXML
	private RadioButton radioButtonNao;
	
	@FXML
	private RadioButton radioButtonSim;
	
	
	@FXML
	private RadioButton radioButton1;
	
	@FXML
	private RadioButton radioButton2;
	
	private String valorItemDisciplina;
	   
    private String valorItemCurso;

	private static Main main = new Main();

	private Turma linha = GerenciarTurmaController.getLinha();

	@FXML
	public void onButtonVoltar() {
		main.mudarTela("/gui/GerenciarTurmasView.fxml");
	}

	@FXML
	public void onButtonAtualizar() {
		labelErro.setText("");
		if (textCargaTotal.getText().equals("") && textCodigo.equals("")) {
			labelErro.setText("TODOS OS CAMPOS DEVEM SER PRENCHIDOS");
		} else {
			String codigo = textCodigo.getText();
			String cargaTotal = textCargaTotal.getText();
			
			RadioButton radio = (RadioButton) anual.getSelectedToggle();
			boolean cargoAnual = radio.getText().equals("Sim") ? true : false;
	
			RadioButton radio2 = (RadioButton) cargoSemestre.getSelectedToggle();
			Integer semestre = radio2.getText().equals("1") ? 1 : 2;
			
			TurmaServices turmaServices = new TurmaServices();
			turmaServices.removerTurmaCadastrada(new Turma(codigo, semestre, cargoAnual, Integer.parseInt(cargaTotal), 10,
					this.valorItemCurso, this.valorItemDisciplina));
			String semestreString = "" + semestre;
			if(cargoAnual) {
				turmaServices.cadastrarTurma(codigo, this.valorItemDisciplina, this.valorItemCurso, cargaTotal, "true", semestreString);
			}
			else {
				turmaServices.cadastrarTurma(codigo, this.valorItemDisciplina, this.valorItemCurso, cargaTotal, "true", semestreString);
			}
			main.mudarTela("/gui/GerenciarTurmasView.fxml");
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
		textCodigo.setText(linha.getCodigo());
		menuButtonDisciplina.setText(linha.getNomeDisciplina());
		menuButtonCurso.setText(linha.getNomeCurso());
		String carga = "" + linha.getCargaTotal();
		textCargaTotal.setText(carga);
		this.valorItemCurso = linha.getNomeCurso();
		this.valorItemDisciplina = linha.getNomeDisciplina();
		
		boolean cargaAnual = linha.isAnual();
		if (cargaAnual){
			this.anual.selectToggle(radioButtonSim);
		}
		else {
			this.anual.selectToggle(radioButtonNao);
		}
		
		Integer semestres = linha.getSemestre();
		if (semestres == 1){
			this.cargoSemestre.selectToggle(radioButton1);
		}
		else {
			this.cargoSemestre.selectToggle(radioButton2);
		}
		
		
	}

}
