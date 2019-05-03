package br.com.jpa.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne
	private Titular titular;
	private String banco;
	private String agencia;
	private String numero;
	@OneToMany(mappedBy = "conta") //apenas reflete o relacionamento ja existente na classe Movimentacao
	private List<Movimentacao> movimentacoes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Titular getTitular() {
		return titular;
	}

	public void setTitular(Titular titular) {
		this.titular = titular;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}

	public String toString(Conta conta) throws Exception {
		if (conta.getBanco().length() > 8) {
			throw new Exception("Campo Banco no máximo 8 dígitos");
		}
		if (conta.getAgencia().length() > 4) {
			throw new Exception("Campo Agencia no máximo 4 dígitos");
		}
		if (conta.getNumero().length() > 6) {
			throw new Exception("Campo Número no máximo 6 dígitos");
		}
		if (conta.getTitular().getNome().length() > 4) {
			throw new Exception("Campo Nome no máximo 4 dígitos");
		}

		return "Banco: " + conta.getBanco() + "	Agência: " + conta.getAgencia() + "	Número: " + conta.getNumero()
				+ "	Nome do Titular: " + conta.getTitular().getNome();
	}
}
