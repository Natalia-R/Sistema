package gui;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class Alocacao5Controller {
	@FXML
	private Button passo4;
	
	@FXML
	private Button passo6;
	
	@FXML
	private Button buttonMenuPrincipal;
	
	@FXML
	private ToggleGroup carga;
	
	@FXML
	private ToggleGroup equitativo;
	
	@FXML
	private ToggleGroup extremos;
	
	private static boolean semestralAnual;
	
	private static boolean modeloEquitativo;
	
	private static boolean modeloExtremos;
	
	private static Main main = new Main();
	

	public static boolean isSemestralAnual() {
		return semestralAnual;
	}

	public static boolean isModeloEquitativo() {
		return modeloEquitativo;
	}

	public static boolean isModeloExtremos() {
		return modeloExtremos;
	}

	@FXML
	public void onbuttonButtonMenuPrincipal() {
		main.mudarTela("/gui/MenuGestorView.fxml");
	}
	
	@FXML
	public void onbuttonButtonPasso4() {
		main.mudarTela("/gui/Alocacoes4View.fxml");
	}

	@FXML
	public void onbuttonButtonPasso6() {
	    pegarOpcoes();
        main.mudarTela("/gui/Alocacoes6View.fxml");
	}
	
	private void pegarOpcoes() {
		RadioButton radio = (RadioButton) carga.getSelectedToggle();
		semestralAnual = radio.getText().equals("Anual") ? true : false;
		
		radio = (RadioButton) equitativo.getSelectedToggle();
		modeloEquitativo = radio.getText().equals("Sim") ? true : false;
		
		radio = (RadioButton) extremos.getSelectedToggle();
		modeloExtremos = radio.getText().equals("Sim") ? true : false;
	}
	
	
}
