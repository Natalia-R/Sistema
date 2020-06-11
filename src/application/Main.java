package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private static Stage stage;
	
	public static void main(String[] args) {
        launch(args);   
    }

	@Override
	public void start(Stage primaryStage) {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/gui/LoginView.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Sistema de apoio a tomada de decisão");
			primaryStage.setScene(scene);
			primaryStage.show();
			setStage(primaryStage);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mudarTela(String caminho) {
		try {
			Stage newStage;
			Parent root = FXMLLoader.load(getClass().getResource(caminho));
			newStage = Main.getStage();
			Scene scene = new Scene(root);
			newStage.setTitle("Sistema de apoio a tomada de decisão");
			newStage.setScene(scene);
			newStage.show();
			setStage(newStage);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void fecharTela() {
		getStage().close();
	}
	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		Main.stage = stage;
	}

}
