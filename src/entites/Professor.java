package entites;

public class Professor {

	private String nome;
	private String registro;
	private Integer cargaMaxS;
	private Integer cargaMinS;
	private Integer cargaMinA;
	private Integer cargaMaxA;
	//private boolean cargoAdm;
	private String cargoAdm;
	
	public Professor() {}
	
	public Professor( String registro, String nome, Integer cargaMaxS, Integer cargaMinS, Integer cargaMinA, Integer cargaMaxA,
			String cargoAdm) {
		this.nome = nome;
		this.registro = registro;
		this.cargaMaxS = cargaMaxS;
		this.cargaMinS = cargaMinS;
		this.cargaMinA = cargaMinA;
		this.cargaMaxA = cargaMaxA;
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

	public int getCargaMaxS() {
		return cargaMaxS;
	}

	public void setCargaMaxS(Integer cargaMaxS) {
		this.cargaMaxS = cargaMaxS;
	}

	public int getCargaMinS() {
		return cargaMinS;
	}

	public void setCargaMinS(Integer cargaMinS) {
		this.cargaMinS = cargaMinS;
	}

	public int getCargaMinA() {
		return cargaMinA;
	}

	public void setCargaMinA(Integer cargaMinA) {
		this.cargaMinA = cargaMinA;
		this.cargaMinS = cargaMinA / 32;
	}

	public int getCargaMaxA() {
		return cargaMaxA;
	}

	public void setCargaMaxA(Integer cargaMaxA) {
		this.cargaMaxA = cargaMaxA;
		this.cargaMaxS = cargaMaxA / 32;
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
		Professor other = (Professor) obj;
		if (registro == null) {
			if (other.registro != null)
				return false;
		} else if (!registro.equals(other.registro))
			return false;
		return true;
	}

	
}
