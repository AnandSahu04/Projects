// Retrieve existing tasks from local storage, or initialize an empty array
let tasks = JSON.parse(localStorage.getItem("tasks")) || [];

// Function to display tasks in the UI
function displayTasks() {
  const taskList = document.getElementById("taskList");
  taskList.innerHTML = "";

  tasks.forEach((task, index) => {
    const listItem = document.createElement("li");
    listItem.innerHTML = `
      <input type="checkbox" onchange="toggleTask(${index})" ${
      task.completed ? "checked" : ""
    }>
      <span class="${task.completed ? "completed" : ""}">${
      task.description
    }</span>
      <button onclick="editTask(${index})">Edit</button>
      <button onclick="deleteTask(${index})">Delete</button>
    `;
    taskList.appendChild(listItem);
  });

  // Save tasks to local storage
  saveTasks();
}

// Function to add a new task
function addTask() {
  const taskInput = document.getElementById("taskInput");
  const taskDescription = taskInput.value.trim();

  if (taskDescription !== "") {
    tasks.push({ description: taskDescription, completed: false });
    taskInput.value = "";
    displayTasks();
  } else {
    alert("Please enter a task!");
  }
}

// Function to toggle task completion
function toggleTask(index) {
  tasks[index].completed = !tasks[index].completed;
  displayTasks();
}

// Function to edit a task
function editTask(index) {
  const newDescription = prompt("Edit the task:", tasks[index].description);
  if (newDescription !== null) {
    tasks[index].description = newDescription.trim();
    displayTasks();
  }
}

// Function to delete a task
function deleteTask(index) {
  tasks.splice(index, 1);
  displayTasks();
}

// Function to save tasks to local storage
function saveTasks() {
  localStorage.setItem("tasks", JSON.stringify(tasks));
}

// Initial display of tasks
displayTasks();
