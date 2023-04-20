const form = document.querySelector('#todo-form');
const input = document.querySelector('#todo-input');
const date = document.querySelector('#todo-date');
const time = document.querySelector('#todo-time');
const ul = document.querySelector('#todo-list');

/*
1. Onload fetch data X
2. Add todo X
3. Remove todo
4. Mark done todo
 */

fetch("/v1/api/todo/getTodayTodos", {
    method: "GET",
    headers: {
        "Content-Type": "application/json",
    },
}).then(function (response) {
    return response.json();
}).then(function (todos) {
    // artContainer.innerHTML = "";
    loadTodos(todos)
});

function loadTodos(todos) {
    todos.forEach(todo => {

        const li = document.createElement('li');
        const task = document.createElement('span');
        const taskDate = document.createElement('span');
        const button = document.createElement('button');

        task.textContent = todo.description;
        taskDate.textContent = todo.executionDate;
        taskDate.classList.add('date'); // add 'date' class to taskDate span
        button.textContent = 'Delete';

        li.appendChild(task);
        li.appendChild(taskDate);
        li.appendChild(button);
        ul.appendChild(li);
        input.value = '';
        date.value = '';
        button.addEventListener('click', function () {
            ul.removeChild(li);
            fetch(`/v1/api/todo/deleteById/${todo.id}`, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json",
                },
            }).then(function (response) {
                console.log(response);
            });
        });
    })
}


function addNewTodo() {
    const dateInput = date.value;
    const timeInput = time.value;
    const localDateTime = dateInput + "T" + timeInput;

    const body = {
        description: input.value,
        executionDate: localDateTime,
        priority: 3
    }

    fetch("/v1/api/todo/save", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(body)
    }).then(function (response) {
        return response.json();
    }).then(function (todo) {
        addNew(todo)
    });

}

form.addEventListener('submit', function (event) {
    addNewTodo();
});

function addNew(todo) {
    const li = document.createElement('li');
    const task = document.createElement('span');
    const taskDate = document.createElement('span');
    const button = document.createElement('button');
    task.textContent = todo.description;
    taskDate.textContent = todo.executionDate;
    taskDate.classList.add('date'); // add 'date' class to taskDate span
    button.textContent = 'Delete';
    li.appendChild(task);
    li.appendChild(taskDate);
    li.appendChild(button);
    ul.appendChild(li);
    input.value = '';
    date.value = '';
    button.addEventListener('click', function () {
        ul.removeChild(li);
    });
}


/*

form.addEventListener('submit', function(event) {
  event.preventDefault();

  if (input.value.trim() === '') {
    alert('Please enter a task.');
    return;
  }

  const li = document.createElement('li');
  const task = document.createElement('span');
  const taskDate = document.createElement('span');
  const button = document.createElement('button');

  task.textContent = input.value;
  taskDate.textContent = date.value;
  taskDate.classList.add('date'); // add 'date' class to taskDate span
  button.textContent = 'Delete';

  li.appendChild(task);
  li.appendChild(taskDate);
  li.appendChild(button);
  ul.appendChild(li);

  input.value = '';
  date.value = '';

  button.addEventListener('click', function() {
    ul.removeChild(li);
  });
});
*/
