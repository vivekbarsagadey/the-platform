package com.platform.health.config;

import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(
        info = @Info(
                description = "Platform Resources",
                version = "V12.0.12",
                title = "Platform Resource API",
                contact = @Contact(
                   name = "Whiz IT", 
                   email = "whizit@whizit.co.in", 
                   url = "http://www.whizit.co.in/"
                ),
                license = @License(
                   name = "Apache 2.0", 
                   url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        consumes = {"application/json", "application/xml"},
        produces = {"application/json", "application/xml"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        externalDocs = @ExternalDocs(value = "Read This For Sure", url = "http://localhost:8081/the-platform-service-api")
)
public interface ApiDocumentationConfig {

}

