function successfullyLogout(){
    alert("You have been successfully logged out");
}

function successfullyDeleted(id) {
    if (confirm("Press a button!")) {
        window.location = "/delete_address/",id;
    }
}