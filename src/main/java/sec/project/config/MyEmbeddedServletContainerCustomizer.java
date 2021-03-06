package sec.project.config;

import org.apache.catalina.Context;
import org.apache.catalina.Manager;
import org.apache.catalina.SessionIdGenerator;
import org.apache.catalina.util.SessionIdGeneratorBase;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author axel
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class MyEmbeddedServletContainerCustomizer implements EmbeddedServletContainerCustomizer {

    @Override
    public void customize(ConfigurableEmbeddedServletContainer cesc) {
        ((TomcatEmbeddedServletContainerFactory) cesc).addContextCustomizers(new TomcatContextCustomizer() {
            @Override
            public void customize(Context context) {
                context.setUseHttpOnly(false);
                context.getManager().setSessionIdGenerator(new SessionIdGeneratorBase() {
                    
                    private int id = 0;
                    
                    @Override
                    public String generateSessionId(String string) {
                        return String.valueOf(id++);
                    }
                });
            }
        });
    }

}
