
package com.proton.domain;

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
    "EntryIP",
    "ExitIP",
    "Domain",
    "ID",
    "Status"
})
public class Server {

    @JsonProperty("EntryIP")
    private String entryIP;
    @JsonProperty("ExitIP")
    private String exitIP;
    @JsonProperty("Domain")
    private String domain;
    @JsonProperty("ID")
    private String iD;
    @JsonProperty("Status")
    private Integer status;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("EntryIP")
    public String getEntryIP() {
        return entryIP;
    }

    @JsonProperty("EntryIP")
    public void setEntryIP(String entryIP) {
        this.entryIP = entryIP;
    }

    @JsonProperty("ExitIP")
    public String getExitIP() {
        return exitIP;
    }

    @JsonProperty("ExitIP")
    public void setExitIP(String exitIP) {
        this.exitIP = exitIP;
    }

    @JsonProperty("Domain")
    public String getDomain() {
        return domain;
    }

    @JsonProperty("Domain")
    public void setDomain(String domain) {
        this.domain = domain;
    }

    @JsonProperty("ID")
    public String getID() {
        return iD;
    }

    @JsonProperty("ID")
    public void setID(String iD) {
        this.iD = iD;
    }

    @JsonProperty("Status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("Status")
    public void setStatus(Integer status) {
        this.status = status;
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
