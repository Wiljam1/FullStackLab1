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
      <div className="container">
        <div className="row">
          <div className="col-md-6 mb-4">
            <div className="border rounded p-4 shadow">
              <h2 className="text-center m-4">All Messages</h2>
              <ul>
                {messages.map((message) => (
                    <li key={message.id}>
                      <strong>Subject:</strong> {message.subject}, <strong>Content:</strong> {message.content}
                    </li>
                ))}
              </ul>
            </div>
          </div>

          <div className="col-md-6 mb-4">
            <div className="border rounded p-4 shadow">
              <h2 className="text-center m-4">Send a Message</h2>
              <form onSubmit={handleSubmit}>
                <div className="mb-3">
                  <label htmlFor="receiverUsername" className="form-label">
                    Receiver Username:
                  </label>
                  <input
                      type="text"
                      className="form-control"
                      id="receiverUsername"
                      name="receiverUsername"
                      value={newMessage.receiverUsername}
                      onChange={handleInputChange}
                      required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="subject" className="form-label">
                    Subject:
                  </label>
                  <input
                      type="text"
                      className="form-control"
                      id="subject"
                      name="subject"
                      value={newMessage.subject}
                      onChange={handleInputChange}
                      required
                  />
                </div>
                <div className="mb-3">
                  <label htmlFor="content" className="form-label">
                    Content:
                  </label>
                  <input
                      type="text"
                      className="form-control"
                      id="content"
                      name="content"
                      value={newMessage.content}
                      onChange={handleInputChange}
                      required
                  />
                </div>
                <button type="submit" className="btn btn-outline-primary">
                  Send Message
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
  );
};

export default MessageViewer;