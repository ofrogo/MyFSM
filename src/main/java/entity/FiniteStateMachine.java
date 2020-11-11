package entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import serialize.FiniteStateMachineDeserializer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JsonDeserialize(using = FiniteStateMachineDeserializer.class)
public class FiniteStateMachine {
    private int rank;
    private String start;
    private String finish;
    private Map<String, List<String>> inputs;
    private Map<String, Map<String, String>> matrix;

    public FiniteStateMachine() {
    }

    public FiniteStateMachine(int rank, String start, String finish, Map<String, List<String>> inputs, Map<String, Map<String, String>> matrix) {
        this.rank = rank;
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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Map.Entry<Boolean, Integer> check(String s, int skip) {
        String stage = start;
        int counter = 0;
        boolean isCorrect = false;
        String oldStage;
        for (int i = skip; i < s.length(); i++) {
            oldStage = stage;
            stage = nextStage(stage, String.valueOf(s.charAt(i)));
            if (stage == null) {
                stage = oldStage;
                break;
            }
            counter++;
            isCorrect = true;
        }
        if (!finish.equals(stage)) {
            isCorrect = false;
        }
        return Map.entry(isCorrect, counter);

    }

    private String nextStage(String from, String charStr) {
        Map<String, String> ways = matrix.get(from);
        if (ways != null) {
            for (Map.Entry<String, String> way : ways.entrySet()) {
                if (way.getKey().equals(charStr.toLowerCase())) {
                    return way.getValue();
                } else if (inputs != null) {
                    if (inputs.get(way.getKey()).contains(charStr.toLowerCase())) {
                        return way.getValue();
                    }
                }
            }
        }
        return null;
    }
}
