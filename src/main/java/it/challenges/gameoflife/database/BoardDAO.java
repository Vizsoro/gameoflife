package it.challenges.gameoflife.database;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

@Service
public class BoardDAO {

	public BoardEntity findById(int id) {
		EntityManager entityManager = Persistence.createEntityManagerFactory("jpa").createEntityManager();
		Object singleResult = entityManager.createNativeQuery("SELECT * FROM board WHERE ID = 1 ").getSingleResult();
		return (BoardEntity) singleResult;
	}

}
