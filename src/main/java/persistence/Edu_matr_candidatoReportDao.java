package persistence;

import java.util.ArrayList;
import java.util.List;

import entity.Edu_matr_candidatoReport;

public class Edu_matr_candidatoReportDao extends Dao {
	
	public List<Edu_matr_candidatoReport> findCandidatopeloID(Integer cand) throws Exception {
		List<Edu_matr_candidatoReport> lista = new ArrayList<Edu_matr_candidatoReport>();
		
		open();
		
		String statement = "select " + 
				"  aa.*, " + 
				"  bb.nome_escola as nome_escola1, cc.nome_escola as nome_escola2, " +
				"  dd.nome_escola as nome_escola3, " +
				"  gg.descricao_modensino, ff.descricao_modensinoanos " +
				"from ( " +
				"select " +
				"  a.id_candidato, a.id_responsavel, a.nome_candidato, a.mae_candidato,  " +
				"  a.pai_candidato, a.nascimento_candidato, a.alunodarede_candidato,  " +
				"  a.matricularede_candidato, a.redeorigem_candidato, a.estciv_candidato,  " +
				"  a.sexo_candidato, a.nacionalidade_candidato, a.email_candidato, " +
				"  a.celular_candidato, a.fixo_candidato, a.bairro_candidato,  " +
				"  a.irmaonaescola_candidato, a.nomeirmao_candidato, a.cpf_candidato,  " +
				"  a.id_escola, a.id_modensinovagas1, a.id_modensinovagas2, a.datainscricao_candidato,  " +
				"  a.id_modensinovagas3, a.id_modensinovagas, a.ano_candidato, " +
				"  a.tipo_candidato, a.necespec_candidato, a.cid_candidato, a.criterio_candidato, " +
				"  a.posicao1_candidato, a.posicao2_candidato, a.posicao3_candidato, " +
 
				"  b.nome_responsavel, b.tipo_responsavel, b.cpf_responsavel,  " +
				"  b.email_responsavel, b.celular_responsavel, b.identidade_responsavel,  " +
				"  b.emissoridt_responsavel, " +

				"  c.nome_escola, " +

				"  d.id_escola as id_escola1, d.vagas_modensinovagas, " +

				"  e.id_escola as id_escola2, " +

				"  f.id_escola as id_escola3, " +

				"  g.id_escola as id_escolaselecionada, " +

				"  h.tipo_endereco, h.identidade_endereco, h.cep_endereco,  " +
				"  h.uf_endereco, h.logradouro_endereco, h.numero_endereco,  " +
				"  h.complemento_endereco, h.codigo_municipio, h.bairro_endereco, " +
				"  i.descricao_cid10 " +
				"from " +
				"  edu_matr_candidato a  " +
				"    left outer join edu_escolas c on (a.id_escola = c.id_escola) " +
				"    left outer join edu_escolas_modensinovagas d on (a.id_modensinovagas1 = d.id_modensinovagas) " +
				"    left outer join edu_escolas_modensinovagas e on (a.id_modensinovagas2 = e.id_modensinovagas) " +
				"    left outer join edu_escolas_modensinovagas f on (a.id_modensinovagas3 = f.id_modensinovagas) " +
				"    left outer join edu_escolas_modensinovagas g on (a.id_modensinovagas = g.id_modensinovagas)  " +
				"    left outer join cid10 i on (a.cid_candidato = i.codigo_cid10),  " +
				"  edu_matr_responsavel b, end_endereco h " +
				"where " +
				"  a.id_candidato = " + cand + " and " +
				"  a.id_responsavel = b.id_responsavel AND " +
				"  a.id_candidato = h.identidade_endereco AND " +
				"  h.tipo_endereco = 'C' " +
				") aa " +
				"  left outer join edu_escolas bb on (aa.id_escola1 = bb.id_escola) " +
				"  left outer join edu_escolas cc on (aa.id_escola2 = cc.id_escola) " +
				"  left outer join edu_escolas dd on (aa.id_escola3 = dd.id_escola), " +
				"  edu_escolas_modensinovagas ee, edu_escolas_modensinoanos ff, " +
				"  edu_escolas_modensino gg " +
				"where " +
				"  aa.id_modensinovagas1 = ee.id_modensinovagas and " +
				"  ee.id_modensinoanos   = ff.id_modensinoanos  and " +
				"  ff.id_modensino       = gg.id_modensino ";
		
		stmt = con.prepareStatement(statement);
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			Edu_matr_candidatoReport item = new Edu_matr_candidatoReport(rs.getInt("id_candidato"),
					rs.getInt("id_responsavel"),
					rs.getString("nome_candidato"),
					rs.getString("mae_candidato"),
					rs.getString("pai_candidato"),
					rs.getString("nascimento_candidato"),
					rs.getString("alunodarede_candidato"),
					rs.getString("matricularede_candidato"),
					rs.getString("redeorigem_candidato"),
					rs.getString("estciv_candidato"),
					rs.getString("sexo_candidato"),
					rs.getString("nacionalidade_candidato"),
					rs.getString("email_candidato"),
					rs.getString("celular_candidato"),
					rs.getString("fixo_candidato"),
					rs.getString("bairro_candidato"),
					
					rs.getString("irmaonaescola_candidato"),
					rs.getString("nomeirmao_candidato"),
					rs.getInt("id_escola"),
					
					rs.getInt("id_modensinovagas1"), 
					rs.getInt("id_modensinovagas2"), 
					rs.getInt("id_modensinovagas3"), 
					rs.getInt("id_modensinovagas"),  
					
					rs.getInt("ano_candidato"),
					
					rs.getString("nome_responsavel"),
					rs.getString("tipo_responsavel"),
					rs.getString("cpf_responsavel"),
					rs.getString("email_responsavel"),
					rs.getString("celular_responsavel"),
					rs.getInt("identidade_responsavel"),
					rs.getString("emissoridt_responsavel"),
					
					rs.getString("nome_escola"),

					rs.getInt("id_escola1"),
					rs.getInt("id_escola2"),
					rs.getInt("id_escola3"),
					rs.getInt("id_escolaselecionada"),

					rs.getString("tipo_endereco"), 
					rs.getString("identidade_endereco"), 
					rs.getString("cep_endereco"), 
					rs.getString("uf_endereco"), 
					rs.getString("logradouro_endereco"), 
					rs.getString("numero_endereco"), 
					rs.getString("complemento_endereco"), 
					rs.getString("codigo_municipio"), 
					rs.getString("bairro_endereco"), 
					rs.getString("nome_escola1"), 
					rs.getString("nome_escola2"), 
					rs.getString("nome_escola3"), 
					null, //rs.getString("nome_escolaselecionada"), // eu não tenho a escola selecionada neste momento
					rs.getString("descricao_modensino"),
					rs.getString("descricao_modensinoanos"), 
					rs.getString("tipo_candidato"), 
					rs.getString("necespec_candidato"), 
					rs.getString("cid_candidato"), 
					rs.getString("descricao_cid10"), 
					rs.getString("cpf_candidato"), 
					rs.getString("datainscricao_candidato"),
					rs.getInt("posicao1_candidato"), 
					rs.getInt("posicao2_candidato"), 
					rs.getInt("posicao3_candidato"),
					rs.getString("criterio_candidato"),
					rs.getInt("vagas_modensinovagas"));
			
			lista.add(item);
		}						  
		
