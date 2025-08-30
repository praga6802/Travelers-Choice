document.getElementById("usersignup").addEventListener("submit",handleSignUp)


async function handleSignUp(event){

    event.preventDefault();


    const formData=new FormData(event.target);

    try{

        const response= await fetch("http://localhost:8080/user/usersignup",{

        method:"POST",
        body:formData

        });

        if(response.ok){
            alert("Sign Up Succesfully");
            event.target.reset();
            window.location.href="../html/login.html";
        }

        else{
            alert("Sign Up Failed");
            const errorMsg=await response.json();
            document.getElementById("error").innerText=JSON.stringify(errorMsg);
            event.target.reset();
        }

      
    }
    catch(err){
        document.getElementById("error").innerText="Network Error..Please Try again";
        console.error(err);
    }

}

