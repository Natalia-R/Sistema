package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import services.DisciplinaServices;
import services.PreferenciasServices;
import services.TurmaServices;

public class CadastrodePreferenciasController implements Initializable {
	@FXML
	private Button buttonHelp;
	
	@FXML
	private Button buttonVoltar;
	
	@FXML
	private Button Cadastrar;
	
	@FXML
	private Label labelErro;
	
	@FXML
	private MenuButton menuButtonTurma1;
	
	@FXML
	private MenuButton menuButtonTurma2;
	
	@FXML
	private MenuButton menuButtonTurma3;
	
	@FXML
	private MenuButton menuButtonDescarte1;
	
	@FXML
	private MenuButton menuButtonDescarte2;
	
	@FXML
	private MenuButton menuButtonDescarte3; 
	
	private static Main main = new Main();
	
	private String valorItemTurma1, valorItemTurma2, valorItemTurma3;
	private String valorItemDiscarte1, valorItemDiscarte2, valorItemDiscarte3;
	
	@FXML
	public void onButtonVoltar() {
		main.mudarTela("/gui/GerenciarPreferenciasView.fxml");
	}
	
	@FXML
	public void onButtonHelp() {
		 Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
         dialogoInfo.setHeaderText("Help");
         dialogoInfo.setWidth(500);
         dialogoInfo.setHeight(500);
         dialogoInfo.setContentText("PREFERÊNCIAS \n " + 
         "As Turmas devem ser cadastradas de acordo com a preferência, da maior para a menor ( 1 > 2 > 3 ). \n" +
          "O modelo tentará alocar o professor a essas turmas, porém, não sendo garantida a alocação. \n"+
          "DESCARTES\n"+
          "As disciplinas descarte são aquelas que o professor não tem segurança para lecionar, \n"+
          "ou simplesmente não queira. O sistema criará restrições no modelo para que o professor não pegue tais disciplinas.\n"+
          "*OBS: Uma disciplina descarte só será entregue ao respectivo professor em casos extremos.\n");
         dialogoInfo.showAndWait();
	}

	public void onButtonCadastrar() {
		labelErro.setText("");
		if (!valorItemTurma1.equals("") && !valorItemTurma2.equals("") && !valorItemTurma3.equals("")
				&& !valorItemDiscarte1.equals("") && !valorItemDiscarte2.equals("") && !valorItemDiscarte3.equals("")) {
			if (!valorItemTurma1.equals(valorItemTurma2) && !valorItemTurma1.equals(valorItemTurma3)
					&& !valorItemTurma2.equals(valorItemTurma3) && !valorItemDiscarte1.equals(valorItemDiscarte2)
					&& !valorItemDiscarte1.equals(valorItemDiscarte3)
					&& !valorItemDiscarte2.equals(valorItemDiscarte3)) {
				PreferenciasServices.salvarPreferencias(valorItemTurma1, valorItemTurma2, valorItemTurma3,
						valorItemDiscarte1, valorItemDiscarte2, valorItemDiscarte3);
				main.mudarTela("/gui/GerenciarPreferenciasView.fxml");
				
			}
			else {
				labelErro.setText("HÁ TURMAS OU DESCARTES IGUAIS");
			}

		}
	}
	
	@FXML
	public void adicionarItens() {
		List<MenuItem> listTurma1 = converterListaTurma1();
		menuButtonTurma1.getItems().addAll(listTurma1);
		
		List<MenuItem> listTurma2 = converterListaTurma2();
		menuButtonTurma2.getItems().addAll(listTurma2);
		
		List<MenuItem> listTurma3 = converterListaTurma3();
		menuButtonTurma3.getItems().addAll(listTurma3);
		
		List<MenuItem> listDiscarte1 = converterListaDisciplina1();
		menuButtonDescarte1.getItems().addAll(listDiscarte1);
		
		List<MenuItem> listDiscarte2 = converterListaDisciplina2();
		menuButtonDescarte2.getItems().addAll(listDiscarte2);
		
		List<MenuItem> listDiscarte3 = converterListaDisciplina3();
		menuButtonDescarte3.getItems().addAll(listDiscarte3);
	}
	
	@FXML
	public List<MenuItem> converterListaTurma1() {
		TurmaServices turmaServices = new TurmaServices();
		List<String> turmas = turmaServices.turmasCadastradasList();
		List<MenuItem> novaList = new ArrayList<>();
		for (String e: turmas) { 
			MenuItem item = new MenuItem(e);
			item.setOnAction(event -> {
				menuButtonTurma1.setText(e);
				novaList.remove(item);
			    valorItemTurma1 = e;
			});
			
			novaList.add(item);
		}
		return novaList;
	}
	
	@FXML
	public List<MenuItem> converterListaTurma2() {
		TurmaServices turmaServices = new TurmaServices();
		List<String> turmas = turmaServices.turmasCadastradasList();
		List<MenuItem> novaList = new ArrayList<>();
		
		for (String e: turmas) { 
			MenuItem item = new MenuItem(e);
			item.setOnAction(event -> {
				menuButtonTurma2.setText(e);
				novaList.remove(item);
			    valorItemTurma2 = e;
			});
			
			novaList.add(item);
			
		}
		return novaList;
	}
	@FXML
	public List<MenuItem> converterListaTurma3() {
		TurmaServices turmaServices = new TurmaServices();
		List<String> turmas = turmaServices.turmasCadastradasList();
		List<MenuItem> novaList = new ArrayList<>();
		for (String e: turmas) { 
			MenuItem item = new MenuItem(e);
			item.setOnAction(event -> {
				menuButtonTurma3.setText(e);
				novaList.remove(item);
			    valorItemTurma3 = e;
			});
			
			novaList.add(item);
		}
		return novaList;
	}
	
	@FXML
	public List<MenuItem> converterListaDisciplina1() {
		List<String> disiciplinas = DisciplinaServices.dadosDisciplinas();
		List<MenuItem> novaList = new ArrayList<>();
		for (String e: disiciplinas) { 
			MenuItem item = new MenuItem(e);
			item.setOnAction(event -> {
				menuButtonDescarte1.setText(e);
				novaList.remove(item);
			    valorItemDiscarte1 = e;
			});
			
			novaList.add(item);
		}
		return novaList;
	}
	
	@FXML
	public List<MenuItem> converterListaDisciplina2() {
		List<String> disiciplinas = DisciplinaServices.dadosDisciplinas();
		List<MenuItem> novaList = new ArrayList<>();
		
		for (String e: disiciplinas) { 
			MenuItem item = new MenuItem(e);
			item.setOnAction(event -> {
				menuButtonDescarte2.setText(e);
				novaList.remove(item);
			    valorItemDiscarte2 = e;
			});
			
			novaList.add(item);
		}
		return novaList;
	}
	@FXML
	public List<MenuItem> converterListaDisciplina3() {
		List<String> disiciplinas = DisciplinaServices.dadosDisciplinas();
		List<MenuItem> novaList = new ArrayList<>();
		
		for (String e: disiciplinas) { 
			MenuItem item = new MenuItem(e);
			item.setOnAction(event -> {
				menuButtonDescarte3.setText(e);
				novaList.remove(item);
			    valorItemDiscarte3 = e;
			});
			
			novaList.add(item);
		}
		return novaList;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		valorItemTurma1 = "";
		valorItemTurma2 = "";
		valorItemTurma3 = "";
		valorItemDiscarte1 = "";
		valorItemDiscarte2 = "";
		valorItemDiscarte3 = "";
		adicionarItens();
		
	}
}
