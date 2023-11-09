import axios from 'axios';
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

export default function AddObservation() {
    let navigate = useNavigate();
    const storedUser = JSON.parse(localStorage.getItem('user'));
    const [observation, setObservation] = useState({
        //should maybe be something else? or add something
        performer: storedUser,
        //patient:
        subject: "",
        basedOn: ""
    });

    const { performer, subject, basedOn } = observation;

    const onInputChange = (e) => {
        setObservation({ ...observation, [e.target.name]: e.target.value });
    };

    const onSubmit = async (e) => {
        e.preventDefault();
        await axios.post("http://localhost:8080/observation", observation);
        navigate("/");
    };

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">Add Observation</h2>
                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className="mb-3">
                            <label htmlFor="Performer" className="form-label">
                                Performer
                            </label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder={performer}
                                name="performer"
                                value={performer}
                                readOnly
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Subject" className="form-label">
                                Subject
                            </label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter the subject"
                                name="subject"
                                value={subject}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="BasedOn" className="form-label">
                                Based On
                            </label>
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter based on information"
                                name="basedOn"
                                value={basedOn}
                                onChange={(e) => onInputChange(e)}
                            />
                        </div>
                        <button type="submit" className="btn btn-outline-primary">
                            Submit
                        </button>
                        <Link className="btn btn-outline-danger mx-2" to="/">
                            Cancel
                        </Link>
                    </form>
                </div>
            </div>
        </div>
    );
}