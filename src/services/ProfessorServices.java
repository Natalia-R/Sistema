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

import entites.Professor;
import table.ProfessorTable;

public class ProfessorServices {
	private String mensagem;
    private List<ProfessorTable> listTable = new ArrayList<>();
    private List<Professor> listTableProfessoresCadastrados = new ArrayList<>();
  
    public ProfessorServices() {
    }
	    
	public String getMensagem() {
		return mensagem;
	}

	public ProfessorServices(List<ProfessorTable> listTable) {
		this.listTable = listTable;
	}
 	
	public List<ProfessorTable> getListTable() {
		return listTable;
	}

	
    public List<Professor> getListTableProfessoresCadastrados() {
		return listTableProfessoresCadastrados;
	}

	public void adicionarProfessoresCadastrados() {
		try (BufferedReader br = new BufferedReader(
			new FileReader(new File("arquivos/ProfessoresCadastrados.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (dados.length > 1) {
					if (dados[6].equals("true")) {
						Professor professor =
								new Professor(dados[0], dados[1], Integer.parseInt(dados[5]),Integer.parseInt(dados[4]), 
										Integer.parseInt(dados[2]), Integer.parseInt(dados[3]),"ADM");
						this.listTableProfessoresCadastrados.add(professor);
					} else {
						Professor professor =
						new Professor(dados[0], dados[1], Integer.parseInt(dados[5]),Integer.parseInt(dados[4]), 
								Integer.parseInt(dados[2]), Integer.parseInt(dados[3])," ");
						
						this.listTableProfessoresCadastrados.add(professor);
						
					}
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	public List<ProfessorTable> adicionarProfessorTable(String item) {	
		String registro[] = item.split("-");
		try (BufferedReader br = new BufferedReader(new FileReader(new File("arquivos/ProfessoresCadastrados.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				line = br.readLine();		
				if (registro[0].equals(dados[0])) {
					if(dados[6].equals("true")){
						ProfessorTable prof = new ProfessorTable(dados[0], dados[1], Integer.parseInt(dados[2]),
								Integer.parseInt(dados[3]), "ADM"); 
						listTable.add(prof);
						return listTable;
					}
					else {
						ProfessorTable prof = new ProfessorTable(dados[0], dados[1], Integer.parseInt(dados[2]),
								Integer.parseInt(dados[3]), " ");
						listTable.add(prof);
						return listTable;
						
					}
					
				}
				
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		ProfessorTable prof = new ProfessorTable("000", "0000", 10, 10, "true"); 
		listTable.add(prof);
		return listTable;
	}
	
	public boolean itemRepetido(String item) {
		String registro[] = item.split("-");
		if (listTable.size() != 0) {
			for (ProfessorTable prof : listTable) {
				if (registro[0].equals(prof.getRegistro())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public ProfessorTable dadosProfessor(String item) {	
		String registro[] = item.split("-");
		try (BufferedReader br = new BufferedReader(new FileReader(new File("arquivos/ProfessoresCadastrados.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				line = br.readLine();
				if (registro[0].equals(dados[0])) {
					if(dados[6].equals("true")){
						ProfessorTable prof = new ProfessorTable(dados[0], dados[1], Integer.parseInt(dados[2]),
								Integer.parseInt(dados[3]), "ADM"); 
						return prof;
					}
					else {
						ProfessorTable prof = new ProfessorTable(dados[0], dados[1], Integer.parseInt(dados[2]),
								Integer.parseInt(dados[3]), " ");
						return prof;
						
					}
					
				}
				
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		ProfessorTable prof = new ProfessorTable("000", "0000", 10, 10, "true"); 		
		return prof;

	}
 	
	public void removerProfessor(ProfessorTable prof) {
		listTable.remove(prof);
	}
	
	public void removerProfessorCadastradoLista(Professor prof) {
		listTableProfessoresCadastrados.remove(prof);
		removerProfessorCadastrado(listTableProfessoresCadastrados);
	}
	
	public static String buscarProfessorLogin(String login) {
		try (BufferedReader br = new BufferedReader(new FileReader(new File("arquivos/Login.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (login.equals(dados[1])) {
					return dados[0];
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return "0000";
	}
	
	public static List<String> professoresCadastradosLista(){
		List<String> list = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File("arquivos/ProfessoresCadastrados.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (!dados[0].equals("")) {
					String professor = dados[0] + "-" + dados[1];
					list.add(professor);
				}
				line = br.readLine();		
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public boolean cadastrarProfessor(String registro, String nome, String cargaMaxA, String cargaMinA, boolean cargoAdm) {
		Locale.setDefault(new Locale("pt", "BR"));
		//if (!professorCadastrado(registro)) {
			try (BufferedWriter bw = new BufferedWriter(
					new FileWriter(new File("arquivos/ProfessoresCadastrados.txt").getCanonicalPath(), true))) {
				Integer cargaMinS = Integer.parseInt(cargaMinA) / 32;
				Integer cargaMaxS = Integer.parseInt(cargaMaxA) / 32;
				if(cargaMinS < 1) cargaMinS =1;
				if(cargaMaxS < 1) cargaMaxS =1;
				
				if (cargoAdm) {
					String novoCadastro = registro + "," + nome + "," + cargaMinA + "," + cargaMaxA + "," + cargaMinS
							+ "," + cargaMaxS + "," + "true";
					bw.newLine();
					bw.write(novoCadastro);
					this.mensagem = "Usuário cadastrado com sucesso";
					return true;
				} else {
					String novoCadastro = registro + "," + nome + "," + cargaMinA + "," + cargaMaxA + "," + cargaMinS
							+ "," + cargaMaxS + "," + "false";
					bw.newLine();
					bw.write(novoCadastro);
					this.mensagem = "Usuário cadastrado com sucesso";
					return true;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		//} else {
		//	this.mensagem = "Professor já cadastrado";
		//	return false;
	//	}
		return false;
	}

	public boolean professorCadastrado(String registro) {
		try (BufferedReader br = new BufferedReader(new FileReader(new File("arquivos/ProfessoresCadastrados.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (registro.equals(dados[0])) {
					return true;
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return false;
	}
	
	public static List<Professor> buscarProfessorNome(String nome) {
		List<Professor> buscaProf = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File("arquivos/ProfessoresCadastrados.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (nome.equals(dados[1])) {
					if (dados[6].contentEquals("true")) {
						Professor prof = new Professor(dados[0], dados[1], Integer.parseInt(dados[5]),Integer.parseInt(dados[4]), 
								Integer.parseInt(dados[2]), Integer.parseInt(dados[3]),"ADM");
						buscaProf.add(prof);
					}
					else {
						Professor prof = new Professor(dados[0], dados[1], Integer.parseInt(dados[5]),Integer.parseInt(dados[4]), 
								Integer.parseInt(dados[2]), Integer.parseInt(dados[3])," ");
						buscaProf.add(prof);
					}
				
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return buscaProf;
	}

	public void removerProfessorCadastrado(List<Professor> list) {
		Locale.setDefault(new Locale("pt", "BR"));

		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(new File("arquivos/ProfessoresCadastrados.txt").getCanonicalPath()))) {
			for (Professor p : list) {

				if (p.getCargoAdm().equals("true")) {
					String novoCadastro = p.getRegistro() + "," + p.getNome() + "," + p.getCargaMinA() + ","
							+ p.getCargaMinA() + "," + p.getCargaMinS() + "," + p.getCargaMaxS() + "," + "true";
					bw.write(novoCadastro);

				} else {
					String novoCadastro = p.getRegistro() + "," + p.getNome() + "," + p.getCargaMinA() + ","
							+ p.getCargaMinA() + "," + p.getCargaMinS() + "," + p.getCargaMaxS() + "," + "false";
					bw.write(novoCadastro);
					bw.newLine();

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// reescreve o arquivo retirando o professor q sera atualizado
	public void removerAtualizarProfessor(Professor obj) {
		Locale.setDefault(new Locale("pt", "BR"));
		adicionarProfessoresCadastrados();
		List<Professor> list = listTableProfessoresCadastrados;
		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(new File("arquivos/ProfessoresCadastrados.txt").getCanonicalPath()))) {
			for (Professor elemento : list) {
				if (elemento.getRegistro().equals(obj.getRegistro())
						&& elemento.getNome().equals(obj.getNome())) {
						
				} else {
					if(elemento.getCargoAdm() == "ADM") {
						String Cadastro = elemento.getRegistro() + "," + elemento.getNome() + ","
								+ elemento.getCargaMaxA() + "," + elemento.getCargaMinA() + ","
								+ elemento.getCargaMaxS() + "," + elemento.getCargaMinS() + "," + true;
						bw.write(Cadastro);
						bw.newLine();
					}
					else {
						String Cadastro = elemento.getRegistro() + "," + elemento.getNome() + ","
								+ elemento.getCargaMaxA() + "," + elemento.getCargaMinA() + ","
								+ elemento.getCargaMaxS() + "," + elemento.getCargaMinS() + "," + false;
						bw.write(Cadastro);
						bw.newLine();
					}
				}
			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
