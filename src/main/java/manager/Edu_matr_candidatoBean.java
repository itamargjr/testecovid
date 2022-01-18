package manager;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.PrimeFaces;

import entity.Edu_escola;
import entity.Edu_escolas_modensino;
import entity.Edu_escolas_modensinoanos;
import entity.Edu_escolas_modensinovagas;
import entity.Edu_matr_candidatoReport;
import entity.Edu_matr_candidatoSelecao;
import net.sf.jasperreports.engine.JasperRunManager;
import persistence.Edu_escolaDao;
import persistence.Edu_escolas_modensinoanosDao;
import persistence.Edu_escolas_modensinovagasDao;
import persistence.Edu_matr_candidatoReportDao;
import persistence.Edu_matr_candidatoSelecaoDao;
import report.DSReportCandidato;
import util.Biblioteca;
import util.SendMail;

@ManagedBean
@ViewScoped
public class Edu_matr_candidatoBean {
	
	public Edu_matr_candidatoReport candidatoreport;
	public Edu_escolas_modensino modensino;
	public Edu_escolas_modensinoanos modensinoanos;
	public Edu_escolas_modensinovagas modensinovagas;
	
	public List<Edu_escolas_modensino> modensinolista;
	public List<Edu_escolas_modensinoanos> modensinoanoslista;
	public List<Edu_escolas_modensinovagas> modensinovagaslista;
	public List<Edu_matr_candidatoReport> candidatoreportlista;
	public List<Edu_matr_candidatoSelecao> candidatoselecaolista;
	public List<Edu_escola> escolalista;
	
	public List<String> etapaslista;
	
	public Boolean necespec;
	public Integer idade;
	public String etapaensino;
    public String etapaensinoanos;
	
	public Edu_matr_candidatoBean() {
		
		candidatoreport = new Edu_matr_candidatoReport();
		modensino = new Edu_escolas_modensino();
		modensinoanos = new Edu_escolas_modensinoanos();
		modensinovagas = new Edu_escolas_modensinovagas();
		
		modensinolista = new ArrayList<Edu_escolas_modensino>();
		modensinoanoslista = new ArrayList<Edu_escolas_modensinoanos>();
		candidatoreportlista = new ArrayList<Edu_matr_candidatoReport>();
		candidatoselecaolista = new ArrayList<Edu_matr_candidatoSelecao>();
		modensinovagaslista = new ArrayList<Edu_escolas_modensinovagas>();
		
		etapaslista = new ArrayList<String>();
		
		necespec = false;
		idade = 0;
		
		try {
	    	Edu_escolaDao ed = new Edu_escolaDao();
			
			escolalista = ed.listar();
		} catch (Exception e) {
			e.printStackTrace();
			
			System.out.println(e.getMessage());
		}		
	}
	
	@PostConstruct
	public void init() {
		carregalistadasession();
		
		limpalistadasession();		
	}
	
	@SuppressWarnings("unchecked") // isso é necessário para o eclipse não colocar warning na atribuição abaixo porque não entende que o objeto retornado é realmente uma lista
	public void carregalistadasession() {					
		candidatoreportlista = (List<Edu_matr_candidatoReport>) new PaginasBean().GetPropriedadeSessionScope("candidatoreportlista");
	}
	
	public void limpalistadasession() {					
		new PaginasBean().RemovePropriedadeSessionScope("candidatoreportlista");
	}

	public List<Edu_escolas_modensinovagas> getModensinovagaslista() {
		return modensinovagaslista;
	}

	public void setModensinovagaslista(List<Edu_escolas_modensinovagas> modensinovagaslista) {
		this.modensinovagaslista = modensinovagaslista;
	}

	public Edu_escolas_modensinovagas getModensinovagas() {
		return modensinovagas;
	}

	public void setModensinovagas(Edu_escolas_modensinovagas modensinovagas) {
		this.modensinovagas = modensinovagas;
	}

	public List<String> getEtapaslista() {
		return etapaslista;
	}

	public void setEtapaslista(List<String> etapaslista) {
		this.etapaslista = etapaslista;
	}

	public List<Edu_matr_candidatoSelecao> getCandidatoselecaolista() {
		return candidatoselecaolista;
	}

	public void setCandidatoselecaolista(List<Edu_matr_candidatoSelecao> candidatoselecaolista) {
		this.candidatoselecaolista = candidatoselecaolista;
	}

	public String getEtapaensino() {
		return etapaensino;
	}

