package mjcsample.repository.certificate;

import mjcsample.model.Certificate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.*;

public class CertificateSearchQuery {
    private final EntityManager entityManager;
    private final Map<String, String> parameters;
    private final Pageable pageable;

    private CertificateSearchQuery(
            EntityManager entityManager,
            Map<String, String> parameters,
            Pageable pageable
    ) {
        this.entityManager = entityManager;
        this.parameters = parameters;
        this.pageable = pageable;
    }

    @SuppressWarnings("unchecked")
    public List<Certificate> getContent() {
        String queryTemplate = String.join("\n", "SELECT",
                "certificate",
                "FROM Certificate certificate",
                "WHERE",
                "%s");

        return buildQueryPrototype(queryTemplate)
                .setFirstResult((int)pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    public int getTotal() {
        String queryTemplate = String.join("\n", "SELECT",
                "COUNT(certificate)",
                "FROM Certificate certificate",
                "WHERE",
                "%s");

        return buildQueryPrototype(queryTemplate).getFirstResult();
    }

    private Query buildQueryPrototype(String queryTemplate) {
        Session session = unwrapSession();

        List<String> conditions = Arrays.asList("1=1");
        Map<String, Object> queryParameters = new HashMap<>();

        if(parameters.containsKey("description")) {
            conditions.add("certificate.description like :description");
            queryParameters.put("description", String.format("%%%s%%", parameters.get("description")));
        }

        String query = String.format(queryTemplate, String.join(" AND ", conditions));

        return session.createQuery(query)
                .setProperties(queryParameters);
    }

    private Session unwrapSession() {
        return entityManager.unwrap(Session.class);
    }

    @Component
    public static class Factory {
        private final EntityManager entityManager;

        Factory(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        public CertificateSearchQuery create(Map<String, String> parameters, Pageable pageable) {
            return new CertificateSearchQuery(entityManager, parameters, pageable);
        }
    }
}
