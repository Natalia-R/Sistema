package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import entites.Professor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import services.ProfessorServices;

public class AtualizarProfessorController implements Initializable{
	@FXML
	private Button buttonVoltar;

	@FXML
	private Button buttonAtualizar;

	@FXML
	private TextField textNome;

	@FXML
	private TextField textRegistro;

	@FXML
	private TextField textCargaMinA;

	@FXML
	private TextField textCargaMaxA;
	
	@FXML
	private RadioButton radioButtonNao;
	
	@FXML
	private RadioButton radioButtonSim;

	@FXML
	private ToggleGroup cargoAdm;

	@FXML
	private Label labelErro;

	private static Main main = new Main();
	
	private Professor linha = GerenciarProfessoresController.getLinha();

	@FXML
	public void onButtonVoltar() {
		main.mudarTela("/gui/GerenciarProfessoresView.fxml");
	}

	@FXML
	public void onButtonAtualizar() {
		labelErro.setText("");
		if (textNome.getText().equals("") && textRegistro.getText().equals("")) {
			labelErro.setText("TODOS OS CAMPOS DEVEM SER PRENCHIDOS");
		} else {
			String registro = textRegistro.getText();
			String nome = textNome.getText();
			String cargaMaxA = textCargaMaxA.getText();
			String cargaMinA = textCargaMinA.getText();
			RadioButton radio = (RadioButton) cargoAdm.getSelectedToggle();
			Boolean cargoAdm = radio.getText().equals("Sim") ? true : false;
			ProfessorServices professorServices = new ProfessorServices();
			professorServices.removerAtualizarProfessor(linha);
			if (professorServices.cadastrarProfessor(registro, nome, cargaMaxA, cargaMinA, cargoAdm)) {
				labelErro.setText(professorServices.getMensagem());
				main.mudarTela("/gui/GerenciarProfessoresView.fxml");
			} else {
				labelErro.setText(professorServices.getMensagem());
			}
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		textNome.setText(linha.getNome());
		textRegistro.setText(linha.getRegistro());
		textCargaMaxA.setText(Integer.toString(linha.getCargaMaxA()));
		textCargaMinA.setText(Integer.toString(linha.getCargaMinA()));
		String cargo = linha.getCargoAdm();
		if (cargo.equals("ADM")){
			cargoAdm.selectToggle(radioButtonSim);
		}
		else {
			cargoAdm.selectToggle(radioButtonNao);
		}
	
	}
}
