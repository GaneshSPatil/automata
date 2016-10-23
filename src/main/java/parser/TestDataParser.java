package parser;

import automata.generator.DFAGenrator;
import automata.generator.MachineGenerator;
import automata.generator.NFAGenerator;
import automata.machine.Machine;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class TestDataParser {
    private static HashMap<String, MachineGenerator> machineGeneratorMapper;
    static{
        machineGeneratorMapper = new HashMap<String, MachineGenerator>();
        machineGeneratorMapper.put("dfa", new DFAGenrator());
        machineGeneratorMapper.put("nfa", new NFAGenerator());
    }

    public static HashMap<Machine, HashMap<String, ArrayList<String>>> getMachines(String fileName) throws IOException {
        String jsonContent = parser.FileReader.read(fileName);
        Type type = new TypeToken<ArrayList<JSONSkeleton>>() {}.getType();
        ArrayList<JSONSkeleton> parsedJsonArray = new Gson().fromJson(jsonContent, type);

        HashMap<Machine, HashMap<String, ArrayList<String>>> machineInfoMap = new HashMap<Machine, HashMap<String, ArrayList<String>>>();
        for (JSONSkeleton json : parsedJsonArray) {
            MachineGenerator generator = machineGeneratorMapper.get(json.getType());
            TupleSkeleton tuple = json.getTuple();
            HashMap<String, HashMap> delta = tuple.getDelta();
            Machine machine = generator.generate(tuple.getStates(), tuple.getAlphabets(), delta, tuple.getStartState(), tuple.getFinalStates());

            HashMap<String, ArrayList<String>> info = getInfo(json);
            machineInfoMap.put(machine, info);
        }

        return machineInfoMap;
    }

    private static HashMap<String, ArrayList<String>> getInfo(final JSONSkeleton json) {
        HashMap<String, ArrayList<String>> info = new HashMap<String, ArrayList<String>>();
        info.put("pass", json.getPassingStrings());
        info.put("fail", json.getFailingStrings());
        return info;
    }


}
