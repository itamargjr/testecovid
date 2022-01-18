package persistence;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import entity.Edu_escolas_matricula;
import entity.Edu_escolas_matricula_quantidade;

public class Edu_escolas_matriculaDao extends Dao {
	
	public void gravar(Edu_escolas_matricula matricula) throws Exception{
		
		//System.out.println("gravar - matriculaDao");
		
		open();
		
		stmt = con.prepareStatement("insert into edu_escolas_matricula (" +
		                            "id_matricula, ano_matricula, id_modensinovagas, " +
		                            "id_candidato, data_matricula, id_escola, " +
		                            "id_escolaorigem) values " +
		                            "(0, ?, ?, ?, DATE_FORMAT(sysdate(),'%d/%m/%Y'), ?, ?)");
		
		stmt.setInt(1, matricula.getAno_matricula());
		stmt.setInt(2, matricula.getId_modensinovagas());
		stmt.setInt(3, matricula.getId_candidato());
		stmt.setInt(4, matricula.getId_escola());
		
		if ((matricula.getId_escolaorigem()==null)||(matricula.getId_escolaorigem()==0)) {
			
			//System.out.println("Setando nulo");
			
			stmt.setNull(5, Types.INTEGER);
		} else {
			
			//System.out.println("Setando: " + matricula.getId_escolaorigem());
			
			stmt.setInt(5, matricula.getId_escolaorigem());
		}
		
		//System.out.println("Executando update...");
		
		stmt.executeUpdate();
		
		//System.out.println("Executou");
		
		close();
	}
	
	public Boolean MatriculaJaExiste(Edu_escolas_matricula matricula) throws Exception{
		Boolean achou = false;
		
		open();
		
		String statement = "select a.id_matricula, a.ano_matricula, a.id_modensinovagas, " +
				           "a.id_candidato, a.data_matricula, a.id_escola, a.id_escolaorigem, " +
				           "b.cpf_candidato " +
				           "from edu_escolas_matricula a, edu_matr_candidato b " +
				           "where a.id_candidato = b.id_candidato ";
		
		if ((matricula.getAno_matricula()!=null)&&(matricula.getAno_matricula()!=0)) {
			statement = statement + " and a.ano_matricula = " + matricula.getAno_matricula();
		}
		
		if ((matricula.getId_candidato()!=null)&&(matricula.getId_candidato()!=0)) {
			statement = statement + " and a.id_candidato = " + matricula.getId_candidato();
		}
		
		if ((matricula.getCpf_candidato()!=null)&&(!matricula.getCpf_candidato().equalsIgnoreCase(""))) {
			statement = statement + " and b.cpf_candidato = '" + matricula.getCpf_candidato() + "'";
		}		
		
		if ((matricula.getId_escola()!=null)&&(matricula.getId_escola()!=0)) {
			statement = statement + " and a.id_escola = " + matricula.getId_escola();
		}
		
		stmt = con.prepareStatement(statement);
		
		rs = stmt.executeQuery();
		
		if (rs.next()) {
			achou = true;
		}
		
		close();
		
		return achou;
	}
	
