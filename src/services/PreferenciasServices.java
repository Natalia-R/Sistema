package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import gui.LoginViewController;
import table.PreferenciaTable;

public class PreferenciasServices {
		
	public static void salvarPreferencias(String turma1, String turma2, String turma3, String disciplina1,
			String disciplina2, String disciplina3) {
        String nomeLogin = LoginViewController.getUsuario(); 
		String registro = ProfessorServices.buscarProfessorLogin(nomeLogin);
		Locale.setDefault(new Locale("pt", "BR"));

		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(new File("arquivos/PreferenciasCadastradas.txt").getCanonicalPath(), true))) { 
				String novoCadastro = registro + "," + nomeLogin +  "," + turma1 + "," + turma2 + "," + turma3 + ","+ disciplina1 + "," + disciplina2 + ","+ disciplina3;
				bw.write(novoCadastro);
				bw.newLine();
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static List<PreferenciaTable> PreferenciasDoProfessor(){
		String nomeLogin = LoginViewController.getUsuario(); 
		List<PreferenciaTable> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File("arquivos/PreferenciasCadastradas.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (nomeLogin.equals(dados[1])) {
					String disciplina1[] = dados[5].split(" - ");
					String disciplina2[] = dados[6].split(" - ");
					String disciplina3[] = dados[7].split(" - ");
					PreferenciaTable preferencia = new PreferenciaTable(nomeLogin, dados[0], dados[2], dados[3], dados[4], 
							disciplina1[1], disciplina2[1], disciplina3[1]);
					list.add(preferencia);
				}

				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}
	
	public static void removerPreferencia(PreferenciaTable obj) {
		Locale.setDefault(new Locale("pt", "BR"));
		List<PreferenciaTable> list = PreferenciasDoProfessor();
		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(new File("arquivos/PreferenciasCadastradas.txt").getCanonicalPath()))) {
			for (PreferenciaTable elemento : list) {
				if (elemento.getRegistroProfessor().equals(obj.getRegistroProfessor())
						&& elemento.getNomeProfessor().equals(obj.getNomeProfessor())
						&& elemento.getTurma1().equals(obj.getTurma1()) 
						&& elemento.getTurma2().equals(obj.getTurma2())
						&& elemento.getTurma3().equals(obj.getTurma3())
						&& elemento.getDescarte1().equals(obj.getDescarte1())
						&& elemento.getDescarte2().equals(obj.getDescarte2())
						&& elemento.getDescarte3().equals(obj.getDescarte3())) {
				} else {
					String disciplina1 = DisciplinaServices.codigoDisciplina(elemento.getDescarte1()) + " - " + elemento.getDescarte1();
					String disciplina2 = DisciplinaServices.codigoDisciplina(elemento.getDescarte2()) + " - " + elemento.getDescarte2();
					String disciplina3 = DisciplinaServices.codigoDisciplina(elemento.getDescarte3()) + " - " + elemento.getDescarte3();
					String Cadastro = elemento.getRegistroProfessor() + "," + elemento.getNomeProfessor() + ","
							+ elemento.getTurma1() + "," + elemento.getTurma2() + "," + elemento.getTurma3() + ","
							+ disciplina1 + "," + disciplina2 + "," + disciplina3;
					bw.write(Cadastro);
					bw.newLine();
				}
			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
