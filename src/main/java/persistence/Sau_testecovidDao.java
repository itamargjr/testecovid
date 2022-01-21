package persistence;

import java.util.ArrayList;
import java.util.List;

import entity.Sau_testecovid;

public class Sau_testecovidDao extends Dao {
	
	public Integer retornaDisponibilidade(Sau_testecovid testecovid) throws Exception{
		
		Integer agendados = 0;
		
		open();
		
		String statement = "select count(a.id_testecovid) as agendados " +
                			"from sau_testecovid a " +
                			"where a.data_testecovid = ? and a.horanum_testecovid = ? and a.local_testecovid = ?";

		stmt = con.prepareStatement(statement);
		
		stmt.setString(1, testecovid.getData_testecovid());
		stmt.setInt(2, testecovid.getHoranum_testecovid());
		stmt.setString(3, testecovid.getLocal_testecovid());

		rs = stmt.executeQuery();

		if (rs.next()) {
			agendados =  rs.getInt("agendados");
		}
		
		close();
		
		return agendados;
		
	}
	
	public List<Sau_testecovid> findAll(Sau_testecovid testecovid) throws Exception{
		
		List<Sau_testecovid> lista = new ArrayList<Sau_testecovid>();
		
		open();
		
		String statement = "select " +
		                   "  id_testecovid, data_testecovid, hora_testecovid, horanum_testecovid, " +
						   "  local_testecovid, cpf_testecovid, nome_testecovid, nascimento_testecovid, " +
		                   "  email_testecovid, cep_testecovid, logradouro_testecovid, numero_testecovid, " +
						   "  complemento_testecovid, bairro_testecovid, tipo_testecovid, datarealizacao_testecovid, " +
		                   "  horarealizacao_testecovid, dataresultado_testecovid, resultado_testecovid " +
                			"from sau_testecovid a " +
                			"where 1 = 1 ";
		
		if ((testecovid.getLocal_testecovid()!=null)&&(!testecovid.getLocal_testecovid().equalsIgnoreCase(""))) {
			statement = statement + " and local_testecovid = '" + testecovid.getLocal_testecovid() + "'";
		}
		
		if ((testecovid.getData_testecovid()!=null)&&(!testecovid.getData_testecovid().equalsIgnoreCase(""))) {
			statement = statement + " and data_testecovid = '" + testecovid.getData_testecovid() + "'";
		}
		
		if ((testecovid.getCpf_testecovid()!=null)&&(!testecovid.getCpf_testecovid().equalsIgnoreCase(""))) {
			statement = statement + " and cpf_testecovid = '" + testecovid.getCpf_testecovid() + "'";
		}

		stmt = con.prepareStatement(statement);

		rs = stmt.executeQuery();

		while (rs.next()) {
			Sau_testecovid t = new Sau_testecovid(rs.getInt("id_testecovid"), rs.getString("data_testecovid"), rs.getString("hora_testecovid"), 
											      rs.getInt("horanum_testecovid"), rs.getString("local_testecovid"), rs.getString("cpf_testecovid"), 
											      rs.getString("nome_testecovid"), rs.getString("nascimento_testecovid"), rs.getString("email_testecovid"), 
											      rs.getString("cep_testecovid"), rs.getString("logradouro_testecovid"), rs.getString("numero_testecovid"), 
											      rs.getString("complemento_testecovid"), rs.getString("bairro_testecovid"), rs.getString("tipo_testecovid"), 
											      rs.getString("datarealizacao_testecovid"), rs.getString("horarealizacao_testecovid"), 
											      rs.getString("dataresultado_testecovid"), rs.getString("resultado_testecovid"));
					
			lista.add(t);
		}
		
		close();
		
		return lista;
		
	}
	
	public Boolean AgendamentoJaExiste(Sau_testecovid testecovid) throws Exception{
		
		Boolean jaexiste = false;
		
		open();
		
		String statement = "select " +
		                   "  id_testecovid, data_testecovid, hora_testecovid, horanum_testecovid, " +
						   "  local_testecovid, cpf_testecovid, nome_testecovid, nascimento_testecovid, " +
		                   "  email_testecovid, cep_testecovid, logradouro_testecovid, numero_testecovid, " +
						   "  complemento_testecovid, bairro_testecovid, tipo_testecovid, datarealizacao_testecovid, " +
		                   "  horarealizacao_testecovid, dataresultado_testecovid, resultado_testecovid " +
                			"from sau_testecovid a " +
                			"where cpf_testecovid = ?";

		stmt = con.prepareStatement(statement);
		
		stmt.setString(1, testecovid.getCpf_testecovid());

		rs = stmt.executeQuery();

		if (rs.next()) {
			jaexiste = true;
		}
		
		close();
		
		return jaexiste;
		
	}
	
	public void gravar(Sau_testecovid testecovid) throws Exception{
		
		open();
		
		String statement = "insert into sau_testecovid " +
		                   "  (id_testecovid, data_testecovid, hora_testecovid, horanum_testecovid, " +
						   "  local_testecovid, cpf_testecovid, nome_testecovid, nascimento_testecovid, " +
		                   "  email_testecovid, cep_testecovid, logradouro_testecovid, numero_testecovid, " +
						   "  complemento_testecovid, bairro_testecovid, tipo_testecovid, datarealizacao_testecovid, " +
		                   "  horarealizacao_testecovid, dataresultado_testecovid, resultado_testecovid) " +
						   "values " +
		                   "  (0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		stmt = con.prepareStatement(statement);
		
		stmt.setString(1, testecovid.getData_testecovid());
		stmt.setString(2, testecovid.getHora_testecovid());
		stmt.setInt(3, testecovid.getHoranum_testecovid());
		stmt.setString(4, testecovid.getLocal_testecovid());
		stmt.setString(5, testecovid.getCpf_testecovid());
		stmt.setString(6, testecovid.getNome_testecovid());
		stmt.setString(7, testecovid.getNascimento_testecovid());
		stmt.setString(8, testecovid.getEmail_testecovid());
		stmt.setString(9, testecovid.getCep_testecovid());
		stmt.setString(10, testecovid.getLogradouro_testecovid());
		stmt.setString(11, testecovid.getNumero_testecovid());
		stmt.setString(12, testecovid.getComplemento_testecovid());
		stmt.setString(13, testecovid.getBairro_testecovid());
		stmt.setString(14, testecovid.getTipo_testecovid());
		stmt.setString(15, testecovid.getDatarealizacao_testecovid());
		stmt.setString(16, testecovid.getHorarealizacao_testecovid());
		stmt.setString(17, testecovid.getDataresultado_testecovid());
		stmt.setString(18, testecovid.getResultado_testecovid());
		
		stmt.executeUpdate();
		
		close();
	}

}


		

	

	
	





