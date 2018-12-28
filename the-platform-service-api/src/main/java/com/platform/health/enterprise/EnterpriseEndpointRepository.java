package com.platform.health.enterprise;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseEndpointRepository extends JpaRepository<EnterpriseEndpoint, String> {

	Optional<EnterpriseEndpoint> findByName(String name); 

}
