import React, { useState, useEffect } from 'react';
import axios from 'axios';

const MessageViewer = () => {
  const [messages, setMessages] = useState([]);
  const storedUser = JSON.parse(sessionStorage.getItem('user'));
  const [newMessage, setNewMessage] = useState({senderId: storedUser.id, receiverId: '', subject: '', content: '' });

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
    axios.post('http://localhost:8080/message', newMessage)
      .then(response => {
        setMessages([...messages, response.data]);
        setNewMessage({receiverId: '', subject: '', content: '' });
      })
      .catch(error => console.error('Error sending message:', error));
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
          <label htmlFor="receiverId">Receiver ID:</label>
          <input
            type="text"
            id="receiverId"
            name="receiverId"
            value={newMessage.receiverId}
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