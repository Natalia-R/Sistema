package entites;

import java.util.Collection;

public class Turma {   
	    private int id;
	    
	    private String codigo;

	    private Integer semestre;

	    private boolean anual;

	    private Integer cargaTotal;

	    private Integer cargaSemanal;

	    private Curso curso;
	    
	    private String nomeCurso;

	    private Disciplina disciplina;
	    
	    private String nomeDisciplina;

	    private Collection<HorarioTurma> ht;
	    
	    public Turma() {}
	     
	    
	    public Turma(String codigo, Integer semestre, boolean anual, Integer cargaTotal, Integer cargaSemanal, String nomeCurso,
				String nomeDisciplina) {
			this.codigo = codigo;
			this.semestre = semestre;
			this.anual = anual;
			this.cargaTotal = cargaTotal;
			this.cargaSemanal = cargaSemanal;
			this.nomeCurso = nomeCurso;
			this.nomeDisciplina = nomeDisciplina;
		}

		public Collection<HorarioTurma> getHt() {
	        return ht;
	    }

	    public void setHt(Collection<HorarioTurma> ht) {
	        this.ht = ht;
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public Curso getCurso() {
	        return curso;
	    }

	    public void setCurso(Curso curso) {
	        this.curso = curso;
	    }

	    public String getCodigo() {
	        return codigo;
	    }

	    public void setCodigo(String codigo) {
	        this.codigo = codigo;
	    }

	    public Disciplina getDisciplina() {
	        return disciplina;
	    }

	    public void setDisciplina(Disciplina disciplina) {
	        this.disciplina = disciplina;
	        this.setNomeDisciplina(disciplina.getNome());
	    }

	    public int getSemestre() {
	        return semestre;
	    }

	    public void setSemestre(int semestre) {
	        this.semestre = semestre;
	    }

	    public boolean isAnual() {
	        return anual;
	    }

	    public void setAnual(boolean anual) {
	        this.anual = anual;
	    }

	    public int getCargaTotal() {
	        return cargaTotal;
	    }

	    public void setCargaTotal(int cargaTotal) {
	        this.cargaTotal = cargaTotal;
	    }


		public String getNomeCurso() {
			return nomeCurso;
		}


		public void setNomeCurso(String nomeCurso) {
			this.nomeCurso = nomeCurso;
		}

	    public int getCargaSemanal() {
	        return cargaSemanal;
	    }

	    public void setCargaSemanal(int cargaSemanal) {
	        this.cargaSemanal = cargaSemanal;
	    }

	    public String getNomeDisciplina() {
	        return nomeDisciplina;
	    }

	    public void setNomeDisciplina(String nomeDisciplina) {
	        this.nomeDisciplina = nomeDisciplina;
	    }
       @Override
	    public int hashCode() {
	        int hash = 7;
	        hash = 23 * hash + this.id;
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
	        final Turma other = (Turma) obj;
	        if (this.id != other.id) {
	            return false;
	        }
	        return true;
	    }


	}




