package manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import entity.Edu_escola;
import entity.Edu_escola_paineldevagas;
import entity.Edu_escolas_modensinoanos;
import entity.Edu_escolas_modensinovagas;
import persistence.Edu_escolaDao;
import persistence.Edu_escola_paineldevagasDao;
import persistence.Edu_escolas_modensinoanosDao;
import persistence.Edu_escolas_modensinovagasDao;

@ManagedBean
@ViewScoped
public class Edu_escolas_modensinovagasBean {
	
	public Edu_escolas_modensinovagas vagas;
	public Edu_escola escola;
	public Edu_escola_paineldevagas painelvagas;
	
	public List<Edu_escolas_modensinovagas> vagaslista;
	public List<Edu_escolas_modensinovagas> vagaslistafiltrada;
	public List<Edu_escolas_modensinoanos> modensinoanoslista;
	public List<Edu_escolas_modensinoanos> modensinoanoslistafiltrada;
	public List<Edu_escola> escolalista;
	public List<Edu_escola> escolalistafiltrada;
	public List<Edu_escola_paineldevagas> painelvagaslista;
	
	public List<String> VALID_COLUMN_KEYS;
	private List<ColumnModel> columns;
	
	public Edu_escolas_modensinovagasBean() {
		
		vagas = new Edu_escolas_modensinovagas();
		escola = new Edu_escola();
		
		painelvagas = new Edu_escola_paineldevagas();
		
		modensinoanoslista = new ArrayList<Edu_escolas_modensinoanos>();
		modensinoanoslistafiltrada = new ArrayList<Edu_escolas_modensinoanos>();
		
		vagaslista = new ArrayList<Edu_escolas_modensinovagas>();
		vagaslistafiltrada = new ArrayList<Edu_escolas_modensinovagas>();
		painelvagaslista = new ArrayList<Edu_escola_paineldevagas>();
		
		try {
			
			escolalista = new Edu_escolaDao().listar();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
	}
	
	public Edu_escola_paineldevagas getPainelvagas() {
		return painelvagas;
	}

	public void setPainelvagas(Edu_escola_paineldevagas painelvagas) {
		this.painelvagas = painelvagas;
	}

	public List<Edu_escola_paineldevagas> getPainelvagaslista() {
		return painelvagaslista;
	}

	public void setPainelvagaslista(List<Edu_escola_paineldevagas> painelvagaslista) {
		this.painelvagaslista = painelvagaslista;
	}

	public List<Edu_escolas_modensinoanos> getModensinoanoslistafiltrada() {
		return modensinoanoslistafiltrada;
	}

	public void setModensinoanoslistafiltrada(List<Edu_escolas_modensinoanos> modensinoanoslistafiltrada) {
		this.modensinoanoslistafiltrada = modensinoanoslistafiltrada;
	}

	public Edu_escola getEscola() {
		return escola;
	}

	public void setEscola(Edu_escola escola) {
		this.escola = escola;
	}

	public List<Edu_escola> getEscolalistafiltrada() {
		return escolalistafiltrada;
	}

	public void setEscolalistafiltrada(List<Edu_escola> escolalistafiltrada) {
		this.escolalistafiltrada = escolalistafiltrada;
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}

	public List<Edu_escolas_modensinovagas> getVagaslistafiltrada() {
		return vagaslistafiltrada;
	}

	public void setVagaslistafiltrada(List<Edu_escolas_modensinovagas> vagaslistafiltrada) {
		this.vagaslistafiltrada = vagaslistafiltrada;
	}

	public List<String> getVALID_COLUMN_KEYS() {
		return VALID_COLUMN_KEYS;
	}

	public void setVALID_COLUMN_KEYS(List<String> vALID_COLUMN_KEYS) {
		VALID_COLUMN_KEYS = vALID_COLUMN_KEYS;
	}

	public List<Edu_escola> getEscolalista() {
		return escolalista;
	}

	public void setEscolalista(List<Edu_escola> escolalista) {
		this.escolalista = escolalista;
	}

	public Edu_escolas_modensinovagas getVagas() {
		return vagas;
	}

	public void setVagas(Edu_escolas_modensinovagas vagas) {
		this.vagas = vagas;
	}

	public List<Edu_escolas_modensinovagas> getVagaslista() {
		return vagaslista;
	}

	public void setVagaslista(List<Edu_escolas_modensinovagas> vagaslista) {
		this.vagaslista = vagaslista;
	}

	public List<Edu_escolas_modensinoanos> getModensinoanoslista() {
		return modensinoanoslista;
	}

	public void setModensinoanoslista(List<Edu_escolas_modensinoanos> modensinoanoslista) {
		this.modensinoanoslista = modensinoanoslista;
	}
	
	static public class ColumnModel implements Serializable {
		 
        /**
		 * 
		 */
		private static final long serialVersionUID = 1073045516278991026L;
		
		private String header;
        private String property;
 
        public ColumnModel(String header, String property) {
            this.header = header;
            this.property = property;
        }
 
        public String getHeader() {
            return header;
        }
 
        public String getProperty() {
            return property;
        }
    }

	public void filtramodensinoanos() {
		try {
			Edu_escolas_modensinoanosDao ad = new Edu_escolas_modensinoanosDao();
			
			modensinoanoslista = ad.findbyIdModEnsino(vagas.getId_modensino());
			
			//System.out.println(modensinoanoslista);
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "")); // passa a mensagem
		}
	}
	
	public void buscarvagas() {
		try {
			
			painelvagaslista = new Edu_escola_paineldevagasDao().montaPainel(painelvagas);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "")); // passa a mensagem
		}		
	}
	
	public void buscarvagassimples() {
		try {
			
			vagaslista = new Edu_escolas_modensinovagasDao().findAll(vagas);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "")); // passa a mensagem
		}		
	}
	
	public void gravarvagas() {
		//System.out.println("Itens: " + painelvagaslista.size());
		//System.out.println("Primeiro item: " + painelvagaslista.get(0));
		//System.out.println("painelvagaslista: " + painelvagaslista);
		try {
			
			Edu_escola_paineldevagasDao ev = new Edu_escola_paineldevagasDao();
			
			ev.gravarVagas(painelvagaslista);
			
			buscarvagas();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "")); // passa a mensagem
		}		
	}
}
