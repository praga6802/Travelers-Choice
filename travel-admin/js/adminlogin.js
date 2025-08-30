document.getElementById("loginform").addEventListener("submit",handleLogin)


async function handleLogin(event){
    event.preventDefault();

    const loginformData= new FormData(event.target);

    try{

        const response=await fetch("http://localhost:8080/admin/adminlogin",{

            method:"POST",
            body:loginformData
        });

        if(response.ok){
            alert('Login Successful');
            event.target.reset();
            window.location.href="left.html";
        }
        else{
            const errorMsg= await response.json();
            document.getElementById("error").innerText=errorMsg.error || 'Login Failed';
            event.target.reset();
        }



    }
    catch(err){
        document.getElementById("error").innerText="Network Error..Please try again";
        console.error(err);
    }


}