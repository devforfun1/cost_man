
package json.model.ec2.sub;

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
    "State",
    "HttpTokens",
    "HttpPutResponseHopLimit",
    "HttpEndpoint",
    "HttpProtocolIpv6"
})

public class MetadataOptions implements Serializable
{

    @JsonProperty("State")
    private String state;
    @JsonProperty("HttpTokens")
    private String httpTokens;
    @JsonProperty("HttpPutResponseHopLimit")
    private Integer httpPutResponseHopLimit;
    @JsonProperty("HttpEndpoint")
    private String httpEndpoint;
    @JsonProperty("HttpProtocolIpv6")
    private String httpProtocolIpv6;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -6436376909865073173L;

    @JsonProperty("State")
    public String getState() {
        return state;
    }

    @JsonProperty("State")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("HttpTokens")
    public String getHttpTokens() {
        return httpTokens;
    }

    @JsonProperty("HttpTokens")
    public void setHttpTokens(String httpTokens) {
        this.httpTokens = httpTokens;
    }

    @JsonProperty("HttpPutResponseHopLimit")
    public Integer getHttpPutResponseHopLimit() {
        return httpPutResponseHopLimit;
    }

    @JsonProperty("HttpPutResponseHopLimit")
    public void setHttpPutResponseHopLimit(Integer httpPutResponseHopLimit) {
        this.httpPutResponseHopLimit = httpPutResponseHopLimit;
    }

    @JsonProperty("HttpEndpoint")
    public String getHttpEndpoint() {
        return httpEndpoint;
    }

    @JsonProperty("HttpEndpoint")
    public void setHttpEndpoint(String httpEndpoint) {
        this.httpEndpoint = httpEndpoint;
    }

    @JsonProperty("HttpProtocolIpv6")
    public String getHttpProtocolIpv6() {
        return httpProtocolIpv6;
    }

    @JsonProperty("HttpProtocolIpv6")
    public void setHttpProtocolIpv6(String httpProtocolIpv6) {
        this.httpProtocolIpv6 = httpProtocolIpv6;
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
