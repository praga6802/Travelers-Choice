document.getElementById("userloginform").addEventListener('submit',handleLogin)


async function handleLogin(event){


    event.preventDefault();


    const data=new FormData(event.target);

    const error=document.getElementById('error');


    try{
        const response=await fetch("http://localhost:8080/user/userlogin",{

            method:"POST",
            body:data
        });

        if(response.ok){

            alert('Login Successful');
            window.location.href='../html/index.html';
            event.target.reset();
        }

        else{
            alert('Login Failed')
            const errorMsg=await response.json();
            document.getElementById('error').innerText=errorMsg;
            event.target.reset();
        }
    }
    catch(err){
        document.getElementById('error').innerText="Network error..Please try again..";
        console.error(err);
    }
}