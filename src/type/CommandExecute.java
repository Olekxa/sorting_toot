package type;

import engine.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandExecute {

        private final List<String> dataList;

        public CommandExecute() {
            this.dataList = new ArrayList<>();
        }

        void executeCommand(Command command) {
            this.dataList.add(command.execute());
        }

        public List<String> getHistory() {
            return this.dataList;
        }
    }

