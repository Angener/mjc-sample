package mjcsample.repository;

import mjcsample.model.Certificate;
import mjcsample.repository.certificate.CertificateSearchQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface CustomCertificateRepository {
    Page<Certificate> search(Map<String, String> parameters, Pageable pageable);
}

class CustomCertificateRepositoryImpl implements CustomCertificateRepository {
    private final CertificateSearchQuery.Factory searchQueryFactory;

    CustomCertificateRepositoryImpl(CertificateSearchQuery.Factory searchQueryFactory) {
        this.searchQueryFactory = searchQueryFactory;
    }

    @Override
    public Page<Certificate> search(Map<String, String> parameters, Pageable pageable) {
        CertificateSearchQuery searchQuery = searchQueryFactory.create(parameters, pageable);

        return new PageImpl<>(searchQuery.getContent(), pageable, searchQuery.getTotal());
    }
}
