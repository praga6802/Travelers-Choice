document.getElementById("signupform").addEventListener("submit",handleSignUp)

async function handleSignUp(event){
    event.preventDefault();

    const formData=new FormData(event.target)

    try{
        const response=await fetch("http://localhost:8080/admin/adminsignup",{
            method:"POST", 
            body:formData
        });
        if(response.ok){
            alert('Sign Up Successful');
            event.target.reset();
            window.location.href="loginform.html"
        }
        else{
            alert('Sign Up Failed');
            const errorMsg= await response.json();
            document.getElementById("error").innerText=JSON.stringify(errorMsg);
            event.target.reset();

        }
    }
    catch(err){
        document.getElementById("error").innerText="Network error.Please try again";
        console.error(err);
    }
}