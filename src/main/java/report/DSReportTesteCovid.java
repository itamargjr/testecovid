package report;

import java.util.Iterator;
import java.util.List;

import entity.Sau_testecovid;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DSReportTesteCovid implements JRDataSource {
	
	private Iterator<Sau_testecovid> dados;
	
	private Sau_testecovid registro;
	
	public DSReportTesteCovid(List<Sau_testecovid> lista) {
		dados = lista.iterator();
	}
	
	@Override
	public Object getFieldValue(JRField field) throws JRException {
		if(field.getName().equalsIgnoreCase("id_testecovid")){
			return registro.getId_testecovid();
		}
		
		if(field.getName().equalsIgnoreCase("data_testecovid")){
			return registro.getData_testecovid();
		}
		
		if(field.getName().equalsIgnoreCase("hora_testecovid")){
			return registro.getHora_testecovid();
		}
		
		if(field.getName().equalsIgnoreCase("horanum_testecovid")){
			return registro.getData_testecovid();
		}
		
		if(field.getName().equalsIgnoreCase("local_testecovid")){
			return registro.getHora_testecovid();
		}
		
		if(field.getName().equalsIgnoreCase("cpf_testecovid")){
			return registro.getData_testecovid();
		}
		
		if(field.getName().equalsIgnoreCase("nome_testecovid")){
			return registro.getHora_testecovid();
		}
		
		if(field.getName().equalsIgnoreCase("nascimento_testecovid")){
			return registro.getData_testecovid();
		}
		
		if(field.getName().equalsIgnoreCase("email_testecovid")){
			return registro.getHora_testecovid();
		}
		
		if(field.getName().equalsIgnoreCase("cep_testecovid")){
			return registro.getData_testecovid();
		}
		
		if(field.getName().equalsIgnoreCase("logradouro_testecovid")){
			return registro.getHora_testecovid();
		}
		
		if(field.getName().equalsIgnoreCase("numero_testecovid")){
			return registro.getData_testecovid();
		}
		
		if(field.getName().equalsIgnoreCase("complemento_testecovid")){
			return registro.getHora_testecovid();
		}
		
		if(field.getName().equalsIgnoreCase("bairro_testecovid")){
			return registro.getData_testecovid();
		}
		
		if(field.getName().equalsIgnoreCase("tipo_testecovid")){
			return registro.getHora_testecovid();
		}
		
		if(field.getName().equalsIgnoreCase("datarealizacao_testecovid")){
			return registro.getData_testecovid();
		}
		
		if(field.getName().equalsIgnoreCase("horarealizacao_testecovid")){
			return registro.getHora_testecovid();
		}
		
		if(field.getName().equalsIgnoreCase("dataresultado_testecovid")){
			return registro.getData_testecovid();
		}
		
		if(field.getName().equalsIgnoreCase("resultado_testecovid")){
			return registro.getHora_testecovid();
		}
		
		return null;
	}

	@Override
	public boolean next() throws JRException {
		if(dados.hasNext()){
			registro = dados.next();
			return true;
		}
		return false;
	}

}
