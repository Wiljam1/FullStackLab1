import React from 'react'
import { Link } from 'react-router-dom'
import {useUser} from "../users/UserContext";

const Navbar = () => {
    // Retrieve user data from local storage
  
                <Link className='btn btn-outline-light' to="/adduser">Add User</Link>
                <Link className='btn btn-outline-light' to="/addobservation">Add Observation</Link>
                <Link className='btn btn-outline-light' to="/login">Login</Link>
            </div>
        </nav>
    </div>
  )

}

export default Navbar;
