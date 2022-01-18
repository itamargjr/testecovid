package persistence;

import java.util.ArrayList;
import java.util.List;

import entity.Edu_matr_candidatoSelecao;

public class Edu_matr_candidatoSelecaoDao extends Dao {
	
	public List<Edu_matr_candidatoSelecao> findCandidatoClass(Integer ano) throws Exception {
		List<Edu_matr_candidatoSelecao> lista = new ArrayList<Edu_matr_candidatoSelecao>();
		
		open();
		
//		SELECT 
//		  a.id_candidato, a.id_escola, b.id_escola as id_escolavaga, a.id_modensinovagas1, b.vagas_modensinovagas,  
//		  a.id_modensinovagas2, a.id_modensinovagas3
//		FROM 
//		  edu_matr_candidato a, edu_escolas_modensinovagas b 
//		WHERE 
//		  a.id_modensinovagas1 = b.id_modensinovagas and
//		  a.necespec_candidato = 'N' and
//   	a.bairro_candidato = 'Outro'
//		ORDER BY
//		  a.id_modensinovagas1, a.id_escola DESC
		
		String statement = "SELECT " +
				 		   "  a.id_candidato, a.id_escola, b.id_escola as id_escolavaga, " +
				           "  a.id_modensinovagas1, b.vagas_modensinovagas,  " +
						   "  a.id_modensinovagas2, a.id_modensinovagas3, a.criterio_candidato " +
				     	   "FROM " +
				           "  edu_matr_candidato a, edu_escolas_modensinovagas b " +
				           "WHERE " +
				           "  a.id_modensinovagas1 = b.id_modensinovagas and " +
				           "  a.ano_candidato = '" + ano + "' and " +
				           "  a.necespec_candidato = 'N' and " +
				           "  a.bairro_candidato <> 'Outro' " +
				           "ORDER BY " +
				           "  a.id_modensinovagas1, a.criterio_candidato, a.id_candidato ";
		
		stmt = con.prepareStatement(statement);
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			Edu_matr_candidatoSelecao c = new Edu_matr_candidatoSelecao(rs.getInt("id_candidato"),
					rs.getInt("id_escola"),
					rs.getInt("id_escolavaga"),				
					rs.getInt("id_modensinovagas1"),
					rs.getInt("vagas_modensinovagas"), 
					rs.getInt("id_modensinovagas2"), 
					rs.getInt("id_modensinovagas3"));
			
			lista.add(c);
		}						  
		
		close();
		
		return lista;
	}
	
	public List<Edu_matr_candidatoSelecao> findCandidatoClassSegOpcao(Integer ano) throws Exception {
		List<Edu_matr_candidatoSelecao> lista = new ArrayList<Edu_matr_candidatoSelecao>();
		
		open();
		
//		SELECT 
//		  a.id_candidato, a.id_escola, b.id_escola as id_escolavaga, 
//		  a.id_modensinovagas1, b.vagas_modensinovagas,  
//		  a.id_modensinovagas2, a.id_modensinovagas3, a.criterio_candidato 
//		FROM 
//		  edu_matr_candidato a, edu_escolas_modensinovagas b 
//		WHERE 
//		  a.id_modensinovagas2 = b.id_modensinovagas and 
//		  a.ano_candidato = 2022 and 
//		  a.necespec_candidato = 'N' and 
//		  a.bairro_candidato <> 'Outro' and
//		  a.id_modensinovagas is null
//		ORDER BY 
//		  a.id_modensinovagas2, a.id_candidato 
		
		String statement = "SELECT " +
				 		   "  a.id_candidato, a.id_escola, b.id_escola as id_escolavaga, " +
				           "  a.id_modensinovagas1, b.vagas_modensinovagas,  " +
						   "  a.id_modensinovagas2, a.id_modensinovagas3, a.criterio_candidato " +
				     	   "FROM " +
				           "  edu_matr_candidato a, edu_escolas_modensinovagas b " +
				           "WHERE " +
				           "  a.id_modensinovagas2 = b.id_modensinovagas and " +
				           "  a.ano_candidato = '" + ano + "' and " +
				           "  a.necespec_candidato = 'N' and " +
				           "  a.bairro_candidato <> 'Outro' and " +
				           "  a.id_modensinovagas is null " +
				           "ORDER BY " +
				           "  a.id_modensinovagas2, a.id_candidato ";
		
		stmt = con.prepareStatement(statement);
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			Edu_matr_candidatoSelecao c = new Edu_matr_candidatoSelecao(rs.getInt("id_candidato"),
					rs.getInt("id_escola"),
					rs.getInt("id_escolavaga"),				
					rs.getInt("id_modensinovagas1"),
					rs.getInt("vagas_modensinovagas"), 
					rs.getInt("id_modensinovagas2"), 
					rs.getInt("id_modensinovagas3"));
			
			lista.add(c);
		}						  
		
		close();
		
		return lista;
	}
	
	public void gravarcriterio(Integer idCand, String crit) throws Exception {
		String smtp = "UPDATE edu_matr_candidato set criterio_candidato = ? " +
	                  "WHERE id_candidato = ?";	
	
		open();	
		
		stmt = con.prepareStatement(smtp);

		stmt.setString(1, crit);
		stmt.setInt(2, idCand);
		
		stmt.executeUpdate();

		close();
	}
	
	public void gravarclassific(Integer idCand, Integer Classific) throws Exception {
		String smtp = "UPDATE edu_matr_candidato set posicao1_candidato = ? " +
	                  "WHERE id_candidato = ?";	
	
		open();	
		
		stmt = con.prepareStatement(smtp);

		stmt.setInt(1, Classific);
		stmt.setInt(2, idCand);
		
		stmt.executeUpdate();

		close();
	}
	
	public void gravarclassificcomescolha(Integer idCand, Integer Classific, Integer IdVaga) throws Exception {
		String smtp = "UPDATE edu_matr_candidato set posicao1_candidato = ?, id_modensinovagas = ? " +
	                  "WHERE id_candidato = ?";	
	
		open();	
		
		stmt = con.prepareStatement(smtp);

		stmt.setInt(1, Classific);
		stmt.setInt(2, IdVaga);
		stmt.setInt(3, idCand);
		
		stmt.executeUpdate();

		close();
	}
	
	public void gravarSegundaclassific(Integer idCand, Integer Classific) throws Exception {
		String smtp = "UPDATE edu_matr_candidato set posicao2_candidato = ? " +
	                  "WHERE id_candidato = ?";	
	
		open();	
		
		stmt = con.prepareStatement(smtp);

		stmt.setInt(1, Classific);
		stmt.setInt(2, idCand);
		
		stmt.executeUpdate();

		close();
	}
	
	public void gravarSegundaclassificcomescolha(Integer idCand, Integer Classific, Integer IdVaga) throws Exception {
		String smtp = "UPDATE edu_matr_candidato set posicao2_candidato = ?, id_modensinovagas = ? " +
	                  "WHERE id_candidato = ?";	
	
		open();	
		
		stmt = con.prepareStatement(smtp);

		stmt.setInt(1, Classific);
		stmt.setInt(2, IdVaga);
		stmt.setInt(3, idCand);
		
		stmt.executeUpdate();

		close();
	}

}
