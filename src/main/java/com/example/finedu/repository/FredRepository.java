package com.example.finedu.repository;

import com.example.finedu.entity.FredData;
import com.example.finedu.entity.id.FredId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FredRepository extends CrudRepository<FredData, FredId> {
    public List<FredData> findAllBySeriesIdAndObservationDateAfterAndObservationDateBefore(String seriesId, String from, String to);
}