package com.alekya.application.hc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T> extends JpaRepository<T, String> {

}
