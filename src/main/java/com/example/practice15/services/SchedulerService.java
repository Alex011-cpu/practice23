package com.example.practice15.services;

import com.example.practice15.models.Game;
import com.example.practice15.models.Level;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;

@Service
@Slf4j
@ManagedResource(
        objectName = "Practice21(22)MBeans:category=MBeans,name=DataScheduler"
)
public class SchedulerService {
    @Autowired
    private GameService gameService;
    @Autowired
    private LevelService levelService;

    @Scheduled(cron = "0 0/1 * * * *")
    @ManagedOperation(description = "Clear output, create .txt with database data")
    public void doTask() throws IOException {
        log.info("Task started");
        File file_dir = ResourceUtils.getFile("src/main/resources/output");
        try {
            for (File file : file_dir.listFiles())
                if (file.isFile()) file.delete();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        log.info("Files deleted");
        BufferedWriter gameFile = new BufferedWriter(new FileWriter(file_dir.getAbsolutePath() + "/game_data.txt"));
        BufferedWriter levelFile = new BufferedWriter(new FileWriter(file_dir.getAbsolutePath() + "/level_data.txt"));
        gameFile.write("id\tname\tcreation_date\n");
        for (Game game : gameService.findAllGames())
            gameFile.write(String.format("%d\t%s\t%tF\n", game.getId(), game.getName(),game.getCreationDate()));
        gameFile.close();
        levelFile.write("id\tgame_id\tcomplexity\tlevel_name\n");
        for (Level level : levelService.findAllLevels())
            levelFile.write(String.format("%d\t%d\t%s\t%s\n", level.getId(), level.getGameId(),
                    level.getComplexity(), level.getLevelName()));
        levelFile.close();
    }

}
