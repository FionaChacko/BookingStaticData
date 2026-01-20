package com.trust.StaticData.repository;

import com.trust.StaticData.model.Cruise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStaticDataRepository extends JpaRepository<Cruise,Integer> {
}
