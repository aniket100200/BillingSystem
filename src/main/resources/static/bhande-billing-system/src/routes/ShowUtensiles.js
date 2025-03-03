import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { baseURL } from '../auth';
import UtensileCard from '../components/UtensileCard';
import '../styles/utesnsiles.scss'

const ShowUtensiles = () => {
    const [utensile, setUtensiles] = useState([]);

    const headers = [];

    if (utensile) {
        for (let val in utensile[0]) {
            headers.push(val);
        }
    }


    useEffect(() => {

        (async () => {
            try {
                const resp = await axios({
                    method: "GET",
                    url: baseURL + "/utensile/get",
                    headers: {
                        Authorization: "Bearer " + localStorage.getItem("token")
                    }

                });

                setUtensiles(resp.data);
            } catch (error) {
                console.log(error);
            }
        })();
    }, []);
    return (
        <div className='show-utensiles cards'>
           {
            utensile.map(card=>{
                return  <UtensileCard card={ card} key={card.uuid} uuid={"aniket"}/>
            })
           }
          
        </div>
    )
}

export default ShowUtensiles
