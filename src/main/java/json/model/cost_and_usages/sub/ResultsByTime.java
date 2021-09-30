package json.model.cost_and_usages.sub;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import json.model.cost_and_usages.sub.Total;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Estimated",
        "Groups",
        "TimePeriod",
        "Total"
})

public class ResultsByTime implements Serializable {

    @JsonProperty("Estimated")
    private Boolean estimated;
    @JsonProperty("Groups")
    private List<Group> groups = null;
    @JsonProperty("TimePeriod")
    private TimePeriod timePeriod;
    @JsonProperty("Total")
    private Total total;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Estimated")
    public Boolean getEstimated() {
        return estimated;
    }

    @JsonProperty("Estimated")
    public void setEstimated(Boolean estimated) {
        this.estimated = estimated;
    }

    @JsonProperty("Groups")
    public List<Group> getGroups() {
        return groups;
    }

    @JsonProperty("Groups")
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @JsonProperty("TimePeriod")
    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    @JsonProperty("TimePeriod")
    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    @JsonProperty("Total")
    public Total getTotal() {
        return total;
    }

    @JsonProperty("Total")
    public void setTotal(Total total) {
        this.total = total;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "ResultsByTime{" +
                "estimated=" + estimated +
                ", groups=" + groups +
                ", timePeriod=" + timePeriod +
                ", total=" + total +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}