	public void setEtapaensino(String etapaensino) {
		this.etapaensino = etapaensino;
	}

	public String getEtapaensinoanos() {
		return etapaensinoanos;
	}

	public void setEtapaensinoanos(String etapaensinoanos) {
		this.etapaensinoanos = etapaensinoanos;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Boolean getNecespec() {
		return necespec;
	}

	public void setNecespec(Boolean necespec) {
		this.necespec = necespec;
	}

	public List<Edu_escola> getEscolalista() {
		return escolalista;
	}

	public void setEscolalista(List<Edu_escola> escolalista) {
		this.escolalista = escolalista;
	}

	public Edu_matr_candidatoReport getCandidatoreport() {
		return candidatoreport;
	}

	public void setCandidatoreport(Edu_matr_candidatoReport candidatoreport) {
		this.candidatoreport = candidatoreport;
	}

	public Edu_escolas_modensino getModensino() {
		return modensino;
	}

	public void setModensino(Edu_escolas_modensino modensino) {
		this.modensino = modensino;
	}

	public Edu_escolas_modensinoanos getModensinoanos() {
		return modensinoanos;
	}

	public void setModensinoanos(Edu_escolas_modensinoanos modensinoanos) {
		this.modensinoanos = modensinoanos;
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

	public List<Edu_matr_candidatoReport> getCandidatoreportlista() {
		return candidatoreportlista;
	}

	public void setCandidatoreportlista(List<Edu_matr_candidatoReport> candidatoreportlista) {
		this.candidatoreportlista = candidatoreportlista;
	}
	
	public void filtramodensinoanos() {
		try {
			Edu_escolas_modensinoanosDao ad = new Edu_escolas_modensinoanosDao();
			
			//System.out.println("filtrando: " + candidatoreport.getDescricao_modensino());
			
			modensinoanoslista = ad.findbyModEnsino(candidatoreport.getDescricao_modensino());
			
			//System.out.println("modensinoanoslista: " + modensinoanoslista);
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "")); // passa a mensagem
		}
	}
	
