package persistence;

import java.util.ArrayList;
import java.util.List;

import entity.Edu_escola_paineldevagas;
import entity.Edu_escolas_modensinovagas;

//import java.lang.reflect.*;

public class Edu_escola_paineldevagasDao extends Dao {
	
	public Integer gravarVagas(List<Edu_escola_paineldevagas> vagaslista) throws Exception {
		
//		static <T> void inspect(Class<T> klazz) {
//	        Field[] fields = klazz.getDeclaredFields();
//	        System.out.printf("%d fields:%n", fields.length);
//	        for (Field field : fields) {
//	            System.out.printf("%s %s %s%n",
//	                Modifier.toString(field.getModifiers()),
//	                field.getType().getSimpleName(),
//	                field.getName()
//	            );
//	        }
//	    }
		
//		Field[] fields = vagaslista.get(0).getClass().getDeclaredFields();
//        
//        for (Field field : fields) {
//        	
//            System.out.println(field.getName());  
//        }
		
		Integer registros = 0;
		
		//System.out.println(vagaslista);
		
		for (Edu_escola_paineldevagas vl : vagaslista) {
			tratavaga(vl.getVagas_bercario(), vl.getId_escola(), 1, vl.getAno());
			tratavaga(vl.getVagas_infantil1(), vl.getId_escola(), 2, vl.getAno());
			tratavaga(vl.getVagas_infantil2(), vl.getId_escola(), 3, vl.getAno());
			tratavaga(vl.getVagas_infantil3(), vl.getId_escola(), 4, vl.getAno());
			tratavaga(vl.getVagas_infantil4(), vl.getId_escola(), 5, vl.getAno());
			tratavaga(vl.getVagas_infantil5(), vl.getId_escola(), 6, vl.getAno());
			tratavaga(vl.getVagas_1ano(), vl.getId_escola(), 7, vl.getAno());
			tratavaga(vl.getVagas_2ano(), vl.getId_escola(), 8, vl.getAno());
			tratavaga(vl.getVagas_3ano(), vl.getId_escola(), 9, vl.getAno());
			tratavaga(vl.getVagas_4ano(), vl.getId_escola(), 10, vl.getAno());
			tratavaga(vl.getVagas_5ano(), vl.getId_escola(), 11, vl.getAno());
			tratavaga(vl.getVagas_6ano(), vl.getId_escola(), 12, vl.getAno());
			tratavaga(vl.getVagas_7ano(), vl.getId_escola(), 13, vl.getAno());
			tratavaga(vl.getVagas_8ano(), vl.getId_escola(), 14, vl.getAno());
			tratavaga(vl.getVagas_9ano(), vl.getId_escola(), 15, vl.getAno());
			tratavaga(vl.getVagas_fase1(), vl.getId_escola(), 16, vl.getAno());
			tratavaga(vl.getVagas_fase2(), vl.getId_escola(), 17, vl.getAno());
			tratavaga(vl.getVagas_fase3(), vl.getId_escola(), 18, vl.getAno());
			tratavaga(vl.getVagas_fase4(), vl.getId_escola(), 19, vl.getAno());
			tratavaga(vl.getVagas_fase5(), vl.getId_escola(), 20, vl.getAno());
			tratavaga(vl.getVagas_fase6(), vl.getId_escola(), 21, vl.getAno());
			tratavaga(vl.getVagas_fase7(), vl.getId_escola(), 22, vl.getAno());
			tratavaga(vl.getVagas_fase8(), vl.getId_escola(), 23, vl.getAno());
			tratavaga(vl.getVagas_fase9(), vl.getId_escola(), 24, vl.getAno());
		}
		
		return registros;
	}
	
	private Integer vagajaexiste(Integer idEscola, Integer idAno, Integer ano) throws Exception {
		Integer idVaga = new Edu_escolas_modensinovagasDao().consultar(idEscola, idAno, ano);
		
		return idVaga;
	}
	
