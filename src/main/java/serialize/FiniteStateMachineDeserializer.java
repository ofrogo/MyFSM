package serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import entity.FiniteStateMachine;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FiniteStateMachineDeserializer extends StdDeserializer<FiniteStateMachine> {
    public FiniteStateMachineDeserializer(){
        this(null);
    }

    protected FiniteStateMachineDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public FiniteStateMachine deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        ObjectMapper objectMapper = new ObjectMapper();
        String start = treeNode.get("start").asText();
        String finish = treeNode.get("finish").asText();
        int rank = treeNode.get("rank").asInt();
        Map<String, List<String>> inputs = null;
        if(treeNode.has("inputs")) {
            inputs = objectMapper.readValue(treeNode.get("inputs").toString(), new TypeReference<Map<String, List<String>>>() {
            });
        }
        Map<String, Map<String, String>> matrix = objectMapper.readValue(treeNode.get("matrix").toString(), new TypeReference<Map<String, Map<String, String>>>() {
        });
        return new FiniteStateMachine(rank, start, finish, inputs, matrix);
    }
}
