package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import entites.Disciplina;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.DisciplinaServices;

public class AtualizarDisciplinasController implements Initializable{
	@FXML
	private Button buttonVoltar;

	@FXML
	private Button buttonAtualizar;

	@FXML
	private TextField textNome;

	@FXML
	private TextField textCodigo;

	@FXML
	private Label labelErro;

	private static Main main = new Main();
	
	private Disciplina linha = GerenciarDisciplinasController.getLinha();

	@FXML
	public void onButtonVoltar() {
		main.mudarTela("/gui/GerenciarDisciplinasView.fxml");
	}

	@FXML
	public void onButtonAtualizar() {
		labelErro.setText("");
		if (textNome.getText().equals("") && textCodigo.getText().equals("")) {
			labelErro.setText("TODOS OS CAMPOS DEVEM SER PRENCHIDOS");
		} else {
			String codigo = textCodigo.getText();
			String nome = textNome.getText();
			DisciplinaServices disciplinaServices = new DisciplinaServices();
			disciplinaServices.removerDisciplinaCadastrada(new Disciplina(linha.getCodigo(),linha.getNome()));
			disciplinaServices.cadastrarDisciplina(codigo, nome);
			main.mudarTela("/gui/GerenciarDisciplinasView.fxml");
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		textNome.setText(linha.getNome());
		textCodigo.setText(linha.getCodigo());
	
	}

}
