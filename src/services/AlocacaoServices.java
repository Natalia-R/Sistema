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
import java.util.Random;

import table.AlocacaoTable;
import table.ProfessorTable;
import table.TurmaTable;

public class AlocacaoServices {
	
	private List<AlocacaoTable> listAlocacao = new ArrayList<>();
	
	private List<AlocacaoTable> alocacaoFinal = new ArrayList<>();
	
	public AlocacaoServices() {
		
	}
	
	public List<AlocacaoTable> getListAlocacao() {
		return listAlocacao;
	}


	public void setListAlocacao(List<AlocacaoTable> listAlocacao) {
		this.listAlocacao = listAlocacao;
	}


	public boolean repetido(String turmaItem, String professorItem) {
		String codigo[] = turmaItem.split(" ");
		String registro[] = professorItem.split(" ");
		if (listAlocacao.size() != 0) {
			for (AlocacaoTable turma : listAlocacao) {
				if (codigo[0].equals(turma.getCodigoTurma())) { 
					if(registro[0].equals(turma.getRegistro())) {
						return true;
					}
					
				}
			}
		}
		return false;
	}
	
	public List<AlocacaoTable> adicionarTurmaProfessor(ProfessorTable professorTable, TurmaTable turmaTable, String ano) {	
		AlocacaoTable alocacao = new AlocacaoTable(professorTable.getRegistro(), professorTable.getNome(), 
				turmaTable.getCodigo(), turmaTable.getDisciplina(), turmaTable.getCurso(), ano);
		
	    listAlocacao.add(alocacao);
		return listAlocacao;

	}
	
	public void removerAlocacaoFixos(AlocacaoTable alocacao) {
		listAlocacao.remove(alocacao);
	}
	
	
	public  List<AlocacaoTable> realizarAlocacao(List<ProfessorTable> prof, List<TurmaTable> turma, String ano, List<AlocacaoTable> fixos ) {
		List<AlocacaoTable> lista = new ArrayList<>(); 
		Integer repetido = 0;
		Integer turmaMenor = turma.size();
		Integer profMenor = prof.size();
		if(fixos.size() > 0) {
			for(AlocacaoTable a: fixos) {
			   	lista.add(a);
			}
		}
		if(prof.size() == turma.size()) {
			for(ProfessorTable p: prof) {
				AlocacaoTable aloc = new AlocacaoTable(p.getRegistro(), p.getNome(), turma.get(repetido).getCodigo(),
						turma.get(repetido).getDisciplina(), turma.get(repetido).getCurso(), ano);
				lista.add(aloc);
				repetido++;
			}
		}
		
		if(prof.size() > turma.size()) {
			Random gerador = new Random();
			for(ProfessorTable p: prof) {
				Integer tAleatorio =  gerador.nextInt(turmaMenor);
				AlocacaoTable aloc = new AlocacaoTable(p.getRegistro(), p.getNome(), turma.get(tAleatorio).getCodigo(),
						turma.get(tAleatorio).getDisciplina(), turma.get(tAleatorio).getCurso(), ano);
				lista.add(aloc);
			}
		}
		
		if(prof.size() < turma.size()) {
			Random gerador2 = new Random();
			for(TurmaTable t: turma) {
				Integer pAleatorio = gerador2.nextInt(profMenor);
				AlocacaoTable aloc = new AlocacaoTable(prof.get(pAleatorio).getRegistro(), prof.get(pAleatorio).getNome(), t.getCodigo(),
						t.getDisciplina(), t.getCurso(), ano);
				lista.add(aloc);
			}
		}
		this.alocacaoFinal = lista;
		return lista;
	}
	
	
	
	public void salavarResultado() {
		List<AlocacaoTable> list = this.alocacaoFinal;
		Locale.setDefault(new Locale("pt", "BR"));
		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(new File("arquivos/AlocacoesRealizadas.txt").getCanonicalPath(), true))) {
			for (AlocacaoTable a : list) {
				String novoCadastro = a.getRegistro() + "," + a.getNome() + "," + a.getCodigoTurma() + ","
						+  a.getDisciplina() + "," + a.getCurso() + "," + a.getAno();
				bw.newLine();
				bw.write(novoCadastro);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<AlocacaoTable> alocacoesCadastradas(){
		List<AlocacaoTable> alocacoes = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File("arquivos/AlocacoesRealizadas.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (dados.length> 1) {
					AlocacaoTable novaAlocacao = new AlocacaoTable(dados[0], dados[1],dados[2], dados[3], dados[4], dados[5]);		
					alocacoes.add(novaAlocacao);
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return alocacoes;
	}
	
	public static List<AlocacaoTable> alocacoesBuscaAno(String ano) {
		List<AlocacaoTable> alocacoes = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(
				new FileReader(new File("arquivos/AlocacoesRealizadas.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (dados.length > 1) {
					if (dados[5].equals(ano)) {
						AlocacaoTable novaAlocacao = new AlocacaoTable(dados[0], dados[1], dados[2], dados[3], dados[4],
								dados[5]);
						alocacoes.add(novaAlocacao);
					}
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return alocacoes;
	}
}


