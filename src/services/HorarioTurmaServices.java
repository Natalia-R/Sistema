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

import entites.HorarioTurma;

public class HorarioTurmaServices {
	
	public List<HorarioTurma> horariosCadastrados(String codigoTurma) {
		List<HorarioTurma> horarioTurma = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(
				new FileReader(new File("arquivos/HorarioTurmaCadastradas.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (dados.length > 1) {
					if(dados[0].equals(codigoTurma)) {
						HorarioTurma novoHorarioTurma = new HorarioTurma(codigoTurma, dados[1], dados[2]);
						horarioTurma.add(novoHorarioTurma);
					}
					
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return horarioTurma;
	}
	
		
	public List<HorarioTurma> todoshorariosCadastrados() {
		List<HorarioTurma> horarioTurma = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(
				new FileReader(new File("arquivos/HorarioTurmaCadastradas.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (dados.length >= 2) {
					HorarioTurma novoHorarioTurma = new HorarioTurma(dados[0], dados[1], dados[2]);
					horarioTurma.add(novoHorarioTurma);
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return horarioTurma;
	}
	
	public void removerHorarioTurmaCadastrado(HorarioTurma obj) {
		Locale.setDefault(new Locale("pt", "BR"));
		List<HorarioTurma> list = todoshorariosCadastrados();
		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(new File("arquivos/HorarioTurmaCadastradas.txt").getCanonicalPath()))) {
			for (HorarioTurma elemento : list) {
				if (elemento.getCodigoTurma().equals(obj.getCodigoTurma()) && elemento.getNomeDia().equals(obj.getNomeDia())
						&& elemento.getAula().equals(obj.getAula())) {
					
				} else {
					String Cadastro = elemento.getCodigoTurma() + "," + elemento.getNomeDia() + "," + elemento.getAula();
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
	
	public List<HorarioTurma> horariosBuscaDia(String codigoTurma, String dia) {
		List<HorarioTurma> horarioTurma = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(
				new FileReader(new File("arquivos/HorarioTurmaCadastradas.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (dados.length > 1) {
					if(dados[0].equals(codigoTurma) && dados[1].equalsIgnoreCase(dia)) {
						HorarioTurma novoHorarioTurma = new HorarioTurma(codigoTurma, dados[1], dados[2]);
						horarioTurma.add(novoHorarioTurma);
					}
					
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return horarioTurma;
	}
	
	public boolean vincularHorarioTurma(String codigoTurma, String nomeDia, String aula) {
		Locale.setDefault(new Locale("pt", "BR"));
		try (BufferedWriter bw = new BufferedWriter(
					new FileWriter(new File("arquivos/HorarioTurmaCadastradas.txt").getCanonicalPath(), true))) {
			 String novoCadastro = codigoTurma + "," + nomeDia + "," + aula;
			 bw.newLine();
			 bw.write(novoCadastro);
			return true;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		return false;
	}
}