	public void filtraetapaensino(){
		try {
			Edu_escolas_modensinovagasDao vd = new Edu_escolas_modensinovagasDao();
			
			etapaslista = vd.findEtapas(candidatoreport.getId_escola());
			
			if (etapaslista.size()>0) {
				modensinoanoslista = vd.findbyModEnsino(candidatoreport.getId_escola(), etapaslista.get(0));
			}

		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "")); // passa a mensagem
		}
	}
	
	public void filtramodensinoanosvagas() {
		try {
			Edu_escolas_modensinovagasDao vd = new Edu_escolas_modensinovagasDao();
			
			modensinoanoslista = vd.findbyModEnsino(candidatoreport.getId_escola(), candidatoreport.getDescricao_modensino());

		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "")); // passa a mensagem
		}
	}
	
	public void buscarcandidatos() {
		try {
			Edu_matr_candidatoReportDao cd = new Edu_matr_candidatoReportDao();
			
			if (necespec) {
				candidatoreport.setNecespec_candidato("S");
			} 
			
			candidatoreportlista = cd.findCandidato(candidatoreport);
			
			necespec = false;
			
			candidatoreport = new Edu_matr_candidatoReport();
			
			new PaginasBean().SetPropriedadeSessionScope("candidatoreportlista", candidatoreportlista);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "")); // passa a mensagem
		}
	}
	
	public void buscarcandidatosclass() {
		try {
						
			modensinovagas = new Edu_escolas_modensinovagasDao().consultardesc(candidatoreport.getId_escola(), candidatoreport.getDescricao_modensinoanos(), candidatoreport.getAno_candidato());
			
			if ((modensinovagas.getId_modensinovagas() == null)||(modensinovagas.getId_modensinovagas() == 0)) {
				throw new Exception("Dados inválidos, refaça a busca");
			} else {
				candidatoreport.setId_modensinovagas(modensinovagas.getId_modensinovagas());
				
				Edu_matr_candidatoReportDao cd = new Edu_matr_candidatoReportDao();
				
				candidatoreportlista = cd.findCandidatoClass(candidatoreport);
				
				candidatoreport.setId_modensinovagas1(modensinovagas.getId_modensinovagas());
				
				candidatoreportlista.addAll(cd.findCandidatoNAOClass(candidatoreport));
				
				candidatoreport = new Edu_matr_candidatoReport();
				
				new PaginasBean().SetPropriedadeSessionScope("candidatoreportlista", candidatoreportlista);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "")); // passa a mensagem
		}
	}
	
	public void buscarcandidatosNAOclass() {
		try {
						
			modensinovagas = new Edu_escolas_modensinovagasDao().consultardesc(candidatoreport.getId_escola(), candidatoreport.getDescricao_modensinoanos(), candidatoreport.getAno_candidato());
			
			if ((modensinovagas.getId_modensinovagas() == null)||(modensinovagas.getId_modensinovagas() == 0)) {
				throw new Exception("Dados inválidos, refaça a busca");
			} else {
				candidatoreport.setId_modensinovagas1(modensinovagas.getId_modensinovagas());
				
				Edu_matr_candidatoReportDao cd = new Edu_matr_candidatoReportDao();
				
				candidatoreportlista = cd.findCandidatoNAOClass(candidatoreport);
				
				candidatoreport = new Edu_matr_candidatoReport();
				
				new PaginasBean().SetPropriedadeSessionScope("candidatoreportlista", candidatoreportlista);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "")); // passa a mensagem
		}
	}
	
	public void mostradialogocandidato() {
		
		idade = Biblioteca.retornaIdadeEmUmaDataBase(candidatoreport.getNascimento_candidato(), "31/03/2022");
		
		PrimeFaces.current().executeScript("PF('Dialogo').show();");
	}
	
	public void imprimirInscricao() {
		imprimeCandidato(candidatoreport.getId_candidato());
	}
	
	public void reenviarInscricao() {
		try {
			enviaremailcandidato(candidatoreport.getId_candidato());
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Email enviado com sucesso", ""));
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}
	
	public String imprimeNAOClassificados(){
		
		if ((candidatoreportlista.size()==0)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum candidato encontrado para enviar imprimir", "")); // passa a mensagem
		} else {
			FacesContext fc = FacesContext.getCurrentInstance();
			
			try{		
				
				DSReportCandidato ds = new DSReportCandidato(candidatoreportlista);
				
				InputStream arquivo = FacesContext.getCurrentInstance()
					.getExternalContext().getResourceAsStream("/candidatosNAOClassificados.jasper");	

				byte[] pdf = JasperRunManager.runReportToPdf(arquivo, null, ds);
					
				HttpServletResponse res = (HttpServletResponse) FacesContext
						.getCurrentInstance().getExternalContext().getResponse();
					
				res.setContentType("application/pdf");
				
				res.setContentLength(pdf.length);
					
				OutputStream out = res.getOutputStream();

				out.write(pdf, 0, pdf.length);

				out.flush();	
				
				out.close();
				
				FacesContext.getCurrentInstance().responseComplete();
				
				OutputStream fileout = new FileOutputStream("listadeesperamatricula.pdf");	
				
				fileout.write(pdf, 0, pdf.length);

				fileout.flush();	

				fileout.close();	
				
			} catch (Exception e) {
				e.printStackTrace();
				
				fc.addMessage("formimprimirclass", new FacesMessage("Erro ao enviar: " + e.getMessage()));	
			}
		}
		
		return null;
	}
	
	public String imprimeclassificacao(){
		
		if ((candidatoreportlista.size()==0)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum candidato encontrado para enviar imprimir", "")); // passa a mensagem
		} else {
			FacesContext fc = FacesContext.getCurrentInstance();
			
			try{		
				
				DSReportCandidato ds = new DSReportCandidato(candidatoreportlista);
				
				InputStream arquivo = FacesContext.getCurrentInstance()
					.getExternalContext().getResourceAsStream("/classificacao.jasper");	

				byte[] pdf = JasperRunManager.runReportToPdf(arquivo, null, ds);
					
				HttpServletResponse res = (HttpServletResponse) FacesContext
						.getCurrentInstance().getExternalContext().getResponse();
					
				res.setContentType("application/pdf");
				
				res.setContentLength(pdf.length);
					
				OutputStream out = res.getOutputStream();

				out.write(pdf, 0, pdf.length);

				out.flush();	
				
				out.close();
				
				FacesContext.getCurrentInstance().responseComplete();
				
				OutputStream fileout = new FileOutputStream("classificacaomatricula.pdf");	
				
				fileout.write(pdf, 0, pdf.length);

				fileout.flush();	

				fileout.close();	
				
			} catch (Exception e) {
				e.printStackTrace();
				
				fc.addMessage("formimprimirclass", new FacesMessage("Erro ao enviar: " + e.getMessage()));	
			}
		}
		
		return null;
	}
	
	public String imprimeCandidato(Integer IdCandidato){
		
		if ((IdCandidato==null)||(IdCandidato==0)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum candidato encontrado para enviar email", "")); // passa a mensagem
		} else {
			FacesContext fc = FacesContext.getCurrentInstance();
			
			try{		
				
				List<Edu_matr_candidatoReport> lista = new Edu_matr_candidatoReportDao().findCandidatopeloID(IdCandidato);
				
				candidatoreport = lista.get(0);

				DSReportCandidato ds = new DSReportCandidato(lista);
				
				InputStream arquivo = FacesContext.getCurrentInstance()
					.getExternalContext().getResourceAsStream("/candidato.jasper");	

				byte[] pdf = JasperRunManager.runReportToPdf(arquivo, null, ds);
					
				HttpServletResponse res = (HttpServletResponse) FacesContext
						.getCurrentInstance().getExternalContext().getResponse();
					
				res.setContentType("application/pdf");
				
				res.setContentLength(pdf.length);
					
				OutputStream out = res.getOutputStream();

				out.write(pdf, 0, pdf.length);

				out.flush();	
				
				out.close();
				
				FacesContext.getCurrentInstance().responseComplete();
				
				OutputStream fileout = new FileOutputStream("relatoriopreped.pdf");	
				
				fileout.write(pdf, 0, pdf.length);

				fileout.flush();	

				fileout.close();	
				
			} catch (Exception e) {
				e.printStackTrace();
				
				fc.addMessage("enviaremailprepedidos", new FacesMessage("Erro ao enviar: " + e.getMessage()));	
			}
		}
		
		return null;
	}

	public void enviaremailcandidato(Integer IdCandidato){
		
		if ((IdCandidato==null)||(IdCandidato==0)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum candidato encontrado para enviar email", "")); // passa a mensagem
		} else {
			FacesContext fc = FacesContext.getCurrentInstance();
			
			try{		
				
				List<Edu_matr_candidatoReport> lista = new Edu_matr_candidatoReportDao().findCandidatopeloID(IdCandidato);
				
				candidatoreport = lista.get(0);

				DSReportCandidato ds = new DSReportCandidato(lista);
				
				InputStream arquivo = FacesContext.getCurrentInstance()
					.getExternalContext().getResourceAsStream("/candidato.jasper");	

				byte[] pdf = JasperRunManager.runReportToPdf(arquivo, null, ds);
					
				String dest = "";
				
				if ((candidatoreport.getEmail_candidato()!=null)&&(!candidatoreport.getEmail_candidato().equalsIgnoreCase(""))) {
					dest = candidatoreport.getEmail_candidato();
				} else if (candidatoreport.getEmail_responsavel()!=null) {
					dest = candidatoreport.getEmail_responsavel();
				}
				
				SendMail sm = new SendMail();
				
				String dadosemailhtml = "<br /> <strong>MATRÍCULA NILÓPOLIS 2022 - CANDIDATO <label>" +
		           		 "</label> </strong><br /> <br />" +
		           		 "<br /> Esta é uma confirmação da sua CANDIDATURA à Matrícula na rede de ensino do município de Nilópolis <br />" +
		           		 "<hr />" +
		           		 "<br /><br />Candidato         : " + candidatoreport.getNome_candidato() +
		           		 "<br /><br />Data de Nascimento: " + candidatoreport.getNascimento_candidato() +
		           		 "<br /><br />Bairro            : " + candidatoreport.getBairro_candidato() +
		           		 "<br /><br />" + 
		           		 "<br /><br />Responsável       : " + candidatoreport.getNome_responsavel() +
		           		 "<br /><br />" + 		           		 
		           		 "<br /><br />Opções de Escola  : " + 
		           		 "<br /><br />" + 
		           		 "<br /><br />1ª Opção          : " + candidatoreport.getNome_escola1() +		           		 		           		
		           		 "<br /><br />2ª Opção          : " + candidatoreport.getNome_escola2() +
		           		 "<br /><br />3ª Opção          : " + candidatoreport.getNome_escola3() +
		           		 "<br /><br />" + 
		           		 "Você realizou a pré-matrícula para concorrer a uma vaga escolar na " +
		           		 "<br />" +
		           		 "Rede Municipal de Nilópolis em 2022. O resultado final  (Classificação) estará " +
		           		 "<br />" +
		           		 "disponível no dia 21/12/2021 no mesmo endereço eletrônico." +
		           		 "<br />" +
		           		 "(www.nilopolisdigital.com/matriculasonline)"+
		           		 "<div style='border:none;border-bottom:solid windowtext 1.0pt;padding:0cm 0cm 1.0pt 0cm'>" +
		           		 
		           		 "<table align='center'><tr>"+
		           		 "<td align='center'><img src='https://nilopolisdigital.com/imagens/logomatricula2022.jpg' alt='Matrículas On Line 2022' /><br /><br />"+
		           		 "<strong>Sistema de Matrículas On Line 2022 - Prefeitura Municipal de Nilópolis</strong>"+
		           		 "</td></tr></table>";
	
				String[] to = {dest, candidatoreport.getEmail_responsavel()}; 
				
				//System.out.println("para: " + to);
							
				sm.sendMailAttachment("inscricaoeducacaonilopolis@gmail.com", to, "Matrícula Nilópolis 2022 - Candidato", dadosemailhtml, pdf);

				//fc.addMessage("enviaremailprepedidos", new FacesMessage("Email enviado com sucesso! "));	
				
			} catch (Exception e) {
				e.printStackTrace();
				
				fc.addMessage("enviaremailprepedidos", new FacesMessage("Erro ao enviar: " + e.getMessage()));	
			}
		}
	}
	
	public String imprimelistainscricoes() {
		
		return "ListarInscricoesRel.xhtml?faces-redirect=true";	
	}
	
	public void classificarcandidatos() {
		try {
			Edu_matr_candidatoSelecaoDao cd = new Edu_matr_candidatoSelecaoDao();
			
			candidatoselecaolista = cd.findCandidatoClass(candidatoreport.getAno_candidato());
			
			//System.out.println("Gravando critérios");
			
			for (Edu_matr_candidatoSelecao cand : candidatoselecaolista) {
				if ((cand.getId_escola()!=null)&&(cand.getId_escola()==cand.getId_escolavaga())) {
					cd.gravarcriterio(cand.getId_candidato(), "1 - Irmão na Escola");
				} else {
					cd.gravarcriterio(cand.getId_candidato(), "2 - Munícipe");
				}
			}
			
			candidatoselecaolista = cd.findCandidatoClass(candidatoreport.getAno_candidato());
			
			//System.out.println("Gravando classificação");
			
			Integer idVaga = 0;
			Integer Classific = 0;
			
			for (Edu_matr_candidatoSelecao cand : candidatoselecaolista) {
				
				if (idVaga != cand.getId_modensinovagas1()) {
					idVaga = cand.getId_modensinovagas1();
					Classific = 1;
				}
				
				if (Classific <= cand.getVagas_modensinovagas()) {
					cd.gravarclassificcomescolha(cand.getId_candidato(), Classific, idVaga);
				} else {
					cd.gravarclassific(cand.getId_candidato(), Classific);
				}							
				
				Classific++;
			}
			
			candidatoselecaolista = cd.findCandidatoClassSegOpcao(candidatoreport.getAno_candidato());
			
			modensinovagaslista = new Edu_escolas_modensinovagasDao().findSobradeVagas(candidatoreport.getAno_candidato());
			
			idVaga = 0;
			Classific = 0;
			
			Integer saldodevagas = 0;
			
			for (Edu_matr_candidatoSelecao cand : candidatoselecaolista) {
				if (idVaga != cand.getId_modensinovagas2()) {
					idVaga = cand.getId_modensinovagas2();
					Classific = 1;
					
					saldodevagas = 0;
					
					for (Edu_escolas_modensinovagas vagassobra : modensinovagaslista) {
						if (vagassobra.getId_modensinovagas()==idVaga) {
							saldodevagas = (vagassobra.getVagas_modensinovagas() - vagassobra.getCandidatos());							
						}
					}
					
				}
				
				if (Classific <= saldodevagas) {
					cd.gravarSegundaclassificcomescolha(cand.getId_candidato(), Classific, idVaga);
				} else {
					cd.gravarSegundaclassific(cand.getId_candidato(), Classific);
				}							
				
				Classific++;
			}
			
			//System.out.println("Terminou");
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Classificação Efetuada com Sucesso!", "")); // passa a mensagem
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "")); // passa a mensagem
		}
	}
}
