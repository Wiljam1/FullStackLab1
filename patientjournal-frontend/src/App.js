import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AddUser from './users/AddUser';
import EditUser from './users/EditUser';
import ViewUser from './users/ViewUser';
import Login from "./users/Login";
import React from 'react';
import AddObservation from "./users/AddObservation";
import Messages from "./users/Messages";
import Patients from './pages/Patients';
import ImageUpload from "./users/ImageUpload";
import ImageEdit from "./users/ImageEdit";

function App() {
  return (
    <div className="App">
      <Router>
      <Navbar/>
      <Routes>
        <Route exact path="/" element={<Home />} />
        <Route exact path="/register" element={<AddUser />} />
        <Route exact path="/patients" element={<Patients />} />
        <Route exact path="/edituser/:id" element={<EditUser />} />
        <Route exact path="/viewuser/:id" element={<ViewUser />} />
        <Route exact path="/login" element={<Login />} />
        <Route exact path="/addobservation" element={<AddObservation />} />
        <Route exact path="/messages" element={<Messages />} />
        <Route path="/image-upload" element={<ImageUpload/>} />
        <Route path="/image-edit/:index" element={<ImageEdit/>} />
      </Routes>
      </Router>
  
      
    </div>
  );
}

export default App;
