import axios from 'axios'
import React, {useState} from 'react'
import { Link, useNavigate } from 'react-router-dom'

export default function AddUser() {

    let navigate = useNavigate()

    const [user, setUser] = useState({
        name:"",
        username:"",
        email:"",
        password:"",
        type:"DOCTOR",
        observations: [],
        conditions: [],
    })

    const{name, username, email, password, type} = user

    const onInputChange = (e) =>{

        setUser({...user, [e.target.name]:e.target.value})

    }

    const onSubmit = async (e) =>{
        e.preventDefault();
        await axios.post("http://localhost:8080/user", user)
        navigate("/")
    }

  return (
    <div className='container'>
        <div className='row'>
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
                <h2 className='text-center m-4'>Register User</h2>
                <form onSubmit={(e) => onSubmit(e)}>
                <div className='mb-3'>
                    <label htmlFor='Name' className='form-label'>
                        Name
                    </label>
                    <input 
                    type="text"
                    className="form-control"
                    placeholder="Enter your name"
                    name="name"
                    value={name}
                    onChange={(e) => onInputChange(e)}
                    />

                </div>
                <div className='mb-3'>
                    <label htmlFor='Username' className='form-label'>
                        Username
                    </label>
                    <input 
                    type = {"text"} 
                    className='form-control' 
                    placeholder='Enter your username' 
                    name = "username"
                    value={username}
                    onChange={(e) => onInputChange(e)}
                    />
                </div>
                <div className='mb-3'>
                    <label htmlFor='Email' className='form-label'>
                        E-mail
                    </label>
                    <input 
                    type = {"email"}
                    className='form-control' 
                    placeholder='Enter your e-mail address' 
                    name = "email"
                    value={email}
                    onChange={(e) => onInputChange(e)}
                    />
                </div>
                <div className='mb-3'>
                    <label htmlFor='password' className='form-label'>
                        password
                    </label>
                    <input 
                    type = {"password"} 
                    className='form-control' 
                    placeholder='Enter your password' 
                    name = "password"
                    value={password}
                    onChange={(e) => onInputChange(e)}
                    />
                </div>
                <div className="mb-3">
                <label htmlFor="Type" className="form-label">
                    User Type
                </label>
                <select
                    className="form-select"
                    name="type"
                    value={type}
                    onChange={(e) => onInputChange(e)}
                >
                    <option value="DOCTOR">DOCTOR</option>
                    <option value="PATIENT">PATIENT</option>
                    <option value="STAFF">STAFF</option>
                </select>
                </div>
                <button type='submit' className='btn btn-outline-primary'>
                    Submit
                </button>
                <Link className='btn btn-outline-danger mx-2' to="/">
                    Cancel
                </Link>
                </form>
            </div>
        </div>
    </div>
  )
}
