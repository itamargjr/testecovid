package entity;

public class Edu_escolas_matricula_quantidade {
	
	private Integer ano_matricula;
	private Integer id_modensinovagas;
	private Integer id_escola;
	
	private String nome_escola;
	private Integer id_modensinoanos;
	private String descricao_modensinoanos;
	private Integer id_modensino;
	private String descricao_modensino;
	private Integer matriculas;
	
	public Edu_escolas_matricula_quantidade() {
		super();
	}

	public Edu_escolas_matricula_quantidade(Integer ano_matricula, Integer id_modensinovagas, Integer id_escola,
			String nome_escola, Integer id_modensinoanos, String descricao_modensinoanos,
			Integer id_modensino, String descricao_modensino, Integer matriculas) {
		super();
		this.ano_matricula = ano_matricula;
		this.id_modensinovagas = id_modensinovagas;
		this.id_escola = id_escola;
		this.nome_escola = nome_escola;
		this.id_modensinoanos = id_modensinoanos;
		this.descricao_modensinoanos = descricao_modensinoanos;
		this.id_modensino = id_modensino;
		this.descricao_modensino = descricao_modensino;
		this.matriculas = matriculas;
	}

	public Integer getAno_matricula() {
		return ano_matricula;
	}

	public void setAno_matricula(Integer ano_matricula) {
		this.ano_matricula = ano_matricula;
	}

	public Integer getId_modensinovagas() {
		return id_modensinovagas;
	}

	public void setId_modensinovagas(Integer id_modensinovagas) {
		this.id_modensinovagas = id_modensinovagas;
	}

	public Integer getId_escola() {
		return id_escola;
	}

	public void setId_escola(Integer id_escola) {
		this.id_escola = id_escola;
	}

	public String getNome_escola() {
		return nome_escola;
	}

	public void setNome_escola(String nome_escola) {
		this.nome_escola = nome_escola;
	}

	public Integer getId_modensinoanos() {
		return id_modensinoanos;
	}

	public void setId_modensinoanos(Integer id_modensinoanos) {
		this.id_modensinoanos = id_modensinoanos;
	}

	public String getDescricao_modensinoanos() {
		return descricao_modensinoanos;
	}

	public void setDescricao_modensinoanos(String descricao_modensinoanos) {
		this.descricao_modensinoanos = descricao_modensinoanos;
	}

	public Integer getId_modensino() {
		return id_modensino;
	}

	public void setId_modensino(Integer id_modensino) {
		this.id_modensino = id_modensino;
	}

	public String getDescricao_modensino() {
		return descricao_modensino;
	}

	public void setDescricao_modensino(String descricao_modensino) {
		this.descricao_modensino = descricao_modensino;
	}

	public Integer getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(Integer matriculas) {
		this.matriculas = matriculas;
	}

	@Override
	public String toString() {
		return "Edu_escolas_matricula_quantidade [ano_matricula=" + ano_matricula + ", id_modensinovagas="
				+ id_modensinovagas + ", id_escola=" + id_escola + ", nome_escola=" + nome_escola
				+ ", id_modensinoanos=" + id_modensinoanos + ", descricao_modensinoanos=" + descricao_modensinoanos
				+ ", id_modensino=" + id_modensino + ", descricao_modensino=" + descricao_modensino + ", matriculas="
				+ matriculas + "]";
	}

}