		close();
		
		return lista;
	}
	
	public List<Edu_matr_candidatoReport> findCandidato(Edu_matr_candidatoReport cand) throws Exception {
		List<Edu_matr_candidatoReport> lista = new ArrayList<Edu_matr_candidatoReport>();
		
		open();
		
		String statement = "select " + 
				"  aa.*, " + 
				"  bb.nome_escola as nome_escola1, cc.nome_escola as nome_escola2, " +
				"  dd.nome_escola as nome_escola3, " +
				"  gg.descricao_modensino, ff.descricao_modensinoanos " +
				"from ( " +
				"select " +
				"  a.id_candidato, a.id_responsavel, a.nome_candidato, a.mae_candidato,  " +
				"  a.pai_candidato, a.nascimento_candidato, a.alunodarede_candidato,  " +
				"  a.matricularede_candidato, a.redeorigem_candidato, a.estciv_candidato,  " +
				"  a.sexo_candidato, a.nacionalidade_candidato, a.email_candidato, " +
				"  a.celular_candidato, a.fixo_candidato, a.bairro_candidato, a.datainscricao_candidato, " +
				"  a.irmaonaescola_candidato, a.nomeirmao_candidato, a.cpf_candidato, " +
				"  a.id_escola, a.id_modensinovagas1, a.id_modensinovagas2,  " +
				"  a.id_modensinovagas3, a.id_modensinovagas, a.ano_candidato, " +
				"  a.tipo_candidato, a.necespec_candidato, a.cid_candidato, a.criterio_candidato, " +
				"  a.posicao1_candidato, a.posicao2_candidato, a.posicao3_candidato, " +
 
				"  b.nome_responsavel, b.tipo_responsavel, b.cpf_responsavel,  " +
				"  b.email_responsavel, b.celular_responsavel, b.identidade_responsavel,  " +
				"  b.emissoridt_responsavel, " +

				"  c.nome_escola, " +

				"  d.id_escola as id_escola1, d.vagas_modensinovagas, " +

				"  e.id_escola as id_escola2, " +

				"  f.id_escola as id_escola3, " +

				"  g.id_escola as id_escolaselecionada, " +

				"  h.tipo_endereco, h.identidade_endereco, h.cep_endereco,  " +
				"  h.uf_endereco, h.logradouro_endereco, h.numero_endereco,  " +
				"  h.complemento_endereco, h.codigo_municipio, h.bairro_endereco, " +
				"  i.descricao_cid10 " +
				"from " +
				"  edu_matr_candidato a  " +
				"    left outer join edu_escolas c on (a.id_escola = c.id_escola) " +
				"    left outer join edu_escolas_modensinovagas d on (a.id_modensinovagas1 = d.id_modensinovagas) " +
				"    left outer join edu_escolas_modensinovagas e on (a.id_modensinovagas2 = e.id_modensinovagas) " +
				"    left outer join edu_escolas_modensinovagas f on (a.id_modensinovagas3 = f.id_modensinovagas) " +
				"    left outer join edu_escolas_modensinovagas g on (a.id_modensinovagas = g.id_modensinovagas)  " +
				"    left outer join cid10 i on (a.cid_candidato = i.codigo_cid10),  " +
				"  edu_matr_responsavel b, end_endereco h " +
				"where " +
				"  a.ano_candidato = '" + cand.getAno_candidato() + "' and " +
				"  a.id_responsavel = b.id_responsavel AND " +
				"  a.id_candidato = h.identidade_endereco AND " +
				"  h.tipo_endereco = 'C' ";
		
		if ((cand.getNome_candidato()!=null)&&(!cand.getNome_candidato().equalsIgnoreCase(""))) {
			statement = statement + " and a.nome_candidato like '%" + cand.getNome_candidato() + "%'";
		}
		
		if ((cand.getEmail_candidato()!=null)&&(!cand.getEmail_candidato().equalsIgnoreCase(""))) {
			statement = statement + " and a.email_candidato = '" + cand.getEmail_candidato() + "'";
		}
		
		if ((cand.getCpf_candidato()!=null)&&(!cand.getCpf_candidato().equalsIgnoreCase(""))) {
			statement = statement + " and a.cpf_candidato = '" + cand.getCpf_candidato() + "'";
		}
		
		if ((cand.getCid_candidato()!=null)&&(!cand.getCid_candidato().equalsIgnoreCase(""))) {
			statement = statement + " and a.cid_candidato = '" + cand.getCid_candidato() + "'";
		}
		
		if ((cand.getNecespec_candidato()!=null)&&(!cand.getNecespec_candidato().equalsIgnoreCase(""))) {
			statement = statement + " and a.necespec_candidato = '" + cand.getNecespec_candidato() + "'";
		}
		
		if ((cand.getTipo_candidato()!=null)&&(!cand.getTipo_candidato().equalsIgnoreCase(""))) {
			statement = statement + " and a.tipo_candidato = '" + cand.getTipo_candidato() + "'";
		}
		
		if ((cand.getBairro_candidato()!=null)&&(!cand.getBairro_candidato().equalsIgnoreCase(""))) {
			statement = statement + " and a.bairro_candidato = '" + cand.getBairro_candidato() + "'";
		}		
		
		if ((cand.getData_ini()!=null)&&(!cand.getData_ini().equalsIgnoreCase("")) && (cand.getData_fim()!=null)&&(!cand.getData_fim().equalsIgnoreCase(""))) {
			statement = statement + " and STR_TO_DATE(a.datainscricao_candidato, '%d/%m/%Y') between STR_TO_DATE('" + cand.getData_ini() + "', '%d/%m/%Y') and STR_TO_DATE('" + cand.getData_fim() + "', '%d/%m/%Y')";					
		} else if ((cand.getData_ini()!=null)&&(!cand.getData_ini().equalsIgnoreCase(""))) {
			statement = statement + " and STR_TO_DATE(a.datainscricao_candidato, '%d/%m/%Y') = STR_TO_DATE('" + cand.getData_ini() + "', '%d/%m/%Y')";	
		}		
		
		String statement2 = ") aa " +
				"  left outer join edu_escolas bb on (aa.id_escola1 = bb.id_escola) " +
				"  left outer join edu_escolas cc on (aa.id_escola2 = cc.id_escola) " +
				"  left outer join edu_escolas dd on (aa.id_escola3 = dd.id_escola), " +
				"  edu_escolas_modensinovagas ee, edu_escolas_modensinoanos ff, " +
				"  edu_escolas_modensino gg " +
				"where " +
				"  aa.id_modensinovagas1 = ee.id_modensinovagas and " +
				"  ee.id_modensinoanos   = ff.id_modensinoanos  and " +
				"  ff.id_modensino       = gg.id_modensino ";
		
		if ((cand.getDescricao_modensino()!=null)&&(!cand.getDescricao_modensino().equalsIgnoreCase(""))) {
			statement2 = statement2 + " and gg.descricao_modensino = '" + cand.getDescricao_modensino() + "'";
		}
		
		if ((cand.getDescricao_modensinoanos()!=null)&&(!cand.getDescricao_modensinoanos().equalsIgnoreCase(""))) {
			statement2 = statement2 + " and ff.descricao_modensinoanos = '" + cand.getDescricao_modensinoanos() + "'";
		}
		
		if ((cand.getId_escola()!=null)&&(cand.getId_escola()!=0)) {
			statement2 = statement2 + " and (aa.id_escola1 = " + cand.getId_escola();
			statement2 = statement2 + " or aa.id_escola2 = " + cand.getId_escola();
			statement2 = statement2 + " or aa.id_escola3 = " + cand.getId_escola() + ")";
		}
		
		//System.out.println(statement + statement2);
		
		stmt = con.prepareStatement(statement + statement2);
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			Edu_matr_candidatoReport item = new Edu_matr_candidatoReport(rs.getInt("id_candidato"),
					rs.getInt("id_responsavel"),
					rs.getString("nome_candidato"),
					rs.getString("mae_candidato"),
					rs.getString("pai_candidato"),
					rs.getString("nascimento_candidato"),
					rs.getString("alunodarede_candidato"),
					rs.getString("matricularede_candidato"),
					rs.getString("redeorigem_candidato"),
					rs.getString("estciv_candidato"),
					rs.getString("sexo_candidato"),
					rs.getString("nacionalidade_candidato"),
					rs.getString("email_candidato"),
					rs.getString("celular_candidato"),
					rs.getString("fixo_candidato"),
					rs.getString("bairro_candidato"),
					
					rs.getString("irmaonaescola_candidato"),
					rs.getString("nomeirmao_candidato"),
					rs.getInt("id_escola"),
					
					rs.getInt("id_modensinovagas1"), 
					rs.getInt("id_modensinovagas2"), 
					rs.getInt("id_modensinovagas3"), 
					rs.getInt("id_modensinovagas"),  
					
					rs.getInt("ano_candidato"),
					
					rs.getString("nome_responsavel"),
					rs.getString("tipo_responsavel"),
					rs.getString("cpf_responsavel"),
					rs.getString("email_responsavel"),
					rs.getString("celular_responsavel"),
					rs.getInt("identidade_responsavel"),
					rs.getString("emissoridt_responsavel"),
					
					rs.getString("nome_escola"),

					rs.getInt("id_escola1"),
					rs.getInt("id_escola2"),
					rs.getInt("id_escola3"),
					rs.getInt("id_escolaselecionada"),

					rs.getString("tipo_endereco"), 
					rs.getString("identidade_endereco"), 
					rs.getString("cep_endereco"), 
					rs.getString("uf_endereco"), 
					rs.getString("logradouro_endereco"), 
					rs.getString("numero_endereco"), 
					rs.getString("complemento_endereco"), 
					rs.getString("codigo_municipio"), 
					rs.getString("bairro_endereco"), 
					rs.getString("nome_escola1"), 
					rs.getString("nome_escola2"), 
					rs.getString("nome_escola3"), 
					null, //rs.getString("nome_escolaselecionada"), // eu não tenho a escola selecionada neste momento
					rs.getString("descricao_modensino"),
					rs.getString("descricao_modensinoanos"), 
					rs.getString("tipo_candidato"), 
					rs.getString("necespec_candidato"), 
					rs.getString("cid_candidato"), 
					rs.getString("descricao_cid10"), 
					rs.getString("cpf_candidato"), 
					rs.getString("datainscricao_candidato"),
					rs.getInt("posicao1_candidato"), 
					rs.getInt("posicao2_candidato"), 
					rs.getInt("posicao3_candidato"),
					rs.getString("criterio_candidato"),
					rs.getInt("vagas_modensinovagas"));
			
			lista.add(item);
		}						  
		
		close();
		
		return lista;
	}
	
	public List<Edu_matr_candidatoReport> findCandidatoClass(Edu_matr_candidatoReport cand) throws Exception {
		List<Edu_matr_candidatoReport> lista = new ArrayList<Edu_matr_candidatoReport>();
		
		open();
		
		String statement = "select " + 
				"  aa.*, " + 
				"  bb.nome_escola as nome_escola1, cc.nome_escola as nome_escola2, " +
				"  dd.nome_escola as nome_escola3, " +
				"  gg.descricao_modensino, ff.descricao_modensinoanos " +
				"from ( " +
				"select " +
				"  a.id_candidato, a.id_responsavel, a.nome_candidato, a.mae_candidato,  " +
				"  a.pai_candidato, a.nascimento_candidato, a.alunodarede_candidato,  " +
				"  a.matricularede_candidato, a.redeorigem_candidato, a.estciv_candidato,  " +
				"  a.sexo_candidato, a.nacionalidade_candidato, a.email_candidato, " +
				"  a.celular_candidato, a.fixo_candidato, a.bairro_candidato, a.datainscricao_candidato, " +
				"  a.irmaonaescola_candidato, a.nomeirmao_candidato, a.cpf_candidato, " +
				"  a.id_escola, a.id_modensinovagas1, a.id_modensinovagas2,  " +
				"  a.id_modensinovagas3, a.id_modensinovagas, a.ano_candidato, " +
				"  a.tipo_candidato, a.necespec_candidato, a.cid_candidato, a.criterio_candidato, " +
				"  a.posicao1_candidato, a.posicao2_candidato, a.posicao3_candidato, " +
 
				"  b.nome_responsavel, b.tipo_responsavel, b.cpf_responsavel,  " +
				"  b.email_responsavel, b.celular_responsavel, b.identidade_responsavel,  " +
				"  b.emissoridt_responsavel, " +

				"  c.nome_escola, " +

				"  d.id_escola as id_escola1, d.vagas_modensinovagas, " +

				"  e.id_escola as id_escola2, " +

				"  f.id_escola as id_escola3, " +

				"  g.id_escola as id_escolaselecionada, " +

				"  h.tipo_endereco, h.identidade_endereco, h.cep_endereco,  " +
				"  h.uf_endereco, h.logradouro_endereco, h.numero_endereco,  " +
				"  h.complemento_endereco, h.codigo_municipio, h.bairro_endereco, " +
				"  i.descricao_cid10 " +
				"from " +
				"  edu_matr_candidato a  " +
				"    left outer join edu_escolas c on (a.id_escola = c.id_escola) " +
				"    left outer join edu_escolas_modensinovagas d on (a.id_modensinovagas1 = d.id_modensinovagas) " +
				"    left outer join edu_escolas_modensinovagas e on (a.id_modensinovagas2 = e.id_modensinovagas) " +
				"    left outer join edu_escolas_modensinovagas f on (a.id_modensinovagas3 = f.id_modensinovagas) " +
				"    left outer join edu_escolas_modensinovagas g on (a.id_modensinovagas = g.id_modensinovagas)  " +
				"    left outer join cid10 i on (a.cid_candidato = i.codigo_cid10),  " +
				"  edu_matr_responsavel b, end_endereco h " +
				"where " +
				"  a.ano_candidato = '" + cand.getAno_candidato() + "' and " +
				"  a.id_responsavel = b.id_responsavel AND " +
				"  a.id_candidato = h.identidade_endereco AND " +
				"  a.posicao1_candidato is not null AND " +
				"  h.tipo_endereco = 'C' ";

		if ((cand.getId_modensinovagas()!=null)&&(cand.getId_modensinovagas()!=0)) {
			statement = statement + " and a.id_modensinovagas = '" + cand.getId_modensinovagas() + "'";
		}		
		
		String statement2 = ") aa " +
				"  left outer join edu_escolas bb on (aa.id_escola1 = bb.id_escola) " +
				"  left outer join edu_escolas cc on (aa.id_escola2 = cc.id_escola) " +
				"  left outer join edu_escolas dd on (aa.id_escola3 = dd.id_escola), " +
				"  edu_escolas_modensinovagas ee, edu_escolas_modensinoanos ff, " +
				"  edu_escolas_modensino gg " +
				"where " +
				"  aa.id_modensinovagas1 = ee.id_modensinovagas and " +
				"  ee.id_modensinoanos   = ff.id_modensinoanos  and " +
				"  ff.id_modensino       = gg.id_modensino ";

		stmt = con.prepareStatement(statement + statement2 + " order by posicao1_candidato, posicao2_candidato");
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			Edu_matr_candidatoReport item = new Edu_matr_candidatoReport(rs.getInt("id_candidato"),
					rs.getInt("id_responsavel"),
					rs.getString("nome_candidato"),
					rs.getString("mae_candidato"),
					rs.getString("pai_candidato"),
					rs.getString("nascimento_candidato"),
					rs.getString("alunodarede_candidato"),
					rs.getString("matricularede_candidato"),
					rs.getString("redeorigem_candidato"),
					rs.getString("estciv_candidato"),
					rs.getString("sexo_candidato"),
					rs.getString("nacionalidade_candidato"),
					rs.getString("email_candidato"),
					rs.getString("celular_candidato"),
					rs.getString("fixo_candidato"),
					rs.getString("bairro_candidato"),
					
					rs.getString("irmaonaescola_candidato"),
					rs.getString("nomeirmao_candidato"),
					rs.getInt("id_escola"),
					
					rs.getInt("id_modensinovagas1"), 
					rs.getInt("id_modensinovagas2"), 
					rs.getInt("id_modensinovagas3"), 
					rs.getInt("id_modensinovagas"),  
					
					rs.getInt("ano_candidato"),
					
					rs.getString("nome_responsavel"),
					rs.getString("tipo_responsavel"),
					rs.getString("cpf_responsavel"),
					rs.getString("email_responsavel"),
					rs.getString("celular_responsavel"),
					rs.getInt("identidade_responsavel"),
					rs.getString("emissoridt_responsavel"),
					
					rs.getString("nome_escola"),

					rs.getInt("id_escola1"),
					rs.getInt("id_escola2"),
					rs.getInt("id_escola3"),
					rs.getInt("id_escolaselecionada"),

					rs.getString("tipo_endereco"), 
					rs.getString("identidade_endereco"), 
					rs.getString("cep_endereco"), 
					rs.getString("uf_endereco"), 
					rs.getString("logradouro_endereco"), 
					rs.getString("numero_endereco"), 
					rs.getString("complemento_endereco"), 
					rs.getString("codigo_municipio"), 
					rs.getString("bairro_endereco"), 
					rs.getString("nome_escola1"), 
					rs.getString("nome_escola2"), 
					rs.getString("nome_escola3"), 
					null, //rs.getString("nome_escolaselecionada"), // eu não tenho a escola selecionada neste momento
					rs.getString("descricao_modensino"),
					rs.getString("descricao_modensinoanos"), 
					rs.getString("tipo_candidato"), 
					rs.getString("necespec_candidato"), 
					rs.getString("cid_candidato"), 
					rs.getString("descricao_cid10"), 
					rs.getString("cpf_candidato"), 
					rs.getString("datainscricao_candidato"),
					rs.getInt("posicao1_candidato"), 
					rs.getInt("posicao2_candidato"), 
					rs.getInt("posicao3_candidato"),
					rs.getString("criterio_candidato"),
					rs.getInt("vagas_modensinovagas"));
			
			lista.add(item);
		}						  
		
		close();
		
		return lista;
	}
	
	public List<Edu_matr_candidatoReport> findCandidatoNAOClass(Edu_matr_candidatoReport cand) throws Exception {
		List<Edu_matr_candidatoReport> lista = new ArrayList<Edu_matr_candidatoReport>();
		
		open();
		
		String statement = "select " + 
				"  aa.*, " + 
				"  bb.nome_escola as nome_escola1, cc.nome_escola as nome_escola2, " +
				"  dd.nome_escola as nome_escola3, " +
				"  gg.descricao_modensino, ff.descricao_modensinoanos " +
				"from ( " +
				"select " +
				"  a.id_candidato, a.id_responsavel, a.nome_candidato, a.mae_candidato,  " +
				"  a.pai_candidato, a.nascimento_candidato, a.alunodarede_candidato,  " +
				"  a.matricularede_candidato, a.redeorigem_candidato, a.estciv_candidato,  " +
				"  a.sexo_candidato, a.nacionalidade_candidato, a.email_candidato, " +
				"  a.celular_candidato, a.fixo_candidato, a.bairro_candidato, a.datainscricao_candidato, " +
				"  a.irmaonaescola_candidato, a.nomeirmao_candidato, a.cpf_candidato, " +
				"  a.id_escola, a.id_modensinovagas1, a.id_modensinovagas2,  " +
				"  a.id_modensinovagas3, a.id_modensinovagas, a.ano_candidato, " +
				"  a.tipo_candidato, a.necespec_candidato, a.cid_candidato, a.criterio_candidato, " +
				"  a.posicao1_candidato, a.posicao2_candidato, a.posicao3_candidato, " +
 
				"  b.nome_responsavel, b.tipo_responsavel, b.cpf_responsavel,  " +
				"  b.email_responsavel, b.celular_responsavel, b.identidade_responsavel,  " +
				"  b.emissoridt_responsavel, " +

				"  c.nome_escola, " +

				"  d.id_escola as id_escola1, d.vagas_modensinovagas, " +

				"  e.id_escola as id_escola2, " +

				"  f.id_escola as id_escola3, " +

				"  g.id_escola as id_escolaselecionada, " +

				"  h.tipo_endereco, h.identidade_endereco, h.cep_endereco,  " +
				"  h.uf_endereco, h.logradouro_endereco, h.numero_endereco,  " +
				"  h.complemento_endereco, h.codigo_municipio, h.bairro_endereco, " +
				"  i.descricao_cid10 " +
				"from " +
				"  edu_matr_candidato a  " +
				"    left outer join edu_escolas c on (a.id_escola = c.id_escola) " +
				"    left outer join edu_escolas_modensinovagas d on (a.id_modensinovagas1 = d.id_modensinovagas) " +
				"    left outer join edu_escolas_modensinovagas e on (a.id_modensinovagas2 = e.id_modensinovagas) " +
				"    left outer join edu_escolas_modensinovagas f on (a.id_modensinovagas3 = f.id_modensinovagas) " +
				"    left outer join edu_escolas_modensinovagas g on (a.id_modensinovagas = g.id_modensinovagas)  " +
				"    left outer join cid10 i on (a.cid_candidato = i.codigo_cid10),  " +
				"  edu_matr_responsavel b, end_endereco h " +
				"where " +
				"  a.ano_candidato = '" + cand.getAno_candidato() + "' and " +
				"  a.id_responsavel = b.id_responsavel AND " +
				"  a.id_candidato = h.identidade_endereco AND " +
				"  a.posicao1_candidato is not null AND " +
				"  h.tipo_endereco = 'C' and " +
				"  a.id_modensinovagas is null ";

		if ((cand.getId_modensinovagas1()!=null)&&(cand.getId_modensinovagas1()!=0)) {
			statement = statement + " and a.id_modensinovagas1 = '" + cand.getId_modensinovagas1() + "'";
		}		
		
		String statement2 = ") aa " +
				"  left outer join edu_escolas bb on (aa.id_escola1 = bb.id_escola) " +
				"  left outer join edu_escolas cc on (aa.id_escola2 = cc.id_escola) " +
				"  left outer join edu_escolas dd on (aa.id_escola3 = dd.id_escola), " +
				"  edu_escolas_modensinovagas ee, edu_escolas_modensinoanos ff, " +
				"  edu_escolas_modensino gg " +
				"where " +
				"  aa.id_modensinovagas1 = ee.id_modensinovagas and " +
				"  ee.id_modensinoanos   = ff.id_modensinoanos  and " +
				"  ff.id_modensino       = gg.id_modensino ";
		
		//System.out.println(statement + statement2 + " order by posicao1_candidato, posicao2_candidato");

		stmt = con.prepareStatement(statement + statement2 + " order by posicao1_candidato, posicao2_candidato");
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			Edu_matr_candidatoReport item = new Edu_matr_candidatoReport(rs.getInt("id_candidato"),
					rs.getInt("id_responsavel"),
					rs.getString("nome_candidato"),
					rs.getString("mae_candidato"),
					rs.getString("pai_candidato"),
					rs.getString("nascimento_candidato"),
					rs.getString("alunodarede_candidato"),
					rs.getString("matricularede_candidato"),
					rs.getString("redeorigem_candidato"),
					rs.getString("estciv_candidato"),
					rs.getString("sexo_candidato"),
					rs.getString("nacionalidade_candidato"),
					rs.getString("email_candidato"),
					rs.getString("celular_candidato"),
					rs.getString("fixo_candidato"),
					rs.getString("bairro_candidato"),
					
					rs.getString("irmaonaescola_candidato"),
					rs.getString("nomeirmao_candidato"),
					rs.getInt("id_escola"),
					
					rs.getInt("id_modensinovagas1"), 
					rs.getInt("id_modensinovagas2"), 
					rs.getInt("id_modensinovagas3"), 
					rs.getInt("id_modensinovagas"),  
					
					rs.getInt("ano_candidato"),
					
					rs.getString("nome_responsavel"),
					rs.getString("tipo_responsavel"),
					rs.getString("cpf_responsavel"),
					rs.getString("email_responsavel"),
					rs.getString("celular_responsavel"),
					rs.getInt("identidade_responsavel"),
					rs.getString("emissoridt_responsavel"),
					
					rs.getString("nome_escola"),

					rs.getInt("id_escola1"),
					rs.getInt("id_escola2"),
					rs.getInt("id_escola3"),
					rs.getInt("id_escolaselecionada"),

					rs.getString("tipo_endereco"), 
					rs.getString("identidade_endereco"), 
					rs.getString("cep_endereco"), 
					rs.getString("uf_endereco"), 
					rs.getString("logradouro_endereco"), 
					rs.getString("numero_endereco"), 
					rs.getString("complemento_endereco"), 
					rs.getString("codigo_municipio"), 
					rs.getString("bairro_endereco"), 
					rs.getString("nome_escola1"), 
					rs.getString("nome_escola2"), 
					rs.getString("nome_escola3"), 
					null, //rs.getString("nome_escolaselecionada"), // eu não tenho a escola selecionada neste momento
					rs.getString("descricao_modensino"),
					rs.getString("descricao_modensinoanos"), 
					rs.getString("tipo_candidato"), 
					rs.getString("necespec_candidato"), 
					rs.getString("cid_candidato"), 
					rs.getString("descricao_cid10"), 
					rs.getString("cpf_candidato"), 
					rs.getString("datainscricao_candidato"),
					rs.getInt("posicao1_candidato"), 
					rs.getInt("posicao2_candidato"), 
					rs.getInt("posicao3_candidato"),
					rs.getString("criterio_candidato"),
					rs.getInt("vagas_modensinovagas"));
			
			lista.add(item);
		}						  
		
		close();
		
		return lista;
	}
	
	public Edu_matr_candidatoReport findCandidatoConsulta(Edu_matr_candidatoReport cand) throws Exception {
		Edu_matr_candidatoReport c = new Edu_matr_candidatoReport();
		
		open();
		
		String statement = "select " + 
				"  aa.*, " + 
				"  bb.nome_escola as nome_escola1, cc.nome_escola as nome_escola2, " +
				"  dd.nome_escola as nome_escola3, hh.nome_escola as nome_escolaselecionada, " +
				"  gg.descricao_modensino, ff.descricao_modensinoanos " +
				"from ( " +
				"select " +
				"  a.id_candidato, a.id_responsavel, a.nome_candidato, a.mae_candidato,  " +
				"  a.pai_candidato, a.nascimento_candidato, a.alunodarede_candidato,  " +
				"  a.matricularede_candidato, a.redeorigem_candidato, a.estciv_candidato,  " +
				"  a.sexo_candidato, a.nacionalidade_candidato, a.email_candidato, " +
				"  a.celular_candidato, a.fixo_candidato, a.bairro_candidato,  " +
				"  a.irmaonaescola_candidato, a.nomeirmao_candidato, a.cpf_candidato, " +
				"  a.id_escola, a.id_modensinovagas1, a.id_modensinovagas2, a.datainscricao_candidato, " +
				"  a.id_modensinovagas3, a.id_modensinovagas, a.ano_candidato, " +
				"  a.tipo_candidato, a.necespec_candidato, a.cid_candidato, a.criterio_candidato, " +
				"  a.posicao1_candidato, a.posicao2_candidato, a.posicao3_candidato, " +
 
				"  b.nome_responsavel, b.tipo_responsavel, b.cpf_responsavel,  " +
				"  b.email_responsavel, b.celular_responsavel, b.identidade_responsavel,  " +
				"  b.emissoridt_responsavel, " +

				"  c.nome_escola, " +

				"  d.id_escola as id_escola1, d.vagas_modensinovagas, " +

				"  e.id_escola as id_escola2, " +

				"  f.id_escola as id_escola3, " +

				"  g.id_escola as id_escolaselecionada, " +

				"  h.tipo_endereco, h.identidade_endereco, h.cep_endereco,  " +
				"  h.uf_endereco, h.logradouro_endereco, h.numero_endereco,  " +
				"  h.complemento_endereco, h.codigo_municipio, h.bairro_endereco, " +
				"  i.descricao_cid10 " +
				"from " +
				"  edu_matr_candidato a  " +
				"    left outer join edu_escolas c on (a.id_escola = c.id_escola) " +
				"    left outer join edu_escolas_modensinovagas d on (a.id_modensinovagas1 = d.id_modensinovagas) " +
				"    left outer join edu_escolas_modensinovagas e on (a.id_modensinovagas2 = e.id_modensinovagas) " +
				"    left outer join edu_escolas_modensinovagas f on (a.id_modensinovagas3 = f.id_modensinovagas) " +
				"    left outer join edu_escolas_modensinovagas g on (a.id_modensinovagas = g.id_modensinovagas)  " +
				"    left outer join cid10 i on (a.cid_candidato = i.codigo_cid10),  " +
				"  edu_matr_responsavel b, end_endereco h " +
				"where " +
				"  a.cpf_candidato = '" + cand.getCpf_candidato() + "' and " +
				"  a.ano_candidato = '" + cand.getAno_candidato() + "' and " +
				"  a.id_responsavel = b.id_responsavel AND " +
				"  a.id_candidato = h.identidade_endereco AND " +
				"  h.tipo_endereco = 'C' " +
				") aa " +
				"  left outer join edu_escolas bb on (aa.id_escola1 = bb.id_escola) " +
				"  left outer join edu_escolas cc on (aa.id_escola2 = cc.id_escola) " +
				"  left outer join edu_escolas hh on (aa.id_escolaselecionada = hh.id_escola) " +
				"  left outer join edu_escolas dd on (aa.id_escola3 = dd.id_escola), " +
				"  edu_escolas_modensinovagas ee, edu_escolas_modensinoanos ff, " +
				"  edu_escolas_modensino gg " +
				"where " +
				"  aa.id_modensinovagas1 = ee.id_modensinovagas and " +
				"  ee.id_modensinoanos   = ff.id_modensinoanos  and " +
				"  ff.id_modensino       = gg.id_modensino ";
		
		stmt = con.prepareStatement(statement);
		
		rs = stmt.executeQuery();
		
		if (rs.next()) {
			c = new Edu_matr_candidatoReport(rs.getInt("id_candidato"),
					rs.getInt("id_responsavel"),
					rs.getString("nome_candidato"),
					rs.getString("mae_candidato"),
					rs.getString("pai_candidato"),
					rs.getString("nascimento_candidato"),
					rs.getString("alunodarede_candidato"),
					rs.getString("matricularede_candidato"),
					rs.getString("redeorigem_candidato"),
					rs.getString("estciv_candidato"),
					rs.getString("sexo_candidato"),
					rs.getString("nacionalidade_candidato"),
					rs.getString("email_candidato"),
					rs.getString("celular_candidato"),
					rs.getString("fixo_candidato"),
					rs.getString("bairro_candidato"),
					
					rs.getString("irmaonaescola_candidato"),
					rs.getString("nomeirmao_candidato"),
					rs.getInt("id_escola"),
					
					rs.getInt("id_modensinovagas1"), 
					rs.getInt("id_modensinovagas2"), 
					rs.getInt("id_modensinovagas3"), 
					rs.getInt("id_modensinovagas"),  
					
					rs.getInt("ano_candidato"),
					
					rs.getString("nome_responsavel"),
					rs.getString("tipo_responsavel"),
					rs.getString("cpf_responsavel"),
					rs.getString("email_responsavel"),
					rs.getString("celular_responsavel"),
					rs.getInt("identidade_responsavel"),
					rs.getString("emissoridt_responsavel"),
					
					rs.getString("nome_escola"),

					rs.getInt("id_escola1"),
					rs.getInt("id_escola2"),
					rs.getInt("id_escola3"),
					rs.getInt("id_escolaselecionada"),

					rs.getString("tipo_endereco"), 
					rs.getString("identidade_endereco"), 
					rs.getString("cep_endereco"), 
					rs.getString("uf_endereco"), 
					rs.getString("logradouro_endereco"), 
					rs.getString("numero_endereco"), 
					rs.getString("complemento_endereco"), 
					rs.getString("codigo_municipio"), 
					rs.getString("bairro_endereco"), 
					rs.getString("nome_escola1"), 
					rs.getString("nome_escola2"), 
					rs.getString("nome_escola3"), 
					rs.getString("nome_escolaselecionada"), 
					rs.getString("descricao_modensino"),
					rs.getString("descricao_modensinoanos"), 
					rs.getString("tipo_candidato"), 
					rs.getString("necespec_candidato"), 
					rs.getString("cid_candidato"), 
					rs.getString("descricao_cid10"), 
					rs.getString("cpf_candidato"), 
					rs.getString("datainscricao_candidato"),
					rs.getInt("posicao1_candidato"), 
					rs.getInt("posicao2_candidato"), 
					rs.getInt("posicao3_candidato"),
					rs.getString("criterio_candidato"),
					rs.getInt("vagas_modensinovagas"));

		}						  
		
		close();
		
		return c;
	}
	
	public void cancelaInscricao(Edu_matr_candidatoReport cand) throws Exception {
		open();
		
		stmt = con.prepareStatement("delete from edu_matr_candidato where id_candidato = ?");
		
		stmt.setInt(1, cand.getId_candidato());
		
		Integer linhasafetadas = stmt.executeUpdate();
		
		if (linhasafetadas==0) {
			throw new Exception("Erro ao cancelar candidato");
		} else {
			stmt = con.prepareStatement("delete from end_endereco where identidade_endereco = ? and tipo_endereco = 'C'");
			
			stmt.setInt(1, cand.getId_candidato());
			
			stmt.executeUpdate();
		}
		
		close();
	}

}
