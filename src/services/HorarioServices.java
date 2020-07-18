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

import entites.Horario;

public class HorarioServices {
	
	private Integer id;
	
	public HorarioServices() {
		this.id = 0;
	}
	
	public List<Horario> horariosCadastrados() {
		List<Horario> horario = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(
				new FileReader(new File("arquivos/HorariosCadastrados.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (dados.length > 1) {
					Integer idInteiro = Integer.parseInt(dados[0]);
					if(this.id < idInteiro) {this.id = idInteiro;}
					Horario novoHorario = new Horario(Integer.parseInt(dados[0]), dados[1], dados[2]);
					horario.add(novoHorario);
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return horario;
	}
	
	public void removerHorarioCadastrada(Horario obj) {
		Locale.setDefault(new Locale("pt", "BR"));
		List<Horario> list = horariosCadastrados();
		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(new File("arquivos/HorariosCadastrados.txt").getCanonicalPath()))) {
			for (Horario elemento : list) {
				if (elemento.getId().equals(obj.getId()) && elemento.getNomeDia().equals(obj.getNomeDia()) && elemento.getAula().equals(obj.getAula())) {
					
				} else {
					String Cadastro = elemento.getId() + "," + elemento.getNomeDia() + "," + elemento.getAula();
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
	
	// remove o horario com base no id
	public void removerHorarioCadastradaId(String id) {
		Locale.setDefault(new Locale("pt", "BR"));
		List<Horario> list = horariosCadastrados();
		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(new File("arquivos/HorariosCadastrados.txt").getCanonicalPath()))) {
			for (Horario elemento : list) {
				String idCadastrado = "" + elemento.getId();
				if (id.equals(idCadastrado)) {
				} else {
					String Cadastro = elemento.getId() + "," + elemento.getNomeDia() + "," + elemento.getAula();
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
	
	public List<Horario> buscarCursoDia(String dia) {
		List<Horario> buscarHorario = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(
				new FileReader(new File("arquivos/HorariosCadastrados.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (dia.equals(dados[1])) {
					Horario novoHorario = new Horario(Integer.parseInt(dados[0]), dados[1], dados[2]);
					buscarHorario.add(novoHorario);
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return buscarHorario;
	}
	
	public boolean cadastrarHorario(String dia, String aula) {
		horariosCadastrados();
		Locale.setDefault(new Locale("pt", "BR"));
		Horario horario = new Horario();
		try (BufferedWriter bw = new BufferedWriter(
					new FileWriter(new File("arquivos/HorariosCadastrados.txt").getCanonicalPath(), true))) {
			 String diaSemana = horario.setNomeDia(dia);
			 Integer idSoma = this.id +1;
			 String idString = "" + idSoma;
			 String novoCadastro = idString + "," + diaSemana + "," + aula;
			 bw.newLine();
			 bw.write(novoCadastro);
			return true;
				
			} catch (IOException e) {
				e.getMessage();
			}
		return false;
	}
	
	
	public boolean atualizarHorario(String dia, String aula, String id) {
		Locale.setDefault(new Locale("pt", "BR"));
		Horario horario = new Horario();
		try (BufferedWriter bw = new BufferedWriter(
					new FileWriter(new File("arquivos/HorariosCadastrados.txt").getCanonicalPath(), true))) {
			 String diaSemana = horario.setNomeDia(dia);
			 String novoCadastro = id + "," + diaSemana + "," + aula;
			 bw.newLine();
			 bw.write(novoCadastro);
			return true;
				
			} catch (IOException e) {
				e.getMessage();
			}
		return false;
	}

}
