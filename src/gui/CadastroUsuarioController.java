package gui;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.UsuarioServices;

public class CadastroUsuarioController {
	@FXML
	private TextField textnumRegistro;
	
	@FXML
	private TextField textLogin;
	
	@FXML
	private PasswordField textSenha;
	
	@FXML
	private PasswordField textConfirmarSenha;
	
	@FXML
	private Button buttonCadastrar;
	
	@FXML
	private Button buttonVoltar;
	
	@FXML
	private Label labelErro;
	
	@FXML
	private Label labelErroSenha;
	
	
	private  static Main main = new Main();
	
	@FXML
	public void onbuttonVoltarAction() {
		main.mudarTela("/gui/LoginView.fxml");
	}
	
	@FXML
	public void onbuttonCadastrarAction() {
		labelErro.setText("");
		labelErroSenha.setText("");
		if (!textnumRegistro.getText().equals("") && !textLogin.getText().equals("") && !textSenha.getText().equals("")
				&& !textConfirmarSenha.getText().equals("")) {
			if (textSenha.getText().equals(textConfirmarSenha.getText())) {
				String numRegistro = textnumRegistro.getText();
				String login = textLogin.getText();
				String senha = textSenha.getText();
				UsuarioServices usuarioServices = new UsuarioServices();
				if (usuarioServices.cadastrarUsuario(numRegistro, login, senha)) {
					 main.mudarTela("/gui/LoginView.fxml");
					
				}
				else {
				   labelErro.setText(usuarioServices.getMensagem());
				   
				}

			} else {
				labelErroSenha.setText("As senhas não coicidem");
			}

		} else {
			labelErro.setText("Voce deve preencher todos os campos");
		}
	}
	
	
	
}
