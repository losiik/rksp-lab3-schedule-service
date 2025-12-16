package ru.rksp.klosep.schedule_service.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.rksp.klosep.schedule_service.dto.UserEventDto;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataServiceClient {

    private final RestTemplate restTemplate;

    @Value("${data.service.url}")
    private String dataServiceUrl;

    public List<UserEventDto> fetchEvents() {
        try {
            String url = dataServiceUrl + "/events";

            log.info("Fetching events from data-service: {}", url);

            ResponseEntity<List<UserEventDto>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<UserEventDto>>() {}
            );

            List<UserEventDto> events = response.getBody();
            log.info("Successfully fetched {} events from data-service",
                    events != null ? events.size() : 0);

            return events;

        } catch (Exception e) {
            log.error("Failed to fetch events from data-service: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch events from data-service", e);
        }
    }
}