	public Edu_escolas_matricula BuscaMatricula(Edu_escolas_matricula matricula) throws Exception{
		Edu_escolas_matricula matr = new Edu_escolas_matricula();
		
		open();
		
		String statement = "select a.id_matricula, a.ano_matricula, a.id_modensinovagas, " +
				           "a.id_candidato, a.data_matricula, a.id_escola, a.id_escolaorigem, " +
				           "b.nome_escola, " +
				           "c.nome_escola as nome_escolaorigem, " +
				           "d.nome_candidato, d.cpf_candidato, " +
				           "e.id_modensinoanos, " +
				           "f.descricao_modensinoanos, f.id_modensino, " +
				           "g.descricao_modensino " +
				           "from " +
				           "  edu_escolas_matricula a left outer join edu_escolas c on " +
				           "  (a.id_escolaorigem = c.id_escola), " +
				           "  edu_escolas b, edu_matr_candidato d, " +
				           "  edu_escolas_modensinovagas e, " +
				           "  edu_escolas_modensinoanos f, edu_escolas_modensino g " +
				           "where " +
				           "  a.id_escola = b.id_escola and " +
				           "  a.id_candidato = d.id_candidato and " +
				           "  a.id_modensinovagas = e.id_modensinovagas and " +
				           "  e.id_modensinoanos = f.id_modensinoanos and " +
				           "  f.id_modensino = g.id_modensino ";
		
		if ((matricula.getAno_matricula()!=null)&&(matricula.getAno_matricula()!=0)) {
			statement = statement + " and a.ano_matricula = " + matricula.getAno_matricula();
		}
		
		if ((matricula.getId_candidato()!=null)&&(matricula.getId_candidato()!=0)) {
			statement = statement + " and a.id_candidato = " + matricula.getId_candidato();
		}
		
		if ((matricula.getId_escola()!=null)&&(matricula.getId_escola()!=0)) {
			statement = statement + " and a.id_escola = " + matricula.getId_escola();
		}
		
		if ((matricula.getId_escolaorigem()!=null)&&(matricula.getId_escolaorigem()!=0)) {
			statement = statement + " and a.id_escolaorigem = " + matricula.getId_escolaorigem();
		}
		
		if ((matricula.getCpf_candidato()!=null)&&(!matricula.getCpf_candidato().equalsIgnoreCase(""))) {
			statement = statement + " and d.cpf_candidato = '" + matricula.getCpf_candidato() + "'";
		}
		
		if ((matricula.getId_modensino()!=null)&&(matricula.getId_modensino()!=0)) {
			statement = statement + " and f.id_modensino = " + matricula.getId_modensino();
		}
		
		if ((matricula.getId_modensinoanos()!=null)&&(matricula.getId_modensinoanos()!=0)) {
			statement = statement + " and e.id_modensinoanos = " + matricula.getId_modensinoanos();
		}		
		
		stmt = con.prepareStatement(statement);
		
		//System.out.println(statement);
		
		rs = stmt.executeQuery();
		
		if (rs.next()) {
			matr = new Edu_escolas_matricula(rs.getInt("id_matricula"), 
					                         rs.getInt("ano_matricula"), 
					                         rs.getInt("id_modensinovagas"), 
					                         rs.getInt("id_candidato"), 
					                         rs.getString("data_matricula"), 
					                         rs.getInt("id_escola"), 
					                         rs.getInt("id_escolaorigem"), 
					                         rs.getString("nome_escola"), 
					                         rs.getString("nome_escolaorigem"), 
					                         rs.getString("nome_candidato"), 
					                         rs.getString("cpf_candidato"), 
					                         rs.getInt("id_modensinoanos"), 
					                         rs.getString("descricao_modensinoanos"), 
					                         rs.getInt("id_modensino"), 
					                         rs.getString("descricao_modensino"));
		}
		
		close();
		
		return matr;
	}
	
	public List<Edu_escolas_matricula> findAll(Edu_escolas_matricula matricula) throws Exception{
		List<Edu_escolas_matricula> lista = new ArrayList<Edu_escolas_matricula>();
		
		open();
		
		String statement = "select a.id_matricula, a.ano_matricula, a.id_modensinovagas, " +
		           "a.id_candidato, a.data_matricula, a.id_escola, a.id_escolaorigem, " +
		           "b.nome_escola, " +
		           "c.nome_escola as nome_escolaorigem, " +
		           "d.nome_candidato, d.cpf_candidato, " +
		           "e.id_modensinoanos, " +
		           "f.descricao_modensinoanos, f.id_modensino, " +
		           "g.descricao_modensino " +
		           "from " +
		           "  edu_escolas_matricula a left outer join edu_escolas c on " +
		           "  (a.id_escolaorigem = c.id_escola), " +
		           "  edu_escolas b, edu_matr_candidato d, " +
		           "  edu_escolas_modensinovagas e, " +
		           "  edu_escolas_modensinoanos f, edu_escolas_modensino g " +
		           "where " +
		           "  a.id_escola = b.id_escola and " +
		           "  a.id_candidato = d.id_candidato and " +
		           "  a.id_modensinovagas = e.id_modensinovagas and " +
		           "  e.id_modensinoanos = f.id_modensinoanos and " +
		           "  f.id_modensino = g.id_modensino ";
		
		if ((matricula.getAno_matricula()!=null)&&(matricula.getAno_matricula()!=0)) {
			statement = statement + " and a.ano_matricula = " + matricula.getAno_matricula();
		}
		
		if ((matricula.getId_candidato()!=null)&&(matricula.getId_candidato()!=0)) {
			statement = statement + " and a.id_candidato = " + matricula.getId_candidato();
		}
		
		if ((matricula.getId_escola()!=null)&&(matricula.getId_escola()!=0)) {
			statement = statement + " and a.id_escola = " + matricula.getId_escola();
		}
		
		if ((matricula.getId_escolaorigem()!=null)&&(matricula.getId_escolaorigem()!=0)) {
			statement = statement + " and a.id_escolaorigem = " + matricula.getId_escolaorigem();
		}
		
		if ((matricula.getCpf_candidato()!=null)&&(!matricula.getCpf_candidato().equalsIgnoreCase(""))) {
			statement = statement + " and d.cpf_candidato = '" + matricula.getCpf_candidato() + "'";
		}
		
		if ((matricula.getId_modensino()!=null)&&(matricula.getId_modensino()!=0)) {
			statement = statement + " and f.id_modensino = " + matricula.getId_modensino();
		}
		
		if ((matricula.getId_modensinoanos()!=null)&&(matricula.getId_modensinoanos()!=0)) {
			statement = statement + " and e.id_modensinoanos = " + matricula.getId_modensinoanos();
		}		
		
		//System.out.println(statement);
		
		stmt = con.prepareStatement(statement);
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			Edu_escolas_matricula m = new Edu_escolas_matricula(rs.getInt("id_matricula"), 
					                         rs.getInt("ano_matricula"), 
					                         rs.getInt("id_modensinovagas"), 
					                         rs.getInt("id_candidato"), 
					                         rs.getString("data_matricula"), 
					                         rs.getInt("id_escola"), 
					                         rs.getInt("id_escolaorigem"), 
					                         rs.getString("nome_escola"), 
					                         rs.getString("nome_escolaorigem"), 
					                         rs.getString("nome_candidato"), 
					                         rs.getString("cpf_candidato"), 
					                         rs.getInt("id_modensinoanos"), 
					                         rs.getString("descricao_modensinoanos"), 
					                         rs.getInt("id_modensino"), 
					                         rs.getString("descricao_modensino"));
			
			lista.add(m);
		}
		
