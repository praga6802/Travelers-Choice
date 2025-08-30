document.addEventListener("DOMContentLoaded",displayUserName);


async function displayUserName(){


   try{
    const response=await fetch("http://localhost:8080/admin/username",{
        method:"GET",
        credentials:"include"
    });

    if(response.ok){
        const username=await response.text();
        console.log("hello,",username)
        document.getElementById("login").textContent="Hello, "+username;
    }
    else{
        document.getElementById("login").textContent="Hello Guest";
        return;
    }

   }
   catch(err){
    console.error(err);
   }
}

