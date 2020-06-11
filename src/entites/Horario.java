package entites;

public class Horario {
	private Integer id;
	private Integer dia;
	private String nomeDia;
	private String aula;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public String getNomeDia() {
		return nomeDia;
	}

	public void setNomeDia() {
		if (this.dia == 2) {
			this.nomeDia = "Segunda-feira";
		} else if (this.dia == 3) {
			this.nomeDia = "Terca-feira";
		} else if (this.dia == 4) {
			this.nomeDia = "Quarta-feira";
		} else if (this.dia == 5) {
			this.nomeDia = "Quinta-feira";
		} else if (this.dia == 6) {
			this.nomeDia = "Sexta-feira";
		} else if (this.dia == 7) {
			this.nomeDia = "Sabado";
		} else if (this.dia == 0) {
			this.nomeDia = null;
		}
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 47 * hash + this.id;
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
		final Horario other = (Horario) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}

}
