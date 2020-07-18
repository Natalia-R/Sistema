package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

public class menuGestorController implements Initializable{
	
	@FXML
	private Tab tabRealizarAlocacoes;
	
	@FXML
	private Tab tabAlocacoesRealizadas;
	
	@FXML
	private Tab Disciplina;
	
	@FXML
	private Tab tabEncerrarSecao;
	
	private static Main main = new Main();
	
	@FXML
	public void onbuttonTabRealizarAlocacoes() {
		 limparDados();
		 main.mudarTela("/gui/RealizarAlocacoesView.fxml");
	}
	private void limparDados() {
		RealizarAlocacoesController.getListProfessores().clear();
		Alocacao2Controller.getListTurma().clear();
		Alocacoes4Controller.getListAlocacaoFixos().clear();
	}
	
	
	@FXML
	public void onbuttonTabAlocacoesRealizadas() {
		 main.mudarTela("/gui/AlocacoesRealizadasView.fxml");
	}
		
	@FXML
	public void onbuttonTabProfessores() {
		 main.mudarTela("/gui/GerenciarProfessoresView.fxml");
	}
	
	@FXML
	public void onbuttonTabDisciplina() {
		 main.mudarTela("/gui/GerenciarDisciplinasView.fxml");
	}
	
	@FXML
	public void onbuttonTabCurso() {
		 main.mudarTela("/gui/GerenciarCursosView.fxml");
	}
	
	@FXML
	public void onbuttonTabHorarios() {
		 main.mudarTela("/gui/GerenciarHorariosView.fxml");
	}
	
	@FXML
	public void onbuttonTabTurmas() {
		 main.mudarTela("/gui/GerenciarTurmasView.fxml");
	}
	
	@FXML
	public void onbuttonTabPreferencias() {
		 //main.mudarTela("/gui/RealizarAlocacoesView.fxml");
	}
	@FXML
	public void onbuttonTabEncerar() {
		 main.fecharTela();
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
	
	

}
