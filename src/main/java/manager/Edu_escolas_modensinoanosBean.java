package manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import entity.Edu_escolas_modensino;
import entity.Edu_escolas_modensinoanos;
import persistence.Edu_escolas_modensinoDao;
import persistence.Edu_escolas_modensinoanosDao;

@ManagedBean
@ViewScoped
public class Edu_escolas_modensinoanosBean {
	
	public Edu_escolas_modensinoanos modensinoanos;
	public Edu_escolas_modensino modensino;
	
	public List<Edu_escolas_modensino> modensinolista;
	public List<Edu_escolas_modensinoanos> modensinoanoslista;
	
	public Edu_escolas_modensinoanosBean() {
		
		modensinoanos = new Edu_escolas_modensinoanos();
		modensino = new Edu_escolas_modensino();
		
		modensinoanoslista =  new ArrayList<Edu_escolas_modensinoanos>();
		
		try {
			modensinolista =  new Edu_escolas_modensinoDao().findAll(modensino);			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
	}
	
	public Edu_escolas_modensinoanos getModensinoanos() {
		return modensinoanos;
	}

	public void setModensinoanos(Edu_escolas_modensinoanos modensinoanos) {
		this.modensinoanos = modensinoanos;
	}

	public Edu_escolas_modensino getModensino() {
		return modensino;
	}

	public void setModensino(Edu_escolas_modensino modensino) {
		this.modensino = modensino;
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

	public void Buscar() {

		try {
			
			modensinoanoslista =  new Edu_escolas_modensinoanosDao().findbyModEnsino(modensinoanos.getDescricao_modensino());			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}
	
	public void AbreDialogoEditar() {		
		
		PrimeFaces.current().executeScript("PF('dialogo').show();");
	}

}