		close();
		
		return lista;
	}
	
	public List<Edu_escolas_matricula_quantidade> RetornaQuant (Edu_escolas_matricula matricula) throws Exception{
		List<Edu_escolas_matricula_quantidade> lista = new ArrayList<Edu_escolas_matricula_quantidade>();
		
		open();
		
		String statement = "select a.ano_matricula, a.id_modensinovagas, " +
		           "a.id_escola, " +
		           "b.nome_escola, " +
		           "e.id_modensinoanos, " +
		           "f.descricao_modensinoanos, f.id_modensino, " +
		           "g.descricao_modensino, count(a.id_candidato) as matriculas " +
		           "from " +
		           "  edu_escolas_matricula a left outer join edu_escolas c on " +
		           "  (a.id_escolaorigem = c.id_escola), " +
		           "  edu_escolas b, edu_matr_candidato d, " +
		           "  edu_escolas_modensinovagas e, " +
		           "  edu_escolas_modensinoanos f, edu_escolas_modensino g " +
		           "where " +
		           "  a.id_escola = b.id_escola and " +
		           "  a.id_candidato = d.id_candidato and " +
		           "  a.id_modensinovagas = e.id_modensinovagas and " +
		           "  e.id_modensinoanos = f.id_modensinoanos and " +
		           "  f.id_modensino = g.id_modensino ";
		
		if ((matricula.getAno_matricula()!=null)&&(matricula.getAno_matricula()!=0)) {
			statement = statement + " and a.ano_matricula = " + matricula.getAno_matricula();
		}
		
		if ((matricula.getId_escola()!=null)&&(matricula.getId_escola()!=0)) {
			statement = statement + " and a.id_escola = " + matricula.getId_escola();
		}
		
		if ((matricula.getId_escolaorigem()!=null)&&(matricula.getId_escolaorigem()!=0)) {
			statement = statement + " and a.id_escolaorigem = " + matricula.getId_escolaorigem();
		}
		
		if ((matricula.getCpf_candidato()!=null)&&(!matricula.getCpf_candidato().equalsIgnoreCase(""))) {
			statement = statement + " and d.cpf_candidato = '" + matricula.getCpf_candidato() + "'";
		}
		
		if ((matricula.getId_modensinoanos()!=null)&&(matricula.getId_modensinoanos()!=0)) {
			statement = statement + " and e.id_modensinoanos = " + matricula.getId_modensinoanos();
		}		
		
		//System.out.println(statement);
		
		stmt = con.prepareStatement(statement + " group BY " +
				                                " a.ano_matricula, a.id_modensinovagas,  " +
				                                " a.id_escola,  " +
				                                " b.nome_escola, " + 
				                                " e.id_modensinoanos, " + 
				                                " f.descricao_modensinoanos, f.id_modensino, " + 
												" g.descricao_modensino");
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			Edu_escolas_matricula_quantidade m = new Edu_escolas_matricula_quantidade(rs.getInt("ano_matricula"), 
					                         rs.getInt("id_modensinovagas"), 
					                         rs.getInt("id_escola"), 
					                         rs.getString("nome_escola"), 
					                         rs.getInt("id_modensinoanos"), 
					                         rs.getString("descricao_modensinoanos"), 
					                         rs.getInt("id_modensino"), 
					                         rs.getString("descricao_modensino"),
					                         rs.getInt("matriculas"));
			
			lista.add(m);
		}
		
		close();
		
		return lista;
	}
}
