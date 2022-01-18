package persistence;

import java.util.ArrayList;
import java.util.List;

import entity.Edu_escolas_modensino;

public class Edu_escolas_modensinoDao extends Dao {
	
	public List<Edu_escolas_modensino> findTodos() throws Exception{
		List<Edu_escolas_modensino> lista = new ArrayList<Edu_escolas_modensino>();
		
		open();
		
		String statement = "select " +
		                   "  a.id_modensino, a.descricao_modensino " +
				           "from " +
		                   "  edu_escolas_modensino a ";
		
		stmt = con.prepareStatement(statement);
		
		//System.out.println(statement);
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			Edu_escolas_modensino m  = new Edu_escolas_modensino(rs.getInt("id_modensino"), rs.getString("descricao_modensino"));
			
			lista.add(m);
		}
		
		close();
		
		return lista;
	}
	
	public List<Edu_escolas_modensino> findAll(Edu_escolas_modensino modensino) throws Exception{
		List<Edu_escolas_modensino> lista = new ArrayList<Edu_escolas_modensino>();
		
		open();
		
		String statement = "select " +
		                   "  a.id_modensino, a.descricao_modensino " +
				           "from " +
		                   "  edu_escolas_modensino a " +
				           "where " +
		                   "  1 = 1 ";
		
		if ((modensino.getId_modensino()!=null)&&(modensino.getId_modensino()!=0)) {
			statement = statement + " and a.id_modensino = " + modensino.getId_modensino();
		}
		
		if ((modensino.getDescricao_modensino()!=null)&&(!modensino.getDescricao_modensino().equalsIgnoreCase(""))) {
			statement = statement + " and a.descricao_modensino = " + modensino.getDescricao_modensino();
		}
		
		stmt = con.prepareStatement(statement);
		
		//System.out.println(statement);
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			Edu_escolas_modensino m  = new Edu_escolas_modensino(rs.getInt("id_modensino"), rs.getString("descricao_modensino"));
			
			lista.add(m);
		}
		
		close();
		
		return lista;
	}
	
	public void gravar(Edu_escolas_modensino modensino) throws Exception{
		
		String smtp = "insert into edu_escolas_modensino (id_modensino, descricao_modensino) " +
		              "values (null, ?)";	
	
		open();	
	
		stmt = con.prepareStatement(smtp);
	
		stmt.setString(1, modensino.getDescricao_modensino());
	
		stmt.execute();
	
		close();
		
	}

}


