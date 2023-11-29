var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;

document.addEventListener("DOMContentLoaded", function () {
    // Function to fetch messages from the backend
    function fetchMessages() {
        fetch("/api/messages/all")
            .then(response => response.json())
            .then(data => displayMessages(data))
            .catch(error => console.error('Error fetching messages:', error));
    }

    // Function to display messages in the HTML
    function displayMessages(messages) {

        // Iterate through messages and append them to the container
        messages.forEach(message => {
            var messageElement = document.createElement('li');

            if(message.type === 'JOIN') {
                messageElement.classList.add('event-message');
                message.content = message.sender + ' joined!';
            } else if (message.type === 'LEAVE') {
                messageElement.classList.add('event-message');
                message.content = message.sender + ' left!';
            } else {
                messageElement.classList.add('chat-message');

                var avatarElement = document.createElement('i');
                var avatarText = document.createTextNode(message.sender[0]);
                avatarElement.appendChild(avatarText);
                avatarElement.style['background-color'] = getAvatarColor(message.sender);

                messageElement.appendChild(avatarElement);

                var usernameElement = document.createElement('span');
                var usernameText = document.createTextNode(message.sender);
                var usernameDate = document.createTextNode(message.localDateTime);
                usernameElement.appendChild(usernameText);
                usernameElement.appendChild(usernameDate)
                messageElement.appendChild(usernameElement);
            }

            var textElement = document.createElement('p');
            var messageText = document.createTextNode(message.content);
            textElement.appendChild(messageText);

            messageElement.appendChild(textElement);// Assuming your messages have a 'text' property
            messageArea.appendChild(messageElement);
        });
    }

    // Fetch messages when the page loads
    fetchMessages();
});