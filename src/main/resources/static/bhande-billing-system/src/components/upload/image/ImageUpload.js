import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { baseURL } from '../../../auth';
import { useSelector } from 'react-redux';

const ImageUpload = () => {
    const[selectedFile, setSelectedFile] = useState(null);
    const [preview, setPreview] = useState(null);

    const user = useSelector(s=>s.user.currentUser);
    var imageName;

    useEffect(function(){
            if(user){
                var abc = user?.name?.toLowerCase();
               imageName= abc.split(' ').join('_');
            }
            
    });


    const handleFileChange = (event)=>{
        const file = event.target.files[0];
        setSelectedFile(file);

        //Generate Preview

        const fileReader = new FileReader();

        fileReader.onloadend = ()=>{
            setPreview(fileReader.result);
        }

        if(file)
        fileReader.readAsDataURL(file);
    }
    const handleUpload = async()=>{
        if(!selectedFile){
            alert("Please Select an Image first!!!");
            return;
        }

        const formData = new FormData();
        formData.append("file",selectedFile);
        
        try{
            const resp= await axios({
                url:`${baseURL}/user/image/`+user.phone,
                method:"POST",
                data:formData,
            headers : {
                Authorization: "Bearer " + localStorage.getItem("token")
            }
            });
            const message = resp.data;

            alert(message.message);

        }catch(t){

        }
    }
  return (
    <div className='upload-container'>
        <input type='file' accept='image/*' onChange={handleFileChange}/>
        {preview && <img src={preview} alt='Preview' width={200}/>}
        <button onClick={handleUpload}>Upload Image</button>
    </div>
  )
}

export default ImageUpload;
