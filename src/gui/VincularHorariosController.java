package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import entites.Turma;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import services.HorarioTurmaServices;

public class VincularHorariosController implements Initializable {
	   @FXML 
	   private Button buttonVoltar;
	   
	   @FXML 
	   private Button buttonVincular;

	   @FXML
	   private MenuButton menuButtonSegunda;
	   
	   @FXML
	   private MenuButton menuButtonTerca;
	   
	   @FXML
	   private MenuButton menuButtonQuarta;
	   
	   @FXML
	   private MenuButton menuButtonQuinta;
	   
	   @FXML
	   private MenuButton menuButtonSexta;
	   
	   @FXML
	   private MenuButton menuButtonSabado;
		   
	   @FXML 
	   private Label labelErro;
	   
	   private static Main main = new Main();
	   
	   private String valorItemSegunda;
	   
	   private String valorItemTerca;
	   
	   private String valorItemQuarta;
	   
	   private String valorItemQuinta;
	   
       private String valorItemSexta;
	   
	   private String valorItemSabado;
	   
	   private Turma dadosTurma = GerenciarTurmaController.getLinhaHorario();
	   
	   
	@FXML
	public void onButtonVoltar() {
		main.mudarTela("/gui/GerenciarHorarioTurmaView.fxml");
	}

	@FXML
	public void onButtonVincular() {
		HorarioTurmaServices turmaServices = new HorarioTurmaServices();
		if (valorItemSegunda != null) {
			turmaServices.vincularHorarioTurma(dadosTurma.getCodigo(), "Segunda-feira", valorItemSegunda);
		}
		if (valorItemTerca != null) {
			turmaServices.vincularHorarioTurma(dadosTurma.getCodigo(), "Terca-feira", valorItemTerca);
		}
		if (valorItemQuarta != null) {
			turmaServices.vincularHorarioTurma(dadosTurma.getCodigo(), "Quarta-feira", valorItemQuarta);
		}
		if (valorItemQuinta != null) {
			turmaServices.vincularHorarioTurma(dadosTurma.getCodigo(), "Quinta-feira", valorItemQuinta);
		}
		if (valorItemSexta != null) {
			turmaServices.vincularHorarioTurma(dadosTurma.getCodigo(), "Sexta-feira", valorItemSexta);
		}
		if (valorItemSabado != null) {
			turmaServices.vincularHorarioTurma(dadosTurma.getCodigo(), "Sabado", valorItemSabado);
		}
		main.mudarTela("/gui/GerenciarHorarioTurmaView.fxml");

	}

	@FXML
	public List<MenuItem> converterListaSegunda() {
		List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");
		List<MenuItem> novaList = new ArrayList<>();
		for (String e : list) {
			MenuItem item = new MenuItem(e);
			item.setOnAction(event -> {
				menuButtonSegunda.setText(e);
				novaList.remove(item);
				this.valorItemSegunda = e;
			});
			novaList.add(item);
		}
		return novaList;
	}

	@FXML
	public List<MenuItem> converterListaTerca() {
		List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");
		List<MenuItem> novaList = new ArrayList<>();
		for (String e : list) {
			MenuItem item = new MenuItem(e);
			item.setOnAction(event -> {
				menuButtonTerca.setText(e);
				novaList.remove(item);
				this.valorItemTerca = e;
			});
			novaList.add(item);
		}
		return novaList;
	}

	@FXML
	public List<MenuItem> converterListaQuarta() {
		List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");
		List<MenuItem> novaList = new ArrayList<>();
		for (String e : list) {
			MenuItem item = new MenuItem(e);
			item.setOnAction(event -> {
				menuButtonQuarta.setText(e);
				novaList.remove(item);
				this.valorItemQuarta = e;
			});
			novaList.add(item);
		}
		return novaList;
	}

	@FXML
	public List<MenuItem> converterListaQuinta() {
		List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");
		List<MenuItem> novaList = new ArrayList<>();
		for (String e : list) {
			MenuItem item = new MenuItem(e);
			item.setOnAction(event -> {
				menuButtonQuinta.setText(e);
				novaList.remove(item);
				this.valorItemQuinta = e;
			});
			novaList.add(item);
		}
		return novaList;
	}

	@FXML
	public List<MenuItem> converterListaSexta() {
		List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");
		List<MenuItem> novaList = new ArrayList<>();
		for (String e : list) {
			MenuItem item = new MenuItem(e);
			item.setOnAction(event -> {
				menuButtonSexta.setText(e);
				novaList.remove(item);
				this.valorItemSexta = e;
			});
			novaList.add(item);
		}
		return novaList;
	}

	@FXML
	public List<MenuItem> converterListaSabado() {
		List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8");
		List<MenuItem> novaList = new ArrayList<>();
		for (String e : list) {
			MenuItem item = new MenuItem(e);
			item.setOnAction(event -> {
				menuButtonSabado.setText(e);
				novaList.remove(item);
				this.valorItemSabado = e;
			});
			novaList.add(item);
		}
		return novaList;
	}

	@FXML
	public void adicionarItens() {
		List<MenuItem> list = converterListaSegunda();
		menuButtonSegunda.getItems().addAll(list);

		List<MenuItem> list2 = converterListaTerca();
		menuButtonTerca.getItems().addAll(list2);

		List<MenuItem> list3 = converterListaQuarta();
		menuButtonQuarta.getItems().addAll(list3);

		List<MenuItem> list4 = converterListaQuinta();
		menuButtonQuinta.getItems().addAll(list4);

		List<MenuItem> list5 = converterListaSexta();
		menuButtonSexta.getItems().addAll(list5);

		List<MenuItem> list6 = converterListaSabado();
		menuButtonSabado.getItems().addAll(list6);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		adicionarItens();
	}

}
