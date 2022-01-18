package persistence;

import java.util.ArrayList;
import java.util.List;

import entity.Edu_escolas_modensinoanos;
import entity.Edu_escolas_modensinovagas;

public class Edu_escolas_modensinovagasDao extends Dao {
	
	public void gravar(Edu_escolas_modensinovagas vagas) throws Exception {
		
		open();
		
		stmt = con.prepareStatement("insert into edu_escolas_modensinovagas " +
									"(id_modensinovagas, ano_modensinovagas, " +
									" id_escola, id_modensinoanos, " +
									" vagas_modensinovagas) values " +
									"(null, ?, ?, ?, ?)");
		
		stmt.setInt(1, vagas.getAno_modensinovagas());
		stmt.setInt(2, vagas.getId_escola());
		stmt.setInt(3, vagas.getId_modensinoanos());
		stmt.setInt(4, vagas.getVagas_modensinovagas());
		
		//System.out.println("Gravou: " + vagas);
		
		stmt.executeUpdate();
		
		close();
	}
	
	public void alterar(Edu_escolas_modensinovagas vagas) throws Exception {
		
		open();
		
		stmt = con.prepareStatement("update edu_escolas_modensinovagas " +
									"set vagas_modensinovagas = ? " +
									"where id_modensinovagas = ?");
		
		stmt.setInt(1, vagas.getVagas_modensinovagas());
		stmt.setInt(2, vagas.getId_modensinovagas());
		
		//System.out.println("Alterou: " + vagas);
		
		stmt.executeUpdate();	
		
		close();
	}
	
	public void excluir(Integer id) throws Exception {
		
		open();
		
		stmt = con.prepareStatement("delete from edu_escolas_modensinovagas " +
									"where id_modensinovagas = ?");
		
		stmt.setInt(1, id);
		
		//System.out.println("Excluiu: " + id);
		
		stmt.executeUpdate();
		
		close();
	}
	
	public Edu_escolas_modensinovagas consultardesc(Integer idEscola, String descAno, Integer ano) throws Exception {
		Edu_escolas_modensinovagas vaga = new Edu_escolas_modensinovagas();
		
		open();
		
		stmt = con.prepareStatement("SELECT " + 
			    "  a.id_modensinovagas, a.ano_modensinovagas, " +
			    "  a.id_escola, a.id_modensinoanos, a.vagas_modensinovagas, " +
				"   " +
				"  b.nome_escola, " +
				" " +  
				"  c.id_modensino, c.descricao_modensinoanos, " +
				" " +  
				"  d.descricao_modensino " +
				"FROM " +
				"  edu_escolas_modensinovagas a, edu_escolas b, " +
				"  edu_escolas_modensinoanos c, edu_escolas_modensino d " +
				"WHERE " + 
				"  a.id_escola          = b.id_escola        AND " +
				"  a.id_modensinoanos   = c.id_modensinoanos AND " +
				"  c.id_modensino       = d.id_modensino     AND " +
				"  a.id_escola          = ?                  and " +
				"  a.ano_modensinovagas = ?                  and " +
				"  c.descricao_modensinoanos = ?");
		
		stmt.setInt(1, idEscola);
		stmt.setInt(2, ano);
		stmt.setString(3, descAno);
		
		rs = stmt.executeQuery();
		
		if (rs.next()) {
			vaga = new Edu_escolas_modensinovagas(rs.getInt("id_modensinovagas"), 
					rs.getInt("ano_modensinovagas"), rs.getInt("id_escola"), rs.getInt("id_modensinoanos"), 
					rs.getInt("vagas_modensinovagas"), rs.getString("descricao_modensinoanos"), rs.getInt("id_modensino"), 
					rs.getString("descricao_modensino"), rs.getString("nome_escola"), 0);
		}
		
		close();
		
		return vaga;
	}
	
	public Integer consultar(Integer idEscola, Integer idAno, Integer ano) throws Exception {
		Integer id = 0;
		
		open();
		
		stmt = con.prepareStatement("select id_modensinovagas from edu_escolas_modensinovagas " +
									"where id_escola = ? and ano_modensinovagas = ? and id_modensinoanos = ?");
		
		stmt.setInt(1, idEscola);
		stmt.setInt(2, ano);
		stmt.setInt(3, idAno);
		
		rs = stmt.executeQuery();
		
		if (rs.next()) {
			id = rs.getInt("id_modensinovagas");
		}
		
		close();
		
		return id;
	}
	
