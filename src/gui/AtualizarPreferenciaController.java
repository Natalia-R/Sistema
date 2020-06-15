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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import services.DisciplinaServices;
import services.PreferenciasServices;
import services.TurmaServices;
import table.PreferenciaTable;

public class AtualizarPreferenciaController implements Initializable{
	@FXML
	private Button buttonHelp;

	@FXML
	private Button buttonVoltar;
	
	@FXML
	private Button Atualizar;
	
	@FXML
	private Button buttonExcluir;
	
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
	private PreferenciaTable linha = GerenciarPreferenciasController.getLinha();
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
          "*OBS: Uma disciplina descarte só será entregue ao repectivo professor em casos extremos.\n");
         dialogoInfo.showAndWait();
	}

	@FXML
	public void onButtonExcluir() {
	    Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btnSim = new ButtonType("Sim");
        ButtonType btnNao = new ButtonType("Não");

        dialogoExe.setTitle(null);
        dialogoExe.setHeaderText("Confirmação");
        dialogoExe.setContentText("Esta ação não poderá ser desfeita, deseja continuar?");
        dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
        dialogoExe.showAndWait().ifPresent(b -> {
            if (b == btnSim) {
            	PreferenciasServices.removerPreferencia(linha);
            	main.mudarTela("/gui/GerenciarPreferenciasView.fxml");
            } else if (b == btnNao) {
            } 
        });
	}

	public void onButtonEditar() {
		labelErro.setText("");
			if (!valorItemTurma1.equals(valorItemTurma2) && !valorItemTurma1.equals(valorItemTurma3)
					&& !valorItemTurma2.equals(valorItemTurma3) && !valorItemDiscarte1.equals(valorItemDiscarte2)
					&& !valorItemDiscarte1.equals(valorItemDiscarte3)
					&& !valorItemDiscarte2.equals(valorItemDiscarte3)) {
 				PreferenciasServices.removerPreferencia(linha);
 				PreferenciasServices.salvarPreferencias(valorItemTurma1, valorItemTurma2, valorItemTurma3,
						valorItemDiscarte1, valorItemDiscarte2, valorItemDiscarte3);
				main.mudarTela("/gui/GerenciarPreferenciasView.fxml");
				
			}
			else {
				labelErro.setText("HÁ TURMAS OU DESCARTES IGUAIS");
			}
	}
	
	@FXML
	public void adicionarItens() {
		List<MenuItem> listTurma1 = converterListaTurma1();
		menuButtonTurma1.getItems().addAll(listTurma1);
		menuButtonTurma1.setText(linha.getTurma1());

		List<MenuItem> listTurma2 = converterListaTurma2();
		menuButtonTurma2.getItems().addAll(listTurma2);
		menuButtonTurma2.setText(linha.getTurma2());
		
		List<MenuItem> listTurma3 = converterListaTurma3();
		menuButtonTurma3.getItems().addAll(listTurma3);
		menuButtonTurma3.setText(linha.getTurma3());
		
		List<MenuItem> listDiscarte1 = converterListaDisciplina1();
		menuButtonDescarte1.getItems().addAll(listDiscarte1);
		menuButtonDescarte1.setText(valorItemDiscarte1);
		
		List<MenuItem> listDiscarte2 = converterListaDisciplina2();
		menuButtonDescarte2.getItems().addAll(listDiscarte2);
		menuButtonDescarte2.setText(valorItemDiscarte2);
	
		
		List<MenuItem> listDiscarte3 = converterListaDisciplina3();
		menuButtonDescarte3.getItems().addAll(listDiscarte3);
		menuButtonDescarte3.setText(valorItemDiscarte3);
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
		valorItemTurma1 = linha.getTurma1() ;
		valorItemTurma2 = linha.getTurma2();
		valorItemTurma3 = linha.getTurma3();
		
		String disciplina = DisciplinaServices.codigoDisciplina(linha.getDescarte1());
		valorItemDiscarte1 = disciplina + " - " + linha.getDescarte1();
		disciplina = DisciplinaServices.codigoDisciplina(linha.getDescarte2());
		valorItemDiscarte2 = disciplina + " - " + linha.getDescarte2();
		disciplina = DisciplinaServices.codigoDisciplina(linha.getDescarte3());
		valorItemDiscarte3 = disciplina + " - " + linha.getDescarte3();
		adicionarItens();
		
	}

}
