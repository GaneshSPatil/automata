package parser;

import automata.machine.Machine;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JSONTestRunner {
    @Test
    public void runDFATests() throws Exception {
        HashMap<Machine, HashMap<String, ArrayList<String>>> parsedData = parser.TestDataParser.getMachines("src/main/resources/dfaExamples.json");

        for (Machine machine : parsedData.keySet()) {
            for (String inputString : parsedData.get(machine).get("pass")) {
                assertTrue(machine.canAccept(inputString));
            }

            for (String inputString : parsedData.get(machine).get("fail")) {
                assertFalse(machine.canAccept(inputString));
            }
        }
    }
}