	public List<String> findEtapas(Integer idEscola) throws Exception {
		List<String> lista = new ArrayList<String>();
		
		open();
		
		String statement =  "SELECT " + 
						    "  distinct d.descricao_modensino " +
							"FROM " +
							"  edu_escolas_modensinovagas a, edu_escolas b, " +
							"  edu_escolas_modensinoanos c, edu_escolas_modensino d " +
							"WHERE " + 
							"  a.id_escola        = b.id_escola AND " +
							"  a.id_modensinoanos = c.id_modensinoanos AND " +
							"  c.id_modensino     = d.id_modensino  AND " +
							"  a.id_escola        = " + idEscola;
		
		stmt = con.prepareStatement(statement);
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {			
			lista.add(rs.getString("descricao_modensino"));
		}
		
		close();
		
		return lista;
	}
	

	
	public List<Edu_escolas_modensinoanos> findbyModEnsino(Integer idEscola, String modensino) throws Exception{
		List <Edu_escolas_modensinoanos> lista = new ArrayList<Edu_escolas_modensinoanos>();
		
		open();
		
		String statement =  "SELECT " + 
						    "  a.id_modensinovagas, a.ano_modensinovagas, " +
						    "  a.id_escola, a.id_modensinoanos, a.vagas_modensinovagas, " +
							"   " +
							"  b.nome_escola, " +
							" " +  
							"  c.id_modensino, c.descricao_modensinoanos, " +
							" " +  
							"  d.descricao_modensino " +
							"FROM " +
							"  edu_escolas_modensinovagas a, edu_escolas b, " +
							"  edu_escolas_modensinoanos c, edu_escolas_modensino d " +
							"WHERE " + 
							"  a.id_escola        = b.id_escola AND " +
							"  a.id_modensinoanos = c.id_modensinoanos AND " +
							"  c.id_modensino     = d.id_modensino AND " + 
							"  a.id_escola        = " + idEscola + " AND " +
							"  d.descricao_modensino = '" + modensino + "'";
		                   	
		stmt = con.prepareStatement(statement);
		
		//System.out.println(statement);
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			Edu_escolas_modensinoanos m = new Edu_escolas_modensinoanos(rs.getInt("id_modensinoanos"), rs.getInt("id_modensino"), 
					                                      rs.getString("descricao_modensinoanos"), rs.getString("descricao_modensino"));
			lista.add(m);
		}
		
