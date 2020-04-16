package br.fepi.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.fepi.model.Carro;
import br.fepi.model.Cliente;
import br.fepi.model.Locacao;

public class OperacoesApp {

	/* Insere um item no BD */

	public static void insert(EntityManager em) throws ParseException {
		Cliente cliente1 = new Cliente("111111111", "Olaf Falo");
		Cliente cliente2 = new Cliente("198765432", "Buda");
		Cliente cliente3 = new Cliente("123456789", "Jered ");

		Carro carro1 = new Carro("Focus", "PXP4505");
		Carro carro2 = new Carro("Civic", "PXP3020");
		Carro carro3 = new Carro("Brasilia", "PXP2503");

		Locacao locacao1 = new Locacao(cliente3, carro2, new SimpleDateFormat("dd/MM/yyyy").parse("01/03/2020"),
				new SimpleDateFormat("dd/MM/yyyy").parse("05/06/2020"));

		Locacao locacao2 = new Locacao(cliente2, carro1, new SimpleDateFormat("dd/MM/yyyy").parse("05/04/2020"),
				new SimpleDateFormat("dd/MM/yyyy").parse("08/05/2020"));

		Locacao locacao3 = new Locacao(cliente1, carro3, new SimpleDateFormat("dd/MM/yyyy").parse("26/03/2020"), null);

		em.persist(cliente1);
		em.persist(cliente2);
		em.persist(cliente3);

		em.persist(carro1);
		em.persist(carro2);
		em.persist(carro3);

		em.persist(locacao1);
		em.persist(locacao2);
		em.persist(locacao3);

	}
	/* faz a atualização de um item no bd */

	public static void update(EntityManager em) {

		Cliente cliente = em.find(Cliente.class, 1L); /// o L siginfica que é um long Int
		cliente.setNomeCliente("Charlie");
		cliente.setCpfCliente("251364879");
		em.merge(cliente); /// faz update, porém se o id n existir ele faz um insert
	}

	/* Remove um item de uma tabela */

	public static void delete(EntityManager em) {

		em.remove(em.find(Locacao.class, 9L));
	}

	/*
	 * faz a consulta no BD retornando 1 resultado o : define que vai receber um
	 * parametro
	 */
	public static void buscaSingle(EntityManager em) {

		String parametro = "098765432";

		Cliente cliente = em.createQuery("from Cliente c where c.cpfCliente = :cpf", Cliente.class)
				.setParameter("cpf", parametro).getSingleResult();

		if (cliente.getIdCliente() != null) {

			System.out.println(cliente.getNomeCliente().toUpperCase());
		}

	}

	/* faz a consulta para exebir varios resultados */

	public static void buscaList(EntityManager em) {

		TypedQuery<Locacao> locacoes = em.createQuery("from Locacao", Locacao.class);

		for (Locacao locacao : locacoes.getResultList()) {
			System.out.println(locacao.getCarro().getModeloCarro() + " foi alugado em "
					+ new SimpleDateFormat("dd/MM/yyyy").format(locacao.getDataLocacao().getTime()) + " por "
					+ locacao.getCliente().getNomeCliente().toUpperCase());
		}
	}

	/* Retorna uma lista de carros ordenado pela placa */

	public static void listCarroOrdenadoPelaPlaca(EntityManager em) {
		TypedQuery<Carro> carros = em.createQuery("FROM Carro c ORDER BY c.placaCarro", Carro.class);
		System.out.println("Carros ordenados pela placa:");
		for (Carro carro : carros.getResultList()) {
			System.out.println("- " + carro.getModeloCarro() + " - Placa: " + carro.getPlacaCarro());
		}
	}

	/* Retorna todos os clientes com o primeiro nome Silvio */

	public static void clienteSilvio(EntityManager em) {
		TypedQuery<Cliente> clientes = em.createQuery("FROM Cliente c WHERE c.nomeCliente LIKE 'Silvio%'",
				Cliente.class);
		System.out.println("Todos os clientes que possuem o primeiro nome Silvio:");
		for (Cliente cliente : clientes.getResultList()) {
			System.out.println("- CPF: " + cliente.getCpfCliente() + "  Nome: " + cliente.getNomeCliente());
		}
	}
	/* Retorne todas as locações do cliente de cpf: ‘11111111111’. */

