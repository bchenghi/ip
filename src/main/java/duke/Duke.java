package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    /**
     * Task types used in Duke.
     */
    public enum TaskType {
        TODO("todo"),
        EVENT("event"),
        DEADLINE("deadline");

        public final String name;

        private TaskType(String name) {
            this.name = name;
        }
    }

    Ui ui;
    TaskList tasks;
    Storage storage;

    /**
     * Initializes a Duke object.
     *
     * @param pathName The path for the storage file.
     */
    public Duke(String pathName) {
        ui = new Ui();
        storage = new Storage(pathName);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke object.
     */
    public void run() {
        ui.showWelcome();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isRunning = !c.isExitCommand();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}