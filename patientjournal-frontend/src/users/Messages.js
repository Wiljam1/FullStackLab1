import React, { useState, useEffect } from 'react';
import axios from 'axios';

const MessageViewer = () => {
  const [messages, setMessages] = useState([]);
  const storedUser = JSON.parse(sessionStorage.getItem('user'));
  const [newMessage, setNewMessage] = useState({
    sender: storedUser,
    receiverUsername: '',
    subject: '',
    content: '',
  });

  useEffect(() => {
    axios.get('http://localhost:8080/messages')
      .then(response => setMessages(response.data))
      .catch(error => console.error('Error fetching messages:', error));
  }, []);

  const handleInputChange = (e) => {
    setNewMessage({ ...newMessage, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Fetch the user with the provided receiverUsername
    axios.get(`http://localhost:8080/user123/${newMessage.receiverUsername}`)
        .then(response => {
          const receiverUser = response.data;

          const messageData = {
            sender: storedUser,
            receiver: receiverUser,
            subject: newMessage.subject,
            content: newMessage.content
          };

          // Send the request to create a new message
          axios.post('http://localhost:8080/message', messageData)
              .then(response => {
                setMessages([...messages, response.data]);
                setNewMessage({ receiverUsername: '', subject: '', content: '' });
              })
              .catch(error => console.error('Error sending message:', error));
        })
        .catch(error => console.error('Error fetching user:', error));
  };

  return (
    <div style={styles.container}>
      <div style={styles.messagesContainer}>
        <h2>All Messages</h2>
        <ul>
          {messages.map(message => (
            <li key={message.id} style={styles.messageItem}>
              <strong>Subject:</strong> {message.subject}, <strong>Content:</strong> {message.content}
            </li>
          ))}
        </ul>
      </div>

      <div style={styles.sendMessageContainer}>
        <h2>Send a Message</h2>
        <form onSubmit={handleSubmit} style={styles.messageForm}>
          <label htmlFor="receiverUsername">Receiver Username:</label>
          <input
            type="text"
            id="receiverUsername"
            name="receiverUsername"
            value={newMessage.receiverUsername}
            onChange={handleInputChange}
            style={styles.input}
            required
          />
          <label htmlFor="subject">Subject:</label>
          <input
            type="text"
            id="subject"
            name="subject"
            value={newMessage.subject}
            onChange={handleInputChange}
            style={styles.input}
            required
          />
          <label htmlFor="content">Content:</label>
          <input
            id="content"
            name="content"
            value={newMessage.content}
            onChange={handleInputChange}
            style={styles.textarea}
            required
          />

          <button type="submit" style={styles.button}>Send Message</button>
        </form>
      </div>
    </div>
  );
};

const styles = {
  container: {
    display: 'flex',
    justifyContent: 'space-around',
    padding: '20px',
  },
  messagesContainer: {
    flex: 1,
    marginRight: '20px',
  },
  messageItem: {
    marginBottom: '10px',
  },
  sendMessageContainer: {
    flex: 1,
  },
  messageForm: {
    display: 'flex',
    flexDirection: 'column',
  },
  input: {
    marginBottom: '10px',
    padding: '5px',
  },
  textarea: {
    marginBottom: '10px',
    padding: '5px',
  },
  button: {
    backgroundColor: '#4CAF50',
    color: 'white',
    padding: '10px',
    border: 'none',
    borderRadius: '5px',
    cursor: 'pointer',
  },
};

export default MessageViewer;