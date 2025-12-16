package ru.rksp.klosep.schedule_service.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.rksp.klosep.schedule_service.dto.UserEventDto;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClickHouseServiceClient {

    private final RestTemplate restTemplate;

    @Value("${clickhouse.service.url}")
    private String clickhouseServiceUrl;

    public void sendEvents(List<UserEventDto> events) {
        try {
            String url = clickhouseServiceUrl + "/events/batch";

            log.info("Sending {} events to clickhouse-service: {}", events.size(), url);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<List<UserEventDto>> request = new HttpEntity<>(events, headers);

            restTemplate.postForObject(url, request, String.class);

            log.info("Successfully sent {} events to clickhouse-service", events.size());

        } catch (Exception e) {
            log.error("Failed to send events to clickhouse-service: {}", e.getMessage());
            throw new RuntimeException("Failed to send events to clickhouse-service", e);
        }
    }
}
