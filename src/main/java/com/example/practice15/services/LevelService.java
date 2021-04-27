package com.example.practice15.services;

import com.example.practice15.models.Game;
import com.example.practice15.models.Level;
import com.example.practice15.repositories.LevelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@Service
@Slf4j
public class LevelService {
    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private EmailService emailService;

    @Transactional
    public void addLevel(Level level) {
        log.info("Save level {}",level);
        /*emailService.sendSimpleEmail("Add new level","Add new level with name " + level.getLevelName());*/
        levelRepository.save(level);
    }

    @Transactional
    public void deleteLevel(int id) {
        log.info("Delete level by id {}",id);
        levelRepository.delete(findLevel(id));
    }

    @Transactional(readOnly = true)
    public Level findLevel(int id ) {
        log.info("find level by id {}",id);
        return levelRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Level> findAllLevels() {
        log.info("find all levels");
        return levelRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Game findGameByLevel(int levelId) {
        log.info("find game by levelId {}",levelId);
        return Objects.requireNonNull(levelRepository.findById(levelId).orElse(null)).getGame();
    }

    @Transactional(readOnly = true)
    public List<Level> filterLevelName() {
        log.info("filter(sort) levels by levelName");
        return levelRepository.findAll(Sort.by(Sort.Direction.DESC,"levelName"));
    }

    @Transactional(readOnly = true)
    public List<Level> filterById() {
        log.info("filter levels by id > 3");
        return levelRepository.findByIdIsGreaterThan(3);
    }

    @Transactional(readOnly = true)
    public List<Level> filterByComplexity() {
        log.info("filter levels by complexity = low");
        return levelRepository.findByComplexityEquals("low");
    }
}
