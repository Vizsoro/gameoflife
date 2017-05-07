package it.challenges.gameoflife.database;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.stereotype.Service;

@Service
public class BoardDAO {

	public BoardEntity findById(int id) {
		EntityManager entityManager = Persistence.createEntityManagerFactory("jpa").createEntityManager();
		BoardEntity singleResult = (BoardEntity) entityManager.createNativeQuery("SELECT * FROM board WHERE IDBOARD = 1 ", BoardEntity.class).getSingleResult();
		return singleResult;
	}

}
