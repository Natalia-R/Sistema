package table;

public class ProfessorTable {
	private String registro;
	private String nome;
	private int cargaMaxA;
	private int cargaMinA;
	private String cargoAdm;
	

	public ProfessorTable() {
		
	}

	public ProfessorTable( String registro, String nome, int cargaMaxA, int cargaMinA, String cargoAdm) {
		this.nome = nome;
		this.registro = registro;
		this.cargaMaxA = cargaMaxA;
		this.cargaMinA = cargaMinA;
		this.cargoAdm = cargoAdm;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public int getCargaMaxA() {
		return cargaMaxA;
	}

	public void setCargaMaxA(int cargaMaxA) {
		this.cargaMaxA = cargaMaxA;
	}

	public int getCargaMinA() {
		return cargaMinA;
	}

	public void setCargaMinA(int cargaMinA) {
		this.cargaMinA = cargaMinA;
	}

	public String getCargoAdm() {
		return cargoAdm;
	}

	public void setCargoAdm(String cargoAdm) {
		this.cargoAdm = cargoAdm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((registro == null) ? 0 : registro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfessorTable other = (ProfessorTable) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (registro == null) {
			if (other.registro != null)
				return false;
		} else if (!registro.equals(other.registro))
			return false;
		return true;
	}
	
	

}