package gui;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import services.ProfessorServices;

public class CadastroProfessorController {
   @FXML 
   private Button buttonVoltar;
   
   @FXML 
   private Button buttonCadastrar;
   
   @FXML 
   private TextField textNome;
   
   @FXML 
   private TextField textRegistro;
   
   @FXML 
   private TextField textCargaMinA;
   
   @FXML 
   private TextField textCargaMaxA;
   
   @FXML
   private ToggleGroup cargoAdm;
   
   @FXML 
   private Label labelErro;
   
   private static Main main = new Main();
   
   @FXML
   public void onButtonVoltar() {
	   main.mudarTela("/gui/GerenciarProfessoresView.fxml");
   }
   
   @FXML
   public void onButtonCadastrar() {
	   labelErro.setText("");
	   if(textNome.getText().equals("") && textRegistro.getText().equals("")) {
		   labelErro.setText("TODOS OS CAMPOS DEVEM SER PRENCHIDOS");
	   }
	   else {
		  String registro = textRegistro.getText();
		  String nome = textNome.getText();
		  String cargaMaxA = textCargaMaxA.getText();
		  String cargaMinA = textCargaMinA.getText();
		  RadioButton radio = (RadioButton) cargoAdm.getSelectedToggle();
		  Boolean cargoAdm = radio.getText().equals("Sim") ? true : false;
		  ProfessorServices professorServices = new ProfessorServices();
		  if(professorServices.cadastrarProfessor(registro, nome, cargaMaxA, cargaMinA, cargoAdm)) {
			  labelErro.setText(professorServices.getMensagem());
			  main.mudarTela("/gui/GerenciarProfessoresView.fxml");
		  }
		  else {
			  labelErro.setText(professorServices.getMensagem());
		  }
		  
	   }
	   
	   
   }
   
   
}
