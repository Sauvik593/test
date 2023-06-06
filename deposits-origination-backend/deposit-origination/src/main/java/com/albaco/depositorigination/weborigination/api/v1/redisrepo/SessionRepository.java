package com.albaco.depositorigination.weborigination.api.v1.redisrepo;

import com.albaco.depositorigination.weborigination.api.v1.model.SessionRedisModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<SessionRedisModel, String> {}
