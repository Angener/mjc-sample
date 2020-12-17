package mjcsample.repository;

import mjcsample.model.Certificate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends CrudRepository<Certificate, Long>, CustomCertificateRepository {
}
