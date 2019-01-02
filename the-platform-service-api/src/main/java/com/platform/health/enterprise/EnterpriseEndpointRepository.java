package com.platform.health.enterprise;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnterpriseEndpointRepository extends MongoRepository<EnterpriseEndpoint, String> {

	Optional<EnterpriseEndpoint> findByName(String name); 

}
