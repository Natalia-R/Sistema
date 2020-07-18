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

import entites.Curso;

public class CursoServices {
	
	public static List<String> cursoNomes(){
		List<String> curso = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File("arquivos/CursosCadastrados.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (dados.length> 1) {
					String novoCurso = dados[1];		
					curso.add(novoCurso);
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return curso;
	}
	
	public List<Curso> cursoCadastrados(){
		List<Curso> curso = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File("arquivos/CursosCadastrados.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (dados.length> 1) {
					Curso novoCurso = new Curso(dados[0], dados[1]);		
					curso.add(novoCurso);
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return curso;
	}
	
	
	public void removerCursoCadastrada(Curso obj) {
		Locale.setDefault(new Locale("pt", "BR"));
		List<Curso> list = cursoCadastrados();
		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(new File("arquivos/CursosCadastrados.txt").getCanonicalPath()))) {
			for (Curso elemento : list) {
				if (elemento.getCodigo().equals(obj.getCodigo()) && elemento.getNome().equals(obj.getNome())) {
					
				} else {
					String Cadastro = elemento.getCodigo() + "," + elemento.getNome();
					bw.write(Cadastro);
					bw.newLine();
				}
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
			e.getMessage();
		}
	}
	
	public List<Curso> buscarCursoNome(String nome) {
		List<Curso> buscarCurso = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(
				new FileReader(new File("arquivos/CursosCadastrados.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (nome.equals(dados[1])) {
					Curso novoCurso = new Curso(dados[0], dados[1]);
					buscarCurso.add(novoCurso);
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return buscarCurso;
	}
	
	public boolean cadastrarCurso(String codigo, String nome) {
		Locale.setDefault(new Locale("pt", "BR"));
		try (BufferedWriter bw = new BufferedWriter(
					new FileWriter(new File("arquivos/CursosCadastrados.txt").getCanonicalPath(), true))) {
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
