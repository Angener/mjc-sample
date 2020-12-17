package mjcsample.controller;

import mjcsample.model.Certificate;
import mjcsample.repository.CertificateRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/sample")
public class SampleController {
    private final CertificateRepository certificateRepository;

    SampleController(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    @GetMapping("/search")
    public Page<Certificate> search(
            @RequestParam Map<String, String> searchParameters,
            Pageable pageable
    ) {
        return certificateRepository.search(searchParameters, pageable);
    }
}
