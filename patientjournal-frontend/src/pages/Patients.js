import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';

export default function Patients() {
    const [users, setUsers] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        loadUsers();
    }, []);

    const loadUsers = async () => {
        try {
            const result = await axios.get("http://localhost:8080/patients");
            setUsers(result.data);
            console.log("Information loaded");
        } catch (error) {
            console.error("Error loading users:", error);
        }
    };

    const storedUser = JSON.parse(sessionStorage.getItem('user'));
    if (storedUser?.type !== 2) { // check if current user is a doctor
        navigate('/');
    }
 
  return (
    <div className='container'>
        <div className='py-4'>
        <table className="table shadow border">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Username</th>
      <th scope="col">Email</th>
      <th scope='col'>Type</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>

{
    users.map((user, index) => (
        <tr key={user.id}>
      <th scope="row" key={index}>{index+1}</th>
      <td>{user.name}</td>
      <td>{user.username}</td>
      <td>{user.email}</td>
      <td>{user.type}</td>
      <td>
        <Link className='btn btn-primary mx-2' to={`/viewuser/${user.id}`}>View</Link>    
      </td>
        </tr>
    ))
}

  </tbody>
</table>
        </div>
    </div>
  )
}
