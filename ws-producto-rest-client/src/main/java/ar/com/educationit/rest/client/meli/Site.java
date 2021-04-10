
package ar.com.educationit.rest.client.meli;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "default_currency_id",
    "id",
    "name"
})
@Generated("jsonschema2pojo")
public class Site {

    @JsonProperty("default_currency_id")
    private String defaultCurrencyId;
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;

    @JsonProperty("default_currency_id")
    public String getDefaultCurrencyId() {
        return defaultCurrencyId;
    }

    @JsonProperty("default_currency_id")
    public void setDefaultCurrencyId(String defaultCurrencyId) {
        this.defaultCurrencyId = defaultCurrencyId;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		return "Site [defaultCurrencyId=" + defaultCurrencyId + ", id=" + id + ", name=" + name + "]";
	}
    
}
