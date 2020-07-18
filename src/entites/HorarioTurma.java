package entites;

public class HorarioTurma {
	private int id;

	private Horario horario;

	private Turma turma;

	private String codigoTurma;

	private String nomeDia;
	
	private String aula;
	
	public HorarioTurma() {}
	
	
	public HorarioTurma(String codigoTurma, String nomeDia, String aula) {
		this.codigoTurma = codigoTurma;
		this.nomeDia = nomeDia;
		this.aula = aula;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
		this.nomeDia = horario.getNomeDia();
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
		this.codigoTurma = turma.getCodigo();
	}

	public String getCodigoTurma() {
		return codigoTurma;
	}

	public void setCodigoTurma(String codigoTurma) {
		this.codigoTurma = codigoTurma;
	}

	public String getNomeDia() {
		return nomeDia;
	}

	public void setNomeDia(String nomeDia) {
		this.nomeDia = nomeDia;
	}

	public String getAula() {
		return aula;
	}
	
	@Override
	public int hashCode() {
		int hash = 3;
		hash = 41 * hash + this.id;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final HorarioTurma other = (HorarioTurma) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}


	
}
