package br.com.jpa.test;

import br.com.jpa.dao.ContaDAO;
import br.com.jpa.model.Conta;
import br.com.jpa.model.Titular;

public class TesteConta {

    public static void main(String[] args) {

        Conta conta = new Conta();
        Titular titular = new Titular();
        titular.setNome("Luiz");
        conta.setTitular(titular);
        conta.setBanco("Bradesco");
        conta.setAgencia("8493");
        conta.setNumero("434446");

        ContaDAO dao = new ContaDAO();
        dao.addConta(conta, titular);
    }
}
