package table;

public class AlocacaoTable {
	private String Registro;
	private String nome;
	private String codigoTurma;
	private String disciplina;
	private String curso;
	private String ano;
		
	public AlocacaoTable() {}


	public AlocacaoTable(String registro, String nome, String codigoTurma, String disciplina, String curso,
			String ano) {
		
		this.Registro = registro;
		this.nome = nome;
		this.codigoTurma = codigoTurma;
		this.disciplina = disciplina;
		this.curso = curso;
		this.ano = ano;
	}

	public String getRegistro() {
		return Registro;
	}

	public void setRegistro(String registro) {
		Registro = registro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigoTurma() {
		return codigoTurma;
	}

	public void setCodigoTurma(String codigoTurma) {
		this.codigoTurma = codigoTurma;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}
	
    
	
}
