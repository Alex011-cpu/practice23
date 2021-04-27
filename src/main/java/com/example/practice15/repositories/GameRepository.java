package com.example.practice15.repositories;

import com.example.practice15.models.Game;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    @Override
    Optional<Game> findById(Integer integer);

    @Override
    List<Game> findAll();

    @Override
    List<Game> findAll(Sort sort);

}
