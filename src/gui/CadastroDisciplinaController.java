package gui;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.DisciplinaServices;

public class CadastroDisciplinaController {
	   @FXML 
	   private Button buttonVoltar;
	   
	   @FXML 
	   private Button buttonCadastrar;
	   
	   @FXML 
	   private TextField textNome;
	   
	   @FXML 
	   private TextField textCodigo;
	      
	   @FXML 
	   private Label labelErro;
	   
	   private static Main main = new Main();
	   
	   @FXML
	   public void onButtonVoltar() {
		   main.mudarTela("/gui/GerenciarDisciplinasView.fxml");
	   }
	   
	   @FXML
	   public void onButtonCadastrar() {
		   labelErro.setText("");
		   if(textNome.getText().equals("") && textCodigo.getText().equals("")) {
			   labelErro.setText("TODOS OS CAMPOS DEVEM SER PRENCHIDOS");
		   }
		   else {
			  String codigo = textCodigo.getText();
			  String nome = textNome.getText();
			 
			  DisciplinaServices disciplinaServices = new DisciplinaServices();
			  if(disciplinaServices.cadastrarDisciplina(codigo, nome)) {
				  main.mudarTela("/gui/GerenciarDisciplinasView.fxml");
			  }
			  else {
				  labelErro.setText("ERRO AO CADASTRAR DISCIPLINA");
			  }
			  
		   }
		   
		   
	   }

}
