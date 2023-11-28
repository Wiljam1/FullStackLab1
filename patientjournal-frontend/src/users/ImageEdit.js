import React, { useState, useRef, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import html2canvas from 'html2canvas';

const ImageEdit = () => {
    const { index } = useParams();
    const [imageData, setImageData] = useState(null);
    const canvasRef = useRef(null);
    const [text, setText] = useState('');
    const [isDrawing, setIsDrawing] = useState(false);
    const [drawColor, setDrawColor] = useState('#000000');
    const [drawingPaths, setDrawingPaths] = useState([]);

    const fetchImage = async () => {
        try {
            const response = await axios.get(`http://localhost:3001/images/${index}`);
            setImageData(response.data);
        } catch (error) {
            console.error('Error fetching image data:', error.message);
        }
    };

    useEffect(() => {
        fetchImage();
    }, [index]);

    useEffect(() => {
        const canvas = canvasRef.current;
        const context = canvas.getContext('2d');

        // Draw the image on the canvas
        const img = new Image();
        img.onload = () => {
            context.drawImage(img, 0, 0, canvas.width, canvas.height);
        };
        if (imageData) {
            img.src = `data:image/jpeg;base64,${imageData.data}`;
        }

        // Drawing functionality
        const startDrawing = (e) => {
            if (canvasRef.current && imageData) {
                setIsDrawing(true);
                const context = canvasRef.current.getContext('2d');

                const rect = canvasRef.current.getBoundingClientRect();
                const offsetX = e.clientX - rect.left;
                const offsetY = e.clientY - rect.top;

                context.beginPath();
                context.moveTo(offsetX, offsetY);
                setDrawingPaths((prevPaths) => [...prevPaths, { x: offsetX, y: offsetY }]);
            }
        };

        const draw = (e) => {
            if (!isDrawing) return;

            const context = canvasRef.current.getContext('2d');

            const rect = canvasRef.current.getBoundingClientRect();
            const offsetX = e.clientX - rect.left;
            const offsetY = e.clientY - rect.top;

            context.lineTo(offsetX, offsetY);
            context.stroke();
            setDrawingPaths((prevPaths) => [...prevPaths, { x: offsetX, y: offsetY }]);
        };

        const stopDrawing = () => {
            setIsDrawing(false);
            const context = canvasRef.current.getContext('2d');
            context.closePath();
        };

        canvas.addEventListener('mousedown', startDrawing);
        canvas.addEventListener('mousemove', draw);
        canvas.addEventListener('mouseup', stopDrawing);

        return () => {
            canvas.removeEventListener('mousedown', startDrawing);
            canvas.removeEventListener('mousemove', draw);
            canvas.removeEventListener('mouseup', stopDrawing);
        };
    }, [isDrawing, imageData]);

    const handleTextChange = (e) => {
        setText(e.target.value);
    };

    const handleSave = async () => {

    };

    return (
        <div>
            <h2>Edit Photo</h2>
            <canvas ref={canvasRef} width={500} height={500} style={{ border: '1px solid black' }} />
            <div>
                <label>
                    Text:
                    <input type="text" value={text} onChange={handleTextChange} />
                </label>
            </div>
            <div>
                <label>
                    Draw Color:
                    <input type="color" value={drawColor} onChange={(e) => setDrawColor(e.target.value)} />
                </label>
            </div>
            <button onClick={handleSave}>Save</button>
        </div>
    );
};

export default ImageEdit;