package ru.rksp.klosep.schedule_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rksp.klosep.schedule_service.client.ClickHouseServiceClient;
import ru.rksp.klosep.schedule_service.client.DataServiceClient;
import ru.rksp.klosep.schedule_service.dto.UserEventDto;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataSyncService {

    private final DataServiceClient dataServiceClient;
    private final ClickHouseServiceClient clickHouseServiceClient;

    public void syncData() {
        log.info("========== Starting data synchronization ==========");

        try {
            List<UserEventDto> events = dataServiceClient.fetchEvents();

            if (events == null || events.isEmpty()) {
                log.warn("No events to synchronize");
                return;
            }

            clickHouseServiceClient.sendEvents(events);

            log.info("========== Data synchronization completed successfully ==========");

        } catch (Exception e) {
            log.error("========== Data synchronization failed: {} ==========", e.getMessage(), e);
        }
    }
}
