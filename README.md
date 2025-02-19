# Sphene User Guide

![Image](https://nhatminh0208.github.io/ip/Ui.png)

**Sphene** is a chatbot inspired by the titular character in *Final Fantasy XIV*.
Sphene helps you **keep track of your tasks** with ease via a command line interface.

## Adding todos: `todo`

Adds a todo task to the task list.

**Format:** `todo [content]`

- `[content]`: The details of the todo task.

**Example:** `todo Clear Cloud of Darkness (Chaotic)`

**Output:** The chatbot displays the new task list.

## Adding deadlines: `deadline`

Adds a deadline to the task list.

**Format:** `deadline [content] /by [deadlineTime]`

- `[content]`: The details of the deadline.
- `[deadlineTime]`: The due time of the deadline, in ISO format `yyyy-mm-ddThh:mm:ss`

**Example:** `deadline Do this week's tP tasks /by 2025-02-23T23:59:59`

**Output:** The chatbot displays the new task list.

## Adding events: `event`

Adds a event to the task list.

**Format:** `event [content] /from [startTime] /to [endTime]`

- `[content]`: The details of the event.
- `[startTime]`: The start time of the event, in ISO format `yyyy-mm-ddThh:mm:ss`
- `[endTime]`: The end time of the event, in ISO format `yyyy-mm-ddThh:mm:ss`

**Example:** `event DRS Reclear /from 2025-02-16T11:00:00 /to 2025-02-16T12:00:00`

**Output:** The chatbot displays the new task list.

## Listing all tasks: `list`

Prints all tasks in the task list.

**Format:** `list`

**Output:** The chatbot displays all tasks in the task list, in the following format:

`<index>. [<type>][<mark status>] <details> (<relevant times in case of deadlines or events>)`

## Marking a task: `mark`

Marks a task in the list as done.

**Format:** `mark [index]`

- `[index]`: The index of the task to mark as done, as shown by `list`

**Output:** The chatbot displays the new task list.

## Unarking a task: `unmark`

Removes the mark of a task in the list.

**Format:** `unmark [index]`

- `[index]`: The numerical index of the task to unmark, as shown by `list`

**Output:** The chatbot displays the new task list.

## Deleting a task: `delete`

Deletes a task from the list

**Format:** `delete [index]`

- `[index]`: The index of the task to delete, as shown by `list`

**Output:** The chatbot displays the new task list.

## Searching for tasks: `find`

Finds tasks in the list whose details contain a given string.
This search is **case-sensitive**.

**Format:** `find [query]`

- `[query]`: The string to search.

**Output:** The chatbot displays the tasks in the list, whose details contain the given string.

## Sorting tasks: `task`

Sorts the task list in chronological order.

For deadlines, the due time is used. For events, the start time is used. Todos are always placed at the end of the list.

**Format:** `sort`

**Output:** The chatbot displays the new task list.

## Exiting the bot: `bye`

Exits from the chatbot.

**Format:** `bye`

**Output:** The chatbot closes.

## Saving the data

The task list is saved at `data/tasks.txt` after any command which modifies the list - no manual action
is required from the user.

