package entity;

public class Edu_escolas_matricula {
	
	private Integer id_matricula;
	private Integer ano_matricula;
	private Integer id_modensinovagas;
	private Integer id_candidato;
	private String data_matricula;
	private Integer id_escola;
	private Integer id_escolaorigem;
	
	private String nome_escola;
	private String nome_escolaorigem;
	private String nome_candidato;
	private String cpf_candidato;
	private Integer id_modensinoanos;
	private String descricao_modensinoanos;
	private Integer id_modensino;
	private String descricao_modensino;
	
	public Edu_escolas_matricula() {
		super();
	}

	public Edu_escolas_matricula(Integer id_matricula, Integer ano_matricula, Integer id_modensinovagas,
			Integer id_candidato, String data_matricula, Integer id_escola, Integer id_escolaorigem, String nome_escola,
			String nome_escolaorigem, String nome_candidato, String cpf_candidato, Integer id_modensinoanos,
			String descricao_modensinoanos, Integer id_modensino, String descricao_modensino) {
		super();
		this.id_matricula = id_matricula;
		this.ano_matricula = ano_matricula;
		this.id_modensinovagas = id_modensinovagas;
		this.id_candidato = id_candidato;
		this.data_matricula = data_matricula;
		this.id_escola = id_escola;
		this.id_escolaorigem = id_escolaorigem;
		this.nome_escola = nome_escola;
		this.nome_escolaorigem = nome_escolaorigem;
		this.nome_candidato = nome_candidato;
		this.cpf_candidato = cpf_candidato;
		this.id_modensinoanos = id_modensinoanos;
		this.descricao_modensinoanos = descricao_modensinoanos;
		this.id_modensino = id_modensino;
		this.descricao_modensino = descricao_modensino;
	}

	public Integer getId_matricula() {
		return id_matricula;
	}

	public void setId_matricula(Integer id_matricula) {
		this.id_matricula = id_matricula;
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

	public Integer getId_candidato() {
		return id_candidato;
	}

	public void setId_candidato(Integer id_candidato) {
		this.id_candidato = id_candidato;
	}

	public String getData_matricula() {
		return data_matricula;
	}

	public void setData_matricula(String data_matricula) {
		this.data_matricula = data_matricula;
	}

	public Integer getId_escola() {
		return id_escola;
	}

	public void setId_escola(Integer id_escola) {
		this.id_escola = id_escola;
	}

	public Integer getId_escolaorigem() {
		return id_escolaorigem;
	}

	public void setId_escolaorigem(Integer id_escolaorigem) {
		this.id_escolaorigem = id_escolaorigem;
	}

	public String getNome_escola() {
		return nome_escola;
	}

	public void setNome_escola(String nome_escola) {
		this.nome_escola = nome_escola;
	}

	public String getNome_escolaorigem() {
		return nome_escolaorigem;
	}

	public void setNome_escolaorigem(String nome_escolaorigem) {
		this.nome_escolaorigem = nome_escolaorigem;
	}

	public String getNome_candidato() {
		return nome_candidato;
	}

	public void setNome_candidato(String nome_candidato) {
		this.nome_candidato = nome_candidato;
	}

	public String getCpf_candidato() {
		return cpf_candidato;
	}

	public void setCpf_candidato(String cpf_candidato) {
		this.cpf_candidato = cpf_candidato;
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

	@Override
	public String toString() {
		return "Edu_escolas_matricula [id_matricula=" + id_matricula + ", ano_matricula=" + ano_matricula
				+ ", id_modensinovagas=" + id_modensinovagas + ", id_candidato=" + id_candidato + ", data_matricula="
				+ data_matricula + ", id_escola=" + id_escola + ", id_escolaorigem=" + id_escolaorigem
				+ ", nome_escola=" + nome_escola + ", nome_escolaorigem=" + nome_escolaorigem + ", nome_candidato="
				+ nome_candidato + ", cpf_candidato=" + cpf_candidato + ", id_modensinoanos=" + id_modensinoanos
				+ ", descricao_modensinoanos=" + descricao_modensinoanos + ", id_modensino=" + id_modensino
				+ ", descricao_modensino=" + descricao_modensino + "]";
	}
}
