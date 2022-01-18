package listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import manager.AcessoBean;

/**
 * Classe que "escuta" o 
 * ciclo do JSF e implementa
 * o evento que captura a navega��o entre 
 * p�ginas do projeto para impedir um
 * usu�rio n�o logado de acessar o
 * sistema 
 * 
 * @author itamar
 *
 */

public class Autorizador implements PhaseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7165802048370933572L;

	@Override
	public void afterPhase(PhaseEvent event) {
		
		FacesContext context = event.getFacesContext();
		
		/**
		 * Se a p�gina a ser acessada � o index.xhtml
		 * nada � feito, j� que esta � a p�gina de login
		 */
		
		if ("/index.xhtml".equals(context.getViewRoot().getViewId())) {
			return;
		} else if ("/AgendarTesteCovid.xhtml".equals(context.getViewRoot().getViewId())) {
			return;			
		}
		
		/**
		 * Instancializo o acessobean pq l� est� a 
		 * refer�ncia se o usu�rio j� est� logado
		 * ou n�o.
		 * 
		 * Isto � registrado no m�todo booleano
		 * isLogado()
		 */
		
		AcessoBean acessoBean = context.getApplication().evaluateExpressionGet(context, "#{acessoBean}", AcessoBean.class);
		
		/**
		 * Se n�o estiver logado, volta pro index.xhtml
		 */
		
		if (!acessoBean.isLogado()) {
			NavigationHandler handler = context.getApplication().getNavigationHandler();
			
			handler.handleNavigation(context, null, "index?faces-redirect=true");
			
			context.renderResponse();
		}
				

		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {

		
	}

	@Override
	public PhaseId getPhaseId() {
		
		return PhaseId.RESTORE_VIEW;
	}
	

}
