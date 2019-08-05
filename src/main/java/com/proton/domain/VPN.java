
package com.proton.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Code",
    "LogicalServers"
})
public class VPN {

    @JsonProperty("Code")
    private Integer code;
    @JsonProperty("LogicalServers")
    private List<LogicalServer> logicalServers = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Code")
    public Integer getCode() {
        return code;
    }

    @JsonProperty("Code")
    public void setCode(Integer code) {
        this.code = code;
    }

    @JsonProperty("LogicalServers")
    public List<LogicalServer> getLogicalServers() {
        return logicalServers;
    }

    @JsonProperty("LogicalServers")
    public void setLogicalServers(List<LogicalServer> logicalServers) {
        this.logicalServers = logicalServers;
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
