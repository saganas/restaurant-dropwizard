package com.ft.restaurants.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantRequest {

    @JsonProperty
    @NotEmpty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private String type;

    @JsonProperty
    private String tag;

    @JsonProperty
    private String address;

    @JsonProperty
    private String city;

    @JsonProperty
    private String postcode;

    @JsonProperty
    private Integer hygieneRating;

    @JsonProperty
    private Location location;

    @JsonCreator
    public RestaurantRequest(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("type") String type,
            @JsonProperty("tag") String tag,
            @JsonProperty("address") String address,
            @JsonProperty("city") String city,
            @JsonProperty("postcode") String postcode,
            @JsonProperty("hygieneRating") Integer hygieneRating,
            @JsonProperty("location") Location location) {
        this.name = checkNotNull(name, "Restaurant name cannot be null");
        this.description = defaultIfNull(description, "");
        this.type = defaultIfNull(type, "Other");
        this.tag = defaultIfNull(tag, "");
        this.address = defaultIfNull(address, "");
        this.city = defaultIfNull(city, "");
        this.postcode = defaultIfNull(postcode, "");
        this.hygieneRating = defaultIfNull(hygieneRating, null);
        this.location = defaultIfNull(location, new Location(null, null));
    }

    public String getName() {
        return name;
    }

    public String getDescription() { return description; }

    public String getType() { return type; }

    public String getTag() {
        return tag;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public Integer getHygieneRating() {
        return hygieneRating;
    }

    public Location getLocation() {
        return location;
    }

}
