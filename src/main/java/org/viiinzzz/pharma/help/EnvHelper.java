package org.viiinzzz.pharma.help;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;

@Component
public class EnvHelper {
    @Autowired
    private Environment environment;
    
    public String getEnvValue(String key){
        return environment.getProperty(key);
    }
}
