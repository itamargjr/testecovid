package manager;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import entity.Edu_escolas_modensino;
import persistence.Edu_escolas_modensinoDao;

@ManagedBean
@ViewScoped
public class Edu_escolas_modensinoBean {
	
	public Edu_escolas_modensino modensino;
	
	public List<Edu_escolas_modensino> modensinolista;
	
	private boolean renderizarBotoesAtualizar;
	
	public Edu_escolas_modensinoBean() {
		
		modensino = new Edu_escolas_modensino();
		
		Buscar();
		
	}

	public boolean isRenderizarBotoesAtualizar() {
		return renderizarBotoesAtualizar;
	}

	public void setRenderizarBotoesAtualizar(boolean renderizarBotoesAtualizar) {
		this.renderizarBotoesAtualizar = renderizarBotoesAtualizar;
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
	
	public void Limpar() {
		modensino = new Edu_escolas_modensino();
	}
	
	public void AbreDialogoEditar() {		
		
		PrimeFaces.current().executeScript("PF('dialogo').show();");
	}
	
	public void Buscar() {

		try {
			
			modensinolista =  new Edu_escolas_modensinoDao().findAll(modensino);			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
	}
	
	public void Cadastrar() {	
		
		try {
			
			new Edu_escolas_modensinoDao().gravar(modensino);	
			
			PrimeFaces.current().executeScript("PF('dialogo').hide();");
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
	}

}
