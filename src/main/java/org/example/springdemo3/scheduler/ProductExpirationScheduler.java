package org.example.springdemo3.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springdemo3.repository.ProductRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProductExpirationScheduler {

    private final ProductRepository productRepository;

     @Scheduled(cron = "0 0 0 * * ?")
    public void checkAndUpdateExpiredProducts() {
        log.info("Automatic check for expired products has started...");

        LocalDate today = LocalDate.now();

        int updatedCount = productRepository.updateExpiredProductsStatus(today);

        log.info("Check completed. A total of {}" +
                " products were marked as 'isExpired = true' due to expiration.",
                updatedCount);
    }
}
