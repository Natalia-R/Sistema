package gui;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AlocacoesRealizadasController {

	@FXML
	private Button buttonMenu;
	
	private static Main main = new Main();
	
	@FXML
	public void onbuttonButtonMenu() {
		main.mudarTela("/gui/MenuGestorView.fxml");
	}
	
}
