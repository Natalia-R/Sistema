package entites;

import java.util.Collection;

public class Curso {
	private int id;
	private String codigo;
	private String nome;
	private Collection<Turma> turmas;
	
	public Curso() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
    public Collection<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(Collection<Turma> turmas) {
        this.turmas = turmas;
    }

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 13 * hash + this.id;
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
		final Curso other = (Curso) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}

}
