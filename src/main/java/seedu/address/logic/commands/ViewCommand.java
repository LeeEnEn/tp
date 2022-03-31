package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.interview.predicate.WithinTimePeriodPredicate;

/**
 * Lists all candidates in the address book to the user.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View all scheduled interviews in the system"
            + " scheduled within a specified time period.\n"
            + "Parameters: TIME_PERIOD\n"
            + "Example: " + COMMAND_WORD + " today\n"
            + "Note: Allowable time periods include `all` (i.e. all scheduled interviews in the system),"
            + " `today` (i.e. same day), `week` (i.e. next 7 days), month (i.e. period till the next month).";

    public static final String MESSAGE_NO_INTERVIEWS_IN_SYSTEM = "No interviews scheduled yet!";

    private final WithinTimePeriodPredicate predicate;

    public ViewCommand(WithinTimePeriodPredicate predicate) {
        this.predicate = predicate;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInterviewSchedule(predicate);
        return new CommandResult(String
                .format(Messages.MESSAGE_INTERVIEWS_LISTED_OVERVIEW, model.getFilteredInterviewSchedule().size()));
    }
}
