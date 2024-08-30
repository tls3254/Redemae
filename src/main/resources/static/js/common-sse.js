// const eventSource = new EventSource("/api/sse/connect");
//
// eventSource.onmessage = function (event) {
// 	const data = JSON.parse(event.data);
// 	console.log(data.message);
// 	alert(data.message);
// 	// window.location.href ="/carts"
// };
// console.log("EventSource created successfully.");
document.addEventListener('DOMContentLoaded', function() {
	const eventSource = new EventSource("/api/sse/connect");

	eventSource.onmessage = function (event) {
		const data = JSON.parse(event.data);
		console.log(data.message);
		alert(data.message);
	};

	console.log("EventSource created successfully.");
});
