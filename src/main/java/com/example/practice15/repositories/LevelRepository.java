package com.example.practice15.repositories;

import com.example.practice15.models.Game;
import com.example.practice15.models.Level;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {
    @Override
    Optional<Level> findById(Integer integer);

    @Override
    List<Level> findAll();

    @Override
    List<Level> findAll(Sort sort);

    List<Level> findByIdIsGreaterThan(Integer id);

    List<Level> findByComplexityEquals(String s);
}