		return lista;
		
	}
	
	public List<Edu_escolas_modensinovagas> findSobradeVagas(Integer ano) throws Exception {
		List<Edu_escolas_modensinovagas> lista = new ArrayList<Edu_escolas_modensinovagas>();
		
		open();
		
		String statement =  "select *  from " +
					        "( " +
							"SELECT " +
							"  a.id_modensinovagas, a.ano_modensinovagas, " +
							"  a.id_escola, a.id_modensinoanos, a.vagas_modensinovagas, " +
							"" + 
							"  b.nome_escola, " +
							"" + 
							"  c.id_modensino, c.descricao_modensinoanos, " +
							" " +
							"  d.descricao_modensino, " +
							"" +
							"  count(e.id_candidato) as candidatos " +
							"FROM " +
							"  edu_escolas_modensinovagas a, edu_escolas b, " +
							"  edu_escolas_modensinoanos c, edu_escolas_modensino d, " +
							"  edu_matr_candidato e " +
							"WHERE " +
							"  a.id_escola        = b.id_escola AND " +
							"  a.id_modensinoanos = c.id_modensinoanos AND " +
							"  c.id_modensino     = d.id_modensino AND " +
							"  a.id_modensinovagas = e.id_modensinovagas1 and " +
						    "  a.ano_modensinovagas = e.ano_candidato and " +
							"  a.ano_modensinovagas = 2022 " +
							"group by " + 
							"  a.id_modensinovagas, a.ano_modensinovagas, " +
							"  a.id_escola, a.id_modensinoanos, a.vagas_modensinovagas, " +
							"" + 
							"  b.nome_escola, " +
							"" + 
							"  c.id_modensino, c.descricao_modensinoanos, " +
							"" + 
							"  d.descricao_modensino " +
							") aa " +
							"where aa.vagas_modensinovagas > aa.candidatos ";
		
		stmt = con.prepareStatement(statement);
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			Edu_escolas_modensinovagas v = new Edu_escolas_modensinovagas(rs.getInt("id_modensinovagas"), 
					rs.getInt("ano_modensinovagas"), rs.getInt("id_escola"), rs.getInt("id_modensinoanos"), 
					rs.getInt("vagas_modensinovagas"), rs.getString("descricao_modensinoanos"), rs.getInt("id_modensino"), 
					rs.getString("descricao_modensino"), rs.getString("nome_escola"), rs.getInt("candidatos"));
			
			lista.add(v);
		}
		
		close();
		
		return lista;
	}
	
	public List<Edu_escolas_modensinovagas> findAll(Edu_escolas_modensinovagas vagas) throws Exception {
		List<Edu_escolas_modensinovagas> lista = new ArrayList<Edu_escolas_modensinovagas>();
		
		open();
		
		String statement =  "SELECT " + 
						    "  a.id_modensinovagas, a.ano_modensinovagas, " +
						    "  a.id_escola, a.id_modensinoanos, a.vagas_modensinovagas, " +
							"   " +
							"  b.nome_escola, " +
							" " +  
							"  c.id_modensino, c.descricao_modensinoanos, " +
							" " +  
							"  d.descricao_modensino, " +
							" " +
							"  count(e.id_candidato) as candidatos " +
							"FROM " +
							"  edu_escolas_modensinovagas a, edu_escolas b, " +
							"  edu_escolas_modensinoanos c, edu_escolas_modensino d, " +
							"  edu_matr_candidato e " +
							"WHERE " + 
							"  a.id_escola        = b.id_escola AND " +
							"  a.id_modensinoanos = c.id_modensinoanos AND " +
							"  c.id_modensino     = d.id_modensino AND " +
							"  a.id_modensinovagas = e.id_modensinovagas1";
		
		if ((vagas.getNome_escola()!=null)&&(!vagas.getNome_escola().equalsIgnoreCase(""))) {
			statement = statement + " and b.nome_escola like '%" + vagas.getNome_escola() + "%'";
		}
	
		if ((vagas.getId_escola()!=null)&&(vagas.getId_escola()!=0)) {
			statement = statement + " and a.id_escola = " + vagas.getId_escola();
		}
		
		if ((vagas.getId_modensino()!=null)&&(vagas.getId_modensino()!=0)) {
			statement = statement + " and c.id_modensino = " + vagas.getId_modensino();
		}
		
		if ((vagas.getId_modensinoanos()!=null)&&(vagas.getId_modensinoanos()!=0)) {
			statement = statement + " and a.id_modensinoanos = " + vagas.getId_modensinoanos();
		}
		
		if ((vagas.getAno_modensinovagas()!=null)&&(vagas.getAno_modensinovagas()!=0)) {
			statement = statement + " and a.ano_modensinovagas = " + vagas.getAno_modensinovagas();
		}
		
		statement = statement + " group by " +
				    "  a.id_modensinovagas, a.ano_modensinovagas, " +
				    "  a.id_escola, a.id_modensinoanos, a.vagas_modensinovagas, " +
					"   " +
					"  b.nome_escola, " +
					" " +  
					"  c.id_modensino, c.descricao_modensinoanos, " +
					" " +  
					"  d.descricao_modensino"; 
		
		stmt = con.prepareStatement(statement);
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			Edu_escolas_modensinovagas v = new Edu_escolas_modensinovagas(rs.getInt("id_modensinovagas"), 
					rs.getInt("ano_modensinovagas"), rs.getInt("id_escola"), rs.getInt("id_modensinoanos"), 
					rs.getInt("vagas_modensinovagas"), rs.getString("descricao_modensinoanos"), rs.getInt("id_modensino"), 
					rs.getString("descricao_modensino"), rs.getString("nome_escola"), rs.getInt("candidatos"));
			
			lista.add(v);
		}
		
		close();
		
		return lista;
	}
	
	public Edu_escolas_modensinovagas findVaga(Integer idEscola, Integer idModEnsinoAnos) throws Exception {
		Edu_escolas_modensinovagas vaga = new Edu_escolas_modensinovagas();
		
		open();
		
		String statement =  "SELECT " + 
						    "  a.id_modensinovagas, a.ano_modensinovagas, " +
						    "  a.id_escola, a.id_modensinoanos, a.vagas_modensinovagas, " +
							"   " +
							"  b.nome_escola, " +
							" " +  
							"  c.id_modensino, c.descricao_modensinoanos, " +
							" " +  
							"  d.descricao_modensino " +
							"FROM " +
							"  edu_escolas_modensinovagas a, edu_escolas b, " +
							"  edu_escolas_modensinoanos c, edu_escolas_modensino d " +
							"WHERE " + 
							"  a.id_escola        = b.id_escola AND " +
							"  a.id_modensinoanos = c.id_modensinoanos AND " +
							"  c.id_modensino     = d.id_modensino AND " +
							"  a.id_escola        = ? AND " +
							"  a.id_modensinoanos = ?";
		
		stmt = con.prepareStatement(statement);
		
		stmt.setInt(1, idEscola);
		stmt.setInt(2, idModEnsinoAnos);
		
		rs = stmt.executeQuery();
		
		if (rs.next()) {
			vaga = new Edu_escolas_modensinovagas(rs.getInt("id_modensinovagas"), 
					rs.getInt("ano_modensinovagas"), rs.getInt("id_escola"), rs.getInt("id_modensinoanos"), 
					rs.getInt("vagas_modensinovagas"), rs.getString("descricao_modensinoanos"), rs.getInt("id_modensino"), 
					rs.getString("descricao_modensino"), rs.getString("nome_escola"), 0);
			
		}
		
		close();
		
		return vaga;
	}
}