	private void tratavaga(Integer quantvagas, Integer IdEscola, Integer IdModEnsino, Integer ano) throws Exception {
		
		Integer IdVaga = vagajaexiste(IdEscola, IdModEnsino, ano);
		
		if ((quantvagas!=null)&&(quantvagas!=0)) {
				
			Edu_escolas_modensinovagas vaga = new Edu_escolas_modensinovagas(IdVaga, ano, IdEscola, IdModEnsino, 
												  quantvagas, null, 0, null, null, 0);
			
			if (IdVaga > 0) {
				new Edu_escolas_modensinovagasDao().alterar(vaga);													
			} else {					
				new Edu_escolas_modensinovagasDao().gravar(vaga);
			}
		} else {
			
			if (IdVaga > 0) {
				new Edu_escolas_modensinovagasDao().excluir(IdVaga);													
			}
		}

	}
	
	public List<Edu_escola_paineldevagas> montaPainel(Edu_escola_paineldevagas painelvagas) throws Exception {
		List<Edu_escola_paineldevagas> lista = new ArrayList<Edu_escola_paineldevagas>();
		
		open();
		
		String statement =  "SELECT id_escola, nome_escola FROM edu_escolas b WHERE 1=1 ";

		if ((painelvagas.getId_escola()!=null)&&(painelvagas.getId_escola()!=0)) {
			statement = statement + " and id_escola = " + painelvagas.getId_escola();
		}
		
		stmt = con.prepareStatement(statement);
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			Edu_escola_paineldevagas v = new Edu_escola_paineldevagas(painelvagas.getAno(), 
											rs.getInt("id_escola"), rs.getString("nome_escola"));
			
			lista.add(v);			
		}
		
		for (int i = 0; i < lista.size(); i++) {
			statement =  "SELECT " +
		                 "  a.id_escola, a.id_modensinovagas, a.ano_modensinovagas, " +
		                 "  a.id_modensinoanos, a.vagas_modensinovagas, " +
		                 "  b.descricao_modensinoanos " +
		                 "FROM " +
		                 "  edu_escolas_modensinovagas a, edu_escolas_modensinoanos b " +
		                 "WHERE " +
		                 "  a.id_modensinoanos = b.id_modensinoanos and " +
		                 "  a.ano_modensinovagas = " + painelvagas.getAno() + " and " +
		                 "  a.id_escola = " + lista.get(i).getId_escola();
			
			stmt = con.prepareStatement(statement);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				if (rs.getInt("id_modensinoanos")==1) {
					lista.get(i).setVagas_bercario(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==2) {
					lista.get(i).setVagas_infantil1(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==3) {
					lista.get(i).setVagas_infantil2(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==4) {
					lista.get(i).setVagas_infantil3(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==5) {
					lista.get(i).setVagas_infantil4(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==6) {
					lista.get(i).setVagas_infantil5(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==7) {
					lista.get(i).setVagas_1ano(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==8) {
					lista.get(i).setVagas_2ano(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==9) {
					lista.get(i).setVagas_3ano(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==10) {
					lista.get(i).setVagas_4ano(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==11) {
					lista.get(i).setVagas_5ano(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==12) {
					lista.get(i).setVagas_6ano(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==13) {
					lista.get(i).setVagas_7ano(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==14) {
					lista.get(i).setVagas_8ano(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==15) {
					lista.get(i).setVagas_9ano(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==16) {
					lista.get(i).setVagas_fase1(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==17) {
					lista.get(i).setVagas_fase2(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==18) {
					lista.get(i).setVagas_fase3(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==19) {
					lista.get(i).setVagas_fase4(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==20) {
					lista.get(i).setVagas_fase5(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==21) {
					lista.get(i).setVagas_fase6(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==22) {
					lista.get(i).setVagas_fase7(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==23) {
					lista.get(i).setVagas_fase8(rs.getInt("vagas_modensinovagas"));
				} else if (rs.getInt("id_modensinoanos")==24) {
					lista.get(i).setVagas_fase9(rs.getInt("vagas_modensinovagas"));
				} 
			}
		}

		
		close();
		
		return lista;
		
	}

}
