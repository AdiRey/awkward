const url = 'ws://localhost:8080';
let stompClient;
let data = {
    "id": null,
    "message": null,
    "fromUserId": null,
    "addTime": null
};

let userId;
let toUserId;

connectToChat = () => {
    console.log("Start connecting...")
    stompClient = Stomp.client(url + '/messages');
    // stompClient = Stomp.over(new SockJs(url + '/messages')); //for SockJs
    stompClient.connect({}, function(frame) {
        console.log("You are connected to: " + frame);
        stompClient.subscribe("/q/messages/" + userId, function(response) {
            data = JSON.parse(response.body);
            // showMessageRed(data);
            showMessage(data);
        });
    });
    /*stompClient.connect({}, function(frame) {
        console.log("You are connected to: " + frame);
        stompClient.subscribe("/q/messages/" + toUserId, function (response) {
            data = JSON.parse(response.body);
            showMessage(data);
        });
    });*/
}

sendMsg = () => {
    console.log("sending message")
    let elementById = document.getElementById("typedMessage");
    stompClient.send("/wss/messages/" + toUserId, {}, JSON.stringify({
        message: elementById.value,
        fromUserId: userId
    }))
    let newMessage = document.createElement('p');
    newMessage.appendChild(document.createTextNode(elementById.value + " ; " + userId));
    document.getElementById("messageField").appendChild(newMessage);
}

setId = () => {
    let id = document.getElementById("your-id").value;
    console.log("Your id: " + id);
    userId = id;
}

writeToSpecifiedUserId = () => {
    let id = document.getElementById("their-id").value;
    console.log("Her/His id: " + id);
    toUserId = id;
}

showMessage = (data) => {
    console.log("Showing message.");
    let newMessage = document.createElement('p');
    newMessage.appendChild(document.createTextNode(data.message + " ; " + data.addTime + " ; " +data.fromUserId + " ; " + data.id));
    document.getElementById("messageField").appendChild(newMessage);
}

showMessageRed = (data) => {
    console.log("Showing message.");
    let newMessage = document.createElement('p');
    newMessage.style.cssText = "color : red";
    newMessage.appendChild(document.createTextNode(data.message + " ; " + data.addTime + " ; " +data.fromUserId + " ; " + data.id));
    document.getElementById("messageField").appendChild(newMessage);
}