	public static void locacoesCliente111(EntityManager em) {
		TypedQuery<Locacao> locacoes = em.createQuery(
				"SELECT l FROM Locacao l INNER JOIN l.cliente c WHERE c.cpfCliente = '11111111111'", Locacao.class);
		System.out.println("Locações realizadas pelo cliente de cpf: 11111111111:");
		for (Locacao locacao : locacoes.getResultList()) {
			System.out.println("- CPF: " + locacao.getCliente().getCpfCliente() + ", Nome "
					+ locacao.getCliente().getNomeCliente().toUpperCase() + ", placa do veiculo: "
					+ locacao.getCarro().getPlacaCarro());
		}
	}

	/*
	 * Mostre todos os clientes que fizeram locações e ainda não devolveram o carro;
	 */

	public static void clientesQueNãoDevolveramCarro(EntityManager em) {
		TypedQuery<Locacao> locacoes = em.createQuery(
				"SELECT l FROM Locacao l INNER JOIN l.cliente c WHERE l.dataDevolucao IS NULL", Locacao.class);
		System.out.println("Clientes que fizeram locação mas não devolveram o carro:");
		for (Locacao locacao : locacoes.getResultList()) {
			System.out.println("- CPF: " + locacao.getCliente().getCpfCliente() + ", Nome "
					+ locacao.getCliente().getNomeCliente().toUpperCase() + ", placa do veiculo: "
					+ locacao.getCarro().getPlacaCarro());
		}
	}

	/* Mostre apenas os carros que foram locados ao menos uma vez. */

	public static void carrosLocadosAoMenosUmaVez(EntityManager em) {
		TypedQuery<Locacao> locacoes = em.createQuery(
				"SELECT l FROM Locacao l INNER JOIN l.carro c WHERE l.idLocacao is NOT NULL", Locacao.class);
		System.out.println("Carros alugados pelo menos uma vez:");
		for (Locacao locacao : locacoes.getResultList()) {
			System.out.println("- Placa: " + locacao.getCarro().getPlacaCarro() + ", modelo "
					+ locacao.getCarro().getModeloCarro());
		}
	}

	/* Retorna a quantidade de clientes */

	public static void qtdClientes(EntityManager em) {
		Long qtdClientes = (Long) em.createQuery("SELECT COUNT(*) FROM Cliente").getSingleResult();
		System.out.println("Existem " + qtdClientes + " clientes");
	}

	/*
	 * Retorne a quatidade de carros locados por cliente ordenado pela quantidade do
	 * maior para o menor.
	 */

	public static void qtdPorClienteLocado(EntityManager em) {
		List<Object[]> results = em.createQuery(
				"SELECT c.cpfCliente, c.nomeCliente, COUNT(ca.placaCarro) FROM Locacao l INNER JOIN l.carro ca INNER JOIN l.cliente c GROUP BY c.cpfCliente, c.nomeCliente")
				.getResultList();

		for (Object[] objeto : results) {
			String cpf = (String) objeto[0];
			String nome = (String) objeto[1];
			int cont = ((Long) objeto[2]).intValue();
			System.out.println("CPF: " + cpf + " - Nome: " + nome + " - alugou " + cont + " carros");
		}
	}

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("locadora1PU");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		try {
			et.begin();
			// buscaSingle(em);
			// buscaList(em);
			// delete(em);
			//insert(em);
			// update(em);
			// listCarroOrdenadoPelaPlaca(em);
			// clienteSilvio(em);
			// locacoesCliente111(em);
			// clientesQueNãoDevolveramCarro(em);
			// carrosLocadosAoMenosUmaVez(em);
			// qtdClientes(em);
			qtdPorClienteLocado(em);
			System.out.println("Sucesso na operação!");
			et.commit();

		} catch (Exception e) {
			// et.rollback();
			System.out.println("Erro na operação!" + e.getMessage());
		} finally {
			em.close();
			emf.close();
		}

	}

}
