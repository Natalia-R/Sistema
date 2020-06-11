package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import entites.Disciplina;

public class DisciplinaServices {
	private List<Disciplina> listDisciplinas;
	
	public List<Disciplina> getListDisciplinas() {
		return listDisciplinas;
	}

	public void setListDisciplinas(List<Disciplina> listDisciplinas) {
		this.listDisciplinas = listDisciplinas;
	}
	
	public static List<String> dadosDisciplinas(){
		List<String> listDados = new ArrayList<>();
		Locale.setDefault(new Locale("pt", "BR"));
		try (BufferedReader br = new BufferedReader(new FileReader(new File("arquivos/DisciplinasCadastradas.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (dados.length> 1) {
					String elemento = dados[0] + " - " + dados[1];
					listDados.add(elemento);
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return listDados;
	}
	public static String codigoDisciplina(String discarte){
		Locale.setDefault(new Locale("pt", "BR"));
		try (BufferedReader br = new BufferedReader(new FileReader(new File("arquivos/DisciplinasCadastradas.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (dados.length> 1) {
					if(discarte.equals(dados[1])) {
						return dados[0];
					}
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return " ";
		
	}
}

