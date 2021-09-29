package json.model.cost_and_usages.sub;

import java.util.HashMap;
import java.util.Map;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "Keys",
            "Metrics"
    })

    public class Group  {

        @JsonProperty("Keys")
        private List<String> keys = null;
        @JsonProperty("Metrics")
        private Metrics metrics;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("Keys")
        public List<String> getKeys() {
            return keys;
        }

        @JsonProperty("Keys")
        public void setKeys(List<String> keys) {
            this.keys = keys;
        }

        @JsonProperty("Metrics")
        public Metrics getMetrics() {
            return metrics;
        }

        @JsonProperty("Metrics")
        public void setMetrics(Metrics metrics) {
            this.metrics = metrics;
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

