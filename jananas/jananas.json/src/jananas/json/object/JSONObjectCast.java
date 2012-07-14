package jananas.json.object;

public interface JSONObjectCast {

	JSON_array toArray();

	JSON_dictionary toDictionary();

	JSON_number toNumber();

	JSON_string toJSONString();

	JSON_null toNull();

}
