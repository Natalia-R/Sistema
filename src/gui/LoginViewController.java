package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import services.UsuarioServices;

public class LoginViewController implements Initializable{
	
	@FXML
	private TextField textUsuario;
	
	@FXML
	private Label labelErro;
	
	@FXML
	private PasswordField textSenha;
	
	@FXML
	private Button buttonEntrar;
	
	@FXML
	private Button buttonCadastrarUsuario;
	
	private static Main main = new Main();
	private static String usuario;
	
	public static String getUsuario() {
		return usuario;
	}

	@FXML
	public void onbuttonCadastrarUsuarioAction() {
		main.mudarTela("/gui/CadastroUsuarioView.fxml");
	}
	
	@FXML
	public void onbuttonEntrar() {
		try {
			labelErro.setText("");
			if (!textUsuario.getText().equals("") && !textSenha.getText().equals("")) {
				String usuario1 = textUsuario.getText();
				String senha = textSenha.getText();
				if (usuario1.equals("adm") && senha.equals("159")) {
					usuario = textUsuario.getText();
					main.mudarTela("/gui/MenuGestorView.fxml");
				}
				if (UsuarioServices.validarLogin(usuario1, senha)) {
					usuario = textUsuario.getText();
					main.mudarTela("/gui/MenuDoProfessorView.fxml");

				} else {
					labelErro.setText("Aviso: Login ou senha inválidos, \n"
							+ "caso não seja cadastrado clique em 'Cadastrar usuario'.");

				}
			} else {
				labelErro.setText("Voce deve preencher todos os campos");
			}
		}

		catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {	
       usuario = "";
	}

	
	
}
