package hazelcast.sample.server.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Param implements Serializable {
    private static final long serialVersionUID = 2868296767358592307L;

    private String paramName;
    private Object paramValue;
}
