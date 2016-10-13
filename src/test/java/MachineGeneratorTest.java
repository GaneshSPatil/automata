import automata.Machine;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MachineGeneratorTest {
    @Test
    public void shouldRunAllTests() throws Exception {
        TestDataParser testDataParser = new TestDataParser();
        ArrayList<Machine> allMachines = testDataParser.getAllMachines();
        ArrayList<ArrayList<String>> allPassCases = testDataParser.getAllPassCasesInSequence();
        ArrayList<ArrayList<String>> allFailCases = testDataParser.getAllFailCasesInSequence();

        for (int i = 0; i < allMachines.size(); i++) {
            Machine machine = allMachines.get(i);
            ArrayList<String> passCases = allPassCases.get(i);
            ArrayList<String> failedCases = allFailCases.get(i);
            for (String validString : passCases) {
                assertTrue(machine.canAccept(validString));
            }
            for (String invalidString : failedCases) {
                assertFalse(machine.canAccept(invalidString));
            }
        }
    }
}
