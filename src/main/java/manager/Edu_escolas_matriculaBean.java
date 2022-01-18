package manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import entity.Edu_escola;
import entity.Edu_escolas_matricula;
import entity.Edu_escolas_matricula_quantidade;
import entity.Edu_escolas_modensino;
import entity.Edu_escolas_modensinoanos;
import entity.Edu_escolas_modensinovagas;
import entity.Edu_matr_candidatoReport;
import persistence.Edu_escolaDao;
import persistence.Edu_escolas_matriculaDao;
import persistence.Edu_escolas_modensinoDao;
import persistence.Edu_escolas_modensinoanosDao;
import persistence.Edu_matr_candidatoReportDao;

@ManagedBean
@ViewScoped
public class Edu_escolas_matriculaBean {
	
	public Edu_escolas_matricula matricula;
	public Edu_escola escola;
	public Edu_matr_candidatoReport candidatoreport;
	public Edu_escolas_modensinovagas vagas;
	
	public List<Edu_escola> escolalista;
	public List<Edu_escolas_matricula> matriculalista;
	public List<Edu_escolas_matricula_quantidade> matriculaquantlista;
	public List<Edu_matr_candidatoReport> candidatoreportlista;
	public List<Edu_escolas_modensino> modensinolista;
	public List<Edu_escolas_modensinoanos> modensinoanoslista;
	
	public Edu_escolas_matriculaBean() {
		
		matricula = new Edu_escolas_matricula();
		escola = new Edu_escola();
		candidatoreport = new Edu_matr_candidatoReport();
		vagas = new Edu_escolas_modensinovagas();
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		if ((session.getAttribute("nomeescola")!=null)&&(!session.getAttribute("nomeescola").toString().equalsIgnoreCase(""))) {
			matricula.setNome_escola(session.getAttribute("nomeescola").toString());
			matricula.setId_escola(Integer.parseInt(session.getAttribute("idescola").toString()));
		}
		
		matricula.setAno_matricula(2022);
		
		matriculalista = new ArrayList<Edu_escolas_matricula>();
		matriculaquantlista = new ArrayList<Edu_escolas_matricula_quantidade>();
		candidatoreportlista = new ArrayList<Edu_matr_candidatoReport>();
		escolalista = new ArrayList<Edu_escola>();
		
		try {
			
			escolalista = new Edu_escolaDao().listar();
			modensinolista =  new Edu_escolas_modensinoDao().findTodos();
			modensinoanoslista =  new Edu_escolas_modensinoanosDao().findTodos();
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}

	public List<Edu_escolas_matricula_quantidade> getMatriculaquantlista() {
		return matriculaquantlista;
	}

	public void setMatriculaquantlista(List<Edu_escolas_matricula_quantidade> matriculaquantlista) {
		this.matriculaquantlista = matriculaquantlista;
	}

	public List<Edu_escolas_modensino> getModensinolista() {
		return modensinolista;
	}

	public void setModensinolista(List<Edu_escolas_modensino> modensinolista) {
		this.modensinolista = modensinolista;
	}

	public List<Edu_escolas_modensinoanos> getModensinoanoslista() {
		return modensinoanoslista;
	}

	public void setModensinoanoslista(List<Edu_escolas_modensinoanos> modensinoanoslista) {
		this.modensinoanoslista = modensinoanoslista;
	}

	public Edu_escolas_modensinovagas getVagas() {
		return vagas;
	}

	public void setVagas(Edu_escolas_modensinovagas vagas) {
		this.vagas = vagas;
	}

	public Edu_escola getEscola() {
		return escola;
	}

	public void setEscola(Edu_escola escola) {
		this.escola = escola;
	}

	public Edu_matr_candidatoReport getCandidatoreport() {
		return candidatoreport;
	}

	public void setCandidatoreport(Edu_matr_candidatoReport candidatoreport) {
		this.candidatoreport = candidatoreport;
	}

	public List<Edu_escola> getEscolalista() {
		return escolalista;
	}

	public void setEscolalista(List<Edu_escola> escolalista) {
		this.escolalista = escolalista;
	}

	public List<Edu_matr_candidatoReport> getCandidatoreportlista() {
		return candidatoreportlista;
	}

	public void setCandidatoreportlista(List<Edu_matr_candidatoReport> candidatoreportlista) {
		this.candidatoreportlista = candidatoreportlista;
	}

	public Edu_escolas_matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Edu_escolas_matricula matricula) {
		this.matricula = matricula;
	}

	public List<Edu_escolas_matricula> getMatriculalista() {
		return matriculalista;
	}

	public void setMatriculalista(List<Edu_escolas_matricula> matriculalista) {
		this.matriculalista = matriculalista;
	}
	
	public void buscarcandidato() {

		try {
			
			Edu_escolas_matriculaDao md = new Edu_escolas_matriculaDao();
			
			if (md.MatriculaJaExiste(matricula)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Candidato já matriculado", "")); // passa a mensagem
			} else {
				candidatoreport.setAno_candidato(matricula.getAno_matricula());
				candidatoreport.setCpf_candidato(matricula.getCpf_candidato());
				
				candidatoreport = new Edu_matr_candidatoReportDao().findCandidatoConsulta(candidatoreport);
				
				if (!candidatoreport.getNome_escolaselecionada().equalsIgnoreCase(matricula.getNome_escola())) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Candidato selecionado para " + candidatoreport.getNome_escolaselecionada(), "")); // passa a mensagem
					
					candidatoreport = new Edu_matr_candidatoReport();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "")); 	
		}	
	}
	
	public void confirmarmatricula() {
		
		//System.out.println("confirmando matrícula...");
		
		matricula.setId_modensinovagas(candidatoreport.getId_modensinovagas());
		matricula.setId_candidato(candidatoreport.getId_candidato());
		
		//System.out.println("setei vagas e candidato");
		
		try {
			Edu_escolas_matriculaDao md = new Edu_escolas_matriculaDao();
			
			//System.out.println("vou gravar");
			
			md.gravar(matricula);
			
			//System.out.println("gravei");

			matricula = new Edu_escolas_matricula();
			candidatoreport = new Edu_matr_candidatoReport();
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			
			if ((session.getAttribute("nomeescola")!=null)&&(!session.getAttribute("nomeescola").toString().equalsIgnoreCase(""))) {
				matricula.setNome_escola(session.getAttribute("nomeescola").toString());
				matricula.setId_escola(Integer.parseInt(session.getAttribute("idescola").toString()));
			}
			
			matricula.setAno_matricula(2022);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Matrícula realizada com sucesso!", ""));
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}
	
	public void buscarmatriculas() {
		try {
			
			//System.out.println("buscando matriculas");
			
			Edu_escolas_matriculaDao md = new Edu_escolas_matriculaDao();
			
			matriculalista = md.findAll(matricula);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "")); 	
		}
	}
	
	public void buscarquantitativomatriculas() {
		try {
			
			//System.out.println("buscando matriculas");
			
			Edu_escolas_matriculaDao md = new Edu_escolas_matriculaDao();
			
			matriculaquantlista = md.RetornaQuant(matricula);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "")); 	
		}
	}

}
