package entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import serialize.FiniteStateMachineDeserializer;

import java.util.*;
import java.util.stream.Collectors;

@JsonDeserialize(using = FiniteStateMachineDeserializer.class)
public class FiniteStateMachine {
    private String start;
    private String finish;
    private Map<String, List<String>> inputs;
    private Map<String, Map<String, String>> matrix;

    public FiniteStateMachine() {
    }

    public FiniteStateMachine(String jsonFsm) throws JsonProcessingException {
        FiniteStateMachine finiteStateMachine = new ObjectMapper().readValue(jsonFsm, FiniteStateMachine.class);
        this.start = finiteStateMachine.start;
        this.finish = finiteStateMachine.finish;
        this.inputs = finiteStateMachine.inputs;
        this.matrix = finiteStateMachine.matrix;
    }

    public FiniteStateMachine(String start, String finish, Map<String, List<String>> inputs, Map<String, Map<String, String>> matrix) {
        this.start = start;
        this.finish = finish;
        this.inputs = inputs;
        this.matrix = matrix;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public Map<String, Map<String, String>> getMatrix() {
        return matrix;
    }

    public void setMatrix(Map<String, Map<String, String>> matrix) {
        this.matrix = matrix;
    }


    public Map<String, List<String>> getInputs() {
        return inputs;
    }

    public void setInputs(Map<String, List<String>> inputs) {
        this.inputs = inputs;
    }

    public Map.Entry<Boolean, Integer> check(String s, int skip) {
        List<String> chars = s.substring(skip).chars()
                .mapToObj(value -> String.valueOf(Character.toChars(value)))
                .collect(Collectors.toList());
        if (matrix.get(start).keySet().stream().anyMatch(mtx -> inputs.get(mtx).contains(chars.get(0)))) {
            String stage = start;
            int counter = 0;
            for (String chr : chars) {
                stage = nextStage(stage, chr);
                if (stage == null) {
                    break;
                }
                counter++;
            }
            return Map.entry(true, counter);
        } else {
            return Map.entry(false, 0);
        }
    }

    private String nextStage(String from, String charStr) {
        if (inputs != null) {
            Map<String, String> ways = matrix.get(from);
            for (Map.Entry<String, String> way : ways.entrySet()) {
                if (inputs.get(way.getKey()).contains(charStr.toLowerCase())) {
                    return way.getValue();
                }
            }
        }
        return null;
    }
}
