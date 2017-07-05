package com.example.delightex.reps;

import com.example.delightex.entity.Request;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mikhail on 05.07.17.
 */
public interface RequestRepository extends CrudRepository<Request, Long> {
}
