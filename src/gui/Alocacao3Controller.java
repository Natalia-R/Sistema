package gui;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Alocacao3Controller {
	@FXML
	private Button buttonMenuPrincipal;

	@FXML
	private Button passo2;
	
	@FXML
	private Button passo4;
	
	@FXML
	private TextField textAno;
	
	@FXML
	private Label labelErro;
	
	private static Main main = new Main();
	
	private static String ano;
	
	public static String getAno() {
		return ano;
	}
	
	@FXML
	public void onbuttonButtonMenu() {
		main.mudarTela("/gui/MenuGestorView.fxml");
	}
	
	@FXML
	public void onbuttonButtonPasso2() {
		main.mudarTela("/gui/Alocacoes2View.fxml");
	}
	
	@FXML
	public void onbuttonButtonPasso4() {
		if(validarText()) {
          main.mudarTela("/gui/Alocacoes4View.fxml");
		}
		
	}
	
	@FXML
	public boolean validarText() {
		String texto = textAno.getText();
		String num[] = texto.split("");
		if(num.length >= 4) {
			ano = textAno.getText();
			return true;
		}
		
		return false;
	}

	
	

}
