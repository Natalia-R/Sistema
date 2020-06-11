package services;

import java.util.ArrayList;
import java.util.List;

import table.AlocacaoTable;
import table.ProfessorTable;
import table.TurmaTable;

public class AlocacaoServices {
	
	private List<AlocacaoTable> listAlocacao = new ArrayList<>();
	
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
	
	
}
