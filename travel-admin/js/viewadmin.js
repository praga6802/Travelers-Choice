document.addEventListener("DOMContentLoaded",async ()=>{

const tbody=document.querySelector("#admintable tbody");

try{

    const response=await fetch("http://localhost:8080/admin/alladmins");

    if(!response) throw new Error("Unable to fetch admin details..");


    const details=await response.json();

    if(details.length===0){
        tbody.innerHTML="<tr><td>No admin details found</td></tr>";
    }


    details.forEach(detail=>{
        const row=document.createElement('tr');
        row.innerHTML=`
        <td>${detail.adminId}</td>
        <td>${detail.username}</td>
        <td>${detail.email}</td>
        <td>${detail.contact}</td>
        `;
        tbody.appendChild(row);
    });
}
catch(err){
    console.error(err);
    tbody.innerHTML="<tr><td>Error No Details Found</td></tr>";
}
});