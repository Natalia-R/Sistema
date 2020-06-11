package table;

public class PreferenciaTable {
	private String nomeProfessor;

	private String registroProfessor;

	private String turma1;

	private String turma2;

	private String turma3;

	private String descarte1;

	private String descarte2;
	
	private String descarte3;

	public PreferenciaTable(String nomeProfessor, String registroProfessor, String turma1, String turma2, String turma3,
			String descarte1, String descarte2, String descarte3) {
		this.nomeProfessor = nomeProfessor;
		this.registroProfessor = registroProfessor;
		this.turma1 = turma1;
		this.turma2 = turma2;
		this.turma3 = turma3;
		this.descarte1 = descarte1;
		this.descarte2 = descarte2;
		this.descarte3 = descarte3;
	}

	public String getNomeProfessor() {
		return nomeProfessor;
	}

	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}

	public String getRegistroProfessor() {
		return registroProfessor;
	}

	public void setRegistroProfessor(String registroProfessor) {
		this.registroProfessor = registroProfessor;
	}

	public String getTurma1() {
		return turma1;
	}

	public void setTurma1(String turma1) {
		this.turma1 = turma1;
	}

	public String getTurma2() {
		return turma2;
	}

	public void setTurma2(String turma2) {
		this.turma2 = turma2;
	}

	public String getTurma3() {
		return turma3;
	}

	public void setTurma3(String turma3) {
		this.turma3 = turma3;
	}

	public String getDescarte1() {
		return descarte1;
	}

	public void setDescarte1(String descarte1) {
		this.descarte1 = descarte1;
	}

	public String getDescarte2() {
		return descarte2;
	}

	public void setDescarte2(String descarte2) {
		this.descarte2 = descarte2;
	}

	public String getDescarte3() {
		return descarte3;
	}

	public void setDescarte3(String descarte3) {
		this.descarte3 = descarte3;
	}
	
	

}
