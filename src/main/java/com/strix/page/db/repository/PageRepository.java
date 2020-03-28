package com.strix.page.db.repository;

import com.strix.page.db.entity.PageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PageRepository extends JpaRepository<PageEntity, UUID> {
}
