package gui;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.HorarioServices;

public class CadastroHorarioController {
	 @FXML 
	   private Button buttonVoltar;
	   
	   @FXML 
	   private Button buttonCadastrar;
	   
	   @FXML 
	   private TextField textDia;
	   
	   @FXML 
	   private TextField textAula;
	      
	   @FXML 
	   private Label labelErro;
	   
	   private static Main main = new Main();
	   
	   @FXML
	   public void onButtonVoltar() {
		   main.mudarTela("/gui/GerenciarHorariosView.fxml");
	   }
	   
	   @FXML
	   public void onButtonCadastrar() {
		   labelErro.setText("");
		   if(textDia.getText().equals("") && textAula.getText().equals("")) {
			   labelErro.setText("TODOS OS CAMPOS DEVEM SER PRENCHIDOS");
		   }
		   else {
			  String dia = textDia.getText();
			  String aula = textAula.getText();
			 
			 HorarioServices horarioServices = new HorarioServices();
			 if(horarioServices.cadastrarHorario(dia, aula)) {
			    main.mudarTela("/gui/GerenciarHorariosView.fxml");
			 }
			 else {
				  labelErro.setText("ERRO AO CADASTRAR CURSO");
			}
			  
		   }
		   
		   
	   }

}
