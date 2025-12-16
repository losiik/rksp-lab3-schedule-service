package ru.rksp.klosep.schedule_service.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.rksp.klosep.schedule_service.service.DataSyncService;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataSyncScheduler {

    private final DataSyncService dataSyncService;

    @Scheduled(cron = "${scheduler.sync.cron}")
    public void scheduleDataSync() {
        log.info("🔄 Scheduler triggered at: {}", java.time.LocalDateTime.now());
        dataSyncService.syncData();
    }
}
