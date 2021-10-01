
package json.model.ec2.ec2data.sub;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "CoreCount",
    "ThreadsPerCore"
})

public class CpuOptions implements Serializable
{

    @JsonProperty("CoreCount")
    private Integer coreCount;
    @JsonProperty("ThreadsPerCore")
    private Integer threadsPerCore;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -5584578282740827167L;

    @JsonProperty("CoreCount")
    public Integer getCoreCount() {
        return coreCount;
    }

    @JsonProperty("CoreCount")
    public void setCoreCount(Integer coreCount) {
        this.coreCount = coreCount;
    }

    @JsonProperty("ThreadsPerCore")
    public Integer getThreadsPerCore() {
        return threadsPerCore;
    }

    @JsonProperty("ThreadsPerCore")
    public void setThreadsPerCore(Integer threadsPerCore) {
        this.threadsPerCore = threadsPerCore;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
