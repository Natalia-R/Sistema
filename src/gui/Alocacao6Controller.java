package gui;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Alocacao6Controller {
 @FXML 
  private Button buttonMenuPrincipal;
 
  private static Main main = new Main();
 @FXML
	public void onbuttonButtonMenuPrincipal() {
		main.mudarTela("/gui/MenuGestorView.fxml");
	}
}
