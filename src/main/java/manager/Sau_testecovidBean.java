package manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entity.Sau_testecovid;

@ManagedBean
@ViewScoped
public class Sau_testecovidBean {
	
	public Sau_testecovid testecovid;
	
	public List<Sau_testecovid> testecovidlista;
	
	public Sau_testecovidBean() {
		testecovid = new Sau_testecovid();
		
		testecovidlista = new ArrayList<Sau_testecovid>();
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
		
	}

}
