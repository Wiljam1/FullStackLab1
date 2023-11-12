import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link, useParams, useNavigate } from 'react-router-dom'

export default function ViewUser() {
    const [user, setUser] = useState({
        name: "",
        username: "",
        email: "",
        type: "",
        patientProfile: null,
        doctorProfile: null,
    });

    const navigate = useNavigate();
    const storedUser = JSON.parse(sessionStorage.getItem('user'));
    const { id } = useParams();

    useEffect(() => {
        const isAllowed = storedUser?.id === id || storedUser?.type === 'DOCTOR';

        if (!isAllowed) {
            console.log("Not authorized, storedId:" + storedUser?.id + " and url id: " + `${id}`);
            navigate('/');
        }

        loadUser();
    }, [id, navigate, storedUser]);

    const loadUser = async () => {
        try {
          const result = await axios.get(`http://localhost:8080/user/${id}`);
          setUser(result.data);
        } catch (error) {
          console.error("Error loading user:", error);
        }
      };

      return (
        <div className='container'>
          <div className='row'>
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
              <h2 className='text-center m-4'>User Details</h2>
    
              <div className='card'>
                <div className='card-header'>
                  Details of user id : {user.id}
                  <ul className='list-group list-group-flush'>
                    <li className='list-group-item'>
                      <b>Name: </b>
                      {user.name}
                    </li>
                    <li className='list-group-item'>
                      <b>Username: </b>
                      {user.username}
                    </li>
                    <li className='list-group-item'>
                      <b>Email: </b>
                      {user.email}
                    </li>
                    <li className='list-group-item'>
                      <b>Type: </b>
                      {user.type}
                    </li>
    
                    {/* Display patient-specific information */}
                    {user.patientProfile && (
                    <>
                        <li className='list-group-item'>
                        <b>Birthdate: </b>
                        {user.patientProfile.birthdate}
                        </li>
                        <li className='list-group-item'>
                        <b>Observations: </b>
                        <table className="table">
                            <thead>
                            <tr>
                                <th scope="col">Subject</th>
                                <th scope="col">Based On</th>
                            </tr>
                            </thead>
                            <tbody>
                            {user.patientProfile.observations.map((observation) => (
                                <tr key={observation.id}>
                                <td>{observation.subject}</td>
                                <td>{observation.basedOn}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                        </li>
                    </>
                    )}
    
                    {/* Display doctor-specific information */}
                    {user.doctorProfile && (
                      <>
                        <li className='list-group-item'>
                          <b>Favorite fruit: </b>
                          {user.doctorProfile.favoriteFruit}
                        </li>
                      </>
                    )}
                  </ul>
                </div>
              </div>
              <Link className='btn btn-primary my-2' to={'/'}>
                Back to home
              </Link>
            </div>
          </div>
        </div>
      );
}
