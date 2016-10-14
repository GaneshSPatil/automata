import automata.machine.Machine;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;

public class MachineGeneratorTest {
    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void shouldRunAllTests() throws Exception {
        TestDataParser testDataParser = new TestDataParser();
        testDataParser.parseData();
        ArrayList<Machine> allMachines = testDataParser.getAllMachines();
        ArrayList<ArrayList<String>> allPassCases = testDataParser.getAllPassCasesInSequence();
        ArrayList<ArrayList<String>> allFailCases = testDataParser.getAllFailCasesInSequence();

        for (int i = 0; i < allMachines.size(); i++) {
            Machine machine = allMachines.get(i);
            ArrayList<String> passCases = allPassCases.get(i);
            ArrayList<String> failedCases = allFailCases.get(i);
            for (String validString : passCases) {
                collector.checkThat(machine.canAccept(validString), equalTo(true));
            }
            for (String invalidString : failedCases) {
                collector.checkThat(machine.canAccept(invalidString), equalTo(false));
            }
        }
    }
}
