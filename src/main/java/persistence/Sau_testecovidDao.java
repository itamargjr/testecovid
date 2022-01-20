package persistence;

public class Sau_testecovidDao extends Dao {
	
	public Integer retornaDisponibilidade(String data, Integer hora) throws Exception{
		
		Integer agendados = 0;
		
		open();
		
		String statement = "select count(a.id_testecovid) as agendados " +
                			"from sau_testecovid a " +
                			"where a.data_testecovid = ? and a.horanum_testecovid = ?";

		stmt = con.prepareStatement(statement);
		
		stmt.setString(1, data);
		stmt.setInt(2, hora);

		rs = stmt.executeQuery();

		if (rs.next()) {
			agendados =  rs.getInt("agendados");
		}
		
		close();
		
		return agendados;
		
	}

}


		

	

	
	





