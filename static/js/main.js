const BORDERS = ["borderLeft", "borderRight", "borderTop", "borderBottom"];
let currMaze = {};

function checkConnection() {
	// Check if connected to server
	fetch("http://localhost:4567/testmaze?size=3", {
		method: "GET",
		"Content-Type": "application/json",
		Accept: "application/json",
	})
		.then((response) => console.log("Connected to server!"))
		.catch(() => {
			Swal.fire({
				title: "Server error",
				text: "Make sure the server is running!",
				icon: "error",
				confirmButtonText: "Retry Connection",
			}).then(() => {
				checkConnection();
			});
		});
}
checkConnection();

function generateMaze() {
	checkConnection();
	let maze = document.getElementById("maze");
	let height = document.getElementById("height-input").value;
	let width = document.getElementById("width-input").value;
	let seed =
		document.getElementById("seed-input").value ||
		Math.floor(Math.random() * 32768);
	fetch(
		"http://localhost:4567/genmaze?height=" +
			height +
			"&width=" +
			width +
			"&seed=" +
			seed,
		{
			method: "GET",
			"Content-Type": "application/json",
			Accept: "application/json",
		}
	)
		.then((response) => response.json())
		.then((data) => {
			console.log(data);
			currMaze = data;
			reset();
			maze.style.gridTemplateRows = "repeat(" + data.rows + ", 1fr)";
			maze.style.gridTemplateColumns = "repeat(" + data.cols + ", 1fr)";
			let ratio = data.cols / data.rows;
			maze.style.width = "min(" + 70 * ratio + "vw, " + 70 * ratio + "vh)";
			maze.style.height =
				"min(" + 70 * (1 / ratio) + "vw, " + 70 * (1 / ratio) + "vh)";
			for (let i = 0; i < data.rows; i++) {
				for (let j = 0; j < data.cols; j++) {
					let id = i * data.cols + j;
					let neighbors = [id - 1, id + 1, id - data.cols, id + data.cols];
					let newTile = document.createElement("div");
					neighbors.map((n, index) => {
						if (data.connections[id].includes(n)) {
							newTile.style[BORDERS[index]] = "none";
						}
					});
					newTile.className = "maze-tile";
					// let size =
					newTile.id = id.toString();
					maze.appendChild(newTile);
				}
			}
		});
}

function solveMaze() {
	checkConnection();
    fetch(
		"http://localhost:4567/solvemaze",
		{
			method: "GET",
			"Content-Type": "application/json",
			Accept: "application/json",
		}
	)
		.then((response) => response.json())
		.then((data) => {
			console.log(data);
            Object.values(data).map(square => {
                document.getElementById(square).classList.add("path");
            })
        });
}

function reset() {
	let maze = document.getElementById("maze");
	while (maze.firstChild) {
		maze.removeChild(maze.firstChild);
	}
	maze.style.height = "0";
	maze.style.width = "0";
}
