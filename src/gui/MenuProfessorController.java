package gui;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuProfessorController {
	@FXML
	private Button buttonEncerrarSecao;
	
	@FXML
	private Button buttonGerenciarPreferencias;
	
	private static Main main = new Main();
	
	@FXML
	public void onbuttonGerenciarPreferencias() {
		 main.mudarTela("/gui/GerenciarPreferenciasView.fxml");
	}	
	@FXML
	public void onbuttonEncerar() {
		 main.fecharTela();
	}
	
}
