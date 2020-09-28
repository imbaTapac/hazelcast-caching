package hazelcast.sample.client;

import com.hazelcast.core.DistributedObject;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/api/hazelcast")
@RequiredArgsConstructor
public class HazelcastController {

    private final HazelcastInstance hazelcastInstance;

    @GetMapping
    public ResponseEntity getObjects() {
        Map<String, Object> result = new HashMap<>();
        Collection<DistributedObject> dists = hazelcastInstance.getDistributedObjects();
        IMap<String, Object> obj = hazelcastInstance.getMap("CONFIGS_CACHE"); // here client get cache
        log.info("{}",obj.containsKey("cachingKey")); // true
        log.info("{}",obj.get("cachingKey")); //method can threw hazelcastNioException
        log.info("Clearing: " + dists.stream().map(Object::toString).collect(Collectors.joining(",")));
        for (DistributedObject distributedObject : dists) {
            result.put(distributedObject.getName(), hazelcastInstance.getMap(distributedObject.getName()));
        }
        return ResponseEntity.ok(result); // there i have 500 internal server error
    }


}
