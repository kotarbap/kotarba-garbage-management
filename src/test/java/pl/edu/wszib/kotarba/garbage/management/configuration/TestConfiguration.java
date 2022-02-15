package pl.edu.wszib.kotarba.garbage.management.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "pl.edu.wszib.kotarba.garbage.management.controllers",
        "pl.edu.wszib.kotarba.garbage.management.service",
        "pl.edu.wszib.kotarba.garbage.management.session"
})
public class TestConfiguration {

}
