document.getElementById("tourForm").addEventListener("submit", handleForm)

async function handleForm(event){
    event.preventDefault();

    const formData = new FormData(event.target);
    const package_name=encodeURIComponent(document.getElementById("package_name").value);
    try {

        const response = await fetch(`http://localhost:8080/user/${package_name}/book`, {
            method: "POST",
            body: formData
        });

        const data = await response.text();

        if(response.ok){
            alert(data);
            event.target.reset();
        } else {
            alert(data.error || "Booking Failed");
        }

    } catch(err) {
        alert("Network Error. Please try again");
        console.error(err);
    }
}