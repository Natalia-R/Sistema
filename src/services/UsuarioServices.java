package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class UsuarioServices {
private String mensagem;
    
	public String getMensagem() {
		return mensagem;
	}
		
	public static boolean usuarioCadastrado(String registro) {
		try (BufferedReader br = new BufferedReader(new FileReader(new File("arquivos/Login.txt").getCanonicalPath()))) {
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
	
	public boolean cadastrarUsuario(String registro, String login, String senha) {
			Locale.setDefault(new Locale("pt", "BR"));
			String novoCadastro = registro + "," + login + "," + senha;
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("arquivos/Login.txt").getCanonicalPath(), true))) {
				bw.write(novoCadastro);
				bw.newLine();
				this.mensagem = "Usuário cadastrado com sucesso";
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return false;
	}
	
	public static String obterRegistro(String login, String senha) {
		String registro = "";
		try (BufferedReader br = new BufferedReader(new FileReader(new File("arquivos/Login.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (dados[1].equals(login) && dados[2].equals(senha)) {
					registro = dados[0];
					return registro;
				}

				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		return registro;
	}
	
	public static boolean validarLogin(String login, String senha) {
		try (BufferedReader br = new BufferedReader(new FileReader(new File("arquivos/Login.txt").getCanonicalPath()))) {
			String line = br.readLine();
			while (line != null) {
				String dados[] = line.split(",");
				if (dados.length > 1) {
					if (dados[1].equals(login) && dados[2].equals(senha)) {
						return true;
					}

					line = br.readLine();
				} 
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		return false;
	}


}
