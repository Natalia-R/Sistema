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
	
	public List<Disciplina> disciplinasCadastradas(){
		List<Disciplina> disciplinas = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File("arquivos/DisciplinasCadastradas.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (dados.length> 1) {
					Disciplina dis = new Disciplina(dados[0], dados[1]);		
					disciplinas.add(dis);
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return disciplinas;
	}
		
	public void removerDisciplinaCadastrada(Disciplina obj) {
		Locale.setDefault(new Locale("pt", "BR"));
		List<Disciplina> list = disciplinasCadastradas();
		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(new File("arquivos/DisciplinasCadastradas.txt").getCanonicalPath()))) {
			for (Disciplina elemento : list) {
				if (elemento.getCodigo().equals(obj.getCodigo()) && elemento.getNome().equals(obj.getNome())) {
					
				} else {
					String Cadastro = elemento.getCodigo() + "," + elemento.getNome();
					bw.write(Cadastro);
					bw.newLine();
				}
			}
		}
		catch (IOException e) {
			e.getMessage();
		}
	}
	
	public List<Disciplina> buscarDisciplinaNome(String nome) {
		List<Disciplina> buscaDisc = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(
				new FileReader(new File("arquivos/DisciplinasCadastradas.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (nome.equals(dados[1])) {
                    Disciplina disc = new Disciplina(dados[0], dados[1]);
					buscaDisc.add(disc);
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return buscaDisc;
	}

	public boolean cadastrarDisciplina(String codigo, String nome) {
		Locale.setDefault(new Locale("pt", "BR"));
			try (BufferedWriter bw = new BufferedWriter(
					new FileWriter(new File("arquivos/DisciplinasCadastradas.txt").getCanonicalPath(), true))) {
					String novoCadastro = codigo + "," + nome;
					bw.newLine();
					bw.write(novoCadastro);
					return true;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		return false;
	}
	
}

