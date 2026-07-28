package com.detaysoft.gorev_yonetim.scheduler;

import com.detaysoft.gorev_yonetim.repository.ProjectRepository;
import com.detaysoft.gorev_yonetim.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SystemStatusScheduler {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Scheduled(fixedRate = 60000)
    public void reportSystemStatus() {
        long taskCount = taskRepository.count();
        long projectCount = projectRepository.count();
        log.info("Sistem durumu: Toplam {} görev, {} proje bulunmaktadır.", taskCount, projectCount);
    }
}