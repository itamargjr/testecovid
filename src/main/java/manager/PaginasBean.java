package manager;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

@ManagedBean
@RequestScoped
public class PaginasBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8941213231309898594L;
	
	private final String index = "index.xhtml";
	private final String principal = "principal.xhtml";
	
	private final String facesRedirect = "?faces-redirect=true";
	
	public String Principal() {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			if (session.getAttribute("idusulogado") == null) {
				return null;
			} else if (((Integer) session.getAttribute("idusulogado"))!= 0) {
				return principal + facesRedirect;				
			} else {
				return null;
			}  													
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getIndex() {
		return index;
	}

	public String getPrincipal() {
		return principal;
	}

	public String getFacesRedirect() {
		return facesRedirect;
	}

	//Executa Codigo Js(javaScript), JQuery via Bean.
	public void ExecutaJs(String js){
		PrimeFaces.current().executeScript(js);
	}
	
	/* Diferen�a entre Redirect e Forward e a quantidade de requisi��es utilizadas,
	 * os dois servem para mudar a "view"(p�gina) do usu�rio,
	 * isso far� diferen�a dependendo do Bean utlizado (para mais informa��es consultar documenta��o: tema grande).
	 */
	public void Redirecionar(String pagina) throws Exception{
		FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
	}
	
	public void Forward(String pagina) throws Exception{
	    FacesContext.getCurrentInstance().getExternalContext().dispatch(pagina);
	}
	
	/* DEPRECIADO NA VERS�O MAIS NOVA DO JAVA (PODENDO SER REVERTIDO DEPOIS) */
		/* *Flash Scope */
			/* *
			 * Flash Scope � um recurso que permite trasmitir vari�veis e objetos entre views 
			 * Obs:. O flash Scope toda vez ap�s um request tudo que est� no flashScope � apagado da mem�ria
			 * se precisar que ele "sobreviva" mais um request use a propriedade "Keep" 
			 * 
			public void SetPropriedadeFlashScope(String texto, Object obj){
				FacesContext.getCurrentInstance().getExternalContext().getFlash().put(texto, obj);
			}
			public Object GetPropriedadeFlashScope(String texto){
				return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(texto);
			}
			*/
		/* *Fim Flash Scope */
	/**/
	
	/* *Session Scope */
		public void SetPropriedadeSessionScope(String chave, Object obj){
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(chave, obj);
		}
		public void RemovePropriedadeSessionScope(String chave){
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(chave);
		}
		public Object GetPropriedadeSessionScope(String chave){
			return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(chave);
		}
		
		public void SetPropriedadeRequest(String chave, Object obj) {
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(chave, obj);
		}
		public Object GetPropriedadeRequestScope(String chave){
			return FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(chave);
		}
	/* *Fim Session Scope */

}
