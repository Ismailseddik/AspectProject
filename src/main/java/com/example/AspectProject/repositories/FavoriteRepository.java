package com.example.AspectProject.repositories;

import com.example.AspectProject.models.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUsername(String username);  // updated to use username instead of userId
}