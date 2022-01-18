package persistence;

import java.util.ArrayList;
import java.util.List;

import entity.Edu_escolas_modensinoanos;

public class Edu_escolas_modensinoanosDao extends Dao {
	
	public Edu_escolas_modensinoanos findAno(String desc) throws Exception{
		
		Edu_escolas_modensinoanos modensinoanos = new Edu_escolas_modensinoanos();
		
		open();
		
		String statement = "select " +
		                   "  a.id_modensinoanos, a.id_modensino, a.descricao_modensinoanos, " +
				           "  b.descricao_modensino " +
				           "from " +
		                   "  edu_escolas_modensinoanos a, edu_escolas_modensino b " +
				           "where " +
		                   "  a.id_modensino = b.id_modensino and " +
		                   "  a.descricao_modensinoanos	= '" + desc + "'";
		
		stmt = con.prepareStatement(statement);
		
		//System.out.println(statement);
		
		rs = stmt.executeQuery();
		
		if (rs.next()) {
			modensinoanos = new Edu_escolas_modensinoanos(rs.getInt("id_modensinoanos"), rs.getInt("id_modensino"), 
					                                      rs.getString("descricao_modensinoanos"), rs.getString("descricao_modensino"));
		}
		
		return modensinoanos;
		
	}
	
	public List<Edu_escolas_modensinoanos> findbyModEnsino(String modensino) throws Exception{
		List <Edu_escolas_modensinoanos> lista = new ArrayList<Edu_escolas_modensinoanos>();
		
		open();
		
		String statement = "select " +
		                   "  a.id_modensinoanos, a.id_modensino, a.descricao_modensinoanos, " +
				           "  b.descricao_modensino " +
				           "from " +
		                   "  edu_escolas_modensinoanos a, edu_escolas_modensino b " +
				           "where " +
		                   "  a.id_modensino = b.id_modensino "; 
		
		if ((modensino!=null)&&(!modensino.equalsIgnoreCase(""))) {
			statement = statement + "  and b.descricao_modensino = '" + modensino + "'";
		}
		                   	
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
	
	public List<Edu_escolas_modensinoanos> findTodos() throws Exception{
		List <Edu_escolas_modensinoanos> lista = new ArrayList<Edu_escolas_modensinoanos>();
		
		open();
		
		String statement = "select " +
		                   "  a.id_modensinoanos, a.id_modensino, a.descricao_modensinoanos, " +
				           "  b.descricao_modensino " +
				           "from " +
		                   "  edu_escolas_modensinoanos a, edu_escolas_modensino b " +
				           "where " +
		                   "  a.id_modensino = b.id_modensino ";
		                   	
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
	
	public List<Edu_escolas_modensinoanos> findbyIdModEnsino(Integer Id) throws Exception{
		List <Edu_escolas_modensinoanos> lista = new ArrayList<Edu_escolas_modensinoanos>();
		
		open();
		
		String statement = "select " +
		                   "  a.id_modensinoanos, a.id_modensino, a.descricao_modensinoanos, " +
				           "  b.descricao_modensino " +
				           "from " +
		                   "  edu_escolas_modensinoanos a, edu_escolas_modensino b " +
				           "where " +
		                   "  a.id_modensino = b.id_modensino "; 
		
		if ((Id!=null)&&(Id!=0)) {
			statement = statement + "  and a.id_modensino = " + Id;
		}
		                   	
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

}
