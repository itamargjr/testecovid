package entity;

public class Edu_matr_candidatoSelecao {
	
	private Integer id_candidato;
	private Integer id_escola;
	private Integer id_escolavaga;
	private Integer id_modensinovagas1; 
	private Integer vagas_modensinovagas;
	private Integer id_modensinovagas2; 
	private Integer id_modensinovagas3; 
	private Integer posicao1_candidato;
	private Integer posicao2_candidato;
	private Integer posicao3_candidato;
	private String criterio_candidato;
	private Integer posicaotemporaria_candidato;

	public Edu_matr_candidatoSelecao(Integer id_candidato, Integer id_escola, Integer id_escolavaga,
			Integer id_modensinovagas1, Integer vagas_modensinovagas, Integer id_modensinovagas2,
			Integer id_modensinovagas3) {
		super();
		this.id_candidato = id_candidato;
		this.id_escola = id_escola;
		this.id_escolavaga = id_escolavaga;
		this.id_modensinovagas1 = id_modensinovagas1;
		this.vagas_modensinovagas = vagas_modensinovagas;
		this.id_modensinovagas2 = id_modensinovagas2;
		this.id_modensinovagas3 = id_modensinovagas3;
	}

	public Edu_matr_candidatoSelecao() {
		super();
	}

	public Integer getPosicaotemporaria_candidato() {
		return posicaotemporaria_candidato;
	}

	public void setPosicaotemporaria_candidato(Integer posicaotemporaria_candidato) {
		this.posicaotemporaria_candidato = posicaotemporaria_candidato;
	}

	public Integer getVagas_modensinovagas() {
		return vagas_modensinovagas;
	}

	public void setVagas_modensinovagas(Integer vagas_modensinovagas) {
		this.vagas_modensinovagas = vagas_modensinovagas;
	}

	public Integer getId_candidato() {
		return id_candidato;
	}

	public void setId_candidato(Integer id_candidato) {
		this.id_candidato = id_candidato;
	}

	public Integer getId_escola() {
		return id_escola;
	}

	public void setId_escola(Integer id_escola) {
		this.id_escola = id_escola;
	}

	public Integer getId_escolavaga() {
		return id_escolavaga;
	}

	public void setId_escolavaga(Integer id_escolavaga) {
		this.id_escolavaga = id_escolavaga;
	}

	public Integer getId_modensinovagas1() {
		return id_modensinovagas1;
	}

	public void setId_modensinovagas1(Integer id_modensinovagas1) {
		this.id_modensinovagas1 = id_modensinovagas1;
	}

	public Integer getId_modensinovagas2() {
		return id_modensinovagas2;
	}

	public void setId_modensinovagas2(Integer id_modensinovagas2) {
		this.id_modensinovagas2 = id_modensinovagas2;
	}

	public Integer getId_modensinovagas3() {
		return id_modensinovagas3;
	}

	public void setId_modensinovagas3(Integer id_modensinovagas3) {
		this.id_modensinovagas3 = id_modensinovagas3;
	}

	public Integer getPosicao1_candidato() {
		return posicao1_candidato;
	}

	public void setPosicao1_candidato(Integer posicao1_candidato) {
		this.posicao1_candidato = posicao1_candidato;
	}

	public Integer getPosicao2_candidato() {
		return posicao2_candidato;
	}

	public void setPosicao2_candidato(Integer posicao2_candidato) {
		this.posicao2_candidato = posicao2_candidato;
	}

	public Integer getPosicao3_candidato() {
		return posicao3_candidato;
	}

	public void setPosicao3_candidato(Integer posicao3_candidato) {
		this.posicao3_candidato = posicao3_candidato;
	}

	public String getCriterio_candidato() {
		return criterio_candidato;
	}

	public void setCriterio_candidato(String criterio_candidato) {
		this.criterio_candidato = criterio_candidato;
	}

	@Override
	public String toString() {
		return "Edu_matr_candidatoSelecao [id_candidato=" + id_candidato + ", id_escola=" + id_escola
				+ ", id_escolavaga=" + id_escolavaga + ", id_modensinovagas1=" + id_modensinovagas1
				+ ", vagas_modensinovagas=" + vagas_modensinovagas + ", id_modensinovagas2=" + id_modensinovagas2
				+ ", id_modensinovagas3=" + id_modensinovagas3 + ", posicao1_candidato=" + posicao1_candidato
				+ ", posicao2_candidato=" + posicao2_candidato + ", posicao3_candidato=" + posicao3_candidato
				+ ", criterio_candidato=" + criterio_candidato + ", posicaotemporaria_candidato="
				+ posicaotemporaria_candidato + "]";
	}
}
