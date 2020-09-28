package hazelcast.sample.server;

import hazelcast.sample.server.config.Config;
import hazelcast.sample.server.config.Param;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;

import com.hazelcast.core.HazelcastInstance;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ConfigInitializer {
    @Bean
    public ApplicationListener<ApplicationStartedEvent> initializeContextHolder() {
        return (applicationStarted) -> {
            ApplicationContext context = applicationStarted.getApplicationContext();
            HazelcastInstance instance = context.getBean(HazelcastInstance.class);
            Map<String, List<Param>> configs = new HashMap<>();
            Param param = new Param("1stParam", true);
            Param param2 = new Param("2ndParam", false);
            configs.put("SMS", Arrays.asList(param, param2));
            Config config = new Config("My own config", "SERVE_DOMAIN", configs);
            IMap<String, Object> cache = hazelcastInstance.getMap("CONFIGS_CACHE");
            cache.put("cachingKey", config);
        };
    }
}
