import automata.generator.DFAGenrator;
import automata.machine.Machine;
import automata.generator.MachineGenerator;
import automata.generator.NFAGenerator;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.System.lineSeparator;

public class TestDataParser {
    private final ArrayList<Machine> allMachines;
    private final ArrayList<ArrayList<String>> allPassCases;
    private final ArrayList<ArrayList<String>> allFailedCases;

    public ArrayList<Machine> getAllMachines() {
        return allMachines;
    }

    public TestDataParser() {
        this.allMachines = new ArrayList<Machine>();
        this.allPassCases = new ArrayList<ArrayList<String>>();
        this.allFailedCases = new ArrayList<ArrayList<String>>();
    }

    public void parseData() throws IOException {
        HashMap<String, MachineGenerator> machineGeneratorMapper = new HashMap<String, MachineGenerator>();
        machineGeneratorMapper.put("dfa", new DFAGenrator());
        machineGeneratorMapper.put("nfa", new NFAGenerator());

        String fileName = "src/main/resources/examples.json";
        String jsonContent = readFile(fileName);
        Type type = new TypeToken<ArrayList<HashMap>>() {}.getType();
        ArrayList<HashMap> parsedJsonArray = new Gson().fromJson(jsonContent, type);
        for (HashMap parsedJSON : parsedJsonArray) {
            LinkedTreeMap tuple = (LinkedTreeMap) parsedJSON.get("tuple");
            ArrayList<String> states = (ArrayList<String>) tuple.get("states");
            ArrayList<String> alphabets = (ArrayList<String>) tuple.get("alphabets");
            ArrayList<String> finalStates = (ArrayList<String>) tuple.get("final-states");
            String startState = (String) tuple.get("start-state");
            HashMap<String , HashMap> delta = getDelta((LinkedTreeMap<String, LinkedTreeMap<String, String>>)tuple.get("delta"));
            allMachines.add(machineGeneratorMapper.get(parsedJSON.get("type")).generate(states, alphabets, delta, startState, finalStates));
            allPassCases.add((ArrayList<String>) parsedJSON.get("pass-cases"));
            allFailedCases.add((ArrayList<String>) parsedJSON.get("fail-cases"));
        }
    }

    private static HashMap<String, HashMap> getDelta(LinkedTreeMap<String, LinkedTreeMap<String, String>> deltaInString) {
        HashMap<String, HashMap> delta = new HashMap<String, HashMap>();
        for (String key : deltaInString.keySet()) {
            LinkedTreeMap<String, String> valuesInString = deltaInString.get(key);
            HashMap<String, String> transition = new HashMap<String, String>();
            for (String alphabet : valuesInString.keySet()) {
                transition.put(alphabet, valuesInString.get(alphabet));
            }
            delta.put(key, transition);
        }
        return delta;
    }

    private static String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

    public ArrayList<ArrayList<String>> getAllPassCasesInSequence() {
        return this.allPassCases;
    }

    public ArrayList<ArrayList<String>> getAllFailCasesInSequence() {
        return allFailedCases;
    }
}
