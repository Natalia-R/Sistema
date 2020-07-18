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

import entites.Turma;
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

	public void removerTurma(TurmaTable turma) {
		listTable.remove(turma);
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

	
	// lista para colocar na tabela de gerenciamento de turmas
	public List<Turma> turmasCadastradasTable(){
		List<Turma> turma = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File("arquivos/TurmasCadastradas.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (dados.length> 1) {
					if(dados[2].equals("true")) {
						Turma novaTurma = new Turma(dados[0], Integer.parseInt(dados[1]),true,Integer.parseInt(dados[3]), Integer.parseInt(dados[4]),
								dados[5], dados[6]);
						turma.add(novaTurma);
					}
					else {
						Turma novaTurma = new Turma(dados[0], Integer.parseInt(dados[1]),false,Integer.parseInt(dados[3]), Integer.parseInt(dados[4]),
								dados[5], dados[6]);
						turma.add(novaTurma);
					}	
					
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return turma;
	}
	
	public void removerTurmaCadastrada(Turma obj) {
		Locale.setDefault(new Locale("pt", "BR"));
		List<Turma> list = turmasCadastradasTable();
		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(new File("arquivos/TurmasCadastradas.txt").getCanonicalPath()))) {
			for (Turma elemento : list) {
				if (obj.getCodigo().equals(elemento.getCodigo()) && obj.getNomeDisciplina().equals(elemento.getNomeDisciplina()) && 
						elemento.getNomeCurso().equals(obj.getNomeCurso())) {
					
				} else {
					if(elemento.isAnual()) {
						String Cadastro = elemento.getCodigo() + "," + elemento.getSemestre() + "," + "true" +  "," + elemento.getCargaTotal() +
								"," + elemento.getCargaSemanal() + "," + elemento.getNomeCurso() + "," + elemento.getNomeDisciplina() + "," + "ht";
						bw.write(Cadastro);
						bw.newLine();
					}
					else {
						String Cadastro = elemento.getCodigo() + "," + elemento.getSemestre() + "," + "false" +  "," + elemento.getCargaTotal() +
								"," + elemento.getCargaSemanal() + "," + elemento.getNomeCurso() + "," + elemento.getNomeDisciplina() + "," + "ht";
						bw.write(Cadastro);
						bw.newLine();
					}
				
				}
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
			e.getMessage();
		}
	}
  
	//busca a os dados da table turma da funcao gerenciar turma a partir do codigo
	public List<Turma> buscarTurmaCodigo(String codigo) {
		List<Turma> buscarTurma = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(
				new FileReader(new File("arquivos/TurmasCadastradas.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (codigo.equals(dados[0])) {
					if(dados[2].equals("true")) {
						Turma novaTurma = new Turma(dados[0], Integer.parseInt(dados[1]),true,Integer.parseInt(dados[3]), Integer.parseInt(dados[4]),
								dados[5], dados[6]);
						buscarTurma.add(novaTurma);
					}
					else {
						Turma novaTurma = new Turma(dados[0], Integer.parseInt(dados[1]),false,Integer.parseInt(dados[3]), Integer.parseInt(dados[4]),
								dados[5], dados[6]);
						buscarTurma.add(novaTurma);
					}
						
					
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return buscarTurma;
	}
    
	public boolean cadastrarTurma(String codigo, String nomeDisciplina, String curso, String cargaTotal, String anual, String semestre) {
		Locale.setDefault(new Locale("pt", "BR"));
		try (BufferedWriter bw = new BufferedWriter(
					new FileWriter(new File("arquivos/TurmasCadastradas.txt").getCanonicalPath(), true))) {
			 String novoCadastro = codigo + "," + semestre + "," + anual + "," + cargaTotal + "," + "10" + "," + curso + "," 
					+ nomeDisciplina + "," + "ht";
			 bw.newLine();
			 bw.write(novoCadastro);
			return true;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		return false;
	}

}
