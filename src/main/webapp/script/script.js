function includeHTML() {
	var z, i, elmnt, file, xhttp;
	/* Loop through a collection of all HTML elements: */
	z = document.getElementsByTagName("*");
	for (i = 0; i < z.length; i++) {
		elmnt = z[i];
		/*search for elements with a certain atrribute:*/
		file = elmnt.getAttribute("include-html");
		if (file) {
			/* Make an HTTP request using the attribute value as the file name: */
			xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4) {
					if (this.status == 200) { elmnt.innerHTML = this.responseText; }
					if (this.status == 404) { elmnt.innerHTML = "Page not found."; }
					/* Remove the attribute, and call this function once more: */
					elmnt.removeAttribute("include-html");
					includeHTML();
				}
			}
			xhttp.open("GET", file, true);
			xhttp.send();
			/* Exit the function: */
			return;
		}
	}
}

function validateUser() {
	let password = document.getElementById('password').value;
	let username = document.getElementById('username').value;
	
	if (username.trim() === "") {

		alert('Username cannot be empty!');
		return false;


	} else if (password.trim() === "") {

		alert('Password cannot be empty!');
		return false;
	}

	else {


		const xhttp = new XMLHttpRequest();
		xhttp.onload = function() {
			var responseText = xhttp.responseText;
			var user = JSON.parse(responseText);

			console.log(user.firstName);
		}

		var endpoint = "http://localhost:8080/bookLibrary/users";
		var params = {
			command: 'VALIDATE_USER'
		}

		var url = endpoint + formatParams(params);
		xhttp.open("POST", url, true);
		xhttp.send();

		return true;
	}

}


function getAllStudents() {
	const xhttp = new XMLHttpRequest();
	var table = document.getElementById('get_all_students_table');
	xhttp.onload = function() {
		var responseText = xhttp.responseText;
		var students = JSON.parse(responseText);

		students.forEach(student => {
			var tr = document.createElement('tr');
			var tdFirstName = document.createElement('td');
			tdFirstName.innerHTML = student.firstName;
			tr.appendChild(tdFirstName);
			var tdLastName = document.createElement('td');
			tdLastName.innerHTML = student.lastName;
			tr.appendChild(tdLastName);
			var tdEmail = document.createElement('td');
			tdEmail.innerHTML = student.email;
			tr.appendChild(tdEmail);


			table.appendChild(tr);
		});

	}

	var endpoint = "http://localhost:8080/bookLibrary/students";
	var params = {
		command: 'GET_ALL_STUDENTS'
	}

	var url = endpoint + formatParams(params);
	xhttp.open("GET", url, true);
	xhttp.send();
}

function formatParams(params) {
	return "?" + Object
		.keys(params)
		.map(function(key) {
			return key + "=" + encodeURIComponent(params[key])
		})
		.join("&")
}


function fetchAllStudents() {
	
	var table = document.getElementById('get_all_students_table');

	var endpoint = "http://localhost:8080/bookLibrary/StudentAPI";
	var params = {
		command: 'GET_ALL_STUDENTS'
	}

	const url = endpoint + formatParams(params);


	fetch(url, {
		method: "POST"
	}


	//body: new FormData(document.getElementById("inputform")),
	// -- or --
	// body : JSON.stringify({
	// user : document.getElementById('user').value,
	// ...
	// })
).then(
	response => response.json() // .json(), etc.
	// same as function(response) {return response.text();}
).then(
	students => students.forEach(student => {
			var tr = document.createElement('tr');
			var tdFirstName = document.createElement('td');
			tdFirstName.innerHTML = student.firstName;
			tr.appendChild(tdFirstName);
			var tdLastName = document.createElement('td');
			tdLastName.innerHTML = student.lastName;
			tr.appendChild(tdLastName);
			var tdEmail = document.createElement('td');
			tdEmail.innerHTML = student.email;
			tr.appendChild(tdEmail);


			table.appendChild(tr);
		})
	
	
);

console.log('All students');


}