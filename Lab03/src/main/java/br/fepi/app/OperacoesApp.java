package br.fepi.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("locadoraPU");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		try {
			//buscaSingle(em);
			buscaList(em);
				
			/*
			 * et.begin(); 
			 * update(em); 
			 * insert(em); 
			 * delete(em); 
			 * et.commit();
			 * System.out.println("Sucesso na operação!");
			 */

		} catch (Exception e) {
			// et.rollback();
			// System.out.println("Erro na operação!" + e.getMessage());
		} finally {
			em.close();
			emf.close();
		}

	}

}
