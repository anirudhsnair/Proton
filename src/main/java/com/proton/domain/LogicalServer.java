
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
    "Name",
    "EntryCountry",
    "ExitCountry",
    "Domain",
    "Tier",
    "Features",
    "Region",
    "City",
    "ID",
    "Location",
    "Status",
    "Servers",
    "Load",
    "Score"
})
public class LogicalServer {

    @JsonProperty("Name")
    private String name;
    @JsonProperty("EntryCountry")
    private String entryCountry;
    @JsonProperty("ExitCountry")
    private String exitCountry;
    @JsonProperty("Domain")
    private String domain;
    @JsonProperty("Tier")
    private Integer tier;
    @JsonProperty("Features")
    private Integer features;
    @JsonProperty("Region")
    private Object region;
    @JsonProperty("City")
    private String city;
    @JsonProperty("ID")
    private String iD;
    @JsonProperty("Location")
    private Location location;
    @JsonProperty("Status")
    private Integer status;
    @JsonProperty("Servers")
    private List<Server> servers = null;
    @JsonProperty("Load")
    private Integer load;
    @JsonProperty("Score")
    private Double score;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("EntryCountry")
    public String getEntryCountry() {
        return entryCountry;
    }

    @JsonProperty("EntryCountry")
    public void setEntryCountry(String entryCountry) {
        this.entryCountry = entryCountry;
    }

    @JsonProperty("ExitCountry")
    public String getExitCountry() {
        return exitCountry;
    }

    @JsonProperty("ExitCountry")
    public void setExitCountry(String exitCountry) {
        this.exitCountry = exitCountry;
    }

    @JsonProperty("Domain")
    public String getDomain() {
        return domain;
    }

    @JsonProperty("Domain")
    public void setDomain(String domain) {
        this.domain = domain;
    }

    @JsonProperty("Tier")
    public Integer getTier() {
        return tier;
    }

    @JsonProperty("Tier")
    public void setTier(Integer tier) {
        this.tier = tier;
    }

    @JsonProperty("Features")
    public Integer getFeatures() {
        return features;
    }

    @JsonProperty("Features")
    public void setFeatures(Integer features) {
        this.features = features;
    }

    @JsonProperty("Region")
    public Object getRegion() {
        return region;
    }

    @JsonProperty("Region")
    public void setRegion(Object region) {
        this.region = region;
    }

    @JsonProperty("City")
    public String getCity() {
        return city;
    }

    @JsonProperty("City")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("ID")
    public String getID() {
        return iD;
    }

    @JsonProperty("ID")
    public void setID(String iD) {
        this.iD = iD;
    }

    @JsonProperty("Location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("Location")
    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonProperty("Status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("Status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonProperty("Servers")
    public List<Server> getServers() {
        return servers;
    }

    @JsonProperty("Servers")
    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    @JsonProperty("Load")
    public Integer getLoad() {
        return load;
    }

    @JsonProperty("Load")
    public void setLoad(Integer load) {
        this.load = load;
    }

    @JsonProperty("Score")
    public Double getScore() {
        return score;
    }

    @JsonProperty("Score")
    public void setScore(Double score) {
        this.score = score;
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
