package com.nguyenhan.hdv.repository;

import com.nguyenhan.hdv.model.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime , Long> {
}
