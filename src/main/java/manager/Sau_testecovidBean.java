package manager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.primefaces.shaded.json.JSONObject;

import entity.Sau_testecovid;
import persistence.Sau_testecovidDao;
import util.Biblioteca;
import util.CepUtil;

@ManagedBean
@ViewScoped
public class Sau_testecovidBean {
	
	public Sau_testecovid testecovid;
	
	public List<Sau_testecovid> testecovidlista;
	public List<String> diasmarcacao;
	
	public String turno;
	
	public Sau_testecovidBean() {
		testecovid = new Sau_testecovid();
		
		testecovidlista = new ArrayList<Sau_testecovid>();
		diasmarcacao = new ArrayList<String>();  
		
		turno = "";
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		LocalDate data = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()); 
		
		for (int i = 1; i < 10; i++) {
			data = data.plusDays(1);
	
			if ((!Biblioteca.getDayOfWeek(data.format(formatter)).equalsIgnoreCase("SÁB."))&&(!Biblioteca.getDayOfWeek(data.format(formatter)).equalsIgnoreCase("DOM."))) {
				diasmarcacao.add(data.format(formatter));
				
				//System.out.println(Biblioteca.getDayOfWeek(data.format(formatter)));
			}					
		}
		
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public List<String> getDiasmarcacao() {
		return diasmarcacao;
	}

	public void setDiasmarcacao(List<String> diasmarcacao) {
		this.diasmarcacao = diasmarcacao;
	}

	public Sau_testecovid getTestecovid() {
		return testecovid;
	}

	public void setTestecovid(Sau_testecovid testecovid) {
		this.testecovid = testecovid;
	}

	public List<Sau_testecovid> getTestecovidlista() {
		return testecovidlista;
	}

	public void setTestecovidlista(List<Sau_testecovid> testecovidlista) {
		this.testecovidlista = testecovidlista;
	}
	
	public void GravarAgendamento() {
		try {	
							
			Sau_testecovidDao sd = new Sau_testecovidDao();
			
			Boolean AgendamentoJaExiste = sd.AgendamentoJaExiste(testecovid);
			
			if (AgendamentoJaExiste) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este agendamento já foi realizado", "")); 
			} else {
												
				sd.gravar(testecovid);
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Seu agendamento foi realizado com sucesso!\n " +
				                                                                                                "Confira o comprovante no seu email, ele deve ser levado no local da vacina.", ""));	
					
				testecovid = new Sau_testecovid();				
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "")); 
		}
	}
	
	public void testaCPF() {		
		if (!Biblioteca.IsCpf(testecovid.getCpf_testecovid())) {
			testecovid.setCpf_testecovid(null);
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF INVÁLIDO! Favor informar um CPF válido", ""));			
		}
	}
	
	public void retornaLogrdoCEP() {
		if ((testecovid.getCep_testecovid()!=null)&&(!testecovid.getCep_testecovid().equalsIgnoreCase(""))) {
			JSONObject obj = new JSONObject(CepUtil.buscarCep(testecovid.getCep_testecovid()));
			
			testecovid.setLogradouro_testecovid(obj.getString("logradouro"));
		}
	}
	
	public void filtrahorario() {
		
		try {
			
			Sau_testecovidDao sd = new Sau_testecovidDao();
			
			Integer agendados = sd.retornaDisponibilidade(testecovid);
			
			if (agendados >= 239) {
				testecovid.setHora_testecovid(null);
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não há vagas para este horário", ""));
			} else {
				
				String hora;				
				Integer valortruncado;
				
				valortruncado = Math.floorDiv(agendados, 4);
				
				if (valortruncado<10) {
					hora = ":0" + valortruncado;
				} else {
					hora = ":" + valortruncado;
				} 
				
				if (testecovid.getHoranum_testecovid()<10) {
					hora = "0" + testecovid.getHoranum_testecovid() + hora;
				} else {
					hora = testecovid.getHoranum_testecovid() + hora;
				} 
				
				testecovid.setHora_testecovid(hora);

			}						
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
	}
	
	public void mostradialogoagendamento() {
		PrimeFaces.current().executeScript("PF('Dialogo').show();");
	}
	
	public void buscaragendamentos() {
		try {
			
			Sau_testecovidDao sd = new Sau_testecovidDao();
			
			testecovidlista = sd.findAll(testecovid);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}

}
