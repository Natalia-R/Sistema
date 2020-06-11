package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import table.TurmaTable;

public class TurmaServices {

    private List<TurmaTable> listTable = new ArrayList<>();
    
    public TurmaServices() {
    	
    }
	    
	public TurmaServices(List<TurmaTable> listTable) {
		this.listTable = listTable;
	}
 	
	public List<TurmaTable> getListTable() {
		return listTable;
	}

	public void setListTable(List<TurmaTable> listTable) {
		this.listTable = listTable;
	}

	public boolean repetido(String item) {
		String codigo[] = item.split(" ");
		if (listTable.size() != 0) {
			for (TurmaTable turma : listTable) {
				if (codigo[0].equals(turma.getCodigo())) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	public List<TurmaTable> adicionarTurma(String item) {
		String codigo[] = item.split(" ");
		try (BufferedReader br = new BufferedReader(new FileReader((new File("arquivos/TurmasCadastradas.txt").getCanonicalPath())))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				line = br.readLine();
				if (codigo[0].equals(dados[0])) {

					TurmaTable turma = new TurmaTable(dados[0], dados[6], dados[5]);
					listTable.add(turma);
					return listTable;

				}

			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		TurmaTable turma = new TurmaTable("", "", "");
		listTable.add(turma);
		return listTable;

	}
	
	public TurmaTable dadosTurma(String item) {
		String codigo[] = item.split(" ");
		try (BufferedReader br = new BufferedReader(new FileReader((new File("arquivos/TurmasCadastradas.txt").getCanonicalPath())))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				line = br.readLine();
				if (codigo[0].equals(dados[0])) {
					TurmaTable turma = new TurmaTable(dados[0], dados[6], dados[5]);	
					return turma;

				}

			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		TurmaTable turma = new TurmaTable("", "", "");
		return turma;

	}
	
	public List<String> turmasCadastradasList(){
		List<String> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File("arquivos/TurmasCadastradas.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (!dados[0].equals("")) {
					String novoDado = dados[0] + " - " + dados[6];
					list.add(novoDado);
				}
				
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}
	public void removerTurma(TurmaTable turma) {

		listTable.remove(turma);

	}
  

}
