package table;

public class TurmaTable {
	private String codigo;
	private String disciplina;
	private String curso;
	
	public TurmaTable(){}
	
	public TurmaTable(String codigo, String disciplina, String curso) {
		this.codigo = codigo;
		this.disciplina = disciplina;
		this.curso = curso;
	}

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	
	

}
