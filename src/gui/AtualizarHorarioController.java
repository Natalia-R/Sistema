package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import entites.Horario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.HorarioServices;

public class AtualizarHorarioController implements Initializable{
	@FXML
	private Button buttonVoltar;

	@FXML
	private Button buttonAtualizar;

	@FXML
	private TextField textDia;

	@FXML
	private TextField textAula;

	@FXML
	private Label labelErro;

	private static Main main = new Main();
	
	private Horario linha = GerenciarHorarioController.getLinha();
	
	private Horario horario = new Horario();

	@FXML
	public void onButtonVoltar() {
		main.mudarTela("/gui/GerenciarHorariosView.fxml");
	}

	@FXML
	public void onButtonAtualizar() {
		labelErro.setText("");
		if (textDia.getText().equals("") && textAula.getText().equals("")) {
			labelErro.setText("TODOS OS CAMPOS DEVEM SER PRENCHIDOS");
		} else {
			String dia = textDia.getText();
			String aula = textAula.getText();
			String id = "" + linha.getId();
			HorarioServices horarioServices = new HorarioServices();
		    horarioServices.removerHorarioCadastradaId(id);
			horarioServices.atualizarHorario(dia, aula, id);
			main.mudarTela("/gui/GerenciarHorariosView.fxml");
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		textDia.setText(horario.converteDia(linha.getNomeDia()));
		textAula.setText(linha.getAula());
	
	}

}
