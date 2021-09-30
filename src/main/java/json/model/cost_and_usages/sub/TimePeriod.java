package json.model.cost_and_usages.sub;

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
        "End",
        "Start"
})

public class TimePeriod implements Serializable {

    @JsonProperty("End")
    private String end;
    @JsonProperty("Start")
    private String start;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("End")
    public String getEnd() {
        return end;
    }

    @JsonProperty("End")
    public void setEnd(String end) {
        this.end = end;
    }

    @JsonProperty("Start")
    public String getStart() {
        return start;
    }

    @JsonProperty("Start")
    public void setStart(String start) {
        this.start = start;
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

