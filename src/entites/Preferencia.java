package entites;

public class Preferencia {
	
	    private String nomeProfessor;
	       
	    private String registroProfessor;
	    
	    private Turma turma1;
	    
	    private Turma turma2;
	    
	    
	    private Turma turma3;  
	 	   
	    private Disciplina descarte1;
	    
	   
	    private Disciplina descarte2;
	    
	   
	    private Disciplina descarte3;
	    
	    public Preferencia() {}
	    
	        
	    public Preferencia(String nomeProfessor, String registroProfessor, Turma turma1, Turma turma2, Turma turma3,
				Disciplina descarte1, Disciplina descarte2, Disciplina descarte3) {
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


		/*	    public void setProfessor(Professor professor) {
	        this.professor = professor;
	        this.registroProfessor = professor.getRegistro();
	    }
*/
	    public Turma getTurma1() {
	        return turma1;
	    }

	    public void setTurma1(Turma turma1) {
	        this.turma1 = turma1;
	    }

	    public Turma getTurma2() {
	        return turma2;
	    }

	    public void setTurma2(Turma turma2) {
	        this.turma2 = turma2;
	    }

	    public Turma getTurma3() {
	        return turma3;
	    }

	    public void setTurma3(Turma turma3) {
	        this.turma3 = turma3;
	    }

	    public Disciplina getDescarte1() {
	        return descarte1;
	    }

	    public void setDescarte1(Disciplina descarte1) {
	        this.descarte1 = descarte1;
	    }

	    public Disciplina getDescarte2() {
	        return descarte2;
	    }

	    public void setDescarte2(Disciplina descarte2) {
	        this.descarte2 = descarte2;
	    }

	    public Disciplina getDescarte3() {
	        return descarte3;
	    }

	    public void setDescarte3(Disciplina descarte3) {
	        this.descarte3 = descarte3;
	    }
  
	    public String getRegistroProfessor() {
	        return registroProfessor;
	    }

	    public void setRegistroProfessor(String registroProfessor) {
	        this.registroProfessor = registroProfessor;
	    }


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((registroProfessor == null) ? 0 : registroProfessor.hashCode());
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
			Preferencia other = (Preferencia) obj;
			if (registroProfessor == null) {
				if (other.registroProfessor != null)
					return false;
			} else if (!registroProfessor.equals(other.registroProfessor))
				return false;
			return true;
		}
 
}
