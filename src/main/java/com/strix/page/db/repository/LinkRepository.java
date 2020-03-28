package com.strix.page.db.repository;

import com.strix.page.db.entity.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LinkRepository extends JpaRepository<LinkEntity, UUID> {
}
