package mjcsample;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionManager;

import javax.persistence.EntityManager;

@SpringBootTest(classes = SampleConfiguration.class)
public class JpaConfigTest {

    private static final Logger logger = LoggerFactory.getLogger(JpaConfigTest.class);

    @Autowired
    EntityManager entityManager;

    @Autowired
    TransactionManager transactionManager;

    @Test
    void basic() {
        logger.info("entityManager: {}", entityManager);
        logger.info("transactionManager: {}", transactionManager);
    }
}
